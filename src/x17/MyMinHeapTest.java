package x17;

public class MyMinHeapTest {
    public static void main(String[] args) {
        MyMinHeap heap = new MyMinHeap();
        System.out.println(heap);   // []
        heap.push(2);
        heap.push(4);
        heap.push(7);
        heap.push(8);
        heap.push(13);
        System.out.println(heap);   // [2, 4, 7, 8, 13]
        heap.push(1);
        System.out.println(heap);   // [1, 4, 2, 8, 13, 7]
        System.out.println(heap.top()); // 1
        heap.pop();
        System.out.println(heap);   // [2, 4, 7, 8, 13]
    }
}
