package stream;

import java.io.IOException;
import java.io.InputStream;

public class TestStream18 {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        while (true){
            int i = is.read();
            System.out.println(i);
        }
    }
}
