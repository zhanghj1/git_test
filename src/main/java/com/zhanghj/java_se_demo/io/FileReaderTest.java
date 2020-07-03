package main.java.com.zhanghj.java_se_demo.io;

import java.io.*;

/**
 * read() :从输入流中读取数据的下一个字节，返回0到255范围内的int字节值
 * read(byte[] b) : 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。以整数形式返回实际读取的字节数
 */
public class FileReaderTest {
    public static void main(String[] args) {
//        testFileReader();
        //字节流-->字符流
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new FileWriter("is2.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                if ("over".equals(line)) {
                    break;
                }
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void testFileReader() {
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(new File("src\\main\\java\\com\\zhanghj\\java_se_demo\\hello.txt"));
            fileWriter = new FileWriter(new File("src\\main\\java\\com\\zhanghj\\java_se_demo\\helloxxx.txt"));
            int data;
            while ((data = fileReader.read()) != -1) {
                fileWriter.write((char) data);
                fileWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    e.getMessage();
                }
            }

            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
    }
}
