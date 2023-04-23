public class Selection extends SortUtil {

    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            int low = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[low])) {
                    low = j;
                }
            }
            exch(a, i, low);
        }
    }

}
