import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArraySum calc1 = new ArraySum(arr);
        System.out.println(calc1.arraySum());

        int[][] matrix = {
                {48, 72, 15, 83},
                {27, 64, 91, 5},
                {10, 36, 53, 77},
                {94, 20, 42, 68}
        };
        MatrixMax calc2 = new MatrixMax(matrix);
        System.out.println(calc2.matrixMax());

        Product [] goods = new Product[12];
        goods[0] = new Product("apples", 77);
        goods[1] = new Product("bananas", 70);
        goods[2] = new Product("fruits", 60);
        goods[3] = new Product("vegetables", 50);
        goods[4] = new Product("keys", 44);
        goods[5] = new Product("tables", 40);
        goods[6] = new Product("chairs", 33);
        goods[7] = new Product("beds", 20);
        goods[8] = new Product("tea", 15);
        goods[9] = new Product("notepads", 13);
        goods[10] = new Product("boxes", 6);
        goods[11] = new Product("fox", 5);

        Storage storage1 = new Storage(goods);
        Storage storage2 = new Storage();
        Worker.assignStorages(storage1, storage2);
        Worker w1 = new Worker();
        Worker w2 = new Worker();
        Worker w3 = new Worker();
        Thread thread1 = new Thread(() -> w1.carry());
        Thread thread2 = new Thread(() -> w2.carry());
        Thread thread3 = new Thread(() -> w3.carry());
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        for (Product a : Worker.getStorages()[1].getGoods()) {
            System.out.println(a.getName());
        }
        System.out.println(Worker.getStorages()[0].getGoods().length);
        System.out.println(Worker.getStorages()[1].getGoods().length);
    }
}