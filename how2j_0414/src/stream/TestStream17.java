package stream;

import charactor.Hero;

import java.io.*;

public class TestStream17 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File f = new File("d:/tmp/garen.lol");
        Hero[] heros = new Hero[10];
        int i = 1;
        int j = 1;
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);

        for (Hero h :
                heros) {
            h = new Hero();
            h.name = String.valueOf("garen" + i);
            h.hp = 616 + i;
            i++;
            System.out.println("第" + i + "个hero:" + h.name + ",hp=" +h.hp);
            oos.writeObject(h);
        }

        Hero[] hero2s = new Hero[10];
        for (Hero h2 :
                hero2s) {
            h2 = (Hero) ois.readObject();
            System.out.println("第" + j + "个hero:" + h2.name + ",hp=" +h2.hp);
            j++;
        }



    }
}
