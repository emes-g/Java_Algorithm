package x05;

import java.util.ArrayList;

public class MyStack2<E> {
    private ArrayList<E> list;
    private int top;

    public MyStack2() {
        list = new ArrayList<>();
        top = -1;
    }

    public boolean isFull() {
        return top == list.size() - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public E push(E e) {
        list.add(e);
        top++;
        return e;
    }

    public E pop() {
        return list.remove(top--);
    }

    public E peek() {
        return list.get(top);
    }

    public int search(E e) {
        int res = list.lastIndexOf(e);
        if (res != -1) {
            return top - res + 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
