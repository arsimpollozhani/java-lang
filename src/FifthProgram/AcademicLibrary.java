package FifthProgram;

public class AcademicLibrary extends Library {
    private boolean researchOpportunity;
    private int numJournals;

    public AcademicLibrary(String name, String city, float fee, boolean weekends, boolean researchOpportunity, int numJournals) {
        super (name, city, fee, weekends);
        this.researchOpportunity = researchOpportunity;
        this.numJournals = numJournals;
    }

    @Override
    public void printDetail() {
        System.out.println(name + " - (Academic) " + city + " " + numJournals + " " + calculateMembershipCardCost());
    }

    @Override
    public float calculateMembershipCardCost () {
        float total = fee;
        if (researchOpportunity) {
            total *= 1.24;
        }
        int extra = numJournals * 6;
        return total + extra;
    }
}
