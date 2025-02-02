package x01;

import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // P01, P03, P04
        System.out.print("N 입력 : ");
        int result = func4(scan.nextInt());
        System.out.println(result);

//        // P02
//        String[] str = scan.nextLine().split(" ");
//        int N = Integer.parseInt(str[str.length - 1]);
//        int[] arr = new int[N];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = Integer.parseInt(str[i]);
//        }
//        int result = func2(arr, N);
//        System.out.println(result);
    }

    public static int func1(int N) {
        int total = 0;
        for (int i = 1; i <= N; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                total += i;
            }
        }
        return total;
    }

    // N : length of arr
    public static int func2(int[] arr, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arr[i] + arr[j] == 100) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public static int func3(int N) {
        for (int i = 1; i * i <= N; i++) {
            if (i * i == N) {
                return 1;
            }
        }
        return 0;
    }

    public static int func4(int N) {
        int x = 2;
        while (x <= N) {
            x = x << 1;
        }
        return x >> 1;
    }
}