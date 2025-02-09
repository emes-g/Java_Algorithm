package x05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Stack1874 stack = new Stack1874(n);
        int unused = 1;
        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());
            while (unused <= x) {
                stack.push(unused++);
                sb.append("+\n");
            }
            if (stack.pop() != x) {
                System.out.println("NO");
                return;
            }
            sb.append("-\n");
        }
        System.out.println(sb);
    }
}

class Stack1874 {
    int[] arr;
    int top;

    public Stack1874(int n) {
        arr = new int[n + 1];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int x) {
        arr[++top] = x;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return arr[top--];
    }
}