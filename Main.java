import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // --- Configuración ---
        String dataFile = "numeros.txt";
        int totalNumeros = 3000;     // max 3000
        int min = 0;                // para Radix
        int max = 100000;

        int startN = 10;
        int endN = 3000;
        int step = 10;              // 10,20,30,...,3000 (ajusta si quieres)

        String outCsv = "resultados_tiempos.csv";

        try {
            // 1) Generar archivo
            FileManager.generateRandomIntegersFile(dataFile, totalNumeros, min, max);

            // 2) Leer archivo a Comparable[]
            Comparable[] base = FileManager.readIntegersAsComparableArray(dataFile);
            if (base.length == 0) {
                System.out.println("El archivo no tiene datos.");
                return;
            }

            // 3) Construir tamaños
            int[] sizes = Benchmark.buildSizes(startN, Math.min(endN, base.length), step);

            // 4) Ejecutar benchmarks
            List<ResultRow> results = new ArrayList<>();
            Benchmark.runAllAlgorithms(base, sizes, results);

            // 5) Exportar CSV
            Benchmark.writeResultsToCSV(outCsv, results);

            System.out.println("✅ Listo.");
            System.out.println("Archivo de datos: " + dataFile);
            System.out.println("CSV de resultados: " + outCsv);

        } catch (IOException e) {
            System.out.println("Error de IO: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error de datos: " + e.getMessage());
        }
    }
}
