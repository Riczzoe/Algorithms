public class Quick3Way extends SortUtil {
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return ;
        }

        int lt = lo, i = lo  + 1, gt = hi;
        Comparable v = a[lo];

        while (i < gt) {
            int cmp = v.compareTo(a[i]);
            if (cmp > 0) {
                exch(a, lt++, i++);
            } else if (cmp < 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}
