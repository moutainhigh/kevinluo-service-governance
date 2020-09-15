package com.kevin.governance.discovery

import com.kevin.governance.Configuration
import com.kevinluo.storage.framework.logging.LogFactory
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel

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
class ServerDiscovery
{

  /**
   * 监听端口
   */
  private static final def port = Configuration.getPort()

  private static def LOG = LogFactory.getLog(ServerDiscovery.class)

  /**
   * 启动服务发现监听
   */
  static void launcher()
  {

    def bossGroup = new NioEventLoopGroup()
    def workerGroup = new NioEventLoopGroup()

    def bootstrap = new ServerBootstrap()
    bootstrap.group(bossGroup, workerGroup)
    .channel(NioServerSocketChannel.class)
    // 初始化服务可连接队列，初始化128
    .option(ChannelOption.SO_BACKLOG, 128)
    // 保持长连接
    .childOption(ChannelOption.SO_KEEPALIVE, true)
    // 绑定客户端连接时触发的操作
    .childHandler(new ListenerHandler())

    // 绑定监听端口，使用sync同步阻塞方法等待绑定操作完成
    ChannelFuture future = bootstrap.bind(port).sync()
    if(future.isSuccess())
    {
      LOG.info("发现服务启动成功, 监听端口：$port")
    } else
    {
      LOG.error("服务启动失败")
      future.cause().printStackTrace()
      bossGroup.shutdownGracefully() // 关闭线程组
      workerGroup.shutdownGracefully()
    }

    future.channel().closeFuture().sync()

  }

}
