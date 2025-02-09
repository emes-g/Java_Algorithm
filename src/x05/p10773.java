package x05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        Stack10773 stack = new Stack10773();
        while (k-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                stack.pop();
            } else {
                stack.push(x);
            }
        }

        int total = 0;
        int i = stack.size();
        while (i-- > 0) {
            total += stack.pop();
        }
        System.out.println(total);
    }
}

class Stack10773 {
    int[] arr;
    int top;

    public Stack10773() {
        arr = new int[100001];
        top = -1;
    }

    public void push(int x) {
        arr[++top] = x;
    }

    public int pop() {
        return arr[top--];
    }

    public int size() {
        return top + 1;
    }
}
