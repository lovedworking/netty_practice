package cn.tukk.netty.thirdexample;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-10 17:19
 **/
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }
}
