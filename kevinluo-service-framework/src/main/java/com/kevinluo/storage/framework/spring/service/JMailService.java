package com.kevinluo.storage.framework.spring.service;

/*
 * Creates on 2019/11/13.
 */

import java.util.List;

/**
 * @author lts
 */
public interface JMailService
{

  /**
   * 发送简单的邮箱信息
   *
   * @param subject 主题
   * @param text    邮件内容
   * @param to      接收人(可添加多个)
   */
  boolean sendMail(String subject, String text, String... to);

  /**
   * 发送带附件信息的邮件
   *
   * @param subject   主题
   * @param text      邮件内容
   * @param filepaths 文件路径集合
   * @param to        接收人(可添加多个)
   */
  boolean sendMail(String subject, String text, List<String> filepaths, String... to);

}
