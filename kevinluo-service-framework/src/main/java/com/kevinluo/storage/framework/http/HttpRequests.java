package com.kevinluo.storage.framework.http;

/*
 * Creates on 2020/5/23.
 */

import com.kevinluo.storage.framework.conf.MyConfiguration;

import java.util.Map;

/**
 * @author lts
 */
@SuppressWarnings("rawtypes")
public interface HttpRequests {

  int CONNECT_TIMEOUT
          = MyConfiguration.getInstance().getHttpclientTimeoutSeconds();

  int GET = 0;
  int POST = 1;

  /**
   * post请求方法
   *
   * @param url     url连接
   * @param headers 请求头
   * @param body    请求体
   * @return String对象
   */
  String doPost(String url, Map<String, String> headers, Map<String, String> body);

  /**
   * get请求方法
   *
   * @param url url连接
   * @return 默认返回String
   */
  <K, V> Map<K, V> doGetForMap(String url);

  /**
   * get请求方法
   *
   * @param url     url连接
   * @param headers 请求头
   * @return String对象
   */
  <K, V> Map<K, V> doGetForMap(String url, Map<String, String> headers);

  /**
   * get请求方法
   *
   * @param url     url连接
   * @param headers 请求头
   * @param body    请求体
   * @return String对象
   */
  <K, V> Map<K, V> doGetForMap(String url, Map<String, String> headers, Map<String, String> body);

  /**
   * post请求方法
   *
   * @param url url连接
   * @return String对象
   */
  <K, V> Map<K, V> doPostForMap(String url);

  /**
   * post请求方法
   *
   * @param url     url连接
   * @param headers 请求头
   * @return String对象
   */
  <K, V> Map<K, V> doPostForMap(String url, Map<String, String> headers);

  /**
   * post请求方法
   *
   * @param url     url连接
   * @param headers 请求头
   * @param body    请求体
   * @return String对象
   */
  <K, V> Map<K, V> doPostForMap(String url, Map<String, String> headers, Map<String, String> body);

}