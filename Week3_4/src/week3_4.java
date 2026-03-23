import java.util.*;

public class week3_4_problem5 {

    // -------- LINEAR SEARCH (First Occurrence) --------
    public static int linearFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First: index=" + i + ", comparisons=" + comparisons);
                return i;
            }
        }

        System.out.println("Linear First: Not found, comparisons=" + comparisons);
        return -1;
    }

    // -------- LINEAR SEARCH (Last Occurrence) --------
    public static int linearLast(String[] arr, String target) {
        int comparisons = 0;
        int lastIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                lastIndex = i;
            }
        }

        System.out.println("Linear Last: index=" + lastIndex + ", comparisons=" + comparisons);
        return lastIndex;
    }

    // -------- BINARY SEARCH (Any One Occurrence) --------
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary Search: index=" + mid + ", comparisons=" + comparisons);
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search: Not found, comparisons=" + comparisons);
        return -1;
    }

    // -------- COUNT OCCURRENCES (Binary Expansion) --------
    public static int countOccurrences(String[] arr, String target, int index) {
        if (index == -1) return 0;

        int count = 1;

        // Left side
        int i = index - 1;
        while (i >= 0 && arr[i].equals(target)) {
            count++;
            i--;
        }

        // Right side
        i = index + 1;
        while (i < arr.length && arr[i].equals(target)) {
            count++;
            i++;
        }

        return count;
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // Linear search on unsorted data
        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        // Sort for binary search
        Arrays.sort(logs);
        System.out.println("\nSorted Logs: " + Arrays.toString(logs));

        // Binary search
        int index = binarySearch(logs, "accB");

        // Count duplicates
        int count = countOccurrences(logs, "accB", index);
        System.out.println("Count of accB: " + count);
    }
}