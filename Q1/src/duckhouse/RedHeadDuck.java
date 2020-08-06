package duckhouse;

/**
 * This class defines a Red Head Duck. A duck can fly and quack. It may also lay some eggs.
 *
 * @author Yu-Cheng Tu
 */
public class RedHeadDuck extends Duck {

    public RedHeadDuck(String name) {
        this.name = name;
        flyBehaviour = new FlyWithWings();
        quackBehaviour = new Quack();
    }

    //  Step ci. Modify this method.
    @Override
    public int layEggs() {
        return 2;
    }

    // Step cii. Modify this method.
    @Override
    public String getDuckGreetings() {
        return name +" the Red Head Duck says " + quackBehaviour.quack();
    }

}
