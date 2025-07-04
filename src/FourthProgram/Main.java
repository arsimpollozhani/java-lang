package FourthProgram;

abstract class Museum {
    protected String name;
    protected String city;
    protected float ticketPrice;
    protected int workingHours;

    public Museum(String name, String city, float ticketPrice, int workingHours) {
        this.name = name;
        this.city = city;
        this.ticketPrice = ticketPrice;
        this.workingHours = workingHours;
    }

    public abstract void printDetails ();
    public abstract float calculateTicketCost ();

    public int getWorkingHours() {
        return workingHours;
    }
}

class ScientificMuseum extends Museum {
    public boolean interactiveExhibits;
    public int numPresentations;

    public ScientificMuseum (String name, String city, float ticketPrice, int workingHours, boolean interactiveExhibits, int numPresentations) {
        super(name, city, ticketPrice, workingHours);
        this.interactiveExhibits = interactiveExhibits;
        this.numPresentations = numPresentations;
    }

    @Override
    public void printDetails () {
        System.out.println("Name: " + name + " - (Scientific)" + city + " " + numPresentations + " " + calculateTicketCost());
    }

    @Override
    public float calculateTicketCost () {
        float total = ticketPrice;
        if (interactiveExhibits) {
            total *= 1.12;
        }
        int extra = numPresentations * 6;
        return total + extra;
    }
}

class ArtMuseum extends Museum {
    private boolean amateurExhibitions;
    private int artworks;

    public ArtMuseum (String name, String city, float ticketPrice, int workingHours, boolean amateurExhibitions, int artworks) {
        super(name, city, ticketPrice, workingHours);
        this.amateurExhibitions = amateurExhibitions;
        this.artworks = artworks;
    }

    @Override
    public void printDetails () {
        System.out.println("Name: " + name + " - (Art)" + city + " " + amateurExhibitions + " " + calculateTicketCost());
    }

    @Override
    public float calculateTicketCost () {
        float total = ticketPrice;
        if (amateurExhibitions) {
            total *= 1.18;
        }
        int extra = artworks * 3;
        return total + extra;
    }
}

public class Main {
    public static int findCheapestScientificMuseum (Museum[] museums) {
        int idx = -1;
        ScientificMuseum cheapest = null;

        for (int i = 1; i < museums.length; i++) {
            //instaceof == dynamic_cast<museum[i]>(ScientificMuseum)
            if (museums[i] instanceof ScientificMuseum) {
                ScientificMuseum current = (ScientificMuseum) museums[i];
                if (cheapest == null || cheapest.calculateTicketCost() > current.calculateTicketCost()) {
                    cheapest = current;
                    idx = i;
                } else if (cheapest.calculateTicketCost() == current.calculateTicketCost()) {
                    if (cheapest.getWorkingHours() < current.getWorkingHours()) {
                        cheapest = current;
                        idx = i;
                    }
                }
            }
        }

        return idx;
    }


    public static void main (String[] args) {
        Museum[] museums = new Museum[5];

        museums[0] = new ScientificMuseum("Science Center A", "Skopje", 5.0f, 40, true, 2);
        museums[1] = new ArtMuseum("Art Gallery X", "Tirana", 6.5f, 35, false, 15);
        museums[2] = new ScientificMuseum("Science Lab B", "Ohrid", 5.0f, 42, false, 1);
        museums[3] = new ScientificMuseum("Tech World", "Zagreb", 4.5f, 38, true, 3);
        museums[4] = new ArtMuseum("Classic Arts", "Berlin", 4.0f, 30, true, 12);

        int index = findCheapestScientificMuseum(museums);

        if (index != -1) {
            System.out.println("Cheapest scientific museum is at index: " + index);
            museums[index].printDetails(); // You can implement this to show more details
        } else {
            System.out.println("No scientific museum found.");
        }
    }
}
