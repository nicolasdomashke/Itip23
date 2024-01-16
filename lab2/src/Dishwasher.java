public class Dishwasher extends Appliance{
    private int dishSets;
    private int programs;
    private boolean looksCute;

    public Dishwasher(String model, String brand, int costs, int a, int b, boolean c) {
        super(model, brand, costs);
        dishSets = a;
        programs = b;
        looksCute = c;
    }
    public Dishwasher(String model, String brand, int costs) {
        this(model, brand, costs, 1, 1, true);
    }
    public int getDishSets() {
        return dishSets;
    }
    public int getPrograms() {
        return programs;
    }
    public boolean isCute() {
        return looksCute;
    }
    public void setDishSets(int a) {
        dishSets = a;
    }
    public void setPrograms(int a) {
        programs = a;
    }
    public void setLooksCute(boolean a) {
        looksCute = a;
    }
    public String getModel() {
        return "Best model eva";
    }

    public void redesign() {
        looksCute = !looksCute;
    }

    @Override
    public String getInfo() {
        return "Dishwasher is a device made for...";
    }
}