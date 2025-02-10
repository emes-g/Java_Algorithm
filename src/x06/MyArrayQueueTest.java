package x06;

public class MyArrayQueueTest {
    public static void main(String[] args) {
//        MyArrayQueue<Integer> q = new MyArrayQueue<>();
        MyArrayQueue<Integer> q = new MyArrayQueue<>(1);
        System.out.println(q);

        q.offer(1);
        System.out.println(q);
        q.offer(2);
        System.out.println(q);
        q.offer(3);
        System.out.println(q);
        q.offer(4);
        System.out.println(q);
        q.offer(5);
        System.out.println(q);

        q.poll();
        System.out.println(q);

        q.poll();
        System.out.println(q);

        q.peek();
        System.out.println(q);

        q.clear();
        System.out.println(q);
    }
}
