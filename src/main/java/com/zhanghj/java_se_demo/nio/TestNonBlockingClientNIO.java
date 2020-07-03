package main.java.com.zhanghj.java_se_demo.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

/*
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）：负责连接
 *
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 *
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer）：负责数据的存取
 *
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 */
public class TestNonBlockingClientNIO {
    public static void main(String[] args) {
        client();
    }

    //客户端
    public static void client() {
        SocketChannel sChannel = null;
        try {
            //1. 获取通道
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
            //2. 切换非阻塞模式
            sChannel.configureBlocking(false);
            //3. 分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //4. 发送数据给服务端
            Scanner scan = new Scanner(System.in);
            while (scan.hasNext()) {
                String str = scan.next();
                buf.put((new Date().toString() + "\n" + str).getBytes());
                buf.flip();
                sChannel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5. 关闭通道
            try {
                sChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
