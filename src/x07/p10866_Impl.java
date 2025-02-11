package x07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10866_Impl {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        Deque10866 dq = new Deque10866(n);
        while (n-- > 0) {
            String cmd = br.readLine();
            if (cmd.startsWith("push_front")) {
                int x = Integer.parseInt(cmd.split(" ")[1]);
                dq.push_front(x);
            } else if (cmd.startsWith("push_back")) {
                int x = Integer.parseInt(cmd.split(" ")[1]);
                dq.push_back(x);
            } else if (cmd.equals("pop_front")) {
                sb.append(dq.pop_front()).append('\n');
            } else if (cmd.equals("pop_back")) {
                sb.append(dq.pop_back()).append('\n');
            } else if (cmd.equals("size")) {
                sb.append(dq.size).append('\n');
            } else if (cmd.equals("empty")) {
                sb.append(dq.isEmpty() ? 1 : 0).append('\n');
            } else if (cmd.equals("front")) {
                sb.append(dq.front()).append('\n');
            } else {
                sb.append(dq.back()).append('\n');
            }
        }
        System.out.println(sb);
    }
}

class Deque10866 {
    int[] arr;
    int front, rear, size;

    public Deque10866(int n) {
        this.arr = new int[n + 1];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void push_front(int x) {     // offerFirst
        arr[front] = x;
        front = (front - 1 + arr.length) % arr.length;
        size++;
    }

    public void push_back(int x) {  // offer(Last)
        rear = (rear + 1) % arr.length;
        arr[rear] = x;
        size++;
    }

    public int pop_front() {    // poll(First)
        if (isEmpty()) {
            return -1;
        }
        front = (front + 1) % arr.length;
        int item = arr[front];
        arr[front] = 0;
        size--;
        return item;
    }

    public int pop_back() { // pollLast
        if (isEmpty()) {
            return -1;
        }
        int item = arr[rear];
        arr[rear] = 0;
        rear = (rear - 1 + arr.length) % arr.length;
        size--;
        return item;
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
