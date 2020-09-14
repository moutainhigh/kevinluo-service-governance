package com.kevinluo.storage.framework.utils.web;

/*
 * creates on 2020/5/11 21:03.
 */

import com.alibaba.fastjson.JSONObject;
import com.kevinluo.storage.framework.common.ConstVariable;
import com.kevinluo.storage.framework.json.FastJsonHelper;
import com.kevinluo.storage.framework.utils.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author lts
 */
public class WebRequest {

  /**
   * 获取{@code HttpServletRequest}
   */
  @SuppressWarnings("ConstantConditions")
  public static HttpServletRequest getHttpServletRequest() {
    return ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes())
            .getRequest();
  }

  public static HttpSession getSession() {
    return getHttpServletRequest().getSession();
  }

  /**
   * 获取头信息
   */
  public static String getHeader(String key) {
    return getHttpServletRequest().getHeader(key);
  }

  /**
   * 获取token
   */
  public static String getAuthorization()
  {
    return getHeader(ConstVariable.AUTHORIZATION);
  }

  private static String getParameter(String key) {
    return getHttpServletRequest().getParameter(key);
  }

  public static String getAttribute(String key)
  {
    return String.valueOf(getHttpServletRequest().getAttribute(key))
            ;
  }

  public static String getString(String key, String def) {
    String value = getParameter(key);
    if (StringUtils.isEmpty(value)) {
      return def;
    }
    value = UrlTools.decode(value);
    return value.trim();
  }

  public static String getString(String key) {
    return getString(key, null);
  }

  public static Integer getInteger(String key, Integer def) {
    Integer rs = def;
    String strValue = getParameter(key);
    if (StringUtils.isEmpty(strValue)) return rs;
    rs = StringUtils.asInt0(strValue);
    return rs;
  }

  public static int getInteger(String key) {
    return getInteger(key, null);
  }

  public static Long getLong(String key, Long def) {
    Long rs = def;
    String strValue = getParameter(key);
    if (StringUtils.isEmpty(strValue)) return rs;
    rs = StringUtils.asLong0(strValue);
    return rs;
  }

  public static Long getLong(String key) {
    return getLong(key, null);
  }

  public static Double getDouble(String key, Double def) {
    Double rs = def;
    String strValue = getParameter(key);
    if (StringUtils.isEmpty(strValue)) return rs;
    rs = StringUtils.asDouble(strValue);
    return rs;
  }

  public static Double getDouble(String key) {
    return getDouble(key, null);
  }

  public static Float getFloat(String key, Float def) {
    Float rs = def;
    String strValue = getParameter(key);
    if (StringUtils.isEmpty(strValue)) return rs;
    rs = StringUtils.asFloat(strValue);
    return rs;
  }

  public static Float getFloat(String key) {
    return getFloat(key, null);
  }

  public static BigDecimal getBigDecimal(String key, BigDecimal def) {
    BigDecimal rs = def;
    String strValue = getParameter(key);
    if (StringUtils.isEmpty(strValue)) return rs;
    rs = StringUtils.asBigDecimal(strValue);
    return rs;
  }

  public static BigDecimal getBigDecimal(String key) {
    return getBigDecimal(key, null);
  }

  public static Boolean getBoolean(String key, Boolean def) {
    Boolean rs = def;
    String strValue = getParameter(key);
    if (StringUtils.isEmpty(strValue)) return rs;
    rs = StringUtils.asBoolean(strValue);
    return rs;
  }

  public static Boolean getBoolean(String key) {
    return getBoolean(key, null);
  }

  public static JSONObject getJson(String key) {
    String strValue = getString(key);
    if (StringUtils.isEmpty(strValue)) {
      return null;
    }
    return JSONObject.parseObject(strValue);
  }

  /**
   * 将json转换成对象
   */
  public static <T> T getObject(String key, Class<T> clazz) {
    String strValue = getString(key);
    if (StringUtils.isEmpty(strValue)) return null;
    return FastJsonHelper.toObject(strValue, clazz);
  }

  /**
   * 将json转换成对象
   */
  public static List<Object> getObjectList(String key) {
    String strValue = getString(key);
    if (StringUtils.isEmpty(strValue)) return null;
    return FastJsonHelper.toList(strValue, Object.class);
  }

  /**
   * 获取IP
   */
  public static String getIPAddr() {
    HttpServletRequest request = getHttpServletRequest();
    String ip = request.getHeader("X-Real-IP");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("x-forwarded-for");
    }
    return getIPAddr(request, ip);
  }

  public static String getIPAddr(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    return getIPAddr(request, ip);
  }

  private static String getIPAddr(HttpServletRequest request, String ip) {
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }
}
