public class ArraySum {
    private int[] arr;
    private int result;

    public ArraySum(int[] arg) {
        arr = arg;
        result = 0;
    }

    public int arraySum() throws InterruptedException {
        Thread thread1 = new Thread(() -> calculateSum(0, arr.length / 2));
        Thread thread2 = new Thread(() -> calculateSum(arr.length / 2, arr.length));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        return result;
    }

    private void calculateSum(int start, int end) {
        int localSum = 0;
        for (int i = start; i < end; i++) {
            localSum += arr[i];
        }
        synchronized (ArraySum.class) {
            result += localSum;
        }
    }
}
