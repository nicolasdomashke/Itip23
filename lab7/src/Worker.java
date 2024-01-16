import javax.print.attribute.standard.OrientationRequested;
import java.util.ArrayList;
import java.util.List;

public class Worker {
    private static List<Product> lst = new ArrayList<>();
    private static int totalWeight = 0;
    private static Storage storage1;
    private static Storage storage2;
    private static boolean isLowerThan150 = true;
    public Worker() {}

    public void carry() {
        synchronized (Worker.class) {
            while (storage1.getGoods().length != 0) {
                Product[] goods = storage1.getGoods();
                if (goods[0].getWeight() + totalWeight <= 150) {
                    Product taken = goods[0];
                    storage1.removeGoods(taken);
                    totalWeight += taken.getWeight();
                    lst.add(taken);
                }
                else {
                    storage2.addGoods(lst.toArray(new Product[lst.size()]));
                    lst = new ArrayList<>();
                    totalWeight = 0;
                }
            }
            storage2.addGoods(lst.toArray(new Product[lst.size()]));
            lst = new ArrayList<>();
        }
    }

    public static void assignStorages(Storage s1, Storage s2) {
        storage1 = s1;
        storage2 = s2;
    }
    public static Storage[] getStorages() {
        return new Storage[] {storage1, storage2};
    }
}