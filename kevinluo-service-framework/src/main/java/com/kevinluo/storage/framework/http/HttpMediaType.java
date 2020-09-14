package com.kevinluo.storage.framework.http;

/*
 * Creates on 2020/5/11.
 */

/**
 * 字符编码统一管理
 *
 * @author lts
 */
public enum HttpMediaType {

  JSON {
    public String getValue() {
      return "application/json; charset=utf-8";
    }
  },

  FORMAT {
    public String getValue() {
      return "application/x-www-form-urlencoded;charset=UTF-8";
    }
  },

  TEXT {
    public String getValue() {
      return "application/text;charset=UTF-8";
    }
  },

  IMAGE {
    @Override
    public String getValue() {
      return "image/png";
    }
  },

  STREAM {
    @Override
    public String getValue() {
      return "application/octet-stream";
    }
  },

  FORCE_DOWNLOAD {
    @Override
    public String getValue() {
      return "application/force-download";
    }
  }

  ;

  public abstract String getValue();

}