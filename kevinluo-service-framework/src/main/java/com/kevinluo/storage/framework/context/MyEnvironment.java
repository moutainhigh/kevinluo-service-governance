package com.kevinluo.storage.framework.context;

/*
 * Creates on 2020/5/11.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kevinluo.storage.framework.conf.MyConfiguration;
import com.kevinluo.storage.framework.json.FastJsonHelper;
import com.kevinluo.storage.framework.thread.Threads;
import com.kevinluo.storage.framework.utils.Charsets;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

/**
 * 上下文环境
 *
 * @author lts
 */
public class MyEnvironment {

  private static final String CLASSPATH;

  /**
   * 配置文件在resource的哪个目录下
   */
  private static final String CONFDIR_IN_RESOURCE = "config";

  static {
    CLASSPATH = MyEnvironment.class.getResource("/").getPath();
  }

  public static String getClasspath() {
    return CLASSPATH;
  }

  public static String getConfigDir() {
    return CONFDIR_IN_RESOURCE;
  }

  /**
   * 获取当前使用环境（dev/test/prod）
   */
  public static String getEnv() {
    return System.getProperty("env");
  }

  /**
   * 加载到环境变量
   */
  public static void loadEnvironment() {
    try {
      Properties src = new Properties();
      JSONObject data = new JSONObject();
      String path = getConfigDir() + "/config/env.properties";
      loadConfig(src, path);
      if (!src.isEmpty()) {
        String strValue = JSON.toJSONString(src);
        JSONObject jsonValue = JSONObject.parseObject(strValue);
        data.putAll(jsonValue);
      }
       loadConfigEnv(data);
      src = FastJsonHelper.toObject(FastJsonHelper.toJSONString(data), Properties.class);
      Objects.requireNonNull(src).putAll(System.getProperties());
      System.setProperties(src);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void loadConfigEnv(JSONObject data) {
    String jsonValue = FastJsonHelper.toJSONString(MyConfiguration.getProperties());
    JSONObject object = FastJsonHelper.toObject(jsonValue, JSONObject.class);
    data.putAll(object);
  }

  /**
   * 将配置加载到{@link Properties}中
   *
   * @param ps   properties对象
   * @param file 配置文件路径
   */
  public static void loadConfig(Properties ps, String file) throws IOException {
    if (!file.startsWith("/")) {
      String path = CONFDIR_IN_RESOURCE + "/" + file;
      InputStream in = Threads.getCallerLoader().getResourceAsStream(path);
      if (in == null) return;
      ps.load(new InputStreamReader(in, Charsets.UTF_8));
      in.close();
    } else {
      File path = new File(file);
      InputStream in = new FileInputStream(path);
      ps.load(new InputStreamReader(in, Charsets.UTF_8));
      in.close();
    }
  }

}