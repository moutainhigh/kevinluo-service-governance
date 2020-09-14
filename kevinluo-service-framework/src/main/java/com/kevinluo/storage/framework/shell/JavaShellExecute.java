package com.kevinluo.storage.framework.shell;

/*
 * Creates on 2020/5/18.
 */

import com.kevinluo.storage.framework.utils.Charsets;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author lts
 */
public class JavaShellExecute {

  /**
   * 调用linux下的.sh脚本文件
   */
  public static String callShell(String script) {
    return exec(script);
  }

  public static String exec(String... commands) {
    try {
      Runtime runtime = Runtime.getRuntime();
      Process pro = runtime.exec(commands);
      int status = pro.waitFor();
      if (status != 0) {
        System.out.println("Failed to call shell's command");
      }
      BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream(), Charsets.GBK));
      StringBuilder strbr = new StringBuilder();
      String line;
      while ((line = br.readLine()) != null) {
        strbr.append(line).append("\n");
      }
      return strbr.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}