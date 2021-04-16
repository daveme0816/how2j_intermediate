package stream;

import java.io.*;

public class TestStream05 {

    public static void main(String[] args) {

    {
        File file = new File("d:/tmp/ccl_new.txt");
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);

            byte[] buf = new byte[10 * 1024];
            int count = 1;
            int len;
            while (true) {

                File in = new File("d:/tmp/ccl" + (count) + ".txt");
                if (in.exists()){
                    System.out.println(in.getName());
                    InputStream input = new FileInputStream(in);
                    while ((len = input.read(buf)) != -1) {
                        out.write(buf, 0, len);
                    }
                    input.close();
                    count++;
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}








