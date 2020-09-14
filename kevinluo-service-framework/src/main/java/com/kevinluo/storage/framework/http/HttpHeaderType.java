package com.kevinluo.storage.framework.http;

/*
 * Creates on 2020/5/14.
 */

/**
 * @author lts
 */
public enum HttpHeaderType {

  /**
   * 配置下载文件名
   */
  CONTENT_DISPOSITION {
    @Override
    public String getKey() {
      return "Content-Disposition";
    }

    @Override
    public String getValue() {
      return null;
    }

    @Override
    public String getValue(String param) {
      return "attachment;fileName=".concat(param);
    }
  };

  public abstract String getKey();
  public abstract String getValue();
  public abstract String getValue(String param);

}