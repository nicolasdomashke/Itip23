import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TopWords {
    public static void main(String [] args) {
        String filePath = "C:/Users/NicolasDomashke/IdeaProjects/lab6/src/hamlet.txt";
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Map<String, Integer> map = new HashMap<>();

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<Map.Entry<String, Integer>> newEntry = new ArrayList<>(map.entrySet());
        newEntry.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        int k = 0;
        for (Map.Entry<String,Integer> entry : newEntry) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            k++;
            if (k == 10) break;
        }
    }
}