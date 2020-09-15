package org.kevin.governance.discovery;

/*
 * Creates on 2019/11/13.
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author kevin
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String>
{

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception
  {
    System.out.println(s);
  }

}
