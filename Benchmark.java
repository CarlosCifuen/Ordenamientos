import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Benchmark {

    // Construye tamaños: start, start+step, ... end
    public static int[] buildSizes(int start, int end, int step) {
        if (start <= 0) throw new IllegalArgumentException("start debe ser > 0");
        if (end < start) throw new IllegalArgumentException("end debe ser >= start");
        if (step <= 0) throw new IllegalArgumentException("step debe ser > 0");

        int count = ((end - start) / step) + 1;
        int[] sizes = new int[count];

        int idx = 0;
        for (int n = start; n <= end; n += step) {
            sizes[idx++] = n;
        }
        return sizes;
    }

    // Interfaz funcional simple para pasar el sort como "función"
    public interface SortFn {
        void sort(Comparable[] a);
    }

    public static void runAllAlgorithms(Comparable[] base, int[] sizes, List<ResultRow> results) {
        runAlgorithm("Gnome", base, sizes, results, Sorts::gnomeSort);
        runAlgorithm("Merge", base, sizes, results, Sorts::mergeSort);
        runAlgorithm("Quick", base, sizes, results, Sorts::quickSort);
        runAlgorithm("Radix", base, sizes, results, Sorts::radixSort);
        runAlgorithm("Selection", base, sizes, results, Sorts::selectionSort);
    }

    private static void runAlgorithm(String name, Comparable[] base, int[] sizes, List<ResultRow> results, SortFn fn) {
        for (int n : sizes) {
            Comparable[] sample = copyPrefix(base, n);

            // 1) random (desordenado)
            long tRandom = timeSort(sample, fn);
            results.add(new ResultRow(name, n, "random", tRandom));

            // 2) sorted (ya ordenado)
            fn.sort(sample);                  // dejar ordenado
            long tSorted = timeSort(sample, fn); // volver a ordenar ya ordenado
            results.add(new ResultRow(name, n, "sorted", tSorted));
        }
    }

    private static Comparable[] copyPrefix(Comparable[] base, int n) {
        Comparable[] out = new Comparable[n];
        System.arraycopy(base, 0, out, 0, n);
        return out;
    }

    private static long timeSort(Comparable[] a, SortFn fn) {
        long start = System.nanoTime();
        fn.sort(a);
        long end = System.nanoTime();
        return end - start;
    }

    public static void writeResultsToCSV(String filename, List<ResultRow> rows) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filename))) {
            bw.write("algorithm,n,scenario,time_ns");
            bw.newLine();
            for (ResultRow r : rows) {
                bw.write(r.getAlgorithm() + "," + r.getN() + "," + r.getScenario() + "," + r.getTimeNs());
                bw.newLine();
            }
        }
    }
}
