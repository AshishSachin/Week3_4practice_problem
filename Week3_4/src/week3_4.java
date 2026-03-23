import java.util.*;

public class week3_4_problem6 {

    // -------- LINEAR SEARCH (unsorted) --------
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear: Found at index " + i + ", comparisons=" + comparisons);
                return i;
            }
        }

        System.out.println("Linear: Not found, comparisons=" + comparisons);
        return -1;
    }

    // -------- BINARY SEARCH (Insertion Point / Lower Bound) --------
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("Lower Bound Index (insertion point): " + low +
                ", comparisons=" + comparisons);
        return low;
    }

    // -------- FLOOR (largest <= target) --------
    public static Integer floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) return arr[mid];

            if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // -------- CEILING (smallest >= target) --------
    public static Integer ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) return arr[mid];

            if (arr[mid] > target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};
        int target = 30;

        // Linear Search (unsorted case)
        linearSearch(risks, target);

        // Binary Search operations (sorted array)
        int index = lowerBound(risks, target);

        Integer f = floor(risks, target);
        Integer c = ceiling(risks, target);

        System.out.println("Floor(" + target + "): " + f);
        System.out.println("Ceiling(" + target + "): " + c);
    }
}