package animal;

public abstract class Animal {
    protected int legs;

    protected Animal(){
        System.out.println("动物没有腿。");
    }

    protected Animal(int legs){
        this.legs = legs;
        System.out.println("动物有" + legs + "条腿。");
    }

    public abstract void eat();

    public void walk(){
        System.out.println("动物用" + legs + "条腿行走。");
    }

}
