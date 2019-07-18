package cn.tukk.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 *
 *
 *
 *

 1 java.io 最为核心的一个概念是流（stream) 面向流的编程,Java中，一个流要么是输入流要么是输出流
 java.nio 中有三个核心概念：selector
                            channel
                            buffer
 面向块缓冲区编程











 *
 *
 *
 */

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-11 14:12
 **/
public class NioTest {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("capacity>>>"+buffer.capacity());
        for (int i = 0; i < buffer.capacity(); ++i) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        System.out.println("before the flip  buffer.limit :"+buffer.limit()+">>>buffer.position"+buffer.position());
        buffer.flip();
        System.out.println("after the flip  buffer.limit :"+buffer.limit()+">>>buffer.position"+buffer.position());
        System.out.println("enter while loop ");
        while(buffer.hasRemaining()){
            System.out.println("position :"+buffer.position());
            System.out.println("limit: "+buffer.limit());
            System.out.println("capacity:"+buffer.capacity());
            System.out.println(buffer.get());
        }
    }
}
