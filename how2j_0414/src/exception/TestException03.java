package exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestException03 {
    public static void main(String[] args) {
        System.out.println(method1());
    }

    public static int method1(){
        try{
            File file = new File("d:/redmine1.txt");

            System.out.println("试图打开 d:/redmine1.txt");
            new FileInputStream(file);
            System.out.println("成功打开");
            return 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 2;
        } finally {
            System.out.println("finally");
            return 3;
        }
    }
}
