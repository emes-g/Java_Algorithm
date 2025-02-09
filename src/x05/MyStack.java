package x05;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

public class MyStack<E> {
    private static final int DEFAULT_CAPACITY = 6;
    private Object[] arr;
    private int top;    // 스택의 마지막 요소를 가리키는 포인터

    public MyStack() {
        this.arr = new Object[DEFAULT_CAPACITY];
        this.top = -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private void resize() {
        if (isFull()) {
            arr = Arrays.copyOf(arr, arr.length * 2);
            return;
        }
        if (top < (arr.length - 1) / 2) {
            arr = Arrays.copyOf(arr, Math.max(arr.length / 2, DEFAULT_CAPACITY));
        }
    }

    public E push(E e) {
        if (isFull()) {
            resize();
        }
        arr[++top] = e;
        return e;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E e = (E) arr[top];
        arr[top--] = null;
        resize();
        return e;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) arr[top];
    }

    public int search(E e) {
        for (int i = top; i >= 0; i--) {    // 스택 위에서 아래로 순회
            if (Objects.equals(arr[i], e)) {
                return top - i + 1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}
