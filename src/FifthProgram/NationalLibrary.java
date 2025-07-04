package FifthProgram;

public class NationalLibrary extends Library {
    private boolean educationalPrograms;
    private int numOriginalScripts;

    public NationalLibrary(String name, String city, float fee, boolean weekends, boolean educationalPrograms, int numOriginalScripts) {
        super (name, city, fee, weekends);
        this.educationalPrograms = educationalPrograms;
        this.numOriginalScripts = numOriginalScripts;
    }

    @Override
    public void printDetail() {
        System.out.println(name + " - (National) " + city + " " + numOriginalScripts + " " + calculateMembershipCardCost());
    }

    @Override
    public float calculateMembershipCardCost () {
        float total = fee;
        if (educationalPrograms) {
            total *= 0.93f;
        }
        int extra = numOriginalScripts * 15;
        return total + extra;
    }
}
