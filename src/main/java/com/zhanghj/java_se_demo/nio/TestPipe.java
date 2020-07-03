package main.java.com.zhanghj.java_se_demo.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class TestPipe {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Pipe.SinkChannel sinkChannel = null;
        Pipe.SourceChannel sourceChannel = null;
        try {
            //1. 获取管道
            Pipe pipe = Pipe.open();
            //2. 将缓冲区中的数据写入管道
            ByteBuffer buf = ByteBuffer.allocate(1024);
            sinkChannel = pipe.sink();
            buf.put("通过单向管道发送数据".getBytes());
            buf.flip();
            sinkChannel.write(buf);
            //3. 读取缓冲区中的数据
            sourceChannel = pipe.source();
            buf.flip();
            int len = sourceChannel.read(buf);
            System.out.println(new String(buf.array(), 0, len));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sourceChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                sinkChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
