package cn.tukk.netty.zeroCopy;



import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: netty_practice
 *
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-16 11:31
 **/
public class OldServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);
        while(true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream= new DataInputStream(socket.getInputStream());
            try {
                byte[] byteArray = new byte[4096];
                while(true){
                    int readCount=dataInputStream.read(byteArray,0,byteArray.length);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
