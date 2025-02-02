package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2490 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int i = 3;
        while (i-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int front = 0;
            while (st.hasMoreTokens()) {
                if (st.nextToken().equals("1")) {
                    front++;
                }
            }
            sb.append(getResult(front)).append("\n");
        }
        System.out.println(sb);
    }

    public static String getResult(int front) {
        if (front == 0) {
            return "D";
        } else if (front == 1) {
            return "C";
        } else if (front == 2) {
            return "B";
        } else if (front == 3) {
            return "A";
        } else {
            return "E";
        }
    }
}
