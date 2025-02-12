package x08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class p4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            Stack<Character> stack = new Stack<>();
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