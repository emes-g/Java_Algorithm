package x03;

import java.util.Arrays;

public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 5;
    private int size;   // size != capacity
    Object[] elements;  // E[]로 선언하지 않은 이유 : E와 같은 type parameter는 직접적으로 초기화될 수 없다.

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.elements = new Object[capacity];
        } else if (capacity == 0) {
            this.elements = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("리스트 용량을 잘못 설정하였습니다.");
        }
        this.size = 0;
    }

    private void resize() {
        int currCapacity = elements.length;
        if (currCapacity == size) {
            elements = Arrays.copyOf(elements, currCapacity * 2);
        } else if ((currCapacity / 2) > size) { // 리스트 절반 이상이 비어 있는 경우
            elements = Arrays.copyOf(elements, Math.max(currCapacity / 2, DEFAULT_CAPACITY));
        } else if (size == 0) {
            elements = new Object[DEFAULT_CAPACITY];
        }
    }

    public void add(E value) {
        resize();
        elements[size++] = value;
    }

    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(value);
        } else {
            resize();
            for (int i = size; i > index; i--) {
                elements[i] = elements[i - 1];
            }
            elements[index] = value;
            size++;
        }
    }

    public int indexOf(E value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E element = (E) elements[index];
        elements[index] = null;
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
            elements[i] = null;
        }
        size--;
        resize();
        return element;
    }

    public boolean remove(E value) {
        int idx = indexOf(value);
        if (idx == -1) {
            return false;
        }
        remove(idx);
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    public void set(int index, E value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        elements[index] = value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}