package main.java.com.zhanghj.java_se_demo.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class datagramSocketClientTest {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            String s = "你好，余欢水";
            DatagramPacket packet = new DatagramPacket(s.getBytes(), s.getBytes().length, InetAddress.getLocalHost(), 8899);
            socket.send(packet);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
