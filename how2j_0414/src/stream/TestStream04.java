package stream;

import java.io.*;

public class TestStream04 {
    public static void main(String[] args) {
        File file = new File("d:/tmp" + "/" + "ccl.txt");
        File dir = new File("d:/tmp");

        try {
            InputStream in = new FileInputStream(file);
            if (!file.exists()){
                dir.mkdirs();
            }
            byte[] buf = new byte[1024 * 100];

            int len, count = 1;
            while ((len =in.read(buf)) != -1){
                OutputStream out = new FileOutputStream(new File
                        ("d:/tmp" + "/" + "ccl" + (count++) + ".txt"));
                out.write(buf,0,len);
                out.close();
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }






    }
}
