public class Palindrome {
    public static String reverseString(String s) {
        String res = "";
        for (int i = 1; i <= s.length(); i++) {
            res += s.charAt(s.length() - i);
        }
        return res;
    }
    public static boolean isPalindrome(String s) {
        return s.equals(reverseString(s));
    }
    public static void main(String[] args) {
        for (String s : args) {
            System.out.println(isPalindrome(s));
        }
    }
}
