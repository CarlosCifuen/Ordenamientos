import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileManager {

    // Genera "count" enteros aleatorios en [min, max] y los guarda, 1 por línea.
    public static void generateRandomIntegersFile(String filename, int count, int min, int max) throws IOException {
        if (count < 0) throw new IllegalArgumentException("count no puede ser negativo.");
        if (max < min) throw new IllegalArgumentException("max no puede ser menor que min.");

        Random r = new Random();
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filename))) {
            for (int i = 0; i < count; i++) {
                int value = r.nextInt(max - min + 1) + min;
                bw.write(Integer.toString(value));
                bw.newLine();
            }
        }
    }

    // Lee enteros del archivo (1 por línea) y los retorna como Comparable[] (Integer implementa Comparable)
    public static Comparable[] readIntegersAsComparableArray(String filename) throws IOException {
        List<Comparable> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    list.add(Integer.parseInt(line));
                }
            }
        }

        return list.toArray(new Comparable[0]);
    }
}
