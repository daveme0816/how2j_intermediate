package stream;

import charactor.Hero;

import java.io.*;

public class TestStream16 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Hero h = new Hero();
        h.name = "garen";
        h.hp = 616;

        File f = new File("d:/tmp/garen.lol");

        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);

        oos.writeObject(h);
        Hero h2 = (Hero) ois.readObject();
        System.out.println(h2.name);
        System.out.println(h2.hp);
    }
}
