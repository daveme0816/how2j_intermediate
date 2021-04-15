package animal;

public class Fish extends Animal implements Pet{
    private String name;

    public void walk(){
        System.out.println("鱼不会走");
    }

    public Fish(){
        super();
        System.out.println("我是一条鱼");
    }

    @Override
    public void eat() {
        System.out.println("鱼吃水草");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        System.out.println("鱼吐泡泡");
    }
}
