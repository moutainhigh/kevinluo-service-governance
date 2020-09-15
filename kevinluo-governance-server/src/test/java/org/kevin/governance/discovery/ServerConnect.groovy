package org.kevin.governance.discovery

import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel

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
 * 测试客户端链接
 * @author kevin
 */
EventLoopGroup group = new NioEventLoopGroup();// 处理线程组  workergroup中取出一个管道channel来建立连接
Bootstrap bootstrap = new Bootstrap()
.group(group)
.channel(NioSocketChannel.class)
.handler(new ChatClientInit())

// 绑定 ip 端口 创建连接 调用sync()方法会阻塞直到服务器完成绑定  然后服务器再获取通道channel
Channel channel = bootstrap.connect("127.0.0.1", 505).sync().channel();

//定义向服务器发送的内容  system.in  控制台输入   in.readLine() 获取值 每次读一行。换句话说，用户输入一行内容，然后回车，这些内容一次性读取进来。
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

// noinspection GroovyInfiniteLoopStatement
while (true)
{
  //writeAndFlush()方法分为两步, 先 write 再 flush
  channel.writeAndFlush(br.readLine() + "\r\n");
}
