public class Shell extends SortUtil {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * N + 1;
        }

        while (h >= 1) {
            for (int i = h; i < N; i += h) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j = h);
                }
            }
            h /= 3;
        }
    }
}
