import java.util.*;

import static java.util.Comparator.*;

public class MatrixMax {
    private int[][] arr;
    private List<Integer> res;

    public MatrixMax(int [][] arg) {
        arr = arg;
        res = new ArrayList<>();
    }

    public int matrixMax() throws InterruptedException {
        Thread [] threads = new Thread[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> calculateMax(arr[finalI]));
        }
        for (int i = 0; i < arr.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < arr.length; i++) {
            threads[i].join();
        }
        return Collections.max(res);
    }

    private void calculateMax(int [] a) {
        int maxE = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > maxE) maxE = a[i];
        }
        res.add(maxE);
    }
}
