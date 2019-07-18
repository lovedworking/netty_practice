package cn.tukk.netty.nio;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-12 14:25
 **/
public class NioServer {
    private static Map<String,SocketChannel>  clientMap=new HashMap<>();
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();//创建serverSocketChannel
        serverSocketChannel.configureBlocking(false); //配置为非阻塞
        ServerSocket serverSocket = serverSocketChannel.socket();//获取serverSocket
        serverSocket.bind(new InetSocketAddress(8899));//绑定端口

        Selector selector = Selector.open();//获取selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//将serverSocketChannel 注册到 Selector选择器上

        while(true){
            try{
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();//获取到 selectkey
                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if(selectionKey.isAcceptable()){//新建立连接
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();//因为前面注册就是serverSocketChannel 向下转型就知道是ServerSocketChannel
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);//又注册一个事件到selector上，注册的是socketChannel 关注数据读取事件

                            String key="【"+ UUID.randomUUID().toString()+"】";
                            clientMap.put(key,client);//客户端注册
                        }else if(selectionKey.isReadable()){
                            client=(SocketChannel) selectionKey.channel();
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int count = client.read(readBuffer);
                            if(count>0){
                                readBuffer.flip();
                                Charset charset=Charset.forName("utf-8");
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());
                                System.out.println(client+" :"+receivedMessage);

                                String sendKey=null;
                                for (Map.Entry<String,SocketChannel> entry : clientMap.entrySet()) {
                                    if(client==entry.getValue()){
                                        sendKey=entry.getKey();
                                        break;
                                    }
                                }
                                for (Map.Entry<String,SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel value = entry.getValue();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                    writeBuffer.put((sendKey+":"+receivedMessage).getBytes());
                                    writeBuffer.flip();
                                    value.write(writeBuffer);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                selectionKeys.clear();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
