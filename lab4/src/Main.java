public class Main {
    public static int validateAge(int i) throws CustomAgeException {
        if (i >= 0 && i <= 120) return i;
        else throw new CustomAgeException("Age out of bounds");
    }
    public static void main(String[] args) throws CustomAgeException {
        try {
            System.out.println(validateAge(30));
            System.out.println(validateAge(-3));
        } catch (CustomAgeException e) {
            System.out.println("Age's kinda messed up!");
        }
        System.out.println(validateAge(-55));
    }
}