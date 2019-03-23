package top.ljjapp.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {

        //1.创建一个RandomAccessFile（随机访问文件）对象
        RandomAccessFile raf = new RandomAccessFile("D:\\niodata.txt", "rw");
        //通过RandomAccessFile对象的getChannel()方法。FileChannel是抽象类。
        FileChannel channel = raf.getChannel();

        //2.创建一个读数据缓冲区对象
        ByteBuffer buf = ByteBuffer.allocate(48);
        //从通道中读取数据
        int read = channel.read(buf);

        //3.创建一个写数据缓冲区对象
        ByteBuffer buf2 = ByteBuffer.allocate(48);
        //写入数据
        buf2.put("filechannel test".getBytes());
        buf2.flip();
        channel.write(buf2);

        //读取数据
        while (read != -1) {
            System.out.println("Read" + read);
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.println( (char)buf.get());
            }

            buf.clear();
            read = channel.read(buf);
        }

        //关闭RandomAccessFile（随机访问文件）对象
        raf.close();
    }
}
