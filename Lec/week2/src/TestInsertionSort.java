public class TestInsertionSort {
    public static void main(String[] args) {
        Integer[] a = {2, 4, 5 ,99, 109, 298, 2, 1, 37827, 29, 2378};
        InsertionSort.sort(a);
        for (Integer num : a) {
            System.out.println(num);
        }
    }
}
