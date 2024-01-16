import java.util.LinkedHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class WordsCounter {
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    public WordsCounter(){}
    public static LinkedHashMap<String, Integer> performAnalisys(List<String> words) {
        try {
            List<String> firstHalf = words.subList(0, words.size() / 2);
            List<String> secondHalf = words.subList(words.size() / 2, words.size());
            WordCountTask firstTask = new WordCountTask(firstHalf);
            WordCountTask secondTask = new WordCountTask(secondHalf);
            Future<LinkedHashMap<String, Integer>> firstFuture = executorService.submit(firstTask);
            Future<LinkedHashMap<String, Integer>> secondFuture = executorService.submit(secondTask);
            LinkedHashMap<String, Integer> firstResult = firstFuture.get();
            LinkedHashMap<String, Integer> secondResult = secondFuture.get();
            mergeResults(firstResult, secondResult);

            LinkedHashMap<String, Integer> sortedTable = firstResult.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));
            return sortedTable;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        } finally {
            executorService.shutdown();
        }
    }

    static class WordCountTask implements Callable<LinkedHashMap<String, Integer>> {
        private final List<String> words;
        WordCountTask(List<String> words) {
            this.words = words;
        }
        @Override
        public LinkedHashMap<String, Integer> call() {
            LinkedHashMap<String, Integer> wordCountTable = new LinkedHashMap<>();
            for (String word : words) {
                String lowercaseWord = word.toLowerCase();
                wordCountTable.put(lowercaseWord, wordCountTable.getOrDefault(lowercaseWord, 0) + 1);
            }
            return wordCountTable;
        }
    }

    private static void mergeResults(LinkedHashMap<String, Integer> leftResult, LinkedHashMap<String, Integer> rightResult) {
        for (Map.Entry<String, Integer> entry : rightResult.entrySet()) {
            leftResult.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }
}
