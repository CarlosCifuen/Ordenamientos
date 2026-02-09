public class Sorts {

    // =========================
    // Selection Sort
    // =========================
    public static void selectionSort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j].compareTo(a[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            swap(a, i, minIdx);
        }
    }

    // =========================
    // Gnome Sort
    // =========================
    public static void gnomeSort(Comparable[] a) {
        int i = 1;
        int n = a.length;

        while (i < n) {
            if (i == 0 || a[i - 1].compareTo(a[i]) <= 0) {
                i++;
            } else {
                swap(a, i, i - 1);
                i--;
            }
        }
    }

    // =========================
    // Merge Sort
    // =========================
    public static void mergeSort(Comparable[] a) {
        if (a.length <= 1) return;
        Comparable[] temp = new Comparable[a.length];
        mergeSortRec(a, temp, 0, a.length - 1);
    }

    private static void mergeSortRec(Comparable[] a, Comparable[] temp, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSortRec(a, temp, left, mid);
        mergeSortRec(a, temp, mid + 1, right);
        merge(a, temp, left, mid, right);
    }

    private static void merge(Comparable[] a, Comparable[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = a[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                a[k++] = temp[i++];
            } else {
                a[k++] = temp[j++];
            }
        }

        while (i <= mid) a[k++] = temp[i++];
        while (j <= right) a[k++] = temp[j++];
    }

    // =========================
    //Quick Sort
    // =========================
    public static void quickSort(Comparable[] a) {
        quickSortRec(a, 0, a.length - 1);
    }

    private static void quickSortRec(Comparable[] a, int low, int high) {
        if (low >= high) return;
        int p = partition(a, low, high);
        quickSortRec(a, low, p - 1);
        quickSortRec(a, p + 1, high);
    }

    private static int partition(Comparable[] a, int low, int high) {
        Comparable pivot = a[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (a[j].compareTo(pivot) <= 0) {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, high);
        return i + 1;
    }

    // =========================
    // Radix Sort (LSD)
    // - Recibe Comparable[] (Integer)
    // - Requiere enteros >= 0
    // =========================
    public static void radixSort(Comparable[] a) {
        if (a.length == 0) return;

        // Validar y encontrar max
        int max = 0;
        for (Comparable c : a) {
            if (!(c instanceof Integer)) {
                throw new IllegalArgumentException("RadixSort requiere que todos los elementos sean Integer.");
            }
            int v = (Integer) c;
            if (v < 0) {
                throw new IllegalArgumentException("RadixSort (LSD) requiere enteros NO negativos.");
            }
            if (v > max) max = v;
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(a, exp);
        }
    }

    private static void countingSortByDigit(Comparable[] a, int exp) {
        int n = a.length;
        Comparable[] output = new Comparable[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            int v = (Integer) a[i];
            int digit = (v / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int v = (Integer) a[i];
            int digit = (v / exp) % 10;
            output[count[digit] - 1] = a[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, a, 0, n);
    }

    // =========================
    // Utilidad: swap
    // =========================
    private static void swap(Comparable[] a, int i, int j) {
        if (i == j) return;
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
