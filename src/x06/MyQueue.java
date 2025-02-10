package x06;

public interface MyQueue<E> {
    boolean offer(E e); // addLast()
    E poll();   //  removeFirst()
    E peek();
}
