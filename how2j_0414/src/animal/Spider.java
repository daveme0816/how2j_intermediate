package animal;

public class Spider extends Animal{

    public Spider(){
        //super.legs=8;
        super(8);
        System.out.println("构造一个8条腿的蜘蛛。");
    }

    @Override
    public void eat() {
        System.out.println("蜘蛛吃猎物。");
    }
}
