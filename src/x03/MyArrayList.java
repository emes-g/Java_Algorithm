package x03;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {
    private static final int DEFAULT_CAPACITY = 5;  // 기본 크기는 10인데, 테스트를 위해 5로 설정

    private int size;   // size != capacity
    private Object[] elementData;   // E[]로 선언하지 않은 이유 : E와 같은 type parameter는 직접적으로 초기화될 수 없다.

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;   // 자바에서는 객체가 생성될 때, 필드가 기본 초기값으로 자동 설정되므로 없어도 됨
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            elementData = new Object[capacity];
        } else if (capacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Invalid Capacity: " + capacity);
        }
        size = 0;
    }

    private void resize() {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        } else if (size < (elementData.length / 2)) {
            elementData = Arrays.copyOf(elementData, Math.max(DEFAULT_CAPACITY, elementData.length / 2));
        } else if (size == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        }
    }

    @Override
    public boolean add(E e) {
        resize();
        elementData[size++] = e;
        return true;
    }

    @Override
    public void add(int idx, E e) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException();
        }
        resize();
        for (int i = size; i > idx; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[idx] = e;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);
        if (idx == -1) {
            return false;
        }
        remove(idx);
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")  // 형 변환시 예외 가능성이 없을 때만 사용
    public E remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }
        E e = (E) elementData[idx];
        for (int i = idx; i < size; i++) {
            elementData[i] = elementData[i + 1];
            elementData[i + 1] = null;  // 마지막 요소에 기존 값이 남아 있을 수도 있으므로 명시적으로 제거
        }
        size--;
        resize();
        return e;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elementData[idx];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int idx, E e) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }
        E originalData = (E) elementData[idx];
        elementData[idx] = e;
        return originalData;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {    // null은 동등 연산자로 비교해야 함
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}