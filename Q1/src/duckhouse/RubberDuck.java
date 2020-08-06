package duckhouse;

public class RubberDuck extends Duck{
    private String material;


    public RubberDuck(String name) {
        this.name = name;
        flyBehaviour = new NoFlying();
        quackBehaviour = new Squeak();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public int layEggs() {
        return 0;
    }

    @Override
    public String getDuckGreetings() {
        return name + " the Rubber Duck says " + quackBehaviour.quack();
    }
}
