package exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestException02 {
    public static void main(String[] args) {
        method1();
    }

    private  static void method1(){
        try{
            method2();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void method2() throws FileNotFoundException {
        File file = new File("d:/redmine1.txt");

        System.out.println("试图打开 d:/redmine1.txt");
        new FileInputStream(file);
        System.out.println("成功打开");
    }
}
