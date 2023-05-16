import java.util.ArrayList;

public class Merge extends SortUtil {
    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        System.out.println("merge(" + lo + ", " + mid + ", " + hi + ")");
        int i = lo, j = mid + 1;
        // System.out.println(hi - lo + 1);

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
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

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void bottomToTopSort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];

        for (int size = 1; size < N; size += size) {
            for (int i = 0; i < N - size; i += size + size) {
                merge(a, i, i + size - 1, Math.min(i + size + size - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        Character[] a = new Character[] {
                'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E',
                'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E',
                'M', 'E', 'R', 'G', 'E', 'S', 'O'
        };
        System.out.println("Before Sort:");
        show(a);
        // sort(a);
        bottomToTopSort(a);
        System.out.println("After SOrt:");
        show(a);
    }
}
