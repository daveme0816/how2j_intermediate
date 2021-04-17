package stream;

import java.io.*;
import java.nio.charset.Charset;

public class TestStream09 {
    public static void main(String[] args) throws IOException {

        File file = new File("d:/tmp/test.txt");
        System.out.println("默认编码方式：" + Charset.defaultCharset());

        FileReader reader = new FileReader(file);
        char[] cs = new char[(int) file.length()];
        reader.read(cs);
        System.out.printf("FileReader会使用默认的编码方式%s,识别出来的字符是：%n",Charset.defaultCharset());
        System.out.println(new String(cs));

        InputStreamReader isr = new InputStreamReader(new FileInputStream(file),Charset.forName("UTF-8"));
        char[] cs1 = new char[(int) file.length()];
        isr.read(cs1);
        System.out.printf("InputStreamReader会使用默认的编码方式UTF-8,识别出来的字符是：%n");
        System.out.println(new String(cs1));

    }
}
