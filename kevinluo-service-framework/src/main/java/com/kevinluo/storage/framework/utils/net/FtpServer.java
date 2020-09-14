package com.kevinluo.storage.framework.utils.net;

/* ************************************************************************
 *
 * Copyright (C) 2020 tiansheng All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/*
 * Creates on 2020/9/10.
 */

import com.alibaba.fastjson.JSONObject;
import com.kevinluo.storage.framework.conf.MyConfiguration;
import com.kevinluo.storage.framework.exception.BusinessException;
import com.kevinluo.storage.framework.logging.Log;
import com.kevinluo.storage.framework.logging.LogFactory;
import com.kevinluo.storage.framework.utils.AutoClose;
import com.kevinluo.storage.framework.utils.Charsets;
import com.kevinluo.storage.framework.utils.collect.Lists;
import com.kevinluo.storage.framework.utils.web.WebFiles;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * ftp连接工具类
 *
 * @author lts
 */
public class FtpServer
{

  private static final Log LOG = LogFactory.getLog(FtpServer.class);

  private static final MyConfiguration.ConfigModel config = MyConfiguration.getInstance();

  /**
   * 链接ftp
   *
   * @return 链接FTP服务器的客户端
   */
  private static FTPClient connect()
  {
    FTPClient client = new FTPClient();
    try
    {
      client.connect(config.getFtpHost(), config.getFtpPort());
      client.login(config.getFtpUsername(), config.getFtpPassword());
      if (!FTPReply.isPositiveCompletion(client.getReplyCode()))
      {
        LOG.error("链接FTP服务失败, 请检查账号密码是否正确");
        client.disconnect();
      } else
      {
        configuration(client);
      }
    } catch (IOException e)
    {
      LOG.error(e);
    }
    return client;
  }

  /**
   * 上传文件
   *
   * @param path 文件目录
   */
  public static void store(String path, List<File> files)
  {
    FTPClient client = connect();
    FileInputStream fileInputStream = null;
    try
    {
      // 切换路径
      changeDirectory(client, path);
      client.setBufferSize(1024);
      client.enterLocalPassiveMode();
      for (File file : files)
      {
        fileInputStream = new FileInputStream(file);
        client.storeFile(file.getName(), fileInputStream);
        fileInputStream.close();
      }
    } catch (IOException e)
    {
      LOG.error(e);
    } finally
    {
      AutoClose.close(fileInputStream);
      disconnect(client);
    }
  }

  /**
   * springboot上传文件调用
   *
   * @param path  目录路径
   * @param files 文件列表集合
   */
  public static void store(String path, MultipartFile[] files)
  {
    store(path, files, null, null);
  }

  /**
   * springboot上传文件调用，并跟踪当前上传进度
   *
   * @param path     目录路径
   * @param files    文件列表集合
   * @param trackMap 上传追踪
   */
  @SuppressWarnings("ConstantConditions")
  public static void store(String path, MultipartFile[] files, String trackId,
                           Map<String, List<CTrackUploadProgressBar>> trackMap)
  {
    FTPClient client = connect();
    try
    {
      // 切换路径
      changeDirectory(client, path);
      client.setBufferSize(1024);
      client.enterLocalPassiveMode();
      for (MultipartFile file : files)
      {
        CTrackUploadProgressBar trackUpload = null;
        if (trackId != null && trackMap != null)
        {
          trackUpload = new CTrackUploadProgressBar(file.getSize());
          trackUpload.setFilename(file.getOriginalFilename());
          if (!trackMap.containsKey(trackId))
          {
            trackMap.put(trackId, Lists.newArrayList());
          }
          trackMap.get(trackId).add(trackUpload);
        }
        client.storeFile(transcode(file.getOriginalFilename()), file.getInputStream(),
                trackUpload);
      }
    } catch (IOException e)
    {
      throw new BusinessException("文件上传失败", e);
    } finally
    {
      // 当所有文件上传完后删除对应的ID
      trackMap.remove(trackId);
      disconnect(client);
    }
  }

  /**
   * 下载文件
   *
   * @param fullpath 文件全路径
   */
  public static void download(String fullpath, HttpServletResponse response)
  {
    FTPClient client = connect();
    try
    {
      // 截取目录路径和文件名
      int indexof = fullpath.lastIndexOf("/");
      String dirpath = fullpath.substring(0, indexof);
      String filename = fullpath.substring(indexof + 1);
      // 切换路径
      changeDirectory(client, dirpath);
      // 读取文件
      WebFiles.configuration(response, filename);
      client.retrieveFile(filename, response.getOutputStream());
    } catch (IOException e)
    {
      LOG.error(e);
    } finally
    {
      disconnect(client);
    }
  }

  /**
   * 创建目录
   *
   * @param where 在哪个目录下创建
   * @param name  目录名
   */
  public static void createDirectory(String where, String name)
  {
    FTPClient client = connect();
    try
    {
      if (!client.makeDirectory(name))
      {
        throw new BusinessException("目录创建失败");
      }
    } catch (IOException e)
    {
      throw new BusinessException(e);
    } finally
    {
      disconnect(client);
    }
  }

  /**
   * 列出档
   *
   * @param path 文件路径
   * @return 当前目录下的所有文件和文件夹
   */
  public static String listDirectory(String path)
  {
    FTPClient client = connect();
    try
    {
      // 切换路径
      changeDirectory(client, path);
      return JSONObject.toJSONString(client.listFiles());
    } catch (IOException e)
    {
      LOG.error(e);
    } finally
    {
      disconnect(client);
    }
    return null;
  }

  /**
   * 切换FTP文件目录
   *
   * @param client FTP客户端连接
   * @param path   文件路径
   * @throws IOException 切换目录异常
   */
  private static void changeDirectory(FTPClient client, String path) throws IOException
  {
    if (!client.changeWorkingDirectory(path))
      throw new BusinessException("文件或目录不存在");
  }

  /**
   * 配置FTPClient连接
   *
   * @param client FTPClient连接对象
   */
  private static void configuration(FTPClient client) throws IOException
  {
    // 由于FTP是使用文本传输的，在传输一些媒体文件的时候会导致失真
    // 所以采用二进制形式的传输方式
    client.setFileType(FTP.BINARY_FILE_TYPE);
    // 设置编码
    client.setControlEncoding(Charsets.UTF_8.displayName());
  }

  /**
   * 断开连接
   *
   * @param client ftp客户端
   */
  private static void disconnect(FTPClient client)
  {
    if (client != null)
    {
      try
      {
        client.disconnect();
      } catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   * 防止中文名称乱码，对文件名做一个转码操作
   *
   * @param input 文件名或其他字符
   * @return 转换后的字符串
   */
  private static String transcode(String input)
  {
    return new String(input.getBytes(Charsets.UTF_8), Charsets.ISO_8859_1);
  }

}