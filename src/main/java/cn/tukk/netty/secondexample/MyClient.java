package cn.tukk.netty.secondexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-10 16:32
 **/
public class MyClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        try {
            Bootstrap bootStrap = new Bootstrap();
            bootStrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer());
            ChannelFuture channelFuture=bootStrap.connect("localhost",8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
