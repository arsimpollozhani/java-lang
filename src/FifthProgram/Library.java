package FifthProgram;

abstract public class Library {
    protected String name;
    protected String city;
    protected float fee;
    protected boolean weekends;

    public Library(String name, String city, float fee, boolean weekends) {
        this.name = name;
        this.city = city;
        this.fee = fee;
        this.weekends = weekends;
    }

    abstract public void printDetail();
    abstract public float calculateMembershipCardCost ();

    boolean getWeekends() {
        return weekends;
    }
}

