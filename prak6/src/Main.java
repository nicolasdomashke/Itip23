import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;
import java.util.Stack;

import static java.lang.Math.*;

public class Main {
    private static Hashtable<Character, Integer> countLetters(String s) {
        Hashtable<Character, Integer> sLetters = new Hashtable<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            sLetters.computeIfPresent(s.charAt(i), (key, val) -> val + 1);
            sLetters.putIfAbsent(s.charAt(i), 1);
        }
        return sLetters;
    }
    public static String hiddenAnagram (String [] arr) {
        String s = arr[1].toLowerCase();
        s = s.replaceAll("[^a-z]", "");
        Hashtable<Character, Integer> sLetters = countLetters(s);
        String s0 = arr[0].toLowerCase();
        s0 = s0.replaceAll("[^a-z]", "");
        int n = s.length();
        for (int i = 0; i + n <= s0.length(); i++) {
            String substring = s0.substring(i, i + n);
            Hashtable<Character, Integer> subStringLetters = countLetters(substring);
            if (subStringLetters.equals(sLetters)) return substring;
        }
        return "notfound";
    }
    public static String[] collect(String s, int n) {
        if (s.length() < n) return new String [] {};
        else {
            String [] arr = collect(s.substring(n), n);
            List<String> strList = new ArrayList<>(Arrays.stream(arr).toList());
            strList.add(s.substring(0, n));
            Collections.sort(strList);
            return strList.toArray(new String[strList.size()]);
        }
    }
    public static String nicoCipher(String message, String key) {
        message = message.toLowerCase();
        key = key.toLowerCase();
        int rows = (int) ceil((double) message.length() / key.length());
        char [][] matrix = new char[key.length()][rows];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                if (k < message.length()) matrix[j][i] = message.charAt(k);
                else matrix[j][i] = ' ';
                k++;
            }
        }
        char [] line = key.toCharArray();
        for (int i = 1; i < key.length(); i++) {
            for (int j = 0; j < key.length() - i; j++) {
                if (line[j] > line[j + 1]) {
                    char temp = line[j];
                    line[j] = line[j + 1];
                    line[j + 1] = temp;
                    char[] temp2 = matrix[j];
                    matrix[j] = matrix[j + 1];
                    matrix[j + 1] = temp2;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                res.append(matrix[j][i]);
            }
        }
        return res.toString();
    }
    public static int[] twoProduct(int[] arr, int n) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] * arr[j] == n) {
                    return new int[] {arr[j], arr[i]};
                }
            }
        }
        return new int[]{};
    }
    public static int[] isExact(int n) {
        int res = isExact(n, 2);
        if (res != -1) return new int[] {n, res};
        return new int[]{};
    }
    public static int isExact(int n, int k) {
        if (n == k) return n;
        else if (n % k == 0) return isExact(n/k, k + 1);
        return -1;
    }
    public static String fractions (String s) {
        int i = s.indexOf(".");
        int j = s.indexOf("(");
        int whole;
        int period;
        int floating;
        if (i == -1) return s + "/1";
        if (j == -1) {
            whole = Integer.parseInt(s.substring(0, i));
            floating = Integer.parseInt(s.substring(i + 1));
            return String.valueOf(whole) + String.valueOf(floating) + "/1" + "0".repeat(String.valueOf(floating).length());
        }
        else period = Integer.parseInt(s.substring(j + 1, s.length() - 1));
        whole = Integer.parseInt(s.substring(0, i));
        int denominator;
        int numerator;
        if (s.substring(i + 1, j).isEmpty()) {
            floating = 0;
            denominator = Integer.parseInt("9".repeat(String.valueOf(period).length()));
            numerator = whole * denominator + period;
        }
        else {
            floating = Integer.parseInt(s.substring(i + 1, j));
            denominator = Integer.parseInt("9".repeat(String.valueOf(period).length()) + "0".repeat(String.valueOf(floating).length()));
            numerator = Integer.parseInt(String.valueOf(whole) + String.valueOf(floating)) * Integer.parseInt("9".repeat(String.valueOf(period).length())) + period;
        }
        int nod = 1;
        for (int k = min(denominator, numerator); k > 1; k--) {
            if (denominator % k == 0 && numerator % k == 0) {
                nod = k;
                break;
            }
        }
        numerator /= nod;
        denominator /= nod;
        return String.valueOf(numerator) + "/" + String.valueOf(denominator);
    }
    public static String pilishString(String s) {
        String pi = "314159265358979";
        List<String> strList = new ArrayList<>();
        int iStart = 0;
        for (int i = 0; i < pi.length(); i++) {
            int c = Integer.parseInt(String.valueOf(pi.charAt(i)));
            if (iStart >= s.length()) break;
            if (iStart + c <= s.length()) {
                strList.add(s.substring(iStart, iStart + c));
                iStart += c;
            }
            else {
                String newS = s.substring(iStart);
                int dif = c - newS.length();
                strList.add(newS + String.valueOf(newS.charAt(newS.length() - 1)).repeat(dif));
                break;
            }
        }
        StringBuilder res = new StringBuilder();
        for (String word : strList) {
            res.append(word).append(" ");
        }
        return res.substring(0, res.length() - 1);
    }
    public static double evaluateExpression(String s) throws Exception {
        char[] tokens = s.toCharArray();
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (Character.isDigit(tokens[i]) || (tokens[i] == '-' && (i == 0 || !Character.isDigit(tokens[i - 1])))) {
                StringBuilder sbuf = new StringBuilder();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.' || (tokens[i] == '-' && sbuf.length() == 0))) {
                    sbuf.append(tokens[i]);
                    i++;
                }
                values.push(Double.parseDouble(sbuf.toString()));
                i--;
            }
            else if (tokens[i] == '(') {
                operators.push(tokens[i]);
            }
            else if (tokens[i] == ')') {
                try {
                    Character peek = operators.peek();
                } catch (EmptyStackException e) {
                    throw new Exception("Brackets are wrong");
                }
                while (operators.peek() != '(') {
                    try {
                        values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                    } catch (EmptyStackException e) {
                        throw new Exception("Equation written wrong");
                    }
                }
                operators.pop(); // Pop the '('
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!operators.empty() && hasPrecedence(tokens[i], operators.peek())) {
                    try {
                        values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                    } catch (EmptyStackException e) {
                        throw new Exception("Equation written wrong");
                    }
                }
                operators.push(tokens[i]);
            }
        }
        while (!operators.empty()) {
            try {
                values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
            } catch (EmptyStackException e) {
                throw new Exception("Equation written wrong");
            }
        }
        return values.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    private static double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new ArithmeticException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    public static int generateNonconsecutive(String s) {
        ScriptEngineManager manager = new ScriptEngineManager();
        for (ScriptEngineFactory engin : manager.getEngineFactories()) {
            System.out.println("name: " + engin.getEngineName());
        }
        /*ScriptEngine engine = manager.getEngineByName("graal.js");
        Object res;
        try {
            res = engine.eval(s);
        } catch (ScriptException e) {
            System.out.println("Err0r");
            return 0;
        }
        return (int) res;*/
        return 0;
    }
    private static boolean isCharactersEqual(String s) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            String word = s.substring(i, i + 1);
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        String [] keys = map.keySet().toArray(new String [map.keySet().size()]);
        int n = map.get(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            if (map.get(keys[i]) != n) return false;
        }
        return true;
    }
    public static String isValid(String s) {
        if (isCharactersEqual(s)) return "YES";
        for (int i = 0; i <= s.length() - 2; i++) {
            if (isCharactersEqual(s.substring(0, i) + s.substring(i + 1))) return "YES";
        }
        if (isCharactersEqual(s.substring(0, s.length() - 1))) return "YES";
        return "NO";
    }
    public static String findLCS(String s1, String s2) {
        int n1 = s1.length() + 1;
        int n2 = s2.length() + 1;
        int [][] matrix = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (i * j == 0) matrix[i][j] = 0;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1)) matrix[i][j] = matrix[i - 1][j - 1] + 1;
                else matrix[i][j] = max(matrix[i - 1][j], matrix[i][j - 1]);
            }
        }

        StringBuilder lcs = new StringBuilder();
        int i = n1 - 1, j = n2 - 1;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.insert(0, s1.charAt(i - 1));
                i--;
                j--;
            } else if (matrix[i - 1][j] > matrix[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return lcs.toString();
    }
    public static void main(String[] args) throws Exception {
        System.out.println(hiddenAnagram(new String[] {"My world evolves in a beautiful space called Tesh.", "sworn love lived"}));
        System.out.println(hiddenAnagram(new String[] {"Banana? margaritas", "ANAGRAM"}));
        System.out.println(Arrays.toString(collect("intercontinentalisationalism", 6)));
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15}, 45)));
        System.out.println(Arrays.toString(isExact(6)));
        System.out.println(Arrays.toString(isExact(24)));
        System.out.println(Arrays.toString(isExact(138)));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("0.1097(3)"));
        System.out.println(pilishString("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"));
        System.out.println(pilishString("FORALOOP"));
        System.out.println(pilishString("CANIMAKEAGUESSNOW"));
        System.out.println(evaluateExpression("3 + 57 * 2"));
        System.out.println(evaluateExpression("(3 + 57) * 2"));
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("abcd"));
        System.out.println(isValid("abcdc"));
        System.out.println(findLCS("abcd", "bd"));
        System.out.println(findLCS("aggtab", "gxtxamb"));
    }
}