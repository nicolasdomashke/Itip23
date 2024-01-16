import java.util.Hashtable;

public class SalesDB<K> {
    private Hashtable<K, Integer> map;

    public SalesDB() {
        map = new Hashtable<>();
    }

    public Hashtable<K, Integer> getTable() {
        return map;
    }

    public void addValue(K key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

}
