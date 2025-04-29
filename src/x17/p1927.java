package x17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1927 {
    static MyMinHeap heap = new MyMinHeap();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if (x > 0) {
                heap.push(x);
                continue;
            }
            if (heap.isEmpty()) {
                sb.append("0\n");
                continue;
            }
            sb.append(heap.top()).append('\n');
            heap.pop();
        }
        System.out.println(sb);
    }
}