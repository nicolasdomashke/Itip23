import java.util.*;

import static java.lang.Math.floorDivExact;
import static java.lang.Math.min;

public class Main {

    public static String nonRepeatable(String s) {
        StringBuilder sb = new StringBuilder(s);
        if (sb.isEmpty()) {
            return "";
        }
        String x = sb.substring(0, 1);
        while (sb.substring(0).contains(x)) {
            sb.deleteCharAt(sb.indexOf(x));
        }
        return x + nonRepeatable(sb.substring(0));
    }
    public static String[] generateBrackets(int n) {
        if(n == 1) return new String[] {"()"};
        String[] s0 = generateBrackets(n - 1);
        List<String> res = new ArrayList<String>();
        for (String s : s0) {
            for (int i = 0; i < s.length(); i++) {
                StringBuilder sb = new StringBuilder(s);
                sb.insert(i, "(");
                for (int j = 1 + i; j < s.length() + 1; j++) {
                    StringBuilder sbnew = new StringBuilder(sb.substring(0, sb.length()));
                    sbnew.insert(j, ")");
                    if (!res.contains(sbnew.toString())) {
                        res.add(sbnew.toString());
                    }
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }
    public static String[] binarySystem(int n) {
        if (n == 1) return new String[] {"0", "1"};
        String[] s0 = binarySystem(n - 1);
        List<String> res = new ArrayList<String>();
        for (String num : new String[] {"0", "1"}) {
            for (String s : s0) {
                for (int i = 0; i <= s.length(); i++) {
                    StringBuilder sb = new StringBuilder(s);
                    sb.insert(i, num);
                    if (sb.indexOf("00") == -1 && !res.contains(sb.toString())) {
                        res.add(sb.toString());
                    }
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }

    public static String alphabeticRow(String s) {
        char x0 = s.charAt(0);
        String res = String.valueOf(x0);
        StringBuilder sb = new StringBuilder(String.valueOf(x0));
        int t = 0;
        char x;
        for (int i = 1; i < s.length(); i++) {
            x = s.charAt(i);
            if (t == 0) {
                if ((int) x - x0 == 1 || (int) x - x0 == -1) {
                    t = (int) x - x0;
                    sb.append(x);
                }
            }
            else {
                if ((int) x - x0 == t) {
                    sb.append(x);
                }
                else if ((int) x - x0 == -t) {
                    if (sb.length() > res.length()) {
                        res = sb.toString();
                    }
                    sb = new StringBuilder(String.valueOf(x0 + x));
                    t = -t;
                }
                else {
                    if (sb.length() > res.length()) {
                        res = sb.toString();
                    }
                    sb = new StringBuilder(String.valueOf(x));
                    t = 0;
                }
            }
            x0 = x;
        }
        if (sb.length() > res.length()) {
            res = sb.toString();
        }
        return res;
    }
    public static String function(String s) {
        List<String> res = new ArrayList<String>();
        int k = 1;
        char x0 = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            char x = s.charAt(i);
            if (x0 == x) {
                k++;
            }
            else {
                res.add(String.valueOf(k) + x0);
                k = 1;
            }
            x0 = s.charAt(i);
        }
        res.add(String.valueOf(k) + x0);
        String[] r = res.toArray(new String[res.size()]);
        Arrays.sort(r);
        StringBuilder sb = new StringBuilder();
        for (String i : r) {
            sb.append(i.charAt(1)).append(i.charAt(0));
        }
        return sb.toString();
    }

    public static int convertToNum(String s) {
        if (s == "one thousand") return 1000;
        StringBuilder sb = new StringBuilder(s);
        String[] construct0 = new String[] {"twen", "thir", "four", "fif", "six", "seven", "eight", "nine"};
        String[] construct1 = new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"};
        String[] first = new String[19];
        System.arraycopy(construct1, 0, first, 0, 12);
        for (int i = 12; i < 19; i++) first[i] = construct0[i - 11] + "teen";
        String[] second = new String[8];
        for (int i = 0; i < 8; i++) second[i] = construct0[i] + "ty";
        String[] third = new String[9];
        for (int i = 0; i < 9; i++) third[i] = construct1[i] + " hundred";
        int res = 0;
        for (int i = 0; i < 9; i++) {
            int j = sb.indexOf(third[i]);
            if (j != -1) {
                res += (i + 1) * 100;
                sb.delete(j, j + third[i].length());
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            int j = sb.indexOf(second[i]);
            if (j != -1) {
                res += (i + 2) * 10;
                sb.delete(j, j + second[i].length());
                break;
            }
        }
        for (int i = 0; i < 19; i++) {
            int j = sb.indexOf(first[i]);
            if (j != -1) {
                res += i + 1;
                sb.delete(j, j + second[i].length());
                break;
            }
        }
        return res;
    }
    public static String uniqueSubstring(String s) {
        StringBuilder sb = new StringBuilder();
        String sMax = "";
        for (int i = 0; i < s.length(); i++) {
            String ch = String.valueOf(s.charAt(i));
            if (sb.indexOf(ch) == -1) {
                sb.append(ch);
            }
            else {
                if (sMax.length() < sb.length()) {
                    sMax = String.valueOf(sb);
                }
                sb.deleteCharAt(0);
                i--;
            }
        }
        return sMax;
    }

    public static int shortestWay(int[][] matrix) {
        int r = 0;
        int n = matrix[0].length;
        int[][] values = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    r = matrix[i][j];
                }
                else if(i == 0) {
                    r = matrix[i][j] + values[i][j - 1];
                }
                else if(j == 0) {
                    r = matrix[i][j] + values[i - 1][j];
                }
                else {
                    r = matrix[i][j] + min(values[i][j - 1], values[i - 1][j]);
                }
                values[i][j] = r;
            }
        }
        return values[n - 1][n - 1];
    }

    public static String numericOrder(String s) {
        String [] S = s.split(" ");
        StringBuilder sb = new StringBuilder();
        int r = S.length;
        for (int i = 1; i <= r; i++) {
            for (int j = 0; j < r; j++) {
                if(S[j].contains(String.valueOf(i))) {
                    sb.append(" ").append(S[j]);
                    sb.replace(sb.indexOf(String.valueOf(i)),String.valueOf(i).length() + sb.indexOf(String.valueOf(i)),"");
                    break;
                }
            }
        }
        return sb.toString();
    }
    public static int switchNums(int a, int b) {
        StringBuilder res = new StringBuilder();
        String bs = String.valueOf(b);
        char [] as = String.valueOf(a).toCharArray();
        Arrays.sort(as);
        int j = as.length - 1;
        for (int i = 0; i < bs.length(); i++) {
            if (j >= 0 && bs.charAt(i) < as[j]) {
                res.append(as[j]);
                j--;
            }
            else {
                res.append(bs.charAt(i));
            }
        }
        return Integer.parseInt(res.toString());
    }
    public static void main(String[] args) {
        System.out.println(nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));
        System.out.println(Arrays.toString(generateBrackets(1)));
        System.out.println(Arrays.toString(generateBrackets(2)));
        System.out.println(Arrays.toString(generateBrackets(3)));
        System.out.println(Arrays.toString(generateBrackets(4)));
        System.out.println(Arrays.toString(binarySystem(3)));
        System.out.println(Arrays.toString(binarySystem(4)));
        System.out.println(alphabeticRow("abcdjuwx"));
        System.out.println(alphabeticRow("klmabzyxw"));
        System.out.println(function("aaabbcdd"));
        System.out.println(function("vvvvaajaaaaa"));
        System.out.println(convertToNum("eight"));
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(convertToNum("thirty one"));
        System.out.println(uniqueSubstring("123412324"));
        System.out.println(uniqueSubstring("111111111"));
        System.out.println(uniqueSubstring("77897898"));
        System.out.println(shortestWay(new int[][] {{1, 3, 1},{1, 5, 1},{4, 2, 1}}));
        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));
        System.out.println(switchNums(519, 723));
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259));
    }
}