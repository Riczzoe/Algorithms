public class Merge2 extends SortUtil {
    public static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        System.out.println("merge(a, " + lo + ", " + mid + ", " + hi + ")");
        int i = lo; int j = mid + 1;
        if (hi + 1 - lo >= 0) {
            System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = (lo + hi) / 2;

        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        sort(a, 0, N -1);
    }

    public static void bottomToTopSort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];

        for (int size = 2; size <= N; size *= 2) {
            for (int index = 0; index <= N - size; index += size) {
                int hi = index  + size - 1;
                hi = hi < N ? hi : N - 1;
                merge(a, index, (index + hi) / 2, hi);
            }
        }
        show(a);
        merge(a, 0, 7, 15);
    }

    public static void main(String[] args) {
        Character[] a = new Character[] {
                'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'
        };
        System.out.println("Before Sort:");
        show(a);
        // sort(a);
        bottomToTopSort(a);
        System.out.println("After Sort:");
        show(a);
    }
}
