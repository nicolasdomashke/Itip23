public class Main {
    public static void main(String[] args) {
        Fridge f = new Fridge("CH-5", "Apple", 60000, 181);
        Vacuum vc = new Vacuum("H-3", "Dyson", 30000, "robot", "apartment", .5f);
        Dishwasher dv = new Dishwasher("Who knows", "Samsung", 50000);
        System.out.println(Fridge.howManyFridges());
        Fridge f1 = new Fridge("CH-6", "Apple", 60000000, 182);
        System.out.println(Fridge.howManyFridges());
        vc.setWeight(.5f);
        System.out.println(vc.getWeight());
        vc.setWeight(.5);
        System.out.println(vc.getWeight());
        System.out.println(dv.getModel());
        System.out.println(dv.getInfo());
    }
}