package com.kevin.governance.discovery

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

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
class NioServerHandler extends SimpleChannelInboundHandler<Object>
{

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception
  {
    println msg
  }
}
