import java.util.*;

// Asset class
class Asset {
    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class week3_4_problem4 {

    // -------- MERGE SORT (Stable, Ascending by returnRate) --------
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Stable merge (<= preserves order)
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // -------- QUICK SORT (Desc returnRate, tie -> low volatility) --------
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high); // move pivot to end

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Median-of-3 pivot selection
    public static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        double a = arr[low].returnRate;
        double b = arr[mid].returnRate;
        double c = arr[high].returnRate;

        if ((a > b && a < c) || (a < b && a > c)) return low;
        else if ((b > a && b < c) || (b < a && b > c)) return mid;
        else return high;
    }

    // Partition logic
    public static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate &&
                            arr[j].volatility < pivot.volatility)) {

                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 7),
                new Asset("GOOG", 15, 4)
        };

        // Clone arrays
        Asset[] mergeArr = assets.clone();
        Asset[] quickArr = assets.clone();

        // Merge Sort
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort (Asc): " + Arrays.toString(mergeArr));

        // Quick Sort
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick Sort (Desc): " + Arrays.toString(quickArr));
    }
}