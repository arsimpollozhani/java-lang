package SeventhProgram;
import java.util.Scanner;
import java.util.ArrayList;

class InvalidSerialNumberException extends Exception {
    private String serial;
    public InvalidSerialNumberException(String serial) {
        this.serial = serial;
    }

    public void msg () {
        System.out.println("The serial number " + serial + " is invalid machine serial number format.");
    }
}

abstract class Machine {
    protected String model;
    protected int powerConsumtion;
    protected float price;
    protected String serial;

    public Machine(String model, int powerConsumtion, String serial) throws InvalidSerialNumberException {

        if (serial.length() != 12 || serial.contains(" ")) {
            throw new InvalidSerialNumberException(serial);
        }

        this.model = model;
        this.powerConsumtion = powerConsumtion;
        this.price = 0;
        this.serial = serial;
    }

    public abstract void showDetails();
    public abstract float calculateCost ();
}



class WashingMachine extends Machine {
    private int capacity;

    public WashingMachine (String model, int powerConsumtion, String serial, int capacity) throws InvalidSerialNumberException {
        super(model, powerConsumtion, serial);
        this.capacity = capacity;
        price = 500;
    }

    @Override
    public void showDetails() {

    }

    @Override
    public float calculateCost () {
        price = 500;
        if (capacity > 8) {
            price *= 1.12f;
        }
        int increments = powerConsumtion / 100;
        price += increments * 20;
        return price;
    }
}



class DryingMachine extends Machine {

    public boolean sensor;

    public DryingMachine (String model, int powerConsumtion, String serial, boolean sensor) throws InvalidSerialNumberException {
        super(model, powerConsumtion, serial);
        this.sensor = sensor;
        price = 600;
    }

    @Override
    public void showDetails() {

    }

    @Override
    public float calculateCost () {
        price = 600;
        if (sensor) {
            price *= 1.15f;
        }
        int additional = powerConsumtion / 100;
        price += additional * 25;
        return price;
    }
}




//TESTING
public class Machines {


    public static void calculatePercentageOfCost(ArrayList<Machine> machines) {
        float costWashing = 0;
        float costDrying = 0;

        for (Machine machine : machines) {
            float cost = machine.calculateCost();
            if (machine instanceof WashingMachine) {
                costWashing += cost;
            } else {
                costDrying += cost;
            }
        }

        float totalCost = costWashing + costDrying;
        float percentWashingMachineCost = (costWashing / totalCost) * 100;
        float percentDryingMachineCost = (costDrying / totalCost) * 100;

        System.out.printf("Total cost of washing machines: %.2f$ which is %.2f%% of the total cost of machines\n", costWashing, percentWashingMachineCost);
        System.out.printf("Total cost of drying machines: %.2f$ which is %.2f%% of the total cost of machines\n", costDrying, percentDryingMachineCost);
    }




    public static ArrayList<Machine> createMachines(Scanner scanner, int[] n) {
        ArrayList<Machine> machines = new ArrayList<>();
        for (int i = 0; i < n[0]; i++) {
            int t = scanner.nextInt();
            String model = scanner.next();
            int powerConsumption = scanner.nextInt();
            String serialNumber = scanner.next();

            try {
                if (t == 1) {
                    int value = scanner.nextInt();
                    machines.add(new WashingMachine(model, powerConsumption, serialNumber, value));
                } else {
                    boolean sensor = scanner.nextBoolean();
                    machines.add(new DryingMachine(model, powerConsumption, serialNumber, sensor));
                }
            } catch (InvalidSerialNumberException e) {
                e.msg();
                n[0]--;
                i--;
            }
        }
        return machines;
    }

    public static void cleanUp(ArrayList<Machine> machines) {
        machines.clear(); // No need for manual memory management in Java
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();
        int[] n = new int[1];
        switch (testCase) {
            case 1:
                System.out.println("TESTING ABSTRACT CLASS AND CHILD CLASSES");
                n[0] = scanner.nextInt();
                ArrayList<Machine> machines1 = createMachines(scanner, n);
                for (Machine m : machines1) m.showDetails();
                cleanUp(machines1);
                System.out.println("ABSTRACT CLASS AND CHILD CLASSES OK");
                break;
            case 2:
                System.out.println("TEST EXCEPTION HANDLING");
                n[0] = scanner.nextInt();
                ArrayList<Machine> machines2 = createMachines(scanner, n);
                for (Machine m : machines2) m.showDetails();
                cleanUp(machines2);
                System.out.println("EXCEPTION HANDLING OK");
                break;
            default:
                System.out.println("INTEGRATION TEST AND TESTING GLOBAL FUNCTION");
                n[0] = scanner.nextInt();
                ArrayList<Machine> machines3 = createMachines(scanner, n);
                for (Machine m : machines3) m.showDetails();
                calculatePercentageOfCost(machines3);
                cleanUp(machines3);
                System.out.println("INTEGRATION TEST AND TESTING GLOBAL FUNCTION OK");
                break;
        }
    }
}
