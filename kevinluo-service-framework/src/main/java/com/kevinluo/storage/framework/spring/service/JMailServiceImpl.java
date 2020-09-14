package com.kevinluo.storage.framework.spring.service;

/*
 * Creates on 2019/11/13.
 */

import com.kevinluo.storage.framework.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @author lts
 */
@Service
public class JMailServiceImpl implements JMailService
{

  // 构建授权信息，用于进行SMTP进行身份验证
  final Authenticator authenticator = new Authenticator()
  {
    @Override
    protected PasswordAuthentication getPasswordAuthentication()
    {
      String username = System.getProperty("mail.user");
      String password = System.getProperty("mail.password");
      return new PasswordAuthentication(username, password);
    }
  };

  // 使用环境属性和授权信息，创建邮件会话
  final Session session = Session.getInstance(System.getProperties(), authenticator);

  /**
   * 发送邮件
   *
   * @param subject 主题
   * @param text    内容
   * @param to      收件人（可选多个）
   * @return 是否发送成功
   */
  public boolean sendMail(String subject, String text, String... to)
  {
    // 创建邮件消息
    MimeMessage message = new MimeMessage(session);
    try
    {
      // 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
      message.setFrom(new InternetAddress(System.getProperty("mail.user"), System.getProperty("mail.nickname")));
      // 设置收件人邮箱地址
      for (String s : to)
      {
        message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(s)});
      }
      message.setSubject(subject);
      message.setContent(text, "text/html;charset=UTF-8");
      // 设置邮件标题
      Transport.send(message);
      return true;
    } catch (Exception e)
    {
      throw new BusinessException("邮件发送失败", e);
    }
  }

  @Override
  public boolean sendMail(String subject, String text, List<String> filepaths, String... to)
  {
    return false;
  }

}
