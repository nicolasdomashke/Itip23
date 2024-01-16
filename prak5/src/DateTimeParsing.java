import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class DateTimeParsing {
    public static void main(String[] args) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm");

        // Corrected date string with double quotes
        String dateString = "June 3, 1970 13:40";

        try {
            //LocalDateTime dateTime = LocalDateTime.parse(dateString, inputFormatter);
            //System.out.println("Parsed date: " + dateTime);
            String mystring = "January 2, 2010 13:40";
            Date thedate = new SimpleDateFormat("MMMM d, yyyy HH:mm", Locale.ENGLISH).parse(dateString);
            System.out.println(thedate);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
