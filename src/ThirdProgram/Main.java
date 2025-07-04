package ThirdProgram;


import java.util.Scanner;

class IntWrapper {
    int value;
    IntWrapper(int value) {
        this.value = value;
    }
}


public class Main {

    public static int findMax (int [] array, int n) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    public static int findMin (int [] array, int n) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        return min;
    }

    public static void fillArray (int [] array, int n) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
    }

    public static void swapWrapper (IntWrapper a, IntWrapper b) {
        int temp = a.value;
        a.value = b.value;
        b.value = temp;
    }

    public static void swap (int [] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void bubbleSort (int [] array, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    public static boolean linearSearch (int [] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearchF (int [] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {

            int middle = low + (high - low) / 2;

            if (array[middle] == target) {
                return true;
            }
            if (array[middle] > target) {
                high = middle - 1;
            }
            if (array[middle] < target) {
                low = middle + 1;
            }
        }
        return false;
    }

    public static boolean binarySearchRec (int [] array, int target) {
        return binarySearchRecHelper(array, target, 0, array.length-1);
    }

    //Java doesn't allow default parameters in methods
    public static boolean binarySearchRecHelper (int [] array, int target, int low, int high) {
        if (low > high) {
            return false;
        }

        int middle = low + (high - low) / 2;

        if (array[middle] == target) {
            return true;
        } else if (array[middle] > target) {
            return binarySearchRecHelper (array, target, low, middle - 1);
        } else {
            return binarySearchRecHelper (array, target, middle + 1, high);
        }
    }

    public static void printArray (int [] array, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int [] array = new int[n];

        fillArray(array, n);
        printArray(array, n);

        int max = findMax(array, n);
        int min = findMin(array, n);

        System.out.println("Max: " + max);
        System.out.println("Min: " + min);

        bubbleSort(array, n);

        printArray(array, n);

        if (linearSearch(array, 4)) {
            System.out.println("Found it");
        } else {
            System.out.println("Not found");
        }


        //must be sorted
        if (binarySearchF(array, 1)) {
            System.out.println("Found it");
        } else {
            System.out.println("Not found");
        }

        if (binarySearchRec(array, 1)) {
            System.out.println("Found it");
        } else {
            System.out.println("Not found");
        }
    }
}
