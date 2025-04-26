package x15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 해쉬맵, O(l * logl)

public class p13414 {
    static HashMap<String, Integer> map = new HashMap<>();
    static Student13414[] arr;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int num = 1;
        while (l-- > 0) {   // O(l)
            String id = br.readLine();
            map.put(id, num++);
        }
        size = map.size();
        arr = new Student13414[size];
        int idx = 0;
        for (String id : map.keySet()) {    // O(l)
            arr[idx++] = new Student13414(id, map.get(id));
        }
        Arrays.sort(arr);   // O(l * logl)
        StringBuilder sb = new StringBuilder();
        k = Math.min(k, size);  // 대기열에 입장한 학생 수가 수강신청 정원보다 적은 경우 고려
        for (int i = 0; i < k; i++) {
            sb.append(arr[i].id).append('\n');
        }
        System.out.println(sb);
    }
}

class Student13414 implements Comparable<Student13414> {
    String id;
    int num;

    public Student13414(String id, int num) {
        this.id = id;
        this.num = num;
    }

    @Override
    public int compareTo(Student13414 o) {
        return this.num - o.num;
    }
}