import java.util.regex.*;

public class URLs {
    public static void main(String[] args) {
        String text = "Is it better to use google.com or yandex.ru? Or maybe https://rambler.ru? Why does it costs 19.99 USD?";
        StringBuilder res = new StringBuilder(text);
        Pattern pattern0 = Pattern.compile("\\w+\\.[a-z]{1,3}");
        Matcher matcher0 = pattern0.matcher(text);
        while (matcher0.find()) {
            String st = matcher0.group();
            //System.out.println(st);
            int i1 = res.indexOf(st);
            if (i1 != 0 && res.charAt(i1 - 1) != '/') res.replace(i1, i1 + st.length(), "https://" + st);
        }
        System.out.println(res.toString());
    }
}