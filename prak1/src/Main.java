public class Main {
    //1
    public static float convert(int x) {
        return x * 3.785f;
    }
    //2
    public static int fitCalc(int min, int intens) {
        return min*intens;
    }
    //3
    public static int containers(int a, int b, int c) {
        return a * 20 + b * 50 + c * 100;
    }
    //4
    public static String triangleType(int x, int y, int z) {
        if (x + y <= z || x + z <= y || y + z <= x) return "not a triangle";
        else if (x == y && z == y) return "equilateral";
        else if (x == y || y == z || x == z) return "isosceles";
        else return "different-sided";
    }
    //5
    public static int ternaryEvaluation(int a, int b) {
        return a > b? a : b;
    }
    //6
    public static int howManyItems(int n, float w, float h) {
        return (int)(n / (2 * w * h));
    }
    //7
    public static int factorial(int x) {
        int res = 1;
        for (int i = 2; i <= x; i++) {
            res *= i;
        }
        return res;
    }
    //8
    public static int gcd(int a, int b) {
        for (int i = a < b ? a : b; i > 0; i--) {
            if (a % i == 0 && b % i == 0) return i;
        }
        return 1;
    }
    public static float ticketSaler(int n, int cost) {
        return n * cost * .72f;
    }

    public static int tables(int s, int t) {
        int delta_t = s / 2 + s % 2 - t;
        if (delta_t > 0) return delta_t;
        else return 0;
    }
    public static void main(String[] args) {
        System.out.println(convert(5));
        System.out.println(fitCalc(15, 1));
        System.out.println(containers(3, 4, 2));
        System.out.println(triangleType(5, 5, 5));
        System.out.println(ternaryEvaluation(8, 4));
        System.out.println(howManyItems(22, 1.4f, 2f));
        System.out.println(factorial(3));
        System.out.println(gcd(48, 18));
        System.out.println(ticketSaler(70, 1500));
        System.out.println(tables(5, 2));
    }
}