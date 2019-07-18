package cn.tukk.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-12 10:07
 **/
public class NioTest10 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(999);
        serverSocketChannel.socket().bind(inetSocketAddress);

        int messageLength=2+3+4;
        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0]=ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);
        SocketChannel socketChannel = serverSocketChannel.accept();
        while (true){
            int bytesRead=0;
            while(bytesRead<messageLength){
                long r = socketChannel.read(byteBuffers);
                bytesRead+=r;
                System.out.println("bytesRead:"+bytesRead);
                Arrays.asList(byteBuffers).stream().
                        map(buffer->"position: "+buffer.position()+",limit: "+buffer.limit()).
                        forEach(System.out::println);

            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            long byteWritten=0;
            while(byteWritten<messageLength){
                long r=socketChannel.write(byteBuffers);
                byteWritten+=r;
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());
            System.out.println("bytesRead:"+bytesRead+",bytesWritten:"+byteWritten+",messageLength:"+messageLength);
        }
    }
}
