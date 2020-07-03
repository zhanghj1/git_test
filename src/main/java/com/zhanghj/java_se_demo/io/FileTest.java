package main.java.com.zhanghj.java_se_demo.io;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * public static final String separator:根据操作系统,动态的提供分隔符--window(/)--unix(\)
 */
public class FileTest {
    public static void main(String[] args) {
        File file = new File("F:\\study\\nio");
        printSubFile(file);
    }

    public static void printSubFile(File dir) {
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                printSubFile(f);
            } else {
                System.out.println(f.getAbsolutePath());
            }
        }
    }
}
