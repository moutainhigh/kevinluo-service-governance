package com.kevinluo.storage.framework.utils;

/*
 * Creates on 2020/5/15.
 */

import com.twmacinta.util.MD5;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;

import java.io.InputStream;
import java.security.MessageDigest;

/**
 * EAC全称是：通用加密算法（Common encryption algorithm）
 *
 * @author lts
 */
public class Secrets
{

  /**
   * MD5加密
   * MD5常用于于密码加密，生成特征值。MD5特点是不可逆
   */
  public static final Md5 MD5 = new Md5();

  /**
   * Base64加密 & 解密
   * Base64加密算法常用于生成图片的base64编码、证书、文件等地方
   */
  public static final Base64Util BASE64 = new Base64Util();

  /**
   * base64工具类
   **/
  public static class Base64Util
  {

    /*
     * base64加密
     */
    public String encode(String input)
    {
      byte[] encodeBase64 = Base64.encodeBase64(input.getBytes(Charsets.UTF_8));
      return new String(encodeBase64, Charsets.UTF_8);
    }

    /*
     * base64解密
     */
    public String decode(String base64)
    {
      byte[] decodes = Base64.decodeBase64(base64.getBytes(Charsets.UTF_8));
      return new String(decodes, Charsets.UTF_8);
    }
  }

  /**
   * MD5工具类
   **/
  public static class Md5
  {

    /*
     * MD5加密大写
     */
    @SneakyThrows
    public static String digest(byte[] source)
    {
      String output;
      char[] hexDigits = { // 用来将字节转换成 16 进制表示的字符
              '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(source);
      byte[] tmp = md.digest();
      char[] str = new char[16 * 2];
      int k = 0;
      for (int i = 0; i < 16; i++)
      {
        byte byte0 = tmp[i];
        str[k++] = hexDigits[byte0 >>> 4 & 0xf];
        str[k++] = hexDigits[byte0 & 0xf];
      }
      output = new String(str);
      return output.toUpperCase();
    }

    /**
     * 获取文件md5特征码
     *
     * @param inputStream 输入流
     * @return 文件的MD5
     */
    public static String complete(InputStream inputStream)
    {
      com.twmacinta.util.MD5 md5 = new MD5();
      try
      {
        int len = 0;
        // 缓冲区设置为1kb
        byte[] hexBuffer = new byte[1048576];
        while (((len = inputStream.read(hexBuffer)) != -1))
        {
          md5.Update(hexBuffer, 0, len);
        }
      } catch (Exception e)
      {
        e.printStackTrace();
      } finally
      {
        AutoClose.close(inputStream);
      }
      return md5.asHex().toUpperCase();
    }

  }

}