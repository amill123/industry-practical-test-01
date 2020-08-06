package duckhouse;

/**
 * This class defines a duck. A duck can fly and quack. It may also lay some eggs.
 *
 * @author Yu-Cheng Tu
 */
public abstract class Duck {
    // Name of the duck
    public String name;

    // Flying behaviour of the duck
    public IFly flyBehaviour;

    // Quack behaviour of the duck
    public IQuack quackBehaviour;

    /**
     * The duck can lay some eggs.
     *
     * @return Number of eggs produced by the duck
     */
    public abstract int layEggs();

    /**
     * The duck does the greeting. E.g. George the Rubber Duck says squeak squeak
     *
     * @return Greetings from the duck
     */
    public abstract String getDuckGreetings();

    /**
     * The duck tries to fly.
     *
     * @return A statement of what the duck is currently doing
     */
    public String doFly() {
        return flyBehaviour.fly();
    }

    /**
     * The duck makes some noise.
     *
     * @return The sound of the duck
     */
    public String doQuack() {
        return quackBehaviour.quack();
    }
}
