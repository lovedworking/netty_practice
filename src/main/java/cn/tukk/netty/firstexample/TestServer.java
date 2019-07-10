package cn.tukk.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**{
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-10 11:43
 **/
public class TestServer {
    public static void main(String[] args) {
        EventLoopGroup  bossGroup = new NioEventLoopGroup();
        EventLoopGroup  workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class).
                    childHandler(new TestServerInitalizer());
            ChannelFuture channelFuture=serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
