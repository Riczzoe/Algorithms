package Exercise.c2s4;

import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;
    private int N;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexMinPQ(int maxN) {
        if (maxN < 0) {
            throw new IllegalArgumentException();
        }
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        N = 0;
        this.maxN = maxN;

        for (int i = 0; i < maxN + 1; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int k) {
        validateIndex(k);
        return qp[k] != -1;
    }

    public void insert(int k, Key key) {
        validateIndex(k);
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = key;
        swim(N);
    }

    public void change(int k, Key key) {
        validateIndex(k);
        if (!contains(k)) {
            throw new NoSuchElementException("Index is not in the priority queue");
        }

        keys[k] = key;
        swim(pq[k]);
        sink(pq[k]);
    }

    public void delete(int k) {
        validateIndex(k);
        if (!contains(k)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }

        exch(k, N--);
        swim(k);
        sink(k);
        keys[k] = null;
        qp[k] = -1;
    }

    public Key min() {
        if (N == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return keys[pq[1]];
    }

    public int delMin() {
        if (N == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        int indexOfMin = pq[1];
        exch(1, N--);
        sink(1);
        keys[indexOfMin] = null;
        qp[indexOfMin] = -1;
        return indexOfMin;
    }

    private void exch(int i, int j) {
        qp[pq[i]] = j;
        qp[pq[j]] = i;

        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= N) {
            int j = i;
            if (less(j, j + 1)) {
                j++;
            }
            if (less(i, j)) {
                exch(i, j);
                i = j;
            }
        }
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    // throw an IllegalArgumentException if i is an invalid index
    private void validateIndex(int i) {
        if (i < 0) throw new IllegalArgumentException("index is negative: " + i);
        if (i >= maxN) throw new IllegalArgumentException("index >= capacity: " + i);
    }
}

