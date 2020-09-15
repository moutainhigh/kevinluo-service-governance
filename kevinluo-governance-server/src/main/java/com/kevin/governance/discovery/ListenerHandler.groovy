package com.kevin.governance.discovery

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
 * Creates on 2019/11/13.
 */

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext

/**
 * @author lts
 */
class ListenerHandler implements ChannelHandler
{

  @Override
  void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception
  {
    println "接收到来自${channelHandlerContext.name()}"
  }

  @Override
  void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception
  {
    System.out.println()
  }

  @Override
  void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception
  {
    System.out.println()
  }

}
