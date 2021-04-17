package stream;

import java.io.*;

public class TestStream15 {
    public static void main(String[] args) {
        try {
            write();
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private  static void read() throws IOException {
        File file = new File("d:/tmp/lol.txt");

        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);


        int i1 = dis.readInt();
        int i2 = dis.readInt();

        System.out.println("读取到整数:"+i1);
        System.out.println("读取到整数:"+i2);
    }

    private  static void write() throws IOException {
        File file = new File("d:/tmp/lol.txt");

        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeInt(333);
        dos.writeInt(444);
    }
}
