package x06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10845_Impl {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        Queue10845 q = new Queue10845();
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

class Queue10845 {
    int[] arr;
    int front, rear, size;

    public Queue10845() {
        this.arr = new int[10001];
    }

    public boolean isEmpty() {
        return front == rear;
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

    public int empty() {
        return isEmpty() ? 1 : 0;
    }
}