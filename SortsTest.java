import org.junit.Test;
import static org.junit.Assert.*;

public class SortsTest {
    // =========================
    // Helpers para testing
    // =========================

    @Test
    /** Retorna true si el arreglo está en orden no-decreciente. */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1].compareTo(a[i]) > 0) return false;
        }
        return true;
    }

    @Test
    /** Hace una copia del arreglo. */
    public static Comparable[] copyOf(Comparable[] a) {
        Comparable[] out = new Comparable[a.length];
        System.arraycopy(a, 0, out, 0, a.length);
        return out;
    }

    @Test
    /** Retorna un arreglo Comparable[] a partir de int... (solo para tests). */
    public static Comparable[] fromInts(int... values) {
        Comparable[] a = new Comparable[values.length];
        for (int i = 0; i < values.length; i++) {
            a[i] = values[i]; // autobox a Integer (Comparable)
        }
        return a;
    }

}
