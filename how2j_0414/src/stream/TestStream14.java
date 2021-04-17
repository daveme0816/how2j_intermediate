package stream;

import java.io.*;

public class TestStream14 {
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

        boolean b = dis.readBoolean();
        int i = dis.readInt();
        String str = dis.readUTF();

        System.out.println("读取到布尔值:"+b);
        System.out.println("读取到整数:"+i);
        System.out.println("读取到字符串:"+str);
    }

    private  static void write() throws IOException {
        File file = new File("d:/tmp/lol.txt");

        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeBoolean(true);
        dos.writeInt(300);
        dos.writeUTF("123 this is gareen");
    }
}
