package com.kevinluo.storage.framework.utils;

/*
 * Creates on 2020/5/14.
 */

import com.kevinluo.storage.framework.logging.Log;
import com.kevinluo.storage.framework.logging.LogFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author lts
 */
public class AutoClose {

  private static final Log mLog = LogFactory.getLog(AutoClose.class);

  public static void close(Closeable... closeables) {
    try {
      if (closeables == null) return;
      for (Closeable closeable : closeables) {
        if (closeable == null) continue;
        closeable.close();
      }
    }catch (IOException e){
      mLog.error("close failure.", e);
    }
  }

}