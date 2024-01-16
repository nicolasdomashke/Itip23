import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static String replaceVovels (String s) {
        s = s.toLowerCase();
        String res = "";
        String alphabet = "aqeyiou";
        for (int i = 0; i < s.length(); i++) {
            if (alphabet.contains("" + s.charAt(i))) res += "*";
            else res += s.charAt(i);
        }
        return res;
    }
    public static String stringTransform (String s) {
        s = s.toLowerCase();
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (i != s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                res += "Double" + s.charAt(i + 1);
                i++;
            } else res += s.charAt(i);
        }
        return res;
    }
    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        int [] arr1 = {a, b, c};
        int [] arr2 = {w, h};
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return arr1[0] <= arr2[0] && arr1[1] <= arr2[1];
    }
    public static boolean numCheck(int n) {
        int sum = 0;
        int k = n;
        while (k > 0) {
            sum += (k % 10) * (k % 10);
            k /= 10;
        }
        return (n % 2) == (sum % 2);
    }
    public static int countRoots(int [] n) {
        int d = n[1] * n[1] - 4 * n[0] * n[2];
        if (d < 0) return 0;
        int sqd = (int) Math.sqrt(d);
        if (sqd * sqd == d) {
            int res = 0;
            if ((d - n[1]) % (2 * n[0]) == 0) res += 1;
            if (d != 0 && (-d - n[1]) % (2 * n[0]) == 0) res += 1;
            return res;
        }
        return 0;
    }
    public static String [] salesData (String [][] matrix) {
        List<String> res = new ArrayList<String>();
        List<String> shops = new ArrayList<String>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (!shops.contains(matrix[i][j])) shops.add(matrix[i][j]);
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            boolean isAll = true;
            List<String> line = Arrays.asList(matrix[i]);
            for (int j = 0; j < shops.size(); j++) {
                if (!line.contains(shops.get(j))) {
                    isAll = false;
                    break;
                }
            }
            if (isAll) res.add(matrix[i][0]);
        }
        return res.toArray(new String[res.size()]);
    }
    public static boolean validSplit(String s) {
        s = s.toLowerCase();
        String [] S = s.split(" ");
        for (int i = 0; i < S.length - 1; i++) {
            if (S[i].charAt(S[i].length() - 1) != S[i + 1].charAt(0)) return false;
        }
        return true;
    }
    public static boolean waveForm(int [] arr) {
        if (arr.length < 2) return true;
        int k = (arr[0] - arr[1]) / Math.abs(arr[0] - arr[1]);
        for (int i = 1; i < arr.length - 1; i++) {
            k *= -1;
            if ((arr[i] - arr[i + 1]) * k <= 0) return false;
        }
        return true;
    }
    public static char commonVovel(String s) {
        s = s.toLowerCase();
        String vovels = "aqeyiou";
        int[] alphabet = new int[7];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 7; j++) {
                if (vovels.charAt(j) == s.charAt(i)) alphabet[j]++;
            }
        }
        int iMax = 0;
        for (int i = 1; i < 7; i++) {
            if (alphabet[i] > alphabet[iMax]) iMax = i;
        }
        return vovels.charAt(iMax);
    }
    public static int[][] dataScience(int [][] matrix) {
        int n = matrix[0].length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += matrix[j][i];
            }
            matrix[i][i] = (sum - matrix[i][i]) / (n - 1);
            sum = 0;
        }
        return matrix;
    }
    
    public static void main(String[] args) {
        System.out.println(replaceVovels("apple"));
        System.out.println(stringTransform("bookkeeper"));
        System.out.println(doesBlockFit(1, 3, 5,  4, 5));
        System.out.println(numCheck(243));
        System.out.println(countRoots(new int[]{1, -3, 2}));
        System.out.println(Arrays.toString(salesData(new String[][]{{"apple", "1", "2"}, {"ban", "1", "2", "3", "4"}, {"pear", "1", "2", "4"}})));
        System.out.println(validSplit("apple eagle easy yhw"));
        System.out.println(waveForm(new int[] {3, 1, 4, 2, 7, 5}));
        System.out.println(commonVovel("Hello world"));
        System.out.println(Arrays.deepToString(dataScience(new int[][] {{1, 2, 3, 4, 5}, {6, 7, 8, 29, 10}, {5, 5, 5, 5, 35}, {7, 4, 3, 14, 2}, {1, 0, 11, 10, 1}})));
    }
}