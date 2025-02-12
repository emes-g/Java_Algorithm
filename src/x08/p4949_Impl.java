package x08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p4949_Impl {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            Stack4949 stack = new Stack4949();
            String str = br.readLine();
            boolean isValid = true;
            if (str.equals(".")) {
                break;
            }
            for (char c : str.toCharArray()) {
                if (c == '(' || c == '[') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.empty() || stack.pop() != '(') {
                        isValid = false;
                        break;
                    }
                } else if (c == ']') {
                    if (stack.empty() || stack.pop() != '[') {
                        isValid = false;
                        break;
                    }
                }
            }
            if (!stack.empty()) {
                isValid = false;
            }
            sb.append(isValid ? "yes" : "no").append("\n");
        }
        System.out.println(sb);
    }
}

class Stack4949 {
    private static final int DEFAULT_CAPACITY = 5;
    private char[] arr;
    private int top;

    public Stack4949() {
        this.arr = new char[DEFAULT_CAPACITY];
        this.top = -1;
    }

    public boolean empty() {
        return top == -1;
    }

    private void resize() {
        if (top == arr.length - 1) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        } else if (top < (arr.length - 1) / 2) {
            arr = Arrays.copyOf(arr, Math.max(arr.length / 2, DEFAULT_CAPACITY));
        } else if (empty()) {
            arr = new char[DEFAULT_CAPACITY];
        }
    }

    public void push(char x) {
        resize();
        arr[++top] = x;
    }

    public char pop() {
        char item = arr[top--];
        resize();
        return item;
    }
}
