package main.java.com.zhanghj.java_se_demo.socket;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

public class ChannelTest {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }

    private static void test6() {
        try {
            Charset cs1 = Charset.forName("GBK");
            //获取编码器
            CharsetEncoder ce = cs1.newEncoder();
            //获取解码器
            CharsetDecoder cd = cs1.newDecoder();
            CharBuffer cBuf = CharBuffer.allocate(1024);
            cBuf.put("你好，胡八一！");
            cBuf.flip();
            //编码
            ByteBuffer bBuf = ce.encode(cBuf);
            for (int i = 0; i < 12; i++) {
                System.out.println(bBuf.get());
            }
            //解码
            bBuf.flip();
            CharBuffer cBuf2 = cd.decode(bBuf);
            System.out.println(cBuf2.toString());
            System.out.println("------------------------------------------------------");
            Charset cs2 = Charset.forName("GBK");
            bBuf.flip();
            CharBuffer cBuf3 = cs2.decode(bBuf);
            System.out.println(cBuf3.toString());
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
    }

    private static void test5() {
        Map<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();
        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    private static void test4() {
        try {
            RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");
            //1. 获取通道
            FileChannel channel1 = raf1.getChannel();
            //2. 分配指定大小的缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(100);
            ByteBuffer buf2 = ByteBuffer.allocate(1024);
            //3. 分散读取
            ByteBuffer[] bufs = {buf1, buf2};
            channel1.read(bufs);
            for (ByteBuffer byteBuffer : bufs) {
                byteBuffer.flip();
            }
            System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
            System.out.println("-----------------");
            System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));
            //4. 聚集写入
            RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
            FileChannel channel2 = raf2.getChannel();
            channel2.write(bufs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test3() {
        try {
            FileChannel inChannel = FileChannel.open(Paths.get("d:/1.mkv"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("d:/2.mkv"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
//		inChannel.transferTo(0, inChannel.size(), outChannel);
            outChannel.transferFrom(inChannel, 0, inChannel.size());
            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test2() {
        try {
            long start = System.currentTimeMillis();
            FileChannel inChannel = FileChannel.open(Paths.get("d:/1.mkv"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("d:/2.mkv"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
            //内存映射文件
            MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            //直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);
            inChannel.close();
            outChannel.close();
            long end = System.currentTimeMillis();
            System.out.println("耗费时间为：" + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        //①获取通道
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("d:/1.mkv");
            fos = new FileOutputStream("d:/2.mkv");
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //②分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //③将通道中的数据存入缓冲区中
            while (inChannel.read(buf) != -1) {
                buf.flip(); //切换读取数据的模式
                //④将缓冲区中的数据写入通道中
                outChannel.write(buf);
                buf.clear(); //清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }
}
