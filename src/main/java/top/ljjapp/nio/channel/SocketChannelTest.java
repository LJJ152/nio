package top.ljjapp.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelTest {
    public static void main(String[] args) throws IOException {
        //1.通过SocketChannel的open()方法创建一个SocketChannel对象
        SocketChannel socketChannel = SocketChannel.open();

        //2.连接到远程服务器（连接此通道的socket）
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 3333));

        // 3.创建写数据缓存区对象
        ByteBuffer writeBuffer = ByteBuffer.allocate(128);
        writeBuffer.put("hello,I'm client".getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        //创建读数据缓存区对象
        ByteBuffer readBuffer = ByteBuffer.allocate(128);
        socketChannel.read(readBuffer);
        StringBuilder stringBuilder = new StringBuilder();
        readBuffer.flip();

        while (readBuffer.hasRemaining()) {
            stringBuilder.append((char) readBuffer.get());
        }

        System.out.println("从服务器端接收到的数据：" + stringBuilder);

        socketChannel.close();
    }
}
