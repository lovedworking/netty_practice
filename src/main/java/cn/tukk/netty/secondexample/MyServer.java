package cn.tukk.netty.secondexample;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-10 16:00
 **/
public class MyServer {
    public static void main(String[] args) {
       EventLoopGroup parentGroup=new NioEventLoopGroup();
       EventLoopGroup childGroup=new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup,childGroup).channel(NioServerSocketChannel.class).
                    childHandler(new MyServerInitializer());
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
