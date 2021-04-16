package stream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestStream06 {
    public static void main(String[] args) {
        File file = new File("d:/tmp/lol.txt");
        try {
            FileReader reader = new FileReader(file);
            char[] all = new char[(int) file.length()];
            reader.read(all);
            for (char b:
                 all) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file2 = new File("d:/tmp/lol2.txt");

        try (FileWriter fr = new FileWriter(file2)){
            String str = "abcdefg123456789";
            char[] cs = str.toCharArray();
            fr.write(cs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
