package x04;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyDoublyLinkedList<E> {
    // 객체가 생성될 때 필드는 디폴트로 초기화
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    // 실제 LinkedList에서는 node()이나, 편의를 위해
    private Node<E> search(int index) {
        // assert that index is valid
        Node<E> x;
        if (size / 2 < index) {
            x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    public void addFirst(E e) {
        final Node<E> first = head; // 자바는 항상 (기본값이든, 참조값이든) 변수의 값을 복사해서 대입한다.
        final Node<E> newNode = new Node<>(null, e, first);
        size++;
        // update original node info.
        head = newNode;
        if (first == null) {
            tail = newNode;
        } else {
            first.prev = newNode;
        }
    }

    public void addLast(E e) {
        final Node<E> last = tail;
        final Node<E> newNode = new Node<>(last, e, null);
        size++;
        // update original node info.
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(e);
            return;
        }
        if (index == size) {
            addLast(e);
            return;
        }
        Node<E> nextNode = search(index);
        Node<E> prevNode = nextNode.prev;
        Node<E> newNode = new Node<>(prevNode, e, nextNode);
        size++;
        // update original node info.
        prevNode.next = newNode;
        nextNode.prev = newNode;
    }

    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        E e = head.item;
        Node<E> first = head.next; // 이 시점의 value.next에 들어 있는 참조값을 복사해서 대입하므로, 2줄 밑 코드에서 영향을 받지 않음
        head.item = null;
        head.next = null;
        size--;
        // after remove
        head = first;
        if (head == null) {
            tail = null;
        } else {
            first.prev = null;
        }
        return e;
    }

    public E remove() {
        return removeFirst();
    }

    public E removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        E e = tail.item;
        Node<E> last = tail.prev;
        tail.item = null;
        tail.prev = null;
        size--;
        // after remove
        tail = last;
        if (tail == null) {
            head = null;
        } else {
            last.next = null;
        }
        return e;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<E> delNode = search(index);
        Node<E> prevNode = delNode.prev;
        Node<E> nextNode = delNode.next;
        E e = delNode.item;
        delNode.prev = null;
        delNode.item = null;
        delNode.next = null;
        size--;
        // after remove
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        return e;
    }

    public boolean remove(Object o) {
        int index = 0;
        for (Node<E> x = head; x != null; x = x.next, index++) {
            if (Objects.equals(x.item, o)) {
                remove(index);
                return true;
            }
        }
        return false;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return search(index).item;
    }

    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> x = search(index);
        E oldVal = x.item;
        x.item = e;
        return oldVal;
    }

    public int indexOf(Object o) {
        int index = 0;
        for (Node<E> x = head; x != null; x = x.next, index++) {
            if (Objects.equals(x.item, o)) {
                return index;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.prev = null;
            x.item = null;
            x.next = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (Node<E> x = head; x.next != null; x = x.next) {
            sb.append(x.item).append(", ");
        }
        sb.append(tail.item).append("]");
        return sb.toString();
    }
}
