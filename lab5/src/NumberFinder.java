import java.util.regex.*;

public class NumberFinder {
    public static void main(String[] args) {
        String text = "The price of the product is $19.99. Or -5RUB, idk";
        Pattern pattern0 = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher0 = pattern0.matcher(text);
        while (matcher0.find()) {
            System.out.println(matcher0.group());
        }
    }
}