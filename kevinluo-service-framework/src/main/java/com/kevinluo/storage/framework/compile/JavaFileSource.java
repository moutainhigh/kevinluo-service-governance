package com.kevinluo.storage.framework.compile;

/*
 * Creates on 2020/3/24.
 */

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * @author lts
 */
public class JavaFileSource extends SimpleJavaFileObject {

  private String code;

  public JavaFileSource(String name, String code) {
    super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
    this.code = code;
  }

  public CharSequence getCharContent(boolean ignoreEncodingErrors) {
    return this.code;
  }

}
