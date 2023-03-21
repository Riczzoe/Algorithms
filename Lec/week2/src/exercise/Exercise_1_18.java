package exercise;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise_1_18 {
    public static void main(String[] args) {
        int comp = 0;
        int change = 0;
        for (int i = 0; i < 1000; i++) {
            Integer[] x = new Integer[100];
            for (int j = 0; j < 100; j++) {
                x[j] = j;
            }
            StdRandom.shuffle(x);
            int[] res = shellSort(x);
            comp += res[0];
            change += res[1];
        }

        System.out.println("shuffle: comp times : " + comp * 1.0 / 1000 + "  change times: " + change * 1.0 / 1000);

        comp = 0;
        change = 0;
        for (int i = 0; i < 1000; i++) {
            Integer[] x = new Integer[100];
            for (int j = 0; j < 100; j++) {
                x[j] = j;
            }
            int[] res = shellSort(x);
            comp += res[0];
            change += res[1];
        }
        System.out.println("order: comp times : " + comp * 1.0 / 1000 + "  change times: " + change * 1.0 / 1000);

        comp = 0;
        change = 0;
        for (int i = 0; i < 1000; i++) {
            Integer[] x = new Integer[100];
            for (int j = 0; j < 100; j++) {
                x[j] = 99 - j;
            }
            int[] res = shellSort(x);
            comp += res[0];
            change += res[1];
        }
        System.out.println("reversed: comp times : " + comp * 1.0 / 1000 + "  change times: " + change * 1.0 / 1000);
    }

    public static int[] getIncreaseSeq(int N) {
        int h = 1;
        int i = 1;

        while (h < N / 3) {
            i++;
            h = 3 * h + 1;
        }

        int[] arr = new int[i];
        while (h > 0) {
            arr[i-- - 1] = h;
            h /= 3;
        }
        return arr;
    }

    public static int[] shellSort(Comparable[] a) {
        int len = a.length;
        int[] arr = getIncreaseSeq(len);
        int stepNum = arr.length;


        int count = 0;
        int change = 0;
        while (stepNum > 0) {
            int step = arr[stepNum - 1];
            for (int i = step; i < len; i++) {

                for (int j = i; j >= step; j -= step) {
                    count++;
                    if (a[j].compareTo(a[j - step]) < 0) {
                        Comparable temp = a[j - step];
                        a[j - step] = a[j];
                        a[j] = temp;
                        change++;
                    } else {
                        break;
                    }
                }
            }
            stepNum--;
        }

        int[] res = {count, change};
        return res;
    }
}
