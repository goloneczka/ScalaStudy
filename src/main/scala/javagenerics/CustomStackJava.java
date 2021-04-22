package javagenerics;

import java.util.ArrayList;
import java.util.List;

public class CustomStackJava<T> {
    private final List<T> elements = new ArrayList<>();

    public void push(T x) {
        elements.add(x);
    }

    public T peek() {
        return elements.get(0);
    }

    public T pop() {
        T currentTop = peek();
        elements.remove(0);
        return currentTop;
    }

    public int size() {
        return elements.size();
    }
}
