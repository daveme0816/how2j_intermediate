package stream;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestStream07 {
    public static void main(String[] args) {
        System.out.println("");
        File encodingFile = new File("d:/tmp/memo.txt");
        File encodedFile = new File("d:/tmp/memo_encoded.txt");
        File decodingFile = new File("d:/tmp/memo_encoded.txt");
        File decodeFile = new File("d:/tmp/memo_decode.txt");
        encodeFile(encodingFile,encodedFile);
        System.out.print("加密前的文件内容：");
        info(encodingFile);
        System.out.print("加密后的文件内容：");
        info(encodedFile);
        decodeFile(decodingFile,decodeFile);
        System.out.print("解密后的文件内容：");
        info(decodeFile);
    }

    public static void encodeFile(File encodingFile, File encodedFile){
        char[] array = new char[(int) encodingFile.length()];
        try {
            FileReader reader = new FileReader(encodingFile);
            reader.read(array);

                for (int i = 0; i < array.length; i++){
                    if (array[i] >= 49 && array[i] <= 57)
                        array[i] = (char) (array[i]-1);
                    else if (array[i] == 48)
                        array[i] = 57;
                    else if (array[i] >= 66 && array[i] <=90 || array[i] >= 98 && array[i] <= 122)
                        array[i] = (char) (array[i]-1);
                    else if (array[i] == 65 || array[i] == 97)
                        array[i] = (char) (array[i] +25);
                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter fw = new FileWriter(encodedFile)){
            fw.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void decodeFile(File decodingFile, File decodedFile){
        char[] decodeStr = new char[(int) decodingFile.length()];
        try {
            FileReader reader = new FileReader(decodingFile);
            reader.read(decodeStr);

            for (int i=0;i< decodeStr.length;i++){
                if((int)decodeStr[i] >= 48 && (int)decodeStr[i]<=56){
                    decodeStr[i]=(char)((int)decodeStr[i]+1);
                }
                else if((int)decodeStr[i]==57)
                    decodeStr[i]=(char)48;
                else if((int)decodeStr[i] >= 65 && (int)decodeStr[i]<=89 || (int)decodeStr[i] >= 97 && (int)decodeStr[i] <= 121){
                    decodeStr[i]=(char)((int)decodeStr[i]+1);
                }
                else if((int)decodeStr[i]==90 || (int)decodeStr[i]==122)
                    decodeStr[i]=(char)((int)decodeStr[i]-25);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter fw = new FileWriter(decodedFile)){
            fw.write(decodeStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //读取文件
    public static void info(File file){
        try {
            FileInputStream in = new FileInputStream(file);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            String str = new String(buffer,"UTF-8");
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
