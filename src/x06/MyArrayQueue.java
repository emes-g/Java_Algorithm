package x06;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayQueue<E> implements MyQueue<E> {
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] arr;
    private int size;
    private int front;
    private int rear;

    public MyArrayQueue() {
        this.arr = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayQueue(int capacity) {
        this.arr = new Object[capacity];
    }

    private void resize() {
        int newCapacity;
        if (isFull()) {
            newCapacity = arr.length * 2;
        } else if (size < (arr.length / 2)) {
            newCapacity = Math.max(DEFAULT_CAPACITY, arr.length / 2);
        } else {
            return;
        }

        Object[] newArr = new Object[newCapacity];
        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            newArr[i] = arr[j % arr.length];
        }
        this.arr = newArr;
        this.front = 0;
        this.rear = size;
    }

    public boolean isFull() {
        return front == (rear + 1) % arr.length;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean offer(E e) {
        resize();
        rear = (rear + 1) % arr.length;
        arr[rear] = e;
        size++;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        front = (front + 1) % arr.length;
        E e = (E) arr[front];
        arr[front] = null;
        size--;
        resize();
        return e;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) arr[(front + 1) % arr.length];
    }

    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        int start = front + 1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[(start + i) % arr.length], o)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        this.arr = new Object[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}
