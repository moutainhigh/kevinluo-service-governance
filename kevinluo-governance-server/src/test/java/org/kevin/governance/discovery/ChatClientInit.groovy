package org.kevin.governance.discovery

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.Delimiters
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder

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
class ChatClientInit extends ChannelInitializer<SocketChannel>
{

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception
  {
    socketChannel.pipeline()
    .addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
    .addLast("decoder", new StringDecoder())//解码
    .addLast("encoder", new StringEncoder())//编码
    .addLast("handler", new ChatClientHandler())
  }

}
