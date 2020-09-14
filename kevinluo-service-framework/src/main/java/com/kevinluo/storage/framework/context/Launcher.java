package com.kevinluo.storage.framework.context;

/*
 * Creates on 2019/11/13.
 */

/**
 * 启动器
 *
 * @author lts
 */
public class Launcher
{

  public static void run(String env, Runnable runnable)
  {
    // set run env
    System.setProperty("env", env);
    MyEnvironment.loadEnvironment();
    // runing
    runnable.run();
  }

}
