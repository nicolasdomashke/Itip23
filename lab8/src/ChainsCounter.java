import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ChainsCounter {
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    public ChainsCounter(){}
    public static LinkedHashMap<String, LinkedHashMap<String, Integer>> performAnalisys(List<String> words) {
        try {
            List<String> firstHalf = words.subList(0, words.size() / 2);
            List<String> secondHalf = words.subList(words.size() / 2, words.size());
            NextWordFrequencyTask firstTask = new NextWordFrequencyTask(firstHalf);
            NextWordFrequencyTask secondTask = new NextWordFrequencyTask(secondHalf);
            Future<LinkedHashMap<String, LinkedHashMap<String, Integer>>> firstFuture = executorService.submit(firstTask);
            Future<LinkedHashMap<String, LinkedHashMap<String, Integer>>> secondFuture = executorService.submit(secondTask);
            LinkedHashMap<String, LinkedHashMap<String, Integer>> firstResult = firstFuture.get();
            LinkedHashMap<String, LinkedHashMap<String, Integer>> secondResult = secondFuture.get();
            mergeResults(firstResult, secondResult);

            LinkedHashMap<String, LinkedHashMap<String, Integer>> sortedTable = sortOuterLinkedHashMap(firstResult);
            return sortedTable;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        } finally {
            executorService.shutdown();
        }
    }

    static class NextWordFrequencyTask implements Callable<LinkedHashMap<String, LinkedHashMap<String, Integer>>> {
        private final List<String> words;
        NextWordFrequencyTask(List<String> words) {
            this.words = words;
        }
        @Override
        public LinkedHashMap<String, LinkedHashMap<String, Integer>> call() {
            LinkedHashMap<String, LinkedHashMap<String, Integer>> frequencyTable = new LinkedHashMap<>();
            for (int i = 0; i < words.size() - 1; i++) {
                String currentWord = words.get(i);
                String nextWord = words.get(i + 1);
                currentWord = currentWord.toLowerCase();
                nextWord = nextWord.toLowerCase();
                if (frequencyTable.get(currentWord) == null) {
                    LinkedHashMap<String, Integer> newVal = new LinkedHashMap<>();
                    newVal.put(nextWord, 1);
                    frequencyTable.put(currentWord, newVal);
                } else {
                    LinkedHashMap<String, Integer> lastList = frequencyTable.get(currentWord);
                    lastList.put(nextWord, lastList.getOrDefault(nextWord, 0) + 1);
                    frequencyTable.put(currentWord, lastList);
                }
            }
            return frequencyTable;
        }
    }

    private static void mergeResults(LinkedHashMap<String, LinkedHashMap<String, Integer>> leftResult,
                                     LinkedHashMap<String, LinkedHashMap<String, Integer>> rightResult) {
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : rightResult.entrySet()) {
            if (leftResult.get(entry.getKey()) == null) {
                leftResult.put(entry.getKey(), entry.getValue());
            }
            else {
                LinkedHashMap<String, Integer> s1 = leftResult.get(entry.getKey());
                LinkedHashMap<String, Integer> s2 = entry.getValue();
                LinkedHashMap<String, Integer> newList = new LinkedHashMap<>();
                for (Map.Entry<String, Integer> entryNew : s1.entrySet()) {
                    if (s2.get(entryNew.getKey()) == null) {
                        newList.put(entryNew.getKey(), entryNew.getValue());
                    }
                    else {
                        newList.put(entryNew.getKey(), s2.get(entryNew.getKey()) + entryNew.getValue());
                    }
                }
                for (Map.Entry<String, Integer> entryNew : s2.entrySet()) {
                    if (newList.get(entryNew.getKey()) == null) {
                        newList.put(entryNew.getKey(), entryNew.getValue());
                    }
                }
            }
        }
    }

    private static LinkedHashMap<String, LinkedHashMap<String, Integer>> sortOuterLinkedHashMap(LinkedHashMap<String, LinkedHashMap<String, Integer>> inputTable) {
        List<Map.Entry<String, LinkedHashMap<String, Integer>>> entryList = new ArrayList<>(inputTable.entrySet());
        entryList.sort(Comparator.comparingInt((Map.Entry<String, LinkedHashMap<String, Integer>> entry) -> entry.getKey().length()).reversed());
        LinkedHashMap<String, LinkedHashMap<String, Integer>> sortedTable = new LinkedHashMap<>();
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : entryList) {
            sortedTable.put(entry.getKey(), entry.getValue());
        }
        return sortedTable;
    }
}
