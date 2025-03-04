package x10;

public class Main {
    public static void main(String[] args) {
//        func1(5);
//        System.out.println(func2(5));
        func3(100000);
        System.out.println("done");
    }

    // print n to 1
    public static void func1(int n) {
        if (n == 0) {
            return;
        }
        System.out.println(n);
        func1(n - 1);
    }

    // get sum of 1 to n
    public static int func2(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n + func2(n- 1);
        }
    }

    // check stack's size
    public static void func3(int n) {
        if (n == 0) {
            return;
        }
        func3(n - 1);
    }
}
