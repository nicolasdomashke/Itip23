import java.util.*;

public class Main {
    public static void main(String[] args) {

        Stack<String> myStack = new Stack<>(10);
        myStack.push("a");
        myStack.push("b");
        myStack.push("c");
        System.out.println(myStack.pop());
        System.out.println(myStack.getSize());
        System.out.println(myStack.peek());
        System.out.println(myStack.getSize());
        myStack.push("d");
        System.out.println(myStack.getSize());
        System.out.println(myStack.pop());
        System.out.println(myStack.getSize());

        System.out.println("***");

        SalesDB<String> database = new SalesDB<>();
        database.addValue("apple");
        database.addValue("apple");
        database.addValue("banana");
        database.addValue("banana");
        database.addValue("grape");
        Hashtable<String, Integer> ht = database.getTable();
        System.out.println(ht.keySet());
        System.out.println(ht.values().stream().mapToInt(Integer::intValue).sum());
        List<Map.Entry<String, Integer>> newEntry = new ArrayList<>(ht.entrySet());
        newEntry.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        Integer k = null;
        for (Map.Entry<String,Integer> entry : newEntry) {
            if (k == null) {
                k = entry.getValue();
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            else if (entry.getValue() == k) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            else break;
        }
    }
}