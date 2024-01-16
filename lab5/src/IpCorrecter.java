import java.util.regex.*;

public class IpCorrecter {
    public static void main(String[] args) {
        String text = "111.111.111.111";
        Pattern pattern0 = Pattern.compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
        Matcher matcher0 = pattern0.matcher(text);
        if (!matcher0.matches()) System.out.println("Incorrect format or NaNs used!");
        else {
            Pattern pattern1 = Pattern.compile("\\d+");
            Matcher matcher1 = pattern1.matcher(text);
            boolean flag = true;
            while (matcher1.find()) {
                String n = matcher1.group();
                Pattern pattern2 = Pattern.compile("25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]");
                Matcher matcher2 = pattern2.matcher(n);
                if (!matcher2.matches()) {
                    System.out.println("Numbers out of bounds");
                    flag = false;
                    break;
                }
            }
            if (flag) System.out.println("Ip accepted");
        }
    }
}