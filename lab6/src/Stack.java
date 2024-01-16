import java.security.PublicKey;

public class Stack<T> {
    private T[] data;
    private  int size;
    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }
    public void push (T element) {
        if (size != data.length) {
            data[size] = element;
            size++;
        }
    }
    public T pop() {
        if (size != 0) {
            T res = data[size - 1];
            data[size - 1] = null;
            size--;
            return res;
        }
        return null;
    }
    public T peek() {
        if (size != 0) {
            T res = data[size - 1];
            return res;
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}