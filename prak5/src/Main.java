import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.*;

public class Main {
    public static boolean sameLetterPattern(String s1, String s2) {
        s1 = s1.toUpperCase();
        s2 = s2.toUpperCase();
        StringBuilder sb1 = new StringBuilder(s1);
        StringBuilder sb2 = new StringBuilder(s2);
        int aInt = (int) 'a';
        for (int i = 0; i < s1.length(); i++) {
            char letter = s1.charAt(i);
            for (int j = i; j < s1.length(); j++) {
                if (sb1.charAt(j) == letter) {
                    sb1.replace(j,  j + 1, String.valueOf((char) aInt));
                }
            }
            aInt += 1;
        }
        aInt = (int) 'a';
        for (int i = 0; i < s2.length(); i++) {
            char letter = s2.charAt(i);
            for (int j = i; j < s2.length(); j++) {
                if (sb2.charAt(j) == letter) {
                    sb2.replace(j,  j + 1, String.valueOf((char) aInt));
                }
            }
            aInt += 1;
        }
        return sb1.compareTo(sb2) == 0;
    }

    public static String spiderVsFly(String s1, String s2) {
        int x1 = Integer.parseInt(String.valueOf(s1.charAt(1)));
        int x2 = Integer.parseInt(String.valueOf(s2.charAt(1)));
        int y1 = (int) s1.charAt(0) - (int) 'A';
        int y2 = (int) s2.charAt(0) - (int) 'A';
        int startX = min(x1, x2);
        int lengthY = min(abs(y1 - y2), 8 - abs(y1 - y2));
        double constant = sqrt(4 + 2 * sqrt(2));
        if ((double) lengthY > constant) {
            StringBuilder res = new StringBuilder(s1 + "-");
            for (int i = x1 - 1; i > 0; i--) {
                res.append(s1.charAt(0)).append(String.valueOf(i)).append("-");
            }
            res.append("A0-");
            for (int i = 1; i <= x2; i++) {
                res.append(s2.charAt(0)).append(String.valueOf(i)).append("-");
            }
            return res.substring(0, res.length() - 1);
        }
        else {
            StringBuilder res = new StringBuilder();
            for (int i = x1; i > startX; i--) {
                res.append(s1.charAt(0)).append(String.valueOf(i)).append("-");
            }
            String alfa = "ABCDEFGH";
            if (abs(y1 - y2) != lengthY) {
                if (y1 < y2) y1 += 8;
                else y2 += 8;
            }
            int inc = (y2 - y1) / abs(y1 - y2);
            for (int i = y1; i <= y2; i += inc) {
                res.append(alfa.charAt(i % 8)).append(startX).append("-");
            }
            for (int i = startX + 1; i <= x2; i++) {
                res.append(s2.charAt(0)).append(String.valueOf(i)).append("-");
            }
            return res.substring(0, res.length() - 1);
        }
    }

    public static int digitsCount(long n) {
        if (n / 10 == 0) {
            return 1;
        }
        else {
            return 1 + digitsCount(n / 10);
        }
    }

    public static int totalPoints(String [] arr, String s) {
        Hashtable<Character, Integer> sLetters = new Hashtable<Character, Integer>();
        int score = 0;
        for (int i = 0; i < s.length(); i++) {
            sLetters.computeIfPresent(s.charAt(i), (key, val) -> val + 1);
            sLetters.putIfAbsent(s.charAt(i), 1);
        }
        for (String word : arr) {
            Hashtable<Character, Integer> wordLetters = new Hashtable<Character, Integer>();
            boolean flag = true;
            for (int i = 0; i < word.length(); i++) {
                if (sLetters.get(word.charAt(i)) == null) {
                    flag = false;
                    break;
                }
                else {
                    wordLetters.computeIfPresent(word.charAt(i), (key, val) -> val + 1);
                    wordLetters.putIfAbsent(word.charAt(i), 1);
                    if (wordLetters.get(word.charAt(i)) > sLetters.get(word.charAt(i))) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                switch (word.length()) {
                    case 3 -> score += 1;
                    case 4 -> score += 2;
                    case 5 -> score += 3;
                    case 6 -> score += 54;
                }
            }
        }
        return score;
    }

    public static int [][] sumUp (int [] n) {
        List<int[]> res = new ArrayList<>();
        for (int i = 1; i < n.length; i++) {
            for (int j = 0; j < i; j++) {
                if (n[i] + n[j] == 8) {
                    int [] res1 = new int [] {n[i], n[j]};
                    res1 = Arrays.stream(res1).sorted().toArray();
                    res.add(res1);
                }
            }
        }
        return res.toArray(new int[res.size()][2]);
    }

    public static String takeDownAverage (String [] arr) {
        int k = arr.length;
        int s = 0;
        for (String word : arr) {
            word = word.substring(0, 2);
            s += Integer.parseInt(word);
        }
        double div = (double) s / k;
        int n = (int) ((k + 1) * (div - 5) - s);
        return String.valueOf(n) + "%";
    }

    public static String caesarCipher(String mode, String s, int n) {
        StringBuilder res = new StringBuilder(s);
        if (mode == "encode") {
            s = s.toLowerCase();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                    res.replace(i, i + 1, String.valueOf((char) (((int) s.charAt(i) - (int) 'a' + n) % 26 + (int) 'a')));
                }
            }
            return res.toString().toUpperCase();
        }
        else {
            s = s.toLowerCase();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                    res.replace(i, i + 1, String.valueOf((char) (((int) s.charAt(i) - (int) 'a' - n) % 26 + (int) 'a')));
                }
            }
            return res.toString();
        }
    }
    public static int setSetup (int n, int k) {
        if (k == 1) return n;
        else return n * setSetup(n - 1, k - 1);
    }

    public static String timeDifference(String city1, String date, String city2) {
        Hashtable<String, Double> map = new Hashtable<>();
        map.put("Los Angeles",  -8.0);
        map.put("New York", -5.0);
        map.put("Caracas", -4.5);
        map.put("Buenos Aires", -3.0);
        map.put("London", 0.0);
        map.put("Rome", 1.0);
        map.put("Moscow", 3.0);
        map.put("Tehran", 3.5);
        map.put("New Delhi", 5.5);
        map.put("Beijing", 8.0);
        map.put("Canberra", 10.0);
        double dif = map.get(city2) - map.get(city1);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
        LocalDateTime modifiedDateTime = dateTime.plusHours((long) dif);
        return modifiedDateTime.format(outputFormatter);
    }

    public static boolean isNew(int n) {
        String s = String.valueOf(n);
        Pattern pattern = Pattern.compile("\\d0*[1-9]*");
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) return false;
        int s0 = s.charAt(0);
        n %= (int) pow(10, (s.length() - 1));
        if (n == 0) return true;
        char [] chars = String.valueOf(n).toCharArray();
        if (chars.length == 0) return true;
        if (s0 > chars[0]) return false;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] > chars[i + 1]) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("GGGG", "ABCD"));

        System.out.println(spiderVsFly("H3", "E2"));
        System.out.println(spiderVsFly("A4", "B2"));
        System.out.println(spiderVsFly("A4", "C2"));

        System.out.println(digitsCount(4666));
        System.out.println(digitsCount(466066));
        System.out.println(digitsCount(0));
        System.out.println(digitsCount(9));
        System.out.println(digitsCount(1234567890123L));

        System.out.println(totalPoints(new String[] {"cat", "create", "sat"}, "caster"));
        System.out.println(totalPoints(new String[] {"trance", "recant"}, "recant"));
        System.out.println(totalPoints(new String[] {"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));

        System.out.println(Arrays.deepToString(sumUp(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.deepToString(sumUp(new int[]{1, 2, 3, 7, 9})));
        System.out.println(Arrays.deepToString(sumUp(new int[]{10, 9, 7, 2, 8})));
        System.out.println(Arrays.deepToString(sumUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7})));

        System.out.println(takeDownAverage(new String[] {"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(takeDownAverage(new String[] {"10%"}));
        System.out.println(takeDownAverage(new String[] {"53%", "79%"}));

        System.out.println(caesarCipher("encode", "hello world!", 3));
        System.out.println(caesarCipher("decode", "EPQSWX PEWX XEWO!", 4));

        System.out.println(setSetup(5, 3));
        System.out.println(setSetup(7, 3));

        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));

        System.out.println(isNew(3));
        System.out.println(isNew(30));
        System.out.println(isNew(0));
        System.out.println(isNew(321));
        System.out.println(isNew(123));
    }
}