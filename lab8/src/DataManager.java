import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.min;

public class DataManager {
    private Object dataProcessor;
    private List<String> sourceData;
    private LinkedHashMap<?, ?> resData;
    public DataManager() {
        dataProcessor = null;
        sourceData = null;
        resData = null;
    }
    public DataManager(Object processor, String link) {
        dataProcessor = processor;
        loadData(link);
    }
    public void registerDataProcessor(Object processor) {
        dataProcessor = processor;
    }
    public void process() {
        if (dataProcessor.getClass() == WordsCounter.class) {
            process((WordsCounter) dataProcessor);
        } else if (dataProcessor.getClass() == ChainsCounter.class) {
            process((ChainsCounter) dataProcessor);
        }
    }
    @DataProcessor(key = String.class, value = Integer.class)
    public void process(WordsCounter s) {
         resData = s.performAnalisys(sourceData);
    }
    @DataProcessor(key = String.class, value = List.class)
    public void process(ChainsCounter s) {
        resData = s.performAnalisys(sourceData);
    }
    public void loadData(String s) {
        File file = new File(s);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        sourceData = new ArrayList<>();
        List<String> unfiltred = new ArrayList<>();
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            unfiltred.add(word);
        }
        for (String a : unfiltred) {
            String word = extractSpecialSymbols(a);
            sourceData.add(word);
        }
    }

    public void saveData(String link) {
        if (resData != null) {
            String[] keys = resData.keySet().toArray(new String[resData.keySet().size()]);
            Class<?> V = resData.get(keys[0]).getClass();
            if (V == Integer.class) {
                Integer[] values = new Integer[keys.length];
                for (int i = 0; i < keys.length; i++) {
                    values[i] = (Integer) resData.get(keys[i]);
                }
                saveData(link, keys, values);
            }
            else {
                LinkedHashMap<String, Integer>[] values = new LinkedHashMap[keys.length];
                for (int i = 0; i < keys.length; i++) {
                    LinkedHashMap<String, Integer> newVal = (LinkedHashMap) resData.get(keys[i]);
                    values[i] = newVal;
                }
                saveData(link, keys, values);
            }
        }
    }
    @DataProcessor(key = String.class, value = Integer.class)
    private void saveData(String link, String[] keys, Integer[] values) {
        File file;
        FileWriter writer;
        try {
            file = new File(link);
            writer = new FileWriter(file);
            for (int i = 0; i < keys.length; i++) {
                writer.write(keys[i] + " : " + String.valueOf(values[i]) + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DataProcessor(key = String.class, value = List.class)
    private void saveData(String link, String[] keys, LinkedHashMap<String, Integer>[] values) {
        File file = new File(link);
        FileWriter writer = null;
        try {
            file = new File(link);
            writer = new FileWriter(file);
            for (int i = 0; i < keys.length; i++) {
                writer.write(keys[i] + " :");
                for (Map.Entry<String, Integer> t : values[i].entrySet()) {
                    writer.write(" (" + t.getKey() + " : " + String.valueOf(t.getValue()) + ")");
                }
                writer.write("\n");
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String extractSpecialSymbols(String s) {
        String patternString = "[^(,\\[\\].?!;:)]";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(s);
        StringBuilder resultBuilder = new StringBuilder();
        while (matcher.find()) {
            resultBuilder.append(matcher.group());
        }
        return resultBuilder.toString();
    }
}