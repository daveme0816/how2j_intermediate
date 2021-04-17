package collection;

import charactor.Hero;

import java.util.ArrayList;

public class TestCollection02 {
    public static void main(String[] args) {
        ArrayList heros = new ArrayList();

        for (int i = 0; i < 5; i++){
            heros.add(new Hero("hero" + i));
        }
        Hero specialHero = new Hero("special hero");
        heros.add(specialHero);
        System.out.println(heros);
        System.out.println("specialHero所处的位置:"+heros.indexOf(specialHero));
        System.out.println("新的英雄，但是名字是\"hero 1\"所处的位置:"+heros.indexOf(new Hero("hero 1")));
        heros.remove(2);
        System.out.println(heros);
        heros.set(2, new Hero("hero x"));
        System.out.println(heros);
        System.out.println(heros.size());
        Hero hs[] = (Hero[])heros.toArray(new Hero[]{});
        System.out.println(hs);

    }
}
