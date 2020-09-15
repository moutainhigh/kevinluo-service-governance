package com.kevin.governance

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
 * Creates on 2020/9/15..
 */


/**
 * @author kevin
 */
class Configuration
{

  /**
   * @return 服务发现使用端口
   */
  static int getPort()
  {
    Integer.valueOf(getConfig("server.discovery.port"))
  }

  /**
   * 获取系统配置，以及其他配置
   *
   * @param value 字段名
   * @return 字段值
   */
  static String getConfig(String value)
  {
    return String.valueOf(System.getProperty(value))
  }

}
