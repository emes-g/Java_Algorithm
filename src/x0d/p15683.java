package x0d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p15683 {
    static int n, m;
    static int ans = Integer.MAX_VALUE;
    static char[][] board, boardCopy;

    // arr의 값이 동일하더라도, cctv의 type이 다르면, scan하는 라인들이 달라짐
    static int cnt;
    static cctv15683[] cctv = new cctv15683[8];
    static int[] arr = new int[8]; // 각 cctv가 scan해야 할 라인에 대한 정보를 담고 있음

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        boardCopy = new char[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = st.nextToken().charAt(0);
                if (board[i][j] != '0' && board[i][j] != '6') {
                    cctv[cnt++] = new cctv15683(i, j, board[i][j] - '0');
                }
            }
        }
        func(0);
        System.out.println(ans);
    }

    public static void func(int depth) {
        if (depth == cnt) {
            // 해당 arr가지고 scan 후 최소값 갱신
            for (int i = 0; i < n; i++) {
                boardCopy[i] = Arrays.copyOf(board[i], board[i].length);
            }
            for (int i = 0; i < cnt; i++) {
                scanLinesByType(cctv[i].x, cctv[i].y, cctv[i].type, arr[i]);
            }
            int areaCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (boardCopy[i][j] == '0') {
                        areaCnt++;
                    }
                }
            }
            ans = Math.min(ans, areaCnt);
            return;
        }
        for (int i = 0; i < cctv[depth].dirCnt; i++) {
            arr[depth] = i;
            func(depth + 1);
        }
    }

    public static void scanLinesByType(int x, int y, int type, int arrVal) {
        switch (type) {
            case 1:
                scanLine(x, y, arrVal);
                break;
            case 2:
                scanLine(x, y, arrVal);
                scanLine(x, y, (arrVal + 2) % 4);
                break;
            case 3:
                scanLine(x, y, arrVal);
                scanLine(x, y, (arrVal + 1) % 4);
                break;
            case 4:
                scanLine(x, y, arrVal);
                scanLine(x, y, (arrVal + 1) % 4);
                scanLine(x, y, (arrVal + 2) % 4);
                break;
            case 5:
                for (int i = 0; i < 4; i++) {
                    scanLine(x, y, i);
                }
                break;

        }
    }

    public static void scanLine(int x, int y, int dir) {
        switch (dir) {
            case 0: // 남
                for (int i = x + 1; i < n; i++) {
                    if (boardCopy[i][y] == '6') {
                        return;
                    }
                    if (boardCopy[i][y] != '0') {
                        continue;
                    }
                    boardCopy[i][y] = '#';
                }
                break;
            case 1: // 동
                for (int i = y + 1; i < m; i++) {
                    if (boardCopy[x][i] == '6') {
                        return;
                    }
                    if (boardCopy[x][i] != '0') {
                        continue;
                    }
                    boardCopy[x][i] = '#';
                }
                break;
            case 2: // 북
                for (int i = x - 1; i >= 0; i--) {
                    if (boardCopy[i][y] == '6') {
                        return;
                    }
                    if (boardCopy[i][y] != '0') {
                        continue;
                    }
                    boardCopy[i][y] = '#';
                }
                break;
            case 3: // 서
                for (int i = y - 1; i >= 0; i--) {
                    if (boardCopy[x][i] == '6') {
                        return;
                    }
                    if (boardCopy[x][i] != '0') {
                        continue;
                    }
                    boardCopy[x][i] = '#';
                }
                break;
        }
    }
}

class cctv15683 {
    int x, y, type, dirCnt;  // 가능한 방향 개수

    public cctv15683(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        if (type == 5) {
            this.dirCnt = 1;
        } else if (type == 2) {
            this.dirCnt = 2;
        } else {
            this.dirCnt = 4;
        }
    }
}