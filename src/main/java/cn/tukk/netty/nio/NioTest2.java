package cn.tukk.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-11 14:34
 **/
public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream=new FileInputStream("NIOTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer buffer= ByteBuffer.allocate(512);
        ByteBuffer.allocateDirect(33);
        fileChannel.read(buffer);

        buffer.flip();

        while(buffer.remaining()>0){
            byte b = buffer.get();
            System.out.println("Character: "+(char) b);
        }

        fileInputStream.close();
    }
}
