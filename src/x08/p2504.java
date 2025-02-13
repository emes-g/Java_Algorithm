package x08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class p2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();
        int sum = 0;
        int weight = 1;
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (curr == '(') {
                stack.push(curr);
                weight *= 2;
            } else if (curr == '[') {
                stack.push(curr);
                weight *= 3;
            } else if (curr == ')') {
                if (stack.empty() || stack.peek() != '(') {
                    System.out.println(0);
                    return;
                }
                char prev = str.charAt(i - 1);
                if (prev == '(') {
                    sum += weight;
                }
                stack.pop();
                weight /= 2;
            } else {
                if (stack.empty() || stack.peek() != '[') {
                    System.out.println(0);
                    return;
                }
                char prev = str.charAt(i - 1);
                if (prev == '[') {
                    sum += weight;
                }
                stack.pop();
                weight /= 3;
            }
        }
        if (!stack.empty()) {
            System.out.println(0);
            return;
        }
        System.out.println(sum);
    }
}
