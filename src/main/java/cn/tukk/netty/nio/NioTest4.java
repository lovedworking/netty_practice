package cn.tukk.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-11 17:43
 **/
public class NioTest4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream=new FileInputStream("input.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("output.txt");
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);

        System.out.println("buffer.position"+byteBuffer.position());
        System.out.println("buffer.limit"+byteBuffer.limit());
        while(true){
            //byteBuffer.clear(); //这行注释掉发生什么？ position 和limit值一样 会导致通道中数据无法继续往buffer里读 ，buffer里一直都是以前的数据

            int read = inputChannel.read(byteBuffer);
            System.out.println("read:>>"+read);
            if(-1==read){
                break;
            }
            byteBuffer.flip();
            outChannel.write(byteBuffer);
        }
        inputChannel.close();
        outChannel.close();
    }
}
