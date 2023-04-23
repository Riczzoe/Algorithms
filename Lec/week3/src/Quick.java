public class Quick extends SortUtil{
    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable mid = a[lo];

        int i = lo + 1;
        int j = hi;
        while (true) {
            while (i < hi && less(a[i], mid)) {
                i++;
            }

            while (j > lo && less(mid, a[j])) {
                j--;
            }

            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }
}
