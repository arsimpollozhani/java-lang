import java.util.ArrayList;

abstract class AlcoholDrink {
    protected String name;
    protected String country;
    protected float alcoholPercent;
    protected int price;
    protected static int DISCOUNT = 5;

    public AlcoholDrink(String name, String country, float alcoholPercent, int price) {
        this.name = name;
        this.country = country;
        this.alcoholPercent = alcoholPercent;
        this.price = price;
    }

    public abstract float computePrice();

    public void printDrink() {
        System.out.println(name + " " + country + " " + alcoholPercent + " " + computePrice());
    }

    public boolean compareDrinks(AlcoholDrink other) {
        return this.computePrice() < other.computePrice();
    }

    public static void changeDiscount(int NEW_DISCOUNT) {
        DISCOUNT = NEW_DISCOUNT;
    }

    public static void total(AlcoholDrink[] drinks) {
        float total = 0;
        for (AlcoholDrink drink : drinks) {
            total += drink.computePrice();
        }
        System.out.println("Total price: " + total);
        System.out.println("Total with discount: " + (total * (100 - DISCOUNT) / 100.0));
    }

}

class Beer extends AlcoholDrink {

    private boolean ingredient;

    //java does not support default values in constructors parameters
    public Beer(String name, String country, float alcoholPercent, int price, boolean ingredient) {
        super(name, country, alcoholPercent, price);
        this.ingredient = ingredient;
    }

    @Override
    public float computePrice() {
        float basePrice = price;
        if (country.equals("Germany")) {
            basePrice *= 1.05f;
        }
        if (!ingredient) {
            basePrice *= 1.10f;
        }
        return basePrice;
    }
}

class Wine extends AlcoholDrink {
    private int year;
    private String grapes;

    public Wine(String name, String country, float alcoholPercent, int price, int year, String grapes) {
        super(name, country, alcoholPercent, price);
        this.year = year;
        this.grapes = grapes;
    }

    @Override
    public float computePrice() {
        float basePrice = price;
        if (country.equals("Italy")) {
            basePrice *= 1.05f;
        }

        if (year < 2005) {
            basePrice *= 1.15f;
        }

        return basePrice;
    }
}


public class Task1 {
    public static void lowestPrice (ArrayList<AlcoholDrink> drinks) {

        AlcoholDrink min = drinks.get(0);

        for (AlcoholDrink drink : drinks) {
            if (drink.computePrice() < min.computePrice()) {
                min = drink;
            }
        }

        System.out.println("Lowest price: ");
        min.printDrink();
    }


    public static void main(String[] args) {
        ArrayList<AlcoholDrink> alcoholDrinks = new ArrayList<>();

        Beer beer1 = new Beer("Paulaner", "Germany", 4.5f, 5, true);
        Beer beer2 = new Beer("Carlsberg", "Denmark", 4.0f, 4, false);
        Wine wine1 = new Wine("Alexandria", "Macedonia", 15.0f, 450, 2001, "red grapes");

        alcoholDrinks.add(beer1);
        alcoholDrinks.add(beer2);
        alcoholDrinks.add(wine1);

        for (int i = 0; i < alcoholDrinks.size(); i++) {
            alcoholDrinks.get(i).printDrink();
        }

        lowestPrice(alcoholDrinks);
    }
}
