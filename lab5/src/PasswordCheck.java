import java.util.regex.*;

public class PasswordCheck {
    public static void main(String[] args) {
        String text = "fyjhgjkhiuui8A";
        Pattern pattern0 = Pattern.compile(".{8,16}");
        Pattern pattern1 = Pattern.compile("\\w*");
        Pattern pattern2 = Pattern.compile(".*\\d.*");
        Pattern pattern3 = Pattern.compile(".*[A-Z].*");
        Matcher matcher0 = pattern0.matcher(text);
        Matcher matcher1 = pattern1.matcher(text);
        Matcher matcher2 = pattern2.matcher(text);
        Matcher matcher3 = pattern3.matcher(text);
        if (!matcher0.matches()) System.out.println("Password must be from 8 to 16 symbols!");
        else if (!matcher1.matches()) System.out.println("Password must contain only letter and numbers!");
        else if (!matcher2.matches()) System.out.println("Password must contain at least one digit!");
        else if (!matcher3.matches()) System.out.println("Password must contain at least one capital!");
        else System.out.println("Password accepted");
    }
}