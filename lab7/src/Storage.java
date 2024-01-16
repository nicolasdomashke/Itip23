import java.util.Objects;

public class Storage {
    private Product [] goods;
    public Storage(Product[] arg) {
        goods = arg;
    }
    public Storage() {
        goods = new Product[0];
    }

    public Product[] getGoods() {
        return goods;
    }

    public void setGoods(Product[] arg) {
        goods = arg;
    }
    public void addGoods(Product[] arg) {
        Product[] newArr = new Product[goods.length + arg.length];
        System.arraycopy(goods, 0, newArr, 0, goods.length);
        System.arraycopy(arg, 0, newArr, goods.length, arg.length);
        goods = newArr;
    }
    public void removeGoods(Product arg) {
        for (int i = 0; i < goods.length; i++) {
            if (Objects.equals(goods[i], arg)) {
                Product[] newArr = new Product[goods.length - 1];
                System.arraycopy(goods, 0, newArr, 0, i);
                System.arraycopy(goods, i + 1, newArr, i, newArr.length - i);
                goods = newArr;
            }
        }
    }
}