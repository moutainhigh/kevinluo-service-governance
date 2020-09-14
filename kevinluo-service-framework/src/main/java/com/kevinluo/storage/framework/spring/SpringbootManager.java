package com.kevinluo.storage.framework.spring;

/*
 * Creates on 2020/5/12.
 */

import com.kevinluo.storage.framework.conf.MyConfiguration;
import com.kevinluo.storage.framework.context.MyEnvironment;
import com.kevinluo.storage.framework.utils.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author lts
 */
@EnableAutoConfiguration
public class SpringbootManager {

  private static final int DEFAULT_SERVER_PORT = 8122;

  public static void run(Class<?> clazz, String[] args) {
    // 配置服务端端口
    String strPort = MyConfiguration.getInstance().getWebsitePort();
    int port = StringUtils.asInt(strPort);
    // 初始化环境变量
    MyEnvironment.loadEnvironment();
    // 启动spring application
    run(clazz, args, port);
  }

  private static void run(Class<?> clazz, String[] args, int port) {
    System.setProperty("server.port", port + "");
    SpringApplication.run(clazz, args);
    System.out.println("=========================> env: " + MyEnvironment.getEnv());
    System.out.println("=========================> port: " + port);
  }

}