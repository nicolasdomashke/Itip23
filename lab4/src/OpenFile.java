import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class OpenFile {
    public static void main(String[] args) {
        String d_c = "C:/Users/NicolasDomashke/IdeaProjects/lab4/src";
        String d_i = "Is the directory supposed to be here?";
        File dir = new File(d_c); //Change the directory
        File source = null;
        File res = null;
        try {
            for (File item : dir.listFiles()) {
                if (item.getName().equals("HelloWorld.txt")) source = item;
                else if (item.getName().equals("result.txt")) res = item;
            }
            FileWriter writer = new FileWriter(res);
            FileReader reader = new FileReader(source);
            char[] buf = new char[256];
            int c;
            while((c = reader.read(buf))>0){

                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }
            }
            writer.write(buf);
            writer.flush();
            System.out.println("Success!");;
        } catch (NullPointerException | IOException e) {
            System.out.println("No needed files found in the catalogue!");
        }
    }
}