package stream;

import java.io.*;

public class TestStream12 {
    public static void main(String[] args) throws IOException {
        File f = new File("d:/tmp/lol.txt");
        File f1 = new File("d:/tmp/lol1.txt");

        FileReader fr = new FileReader(f);
        BufferedReader in = new BufferedReader(fr);
        FileWriter fw = new FileWriter(f1);
        PrintWriter pw = new PrintWriter(fw);
        pw.write("xxx");
        pw.printf("xxx");

        while(true) {
            String line = in.readLine();
            if (line == null)
                break;
            System.out.println(line);
            pw.print(line);
        }

    }
}
