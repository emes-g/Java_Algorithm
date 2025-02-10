package x06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue2164 q = new Queue2164(n);
        for (int i = 1; i <= n; i++) {
            q.push(i);
        }
        while (q.size > 1) {
            q.pop();
            q.push(q.pop());
        }
        System.out.println(q.pop());
    }
}

class Queue2164 {
    int[] arr;
    int front, rear, size;

    public Queue2164(int n) {
        arr = new int[n + 1];
    }

    public void push(int x) {
        rear = (rear + 1) % arr.length;
        arr[rear] = x;
        size++;
    }

    public int pop() {
        front = (front + 1) % arr.length;
        int item = arr[front];
        arr[front] = 0;
        size--;
        return item;
    }
}
