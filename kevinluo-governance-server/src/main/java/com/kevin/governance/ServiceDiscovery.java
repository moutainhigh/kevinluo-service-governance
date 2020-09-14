package com.kevin.governance;

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
 * Creates on 2020/9/14.
 */

/**
 * 1. 服务发现，当启动服务后会开启一个端口并对它一直进行
 * 监听，如果发现了请求上来的服务的话，那么将会将它加入统一的
 * 服务管理中并对该服务进行监控。
 *
 * 2. 获取到该服务的信息后，将信息持久化保存。一般保存以下数据：
 *    #1 服务名称
 *    #2 服务分类
 *    #3 开放API地址
 *    #4 管理页面
 *    ...
 *
 * @author lts
 */
public class ServiceDiscovery
{



}