public abstract class Appliance {
    private String modelName;
    private String brandName;
    private int price;
    public Appliance (String model, String brand, int costs) {
        modelName = model;
        brandName = brand;
        price = costs;
    }
    public String getModel() {
        return modelName;
    }
    public String getBrand() {
        return brandName;
    }
    public int getPrice() {
        return price;
    }
    public void setModel(String model) {
        modelName = model;
    }
    public void setBrand(String brand) {
        modelName = brand;
    }
    public void setPrice(int costs) {
        price = costs;
    }

    public String fullTitle() {
        return modelName + " by " +  brandName;
    }

    public void hyperInflation() {
        price *= 2;
    }
    public abstract String getInfo();
}