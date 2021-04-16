package file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestFile03 {
    public static void main(String[] args) throws IOException {

        long maxLength = Integer.MIN_VALUE;
        String maxFile = null;
        long minLength = Integer.MAX_VALUE;
        String minFile = null;

        File file = new File("D:/09.PDF");

        File[] fs= file.listFiles();

        for (File f : fs) {
            if (f.length() > maxLength) {
                maxLength = f.length();
                maxFile = f.getName();
            }
            if (f.length() < minLength) {
                minLength = f.length();
                minFile  = f.getName();
            }

        }

        System.out.println("最大的文件:" + maxFile + "大小是:" + maxLength +"字节");
        System.out.println("最小的文件:" + minFile + "大小是:" + minLength +"字节");
    }
}
