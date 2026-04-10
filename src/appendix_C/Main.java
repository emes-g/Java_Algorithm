package appendix_C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        func2();
        func3();
    }

    // {0, 1, 2, 3}의 모든 부분집합 출력하기
    public static void func2() {
        int[] arr = {0, 1, 2, 3};
        int n = arr.length;
        int subsetCount = (int) Math.pow(2, n); // 부분집합의 개수: 2^n

        // e.g.) 13 == 1101 == {0, 2, 3}
        StringBuilder sb = new StringBuilder();
        for (int dec = 0; dec < subsetCount; dec++) {
            int bin = dec;
            for (int i = 0; i < n; i++) {
                if (bin % 2 == 1) { // check if curr bit == 1
                    sb.append(arr[i]).append(' ');
                }
                bin /= 2;   // move to next bit
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    // {0, 1, 2, 3}의 모든 부분집합 출력하기
    public static void func3() {
        int[] arr = {0, 1, 2, 3};
        int n = arr.length;
        int subsetCount = 1 << n;

        // e.g.) 13 == 1101 == {0, 2, 3}
        StringBuilder sb = new StringBuilder();
        for (int dec = 0; dec < subsetCount; dec++) {
            for (int i = 0; i < n; i++) {
                // 특정 비트가 1인지 확인하기
                if ((dec & (1 << i)) != 0) {    // 좌변 전체에 괄호를 씌우지 않으면, 우선순위에 의해 (1 << i) != 0이 먼저 계산됨
                    sb.append(arr[i]).append(' ');
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}