package com.kevinluo.storage.framework.logging.slf4j;

/*
 * creates on 2020/5/11 20:17.
 */

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.kevinluo.storage.framework.context.MyEnvironment;
import com.kevinluo.storage.framework.exception.LogException;
import com.kevinluo.storage.framework.logging.Log;
import com.kevinluo.storage.framework.logging.LogAdapter;
import com.kevinluo.storage.framework.thread.Threads;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * @author lts
 */
public class Slf4jLogAdapter implements LogAdapter
{

  private volatile static boolean LOADING = false;

  /**
   * 加载配置文件
   */
  public Slf4jLogAdapter() {
    if (!LOADING) {
      String env = MyEnvironment.getEnv();
      String filename = MyEnvironment.getConfigDir() + "/logback-" + MyEnvironment.getEnv() + ".xml";
      InputStream in = Threads.getCallerLoader().getResourceAsStream(filename);
      available(in, filename);
      loadLogback(in);
      LOADING = true;
    }
  }

  void loadLogback(InputStream in) {
    try {
      LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
      JoranConfigurator configurator = new JoranConfigurator();
      configurator.setContext(context);
      context.reset();
      configurator.doConfigure(in);
//      StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    } catch (JoranException e) {
      e.printStackTrace();
    }
  }

  void available(InputStream in, String logfile) {
    if (in == null) {
      throw new LogException("cannot find logback config file in " + logfile);
    }
  }

  @Override
  public Log getLog(String key) {
    return new Slf4jLog(LoggerFactory.getLogger(key));
  }

  @Override
  public Log getLog(Class<?> key) {
    return new Slf4jLog(LoggerFactory.getLogger(key));
  }
}
