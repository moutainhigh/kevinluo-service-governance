package com.kevinluo.storage.framework.conf;

/*
 * Creates on 2020/5/11.
 */

import com.alibaba.fastjson.JSONObject;
import com.kevinluo.storage.framework.context.MyEnvironment;
import com.kevinluo.storage.framework.json.FastJsonHelper;
import com.kevinluo.storage.framework.utils.StringUtils;
import com.kevinluo.storage.framework.utils.math.Maths;
import lombok.Data;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author lts
 */
public class MyConfiguration {

  private final ConfigModel conf;

  private final Properties src = new Properties();

  private static final String WEBSITE_CONFIG_NAME_PREFIX = "website-site-";

  interface AppConfigurationInternal {
    MyConfiguration configuration = new MyConfiguration();
  }

  public static Properties getProperties() {
    return AppConfigurationInternal.configuration.src;
  }

  public static ConfigModel getInstance() {
    return AppConfigurationInternal.configuration.conf;
  }

  @Data
  public static
  class ConfigModel {
    String websiteName;
    String logConfigUsing;
    String logUnprintLevel;
    String logPrintFormat;
    String logPrintDirector;
    String pikaServerIp;
    String websitePort;
    boolean datasourceConnectionDesiredAutoCommit; // 设置DaoSupport事务
    String httpclientTimeoutSeconds = "3"; // httpClient连接超时时间，默认3秒

    String jmailSenderAccount;
    String jmailSenderAddress;
    String jmailSenderPassword;
    String jmailSmtpHost;

    long jwtExpiresSecond;
    String jwtClientId;
    String jwtBase64Secret;
    String jwtName;

    String ftpHost;
    int ftpPort = 21;
    String ftpUsername;
    String ftpPassword;
    long ftpTimeout;

    String swaggerMapping;
    String swaggerController;

    String configModel;

    String springServerContextpath;

    public int getHttpclientTimeoutSeconds() {
      return Integer.parseInt(httpclientTimeoutSeconds);
    }

  }

  /**
   * 配置文件类型，一般分为dev、test、product
   * 分别代表（开发环境/测试环境/生产环境）
   */
  public MyConfiguration() {
    synchronized (MyConfiguration.class) {
      tryFindAvailableConfig(this::usingEnvConfig);
      tryFindAvailableConfig(this::usingProdConfig);
      String jsonValue = FastJsonHelper.toJSONString(src);
      JSONObject configModel = new JSONObject();
      // 将小数点转成驼峰
      JSONObject jsonObject = JSONObject.parseObject(jsonValue);
      for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
        String humpName = StringUtils.characterToHump(entry.getKey(), "\\.");
        if("ftpTimeout".equals(humpName))
        {
          long timeout = Maths.calculateString(String.valueOf(entry.getValue()));
          configModel.put(humpName, timeout);
          continue;
        }
        configModel.put(humpName, entry.getValue());
      }
      conf = FastJsonHelper.toObject(FastJsonHelper.toJSONString(configModel), ConfigModel.class);
    }
  }

  /**
   * 在上下文中查找可用的配置文件
   */
  private void tryFindAvailableConfig(Runnable runnable) {
    if (src.isEmpty()) {
      runnable.run();
    }
  }

  /**
   * 使用本地环境的配置文件
   */
  private void usingEnvConfig() {
    try {
      MyEnvironment.loadConfig(src, WEBSITE_CONFIG_NAME_PREFIX + MyEnvironment.getEnv() + ".cfg");
    } catch (IOException e) {
      // e.printStackTrace();
    }
  }

  /**
   * 使用线上环境的配置文件
   */
  private void usingProdConfig() {
    try {
      MyEnvironment.loadConfig(src, "/etc/website/conf/" + WEBSITE_CONFIG_NAME_PREFIX + "prod.cfg");
    } catch (IOException e) {
      // e.printStackTrace();
    }
  }

}