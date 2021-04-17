package collection;

import charactor.Hero;
import property.Item;

import java.util.ArrayList;
import java.util.List;

public class TestCollection03 {
    public static void main(String[] args) {
        List heros = new ArrayList();
        heros.add(new Hero("garen"));
        heros.add(new Item("ice"));

        Hero h1 = (Hero) heros.get(0);
        //Hero h2 = (Hero) heros.get(1);

        List<Hero> genericheros = new ArrayList<Hero>();
        genericheros.add(new Hero("garen"));
        //genericheros.add(new Item("ice"));

        //genericheros.add(new APhero());

    }
}
