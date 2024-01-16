public class Fridge extends Appliance{
    private int volume;
    private boolean hasFreezer;
    private int compressors;
    private static int count = 0;

    public Fridge (String x, String y, int z, int a, boolean b,  int c) {
        super(x, y, z);
        volume = a;
        hasFreezer = b;
        compressors = c;
        count += 1;
    }
    public Fridge (String x, String y, int z, int a, boolean b) {
        this(x, y, z, a, b, 1);
    }
    public Fridge (String x, String y, int z, int a) {
        this(x, y, z, a, false, 1);
    }
    public int getVolume() {
        return volume;
    }
    public boolean getFreezer() {
        return hasFreezer;
    }
    public int getCompressors() {
        return compressors;
    }
    public void setVolume(int a) {
        volume = a;
    }
    public void setFreezer(boolean a) {
        hasFreezer = a;
    }
    public void setCompressors(int a) {
        compressors = a;
    }
    public static int howManyFridges() {
        return count;
    }

    public void extraCompressors(int a) {
        compressors += a;
    }
    @Override
    public String getInfo() {
        return "Fridge is a device made for...";
    }
}