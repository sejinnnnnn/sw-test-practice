import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 진짜 ㅈㄴ 어렵네요 ... 이런 거 나오면 풀 자신이 없는데 ..
 */
public class 구슬탈출2 {

    static final int[] dx = { 1, -1, 0, 0 };
    static final int[] dy = { 0, 0, 1, -1 };

    static int N, M;
    static char[][] map;

    static int[] rStart;
    static int[] bStart;

    static int[] endPoint;

    static int trials = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rStart = new int[2];
        bStart = new int[2];
        endPoint = new int[2];

        map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    rStart[0] = i;
                    rStart[1] = j;
                } else if (map[i][j] == 'B') {
                    bStart[0] = i;
                    bStart[1] = j;
                } else if (map[i][j] == 'O') {
                    endPoint[0] = i;
                    endPoint[1] = j;
                }
            }
        }

        boolean[][][][] visited = new boolean[N][M][N][M];
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] { rStart[0], rStart[1], bStart[0], bStart[1], 0 });
        visited[rStart[0]][rStart[1]][bStart[0]][bStart[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[4] == 10) break;

            for (int i = 0; i < 4; i++) {
                int nextRX = current[0];
                int nextRY = current[1];
                int nextBX = current[2];
                int nextBY = current[3];

                while (map[nextRX + dx[i]][nextRY + dy[i]] != '#') {
                    nextRX += dx[i];
                    nextRY += dy[i];

                    if (map[nextRX][nextRY] == 'O') break;
                }

                while (map[nextBX + dx[i]][nextBY + dy[i]] != '#') {
                    nextBX += dx[i];
                    nextBY += dy[i];

                    if (map[nextBX][nextBY] == 'O') break;
                }

                if (nextBX == endPoint[0] && nextBY == endPoint[1]) continue;
                if (nextRX == endPoint[0] && nextRY == endPoint[1]) {
                    System.out.println((current[4] + 1));
                    return;
                }

                if (nextRX == nextBX && nextRY == nextBY) {
                    if (i == 0) {
                        if (current[0] < current[2]) nextRX -= 1;
                        else nextBX -= 1;
                    } else if (i == 1) {
                        if (current[0] < current[2]) nextBX += 1;
                        else nextRX += 1;
                    } else if (i == 2) {
                        if (current[1] < current[3]) nextRY -= 1;
                        else nextBY -= 1;
                    } else {
                        if (current[1] < current[3]) nextBY += 1;
                        else nextRY += 1;
                    }
                }

                if (!visited[nextRX][nextRY][nextBX][nextBY]) {
                    visited[nextRX][nextRY][nextBX][nextBY] = true;
//                    System.out.println(i);
//                    System.out.println(Arrays.toString(new int[] { nextRX, nextRY, nextBX, nextBY, current[4] + 1 }));
                    queue.offer(new int[] { nextRX, nextRY, nextBX, nextBY, current[4] + 1 });
                }

            }

        }

        System.out.println(-1);

    }

}
