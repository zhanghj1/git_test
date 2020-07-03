package main.java.com.zhanghj.java_se_demo.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class datagramSocketServerTest {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(8899);
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            while (true) {
                socket.receive(packet);
                System.out.println(new String(bytes, 0, packet.getLength()));
            }
//            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
