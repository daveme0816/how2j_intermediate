package animal;

public class test {
    public static void main(String[] args) {
        //  测试蜘蛛
        Spider s = new Spider();
        s.eat();
        s.walk();

        System.out.println();
        //  测试猫
        Cat c = new Cat();
        Cat c1 = new Cat("喵星人");
        c.setName("没有名字的喵");
        System.out.println("第一只猫的名字：" + c.getName());
        System.out.println("第二只猫的名字：" + c1.getName());
        c1.setName("汪星人");
        System.out.println("第二只猫改名后的名字：" + c1.getName());
        c.walk();
        c1.eat();
        c.play();

        System.out.println();
        //  测试鱼
        Fish f = new Fish();
        f.setName("红烧鱼");
        System.out.println("鱼的名字：" + f.getName());
        f.eat();
        f.play();
        f.walk();
    }
}
