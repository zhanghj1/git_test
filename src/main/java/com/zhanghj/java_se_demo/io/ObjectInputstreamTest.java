package main.java.com.zhanghj.java_se_demo.io;

import java.io.*;
import java.net.InetAddress;

//创建要写入磁盘的类，这个类需要实现接口 Serializable（可系列化的）
class Student implements Serializable {

    // 在这里保证了serialVersionUID 的唯一性，防止属性变量的临时改变，从而造成写入id与读取id不同
    private static final long serialVersionUID = 1L;
    int id; //额外需要添加一个属性
    String name;
    transient String sex; //transient修饰属性，表示暂时的，则这个属性不会被写入磁盘 static和transient修饰属性是不可序列化的
    transient int age;

    public Student(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}

public class ObjectInputstreamTest {
    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        createObj();
        readObj();
    }

    //（一）先写入对象
    public static void createObj() throws IOException {
        //1.创建目标路径
        File file = new File("D:\\code\\obj_test.txt");
        //2.创建流通道
        FileOutputStream fos = new FileOutputStream(file);
        //3.创建对象输出流
        ObjectOutputStream objOP = new ObjectOutputStream(fos);
        //4.创建类对象，并初始化
        Student stu = new Student("玛丽苏", "男", 18);
        //5.向目标路径文件写入对象
        objOP.writeObject(stu);
        //6.关闭资源
        objOP.close();
    }

    //再读取对象
    public static void readObj() throws IOException, ClassNotFoundException {
        File file = new File("D:\\code\\obj_test.txt");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream objIP = new ObjectInputStream(fis);
        //读取对象数据，需要将对象流强制转换为 要写入对象的类型
        Student stu = (Student) objIP.readObject();
        System.out.println("\n name:" + stu.name + "\n sex:" + stu.sex + "\n age:" + stu.age);
        objIP.close();
    }
}
