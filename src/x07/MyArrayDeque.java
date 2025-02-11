package x07;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayDeque<E> implements MyQueue<E> {
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] arr;
    private int front, rear, size;

    public MyArrayDeque() {
        this.arr = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayDeque(int capacity) {
        this.arr = new Object[capacity];
    }

    public boolean isFull() {
        return front == (rear + 1) % arr.length;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    private void resize() {
        int newCapacity;
        if (isFull()) {
            newCapacity = arr.length * 2;
        } else if (size < (arr.length / 2)) {
            newCapacity = Math.max(arr.length / 2, DEFAULT_CAPACITY);
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

    public boolean offerLast(E e) {
        resize();
        rear = (rear + 1) % arr.length;
        arr[rear] = e;
        size++;
        return true;
    }

    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }

    public boolean offerFirst(E e) {
        resize();
        arr[front] = e;
        front = (front - 1 + arr.length) % arr.length;  // 포인터를 후에 이동
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E pollFirst() {
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
    public E poll() {
        return pollFirst();
    }

    @SuppressWarnings("unchecked")
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) arr[rear];
        arr[rear] = null;
        rear = (rear - 1 + arr.length) % arr.length;
        size--;
        resize();
        return e;
    }

    @SuppressWarnings("unchecked")
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return (E) arr[(front + 1) % arr.length];
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @SuppressWarnings("unchecked")
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return (E) arr[rear];
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
