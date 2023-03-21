package exercise;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Exercise_1_11 {
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

    public static void shellSort(Comparable[] a) {
        int len = a.length;
        int[] arr = getIncreaseSeq(len);
        int stepNum = arr.length;


        while (stepNum > 0) {
            int step = arr[stepNum - 1];
            for (int i = step; i < len; i++) {
                for (int j = i; j >= step && a[j].compareTo(a[j - step]) < 0; j -= step) {
                    Comparable temp = a[j - step];
                    a[j - step] = a[j];
                    a[j] = temp;
                }
            }
            stepNum--;
        }
    }

    public static void main(String[] args) {
        Double[] a = new Double[10000];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniformDouble(0,1000);
        }
        Stopwatch timer = new Stopwatch();
        shellSort(a);
        double time = timer.elapsedTime();
        System.out.println("Time: " + time);
        for (int i = 0; i < a.length; i++) {
            if (i % 5 == 0) {
                System.out.println();
            }
            System.out.print(a[i] + "\t");
        }
    }

}
