package stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestStream03 {
    public static void main(String[] args) {
        String filename = "d:/tmp/lol2.txt";
        File file = new File("d:/tmp" + "/" + "lol2.txt");
        if (!file.getParentFile().exists()){
            System.out.println("目录不存在");
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] data = {88, 89};

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
