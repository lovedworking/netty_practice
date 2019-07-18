package cn.tukk.netty.secondexample;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-10 16:00
 **/
public class MyServer {
    public static void main(String[] args) {
       EventLoopGroup parentGroup=new NioEventLoopGroup();//第一步：设置一些配置，默认设为1；；；；如何不设默认需要设置机器支持的线程数   如果设为1 代表只需要一个线程来
       EventLoopGroup childGroup=new NioEventLoopGroup();
        System.out.println();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup,childGroup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.WARN))
                    .childHandler(new MyServerInitializer());
            ChannelFuture ch = serverBootstrap.bind(8899).sync();
            ch.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }

    }
}
