package duckhouse;

/**
 * This class generates different ducks and displays the information to the console.
 *
 */
public class DuckGenerator {

    private final String[] DUCK_NAMES = {"Sam", "Mary", "George", "David", "Helen",
            "Eve", "Mike", "Kate", "Jessica", "Peter"};

    private Duck[] ducks;


    // This is the main method. Do not change this!
    public static void main(String[] args) {
        DuckGenerator duckGenerator = new DuckGenerator();
        duckGenerator.start();
    }

    // This is your starting method. Do not change this!
    public void start() {

        // Step fi. Create the method generateDucks randomly generate 10 ducks of different types.


        ducks = generateDucks();

        System.out.println("Greetings from Ducks");
        System.out.println("====================");

        // Step fii. Create the method printDuckGreetings
        printDuckGreetings();

        System.out.println("====================");

        System.out.println("We are plastic ducks!");
        System.out.println("---------------------");

        // Step fiii. Create the method printPlasticDucks
        printPlasticDucks();
        System.out.println("--------------------");
    }


    private Duck[] generateDucks(){
        //Generates a Duck array of length 10. then assigns a randomized sub class to each position of the array
        ducks = new Duck[10];
        for (int i = 0; i < ducks.length; i++) {
            int num = (int)(Math.random()*3+1);
            switch(num) {
                case 1:
                    ducks[i] = new RedHeadDuck(DUCK_NAMES[i]);
                    break;
                case 2:
                    ducks[i] = new MallardDuck(DUCK_NAMES[i]);
                    break;
                case 3:
                    ducks[i] = new RubberDuck(DUCK_NAMES[i]);
                    break;
            }
        }

        return  ducks;
    }

    private void printDuckGreetings() {
        //Loops through ducks array and prints greeting and fly behaviour to console
        for (int i = 0; i < ducks.length; i++) {
            System.out.println(ducks[i].getDuckGreetings()+". " + ducks[i].flyBehaviour.fly());
        }
    }

    private void printPlasticDucks() {
        //Loop through the ducks array and if it is of the RubberDuck class, i.e instance of RubberDuck, then set the material to rubber and print greeting
        for (int i = 0; i < ducks.length; i++) {
            if(ducks[i] instanceof RubberDuck){
                RubberDuck rubberDuck = (RubberDuck) ducks[i];
                rubberDuck.setMaterial("Rubber");
                System.out.println(rubberDuck.getDuckGreetings());
            }
        }

    }


}
