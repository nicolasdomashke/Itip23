import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstLetter {
    public static void main(String[] args) {
        String text = "An apple a day you know this one bro come on";
        List<String> words = new ArrayList<>();
        char letter = 'a';
        Pattern pattern0 = Pattern.compile("\\b" + letter + "\\w*\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher0 = pattern0.matcher(text);
        while(matcher0.find()) {
            String s = matcher0.group();
            words.add(s);
        }
        System.out.println(words);
    }
}
