package x05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack2493 stack = new Stack2493(n);
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int num = 1;
        while (st.hasMoreTokens()) {
            int now = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()) {
                Top x = stack.peek();
                if (now < x.height) {
                    stack.push(new Top(now, num++));
                    sb.append(x.num).append(' ');
                    break;
                }
                stack.pop();
            }
            if (stack.isEmpty()) {
                stack.push(new Top(now, num++));
                sb.append(0).append(' ');
            }
        }
        System.out.println(sb);
    }
}

class Top {
    int height;
    int num;

    public Top(int height, int num) {
        this.height = height;
        this.num = num;
    }
}

class Stack2493 {
    Top[] arr;
    int top;

    public Stack2493(int n) {
        arr = new Top[n + 1];
        top = -1;
    }

    public void push(Top x) {
        arr[++top] = x;
    }

    public Top pop() {
        return arr[top--];
    }

    public Top peek() {
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}