package com.kevinluo.storage.framework.utils.io;

/* ************************************************************************
 *
 * Copyright (C) 2020 tiansheng All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/*
 * Creates on 2020/9/12.
 */

import com.kevinluo.storage.framework.utils.Assert;

import java.io.File;

/**
 * 和文件操作相关的工具类
 *
 * @author lts
 */
public class Files
{

  /**
   * 获取文件后缀名
   *
   * @param file 文件路径或者是文件名
   * @return 文件后缀名
   */
  public static String getExtension(String file)
  {
    Assert.requiredNonNull(file);
    String name = new File(file).getName();
    int indexOf = name.lastIndexOf('.');
    return name.substring(indexOf + 1);
  }

}