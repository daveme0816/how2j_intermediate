package stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestStream02 {
    public static void main(String[] args) {
        File f = new File("d:/lol2.txt");
        byte[] data = {88, 89};

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
