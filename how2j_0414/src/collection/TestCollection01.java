package collection;

import charactor.Hero;

import java.util.ArrayList;

public class TestCollection01 {
    public static void main(String[] args) {
        ArrayList heros = new ArrayList();
        heros.add(new Hero("garen"));
        System.out.println(heros.size());
        heros.add(new Hero("motee"));
        System.out.println(heros.size());
    }
}
