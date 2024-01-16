public class Vacuum extends Appliance{
    private String type;
    private String usageDestination;
    private float weight;
    public Vacuum(String model, String brand, int costs, String a, String b, float c) {
        super(model, brand, costs);
        type = a;
        usageDestination = b;
        weight = c;
    }
    public Vacuum(String model, String brand, int costs, String a, String b, double c) {
        this(model, brand, costs, a, b, (float) c);
    }
    public Vacuum(String model, String brand, int costs) {
        this(model, brand, costs, "manual", "apartment", 3);
    }
    public String getType() {
        return type;
    }
    public String  getUsageDest() {
        return usageDestination;
    }
    public double getWeight() {
        return weight;
    }
    public void setType(String a) {
        type = a;
    }
    public void  setUsageDest(String a) {
        usageDestination = a;
    }
    public void setWeight(double a) {
        weight = (float) a;
    }
    public void setWeight(float a) {
        weight = a;
    }

    public String getTypeInfo () {
        if (type == "manual") return "Manual type means that...";
        if(type == "robot") return "Robot type means that...";
        return "Unknown type";
    }
    @Override
    public String getInfo() {
        return "Vacuum cleaner is a device made for...";
    }
}