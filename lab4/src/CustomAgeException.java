import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CustomAgeException extends Exception {
    public CustomAgeException(String message) {
        super(message);
        char[] buf = new char[256];
        try {
            FileReader reader = new FileReader("AgeExceptionsLog.txt");
            int c;
            while((c = reader.read(buf))>0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
            }
        } catch (IOException ignored) {}
        try {
            FileWriter writer = new FileWriter("AgeExceptionsLog.txt");
            writer.write(buf);
            writer.write(message);
            writer.write("\n");
            writer.flush();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}