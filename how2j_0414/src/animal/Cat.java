package animal;

public class Cat extends Animal implements Pet{

    String name;

    public Cat(String name){
        super(4);
        this.name = name;
        System.out.println("构造一只叫做" + name + "的猫，有四条腿");
    }

    public Cat(){
        this("");
        System.out.println("请主人给本喵起个名字");
    }

    @Override
    public void eat() {
        System.out.println("猫吃老鼠");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        System.out.println("猫在玩耍");
    }
}
