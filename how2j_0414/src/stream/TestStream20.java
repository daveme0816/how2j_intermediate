package stream;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class TestStream20 {

    public static void main(String[] args) {
        CreateClass("d:/tmp");
    }

    public static void CreateClass(String path) {
        File f = new File(path);//文件位置

        Scanner s = new Scanner(System.in);
        System.out.print("请输入类名：");
        String className = s.next();
        System.out.print("请输入属性类型：");
        String type = s.next();
        System.out.print("请输入属性名：");
        String typeName = s.next();
        String typeName_up = typeName.replace(typeName.substring(0, 1), typeName.substring(0, 1).toUpperCase());//大写属性名首字母，set和get更规范

        try {
            FileWriter fw = new FileWriter(new File(path + "/" + className + ".java"));
            fw.write("package IO流.自动创建类;\n" +
                    "\npublic class " + className + " {\n" +
                    "    public " + type + " " + typeName + ";\n" +
                    "    public " + className + "() {\n" +
                    "    }\n" +
                    "    public void set" + typeName_up + "(" + type + "  " + typeName + "){\n" +
                    "        this." + typeName + " = " + typeName + ";\n" +
                    "    }\n" +
                    "      \n" +
                    "    public " + type + "  get" + typeName_up + "(){\n" +
                    "        return this." + typeName + ";\n" +
                    "    }\n" +
                    "}");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
