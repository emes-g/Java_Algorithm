package appendix_C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최대한 많은 곡을 제대로 연주하려고 할 때, 필요한 기타의 최소 개수

public class p1497 {

    static int n, m;
    static String[] names;  // 기타 이름
    static long[] infos;  // 기타별 연주가능한 곡 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        names = new String[n];
        infos = new long[n];
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String str = st.nextToken();
            long info = 0;
            for (int j = 0; j < m; j++) {
                if (str.charAt(j) == 'N') {
                    continue;
                }
                info |= (1L << j);
            }
            names[i] = name;
            infos[i] = info;
        }
        int maxSongCnt = 0;    // 지금까지 발견한 최대 연주가능 곡 개수
        int minGuitarCnt = Integer.MAX_VALUE;  // 해당 곡들을 연주하는데 필요한 최소 기타 개수
        int subsetCount = 1 << n;   // 2^n
        for (int dec = 0; dec < subsetCount; dec++) {
            long totalInfo = 0;
            int guitarCnt = 0;  // 해당 곡들을 연주하는데 필요한 기타 개수
            for (int i = 0; i < n; i++) {
                if ((dec & (1 << i)) != 0) {
                    totalInfo |= infos[i];
                    guitarCnt++;
                }
            }
            int songCnt = Long.bitCount(totalInfo);
            if (maxSongCnt == m) {   // 이미 모든 곡이 연주가능한 경우였다면
                if (songCnt == m) {
                    minGuitarCnt = Math.min(minGuitarCnt, guitarCnt); // 더 적은 기타 개수로 연주가능한지
                }
                continue;
            }
            // 아직 모든 곡이 연주가능하지 않은 상황에서
            if (maxSongCnt < songCnt) {  // 기존보다 연주가능한 곡 개수가 많은 경우
                maxSongCnt = songCnt;
                minGuitarCnt = guitarCnt;
            } else if (maxSongCnt == songCnt) {
                minGuitarCnt = Math.min(minGuitarCnt, guitarCnt);
            }
        }
        System.out.println(minGuitarCnt == 0 ? -1 : minGuitarCnt);
    }
}
