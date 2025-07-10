package x1d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
[input]
6 8
1 2 3
1 3 2
1 4 5
2 3 2
2 5 8
3 4 2
4 5 6
5 6 1
1 5

[output]
0	3	2	4	10	11
1	3	4	5
 */

public class Main {
    static final int MAX = Integer.MAX_VALUE / 2;
    static int n, m;
    static ArrayList<ArrayList<edgeMain>> list = new ArrayList<>();
    static int[] dist, prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(from).add(new edgeMain(to, weight));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dijkstra(start);
        show(dist);
        showPath(start, end);
    }

    // [기본적인 알고리즘]
    // 현재 정점(curr.to)까지 curr이라는 간선을 타고 왔고,
    // 다음 정점(next.to)까지는 next라는 간선을 타고 갈 것이다.
    // 기존에 next.to 정점으로 이동하는 방법보다, 현재 정점(curr.to)에서 next라는 간선을 타는 것이 효율적인 경우 갱신
    public static void dijkstra(int start) {
        dist = new int[n + 1];
        Arrays.fill(dist, MAX);
        prev = new int[n + 1];
        PriorityQueue<edgeMain> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        dist[start] = 0;
        pq.offer(new edgeMain(start, dist[start]));
        while (!pq.isEmpty()) {
            edgeMain curr = pq.poll();
            // 유효한 간선이 아니라면
            // (현재 dist 값으로 갱신하기 위해 사용했던 간선이 아니라면)
            if (dist[curr.to] != curr.weight) {
                continue;
            }
            for (edgeMain next : list.get(curr.to)) {
                if (dist[next.to] > dist[curr.to] + next.weight) {
                    dist[next.to] = dist[curr.to] + next.weight;
                    prev[next.to] = curr.to;
                    pq.offer(new edgeMain(next.to, dist[next.to])); // 단순히 next 삽입하면 안 됨
                }
            }
        }
    }

    public static void show(int[] arr) {
        for (int i = 1; i <= n; i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
    }

    public static void showPath(int start, int end) {
        if (start == end || dist[end] == MAX) {
            System.out.println("출발지와 목적지가 동일하거나 경로가 없습니다.");
            return;
        }
        Stack<Integer> s = new Stack<>();
        int curr = end;
        while (curr != start) {
            s.push(curr);
            curr = prev[curr];
        }
        s.push(curr);   // curr == start
        while (!s.isEmpty()) {
            System.out.printf("%d\t", s.pop());
        }
        System.out.println();
    }
}

class edgeMain {
    int to, weight;

    public edgeMain(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}