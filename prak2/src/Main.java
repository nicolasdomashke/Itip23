import java.util.Arrays;
import java.util.Random;

public class Main {
    public static boolean duplicateChars(String s) {
        s = s.toLowerCase();
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alphabet[(int) s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] > 1) return true;
        }
        return false;
    }
    public static String getInitials(String s) {
        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) == ' ') {
                return "" + s.charAt(0) + s.charAt(i + 1);
            }
        }
        return "Err0r";
    }
    public static int differenceEvenOdd(int [] nums) {
        int a = 0;
        int b = 0;
        for (int i : nums) {
            if (i % 2 == 0) a += i;
            else b += i;
        }
        return Math.abs(a - b);
    }
    public static boolean equalToAvg(int [] nums) {
        float sum = 0;
        for (int i : nums) {
            sum += i;
        }
        sum /= nums.length;
        for (int i : nums) {
            if ((float) i == sum) return true;
        }
        return false;
    }
    public static int [] indexMult(int [] nums) {
        int [] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i] * i;
        }
        return res;
    }
    public static String reverse(String s) {
        String res = "";
        for (int i = 1; i <= s.length(); i++) {
            res += s.charAt(s.length() - i);
        }
        return res;
    }
    public static int Tribonacci(int n) {
        int a = 0;
        int b = 0;
        int c = 1;
        if (n < 3) return 0;
        for (int i = 3; i < n; i++) {
            c += a + b;
            int t = b;
            b = c - b - a;
            a = t;
        }
        return c;
    }
    public static String pseudoHash(int n) {
        Random r = new Random();
        String s = "";
        String alphabet = "1234567890abcdef";
        for (int i = 0; i < n; i++) {
            s += alphabet.charAt(r.nextInt(16));
        }
        return s;
    }
    public static String botHelper(String s) {
        s = s.toLowerCase();
        /*for (int i = 0; i < s.length() - 5; i++) {
            if (s.charAt(i) == 'h' && s.charAt(i + 1) == 'e' && s.charAt(i + 2) == 'l' && s.charAt(i + 3) == 'p') {
                return "Calling for a staff member";
            }
            //if (s.substring(i, i + 4).equals("help")) return "Calling for a staff member";
        }*/
        String [] S = s.split(" ");
        if (Arrays.asList(S).contains("help")) return "Calling for a staff member";
        return "Keep waiting";
    }
    public static boolean isAnagram(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int[] alphabet1 = new int[26];
        int[] alphabet2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            alphabet1[(int) s1.charAt(i) - 'a']++;
            alphabet2[(int) s2.charAt(i) - 'a']++;
        }
        return Arrays.equals(alphabet1, alphabet2);
    }
    public static void main(String[] args) {
        System.out.println(duplicateChars("Donald"));
        System.out.println(getInitials("Ronald Mcdonald"));
        System.out.println(differenceEvenOdd(new int[] {22, 50, 16, 63, 31, 55}));
        System.out.println(equalToAvg(new int[] {1, 2, 3, 4, 5}));
        System.out.println(Arrays.toString(indexMult(new int[] {0, 1, 2, 3, 4, 5})));
        System.out.println(reverse("whywhy"));
        System.out.println(Tribonacci(7));
        System.out.println(pseudoHash(20));
        System.out.println(botHelper("help her"));
        System.out.println(botHelper("she needs help"));
        System.out.println(botHelper("where the help come on"));
        System.out.println(botHelper("he helps her"));
        System.out.println(botHelper("she has been helped"));
        System.out.println(botHelper("he is nice helper"));
        System.out.println(isAnagram("meme", "emem"));
    }
}