package stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestStream01 {
    public static void main(String[] args) {

        File f = new File("d:/lol.txt");
        try {
            FileInputStream fis = new FileInputStream(f);
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
            for (byte b:all ) {
                System.out.println(b);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
