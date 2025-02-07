package x04;

public class MyDoublyLinkedListTest {
    public static void main(String[] args) {
        MyDoublyLinkedList<Number> list = new MyDoublyLinkedList<>();
        list.addLast(2);
        list.addFirst(1);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        System.out.println(list);   // 1 2 3 4 5

        list.removeFirst();
        System.out.println(list);   // 2 3 4 5

        list.removeLast();
        System.out.println(list);   // 2 3 4

        list.remove(2.5);
        System.out.println(list);   // 2 3 4

        System.out.println(list.get(2));    // 4
        list.set(1, 3.5);

        System.out.println(list);   // 2 3.5 4

        list.clear();
        System.out.println(list);   // []
    }
}
