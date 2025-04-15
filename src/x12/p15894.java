package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p15894 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long ans = n * 4L;
        System.out.println(ans);
    }
}
