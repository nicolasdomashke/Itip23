public class Product {
    private int weight;
    private String name;

    public Product(String arg1, int arg2) {
        name = arg1;
        weight = arg2;
    }

    public int getWeight() {
        return weight;
    }
    public String getName() {
        return name;
    }
    public void setWeight(int a) {
        weight = a;
    }

    public void setName(String a) {
        name = a;
    }
}