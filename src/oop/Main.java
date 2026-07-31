public class Main{

    public static void main(String[] args) {

        //Test reactors
        Reactor reactorA = new Reactor("CH4-Reactor", 700.0, 500.0 );
        Reactor reactorB = new Reactor("CO2-Reactor", 1020.0, 200.0);
        Reactor reactorC = new Reactor("CH3-COOH-Reactor", 450.0, 150.0);

        //Print the initial state
        System.out.println(reactorA);
        System.out.println(reactorB);
        System.out.println(reactorC);

        //Test reactor operations
        reactorA.checkAutothermicPoint();
        reactorC.checkAutothermicPoint();

        reactorC.flowTransfer(reactorA, 200.0);
        reactorA.flowTransfer(reactorB, 150.0);

        System.out.println(reactorA);
        System.out.println(reactorB);

        reactorC.adjustTemperature(660.0);
        reactorC.checkAutothermicPoint();

    }

}