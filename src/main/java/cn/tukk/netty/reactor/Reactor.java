package cn.tukk.netty.reactor;

import com.sun.corba.se.pept.broker.Broker;
import com.sun.corba.se.pept.encoding.InputObject;
import com.sun.corba.se.pept.encoding.OutputObject;
import com.sun.corba.se.pept.protocol.MessageMediator;
import com.sun.corba.se.pept.transport.Acceptor;
import com.sun.corba.se.pept.transport.Connection;
import com.sun.corba.se.pept.transport.EventHandler;
import com.sun.corba.se.pept.transport.InboundConnectionCache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/** https://www.cnblogs.com/luxiaoxun/archive/2015/03/11/4331110.html
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-18 16:20
 **/
public class Reactor implements  Runnable{
    final Selector selector;
    final ServerSocketChannel serverSocket;

    // Reactor SetUP
    Reactor(int port) throws IOException{
        selector = Selector.open();
        serverSocket=ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }


    // Reactor2:Dispatch Loop 调度循环
    @Override
    public void run() {//normally in a new Thread
        try {
            while (!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()){
                    dispatch((SelectionKey)it.next());
                }
                selected.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    void dispatch(SelectionKey key){
        Runnable r = (Runnable) (key.attachment());
        if(r!=null)
            r.run();
    }

    // Reactor 3: Acceptor
    class Acceptor implements Runnable{ //inner
        @Override
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if(c!=null)
                    new Handler(selector,c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}


final class Handler implements Runnable{
    final SocketChannel socket;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    int state = READING;
    Handler(Selector sel,SocketChannel c) throws IOException {
        socket=c;
        c.configureBlocking(false);
        sk=socket.register(sel,0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    @Override
    public void run() {
        try {
            if(state==READING) read();
            else if(state==SENDING) send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void read() throws IOException{
        socket.read(input);
        if(inputIsComplete()){
            process();
            state=SENDING;
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }
    void send() throws IOException {
        socket.write(output);
        if (outputIsComplete()) sk.cancel();
    }

    boolean inputIsComplete() {return false;};
    boolean outputIsComplete() { return false;};

    void process() { };


}










