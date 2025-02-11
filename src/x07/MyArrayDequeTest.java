package x07;

public class MyArrayDequeTest {
    public static void main(String[] args) {
        MyArrayDeque<Integer> dq = new MyArrayDeque<>();
        System.out.println(dq);

        dq.offer(1);
        System.out.println(dq);
        dq.offerFirst(2);
        System.out.println(dq);
        dq.offer(3);
        System.out.println(dq);
        dq.offerFirst(4);
        System.out.println(dq);
        dq.offer(5);    // 이를 통해 resize()가 발생하면서, offerFirst(4)가 성공적으로 수행되었음을 확인할 수 있음
        System.out.println(dq);

        System.out.println(dq.poll());
        System.out.println(dq.pollLast());
        System.out.println(dq);

        System.out.println(dq.peek());
        System.out.println(dq.peekLast());

        System.out.println(dq.contains(3));
        System.out.println(dq.contains(4));

        dq.clear();
        System.out.println(dq);
    }
}
