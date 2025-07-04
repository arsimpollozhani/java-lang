package FifthProgram;

public class Main {

    public static int findMostExpensiveNationalLibrary (Library [] libraries) {
        int idx = -1;
        NationalLibrary expensive = null;

        for (int i = 0; i < libraries.length; i++) {
            if (libraries[i] instanceof NationalLibrary) {
                NationalLibrary current = (NationalLibrary) libraries[i];
                if (expensive == null || expensive.calculateMembershipCardCost() < current.calculateMembershipCardCost() ) {
                    expensive = current;
                    idx = i;
                } else if (expensive.calculateMembershipCardCost() == current.calculateMembershipCardCost()) {
                    if (current.getWeekends()) {
                        expensive = current;
                        idx = i;
                    }
                }
            }
        }

        return idx;
    }

    //TESTER
    public static void main(String[] args) {
        Library[] libraries = new Library[5];

        libraries[0] = new AcademicLibrary("Tech University Library", "Skopje", 10.0f, true, true, 5);
        libraries[1] = new NationalLibrary("State Library", "Tirana", 12.0f, false, true, 4);
        libraries[2] = new NationalLibrary("Republic Library", "Dublin", 11.0f, true, false, 5);
        libraries[3] = new NationalLibrary("Historic Archives", "Riga", 13.0f, false, true, 3);
        libraries[4] = new AcademicLibrary("Engineering Library", "Stockholm", 9.5f, true, false, 7);

        int index = findMostExpensiveNationalLibrary(libraries);

        if (index != -1) {
            System.out.println("Most expensive National Library is at index: " + index);
            libraries[index].printDetail();
        } else {
            System.out.println("No national libraries found.");
        }
    }
}
