package x08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class p3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 0;
        while (n-- > 0) {
            String word = br.readLine();
            Stack<Character> stack = new Stack<>();
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