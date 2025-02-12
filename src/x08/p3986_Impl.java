package x08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p3986_Impl {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 0;
        while (n-- > 0) {
            String word = br.readLine();
            Stack3986 stack = new Stack3986(word.length());
            for (char c : word.toCharArray()) {
                if (stack.empty() || c != stack.peek()) {
                    stack.push(c);
                } else {
                    stack.pop();
                }
            }
            if (stack.empty()) {
                count++;
            }
        }
        System.out.println(count);
    }
}

class Stack3986 {
    char[] arr;
    int top;

    public Stack3986(int n) {
        arr = new char[n + 1];
        top = -1;
    }

    public void push(char x) {
        arr[++top] = x;
    }

    public char pop() {
        return arr[top--];
    }

    public char peek() {
        return arr[top];
    }

    public boolean empty() {
        return top == -1;
    }
}