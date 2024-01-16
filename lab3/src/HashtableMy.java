import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.hash;

public class HashtableMy {
    private List<Integer> indexes;
    private List<List<Book>> values;

    public HashtableMy() {
        indexes = new ArrayList<>();
        values = new ArrayList<>();
    }
    public void put(String key, Book value) {
        int index = hash(key);
        if (indexes.contains(index)) {
            List<Book> newVal = new ArrayList<>(values.get(indexes.indexOf(index)));
            newVal.add(value);
            values.remove(values.get(indexes.indexOf(index)));
            values.add(indexes.indexOf(index), newVal);
        }
        else {
            indexes.add(index);
            values.add(List.of(new Book[]{value}));
        }
    }
    public void remove(String key) {
        int index = indexes.indexOf(hash(key));
        if (index != -1) {
            indexes.remove(index);
            values.remove(index);
        }
    }
    public List<Book> get(String key) {
        if (indexes.contains(hash(key))) {
            return values.get(indexes.indexOf(hash(key)));
        }
        else {
            return List.of(new Book[]{new Book()});
        }
    }
    public int size() {
        return indexes.size();
    }
    public boolean isEmpty() {
        return indexes.isEmpty();
    }
}