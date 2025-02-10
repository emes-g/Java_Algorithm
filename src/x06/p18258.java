package x06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p18258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue18258 q = new Queue18258(n);

        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            String cmd = br.readLine();
            if (cmd.equals("pop")) {
                sb.append(q.pop()).append('\n');
            } else if (cmd.equals("size")) {
                sb.append(q.size()).append('\n');
            } else if (cmd.equals("empty")) {
                sb.append(q.empty()).append('\n');
            } else if (cmd.equals("front")) {
                sb.append(q.front()).append('\n');
            } else if (cmd.equals("back")) {
                sb.append(q.back()).append('\n');
            } else {
                int x = Integer.parseInt(cmd.split(" ")[1]);
                q.push(x);
            }
        }
        System.out.println(sb);
    }
}

class Queue18258 {
    int[] arr;
    int front, rear, size;

    public Queue18258(int n) {
        this.arr = new int[n + 1];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return front == (rear + 1) % arr.length;
    }

    public void push(int x) {
        rear = (rear + 1) % arr.length;
        arr[rear] = x;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        front = (front + 1) % arr.length;
        int item = arr[front];
        arr[front] = 0;
        size--;
        return item;
    }

    public int size() {
        return size;
    }

    public int empty() {
        return isEmpty() ? 1 : 0;
    }

    public int front() {
        if (isEmpty()) {
            return -1;
        }
        return arr[(front + 1) % arr.length];
    }

    public int back() {
        if (isEmpty()) {
            return -1;
        }
        return arr[rear];
    }
}
