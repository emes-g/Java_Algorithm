package x03;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyList<Number> list = new MyArrayList<>();
        System.out.println(list);

        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1, 1.5);
        System.out.println(list);

        System.out.println(list.indexOf(1));    // 0

        // type parameter를 Number가 아니라 Integer로 했으면, 오토박싱 문제가 발생할 수 있음
        // 오토박싱 문제는 명시적 캐스팅을 활용하여 해결할 수 있음
        System.out.println(list.remove(0));
        System.out.println(list.remove(2));         // remove(int idx) 호출
//        System.out.println(list.remove((Integer) 2));   // remove(Object o) 호출
        System.out.println(list);

        System.out.println(list.remove((Integer) 1)); // false
        System.out.println(list.remove((Integer) 2)); // true
        System.out.println(list); // [1.5, 3, 4, null, null]

        list.clear();
        System.out.println(list); // [null, null, null, null, null]
    }
}
