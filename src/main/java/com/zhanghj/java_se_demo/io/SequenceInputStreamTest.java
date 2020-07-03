package main.java.com.zhanghj.java_se_demo.socket;

import java.io.*;
import java.util.Vector;

public class SequenceInputStreamTest {
    public static void main(String[] args) {
        //创建一个合并流的对象
        SequenceInputStream sis = null;
        //创建输出流
        BufferedOutputStream bos = null;
        Vector<InputStream> inputStreams = new Vector<>();
        try {
            inputStreams.add(new FileInputStream("F:\\data\\限时购\\创建限时购.html"));
            inputStreams.add(new FileInputStream("F:\\data\\限时购\\商品管理.html"));
            inputStreams.add(new FileInputStream("F:\\data\\限时购\\营销中心.html"));
            sis = new SequenceInputStream(inputStreams.elements());
            bos = new BufferedOutputStream(new FileOutputStream("D:\\text4.html"));
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = sis.read(buf)) != -1) {
                bos.write(buf, 0, len);
                bos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


