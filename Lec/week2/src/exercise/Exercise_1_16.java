package exercise;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Exercise_1_16 {
    public static void main(String[] args) {
        Double[] a = new Double[10000];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniformDouble(0,1000);
        }
        Stopwatch timer = new Stopwatch();
        Exercise_1_11.shellSort(a);
        double time = timer.elapsedTime();
        if (check(a)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public static boolean check(Comparable[] a) {
        Map<Comparable, Integer> map = new HashMap<>();

        for (Comparable item : a) {
            map.put(item, (Integer) ((map.get(item) == null ? 0 : map.get(item)) + 1));
        }

        Exercise_1_11.shellSort(a);

        int len = a.length;
        for (int i = 0; i < len - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) {
                System.out.println(a[i] + " : " + a[i + 1]);
                int c = 1;
                return false;
            }
        }

        for (Comparable item : a) {
            if (map.containsKey(item)) {
                int count = map.get(item);
                count--;
                if (count == 0) {
                    map.remove(item);
                } else {
                    map.put(item, count);
                }
            } else {
                int c = 1;
                return false;
            }
        }

        System.out.println(map.size());
        return map.size() == 0;
    }
}
