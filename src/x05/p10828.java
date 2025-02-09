package x05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Stack10828 stack = new Stack10828();
        while (n-- > 0) {
            String cmd = br.readLine();
            if (cmd.equals("pop")) {
                sb.append(stack.pop()).append("\n");
            } else if (cmd.equals("size")) {
                sb.append(stack.size()).append("\n");
            } else if (cmd.equals("empty")) {
                sb.append(stack.empty()).append("\n");
            } else if (cmd.equals("top")) {
                sb.append(stack.top()).append("\n");
            } else {
                int x = Integer.parseInt(cmd.split(" ")[1]);
                stack.push(x);
            }
        }
        System.out.println(sb);
    }
}

class Stack10828 {
    int[] arr;
    int top;

    public Stack10828() {
        arr = new int[10001];
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

    public int size() {
        return top + 1;
    }

    public int empty() {
        return isEmpty() ? 1 : 0;
    }

    public int top() {
        if (isEmpty()) {
            return -1;
        }
        return arr[top];
    }
}