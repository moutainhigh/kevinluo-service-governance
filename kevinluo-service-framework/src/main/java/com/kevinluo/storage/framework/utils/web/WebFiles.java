package com.kevinluo.storage.framework.utils.web;

/*
 * Creates on 2020/5/13.
 */

import com.kevinluo.storage.framework.beans.HttpCode;
import com.kevinluo.storage.framework.http.HttpHeaderType;
import com.kevinluo.storage.framework.http.HttpMediaType;
import com.kevinluo.storage.framework.logging.Log;
import com.kevinluo.storage.framework.logging.LogFactory;
import com.kevinluo.storage.framework.utils.AutoClose;
import com.kevinluo.storage.framework.utils.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * 文件传输
 *
 * @author lts
 */
public class WebFiles
{

  private static final Log log = LogFactory.getLog(WebFiles.class);

  /**
   * 文件上传
   */
  public static String upload(MultipartFile file, String filepath, String filename) throws Exception
  {
    if (file == null)
    {
      throw new NullPointerException("RequestFile is null");
    }
    if (StringUtils.isEmpty(filename))
    {
      filename = file.getOriginalFilename();
    }
    File file0 = new File(filepath + "/" + filename);
    file.transferTo(file0);
    return file0.toString();
  }

  /**
   * 文件下载
   *
   * @param path 文件路径
   */
  public static boolean download(HttpServletResponse response, String path)
  {
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    try
    {
      if (!StringUtils.isEmpty(path))
      {
        File file = new File(path);
        if (file.exists())
        {
          configuration(response, file.getName());
          byte[] bytes = new byte[1024];
          fis = new FileInputStream(file);
          bis = new BufferedInputStream(fis);
          OutputStream os = response.getOutputStream();
          int i;
          while ((i = bis.read(bytes)) != -1)
          {
            os.write(bytes, 0, i);
          }
          return true;
        }
      }
      response.setContentType(HttpMediaType.FORCE_DOWNLOAD.getValue());
    } catch (Exception e)
    {
      response.setStatus(HttpCode.STATUS_500);
      log.error("file download failure. ", e);
    } finally
    {
      AutoClose.close(fis, bis);
    }
    return false;
  }

  /**
   * 配置文件下载的response
   *
   * @param response {@link HttpServletResponse}
   */
  public static void configuration(HttpServletResponse response, String filename)
  {
    response.setContentType(HttpMediaType.FORCE_DOWNLOAD.getValue());
    HttpHeaderType contentDisposition = HttpHeaderType.CONTENT_DISPOSITION;
    response.setHeader(contentDisposition.getKey(), contentDisposition.getValue(filename));
  }

}