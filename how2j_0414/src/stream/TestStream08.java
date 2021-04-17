package stream;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestStream08 {
    public static void main(String[] args) {
        File file = new File("d:/tmp/test.txt");

        FileInputStream fis;

            try {
                fis = new FileInputStream(file);
                byte[] all = new byte[(int) file.length()];
                fis.read(all);
                for (byte b:
                        all) {
                    int i = b&0x000000ff;
                    System.out.println(Integer.toHexString(i));

                }
                String str = new String(all,"GBk");
                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


}
