package duckhouse;

public class MallardDuck extends Duck{

    public MallardDuck(String name) {
        this.name = name;
        flyBehaviour = new FlyWithWings();
        quackBehaviour = new Quack();
    }

    @Override
    public int layEggs() {
        return 5;
    }

    @Override
    public String getDuckGreetings() {
        return name + " the Mallard Duck says " + quackBehaviour.quack();
    }
}
