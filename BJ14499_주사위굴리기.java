import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 배열 복사할 때 유지하고 싶은 원본 값은 일일히 넣어줘야 해 ,,,
 */
public class BJ14499_주사위굴리기 {

    // 동 서 북 남 순서로
    static final int[] dx = { 0, 0, 0, -1, 1 };
    static final int[] dy = { 0, 1, -1, 0, 0 };

    static int N, M, K;
    static int x, y;

    static int[][] map;

    // 최초 위치
    // 0 : 윗면, 1 : 동쪽, 2 : 서쪽, 3 : 북쪽, 4 : 남쪽, 5 : 밑면
    static int[] dice;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new int[6];
        st = new StringTokenizer(br.readLine());

        for (int c = 0; c < K; c++) {
            int direction = Integer.parseInt(st.nextToken());
            if (!isInRange(x + dx[direction], y + dy[direction])) continue;
            answer.append(String.format("%d\n", rollDice(direction)));
        }

        System.out.println(answer.toString().trim());

    }

    private static int rollDice(int direction) {

//        System.out.println("rollDice(direction = " + direction + ")");

        int[] nextDice = new int[6];

        // 0 : 윗면, 1 : 동쪽, 2 : 서쪽, 3 : 북쪽, 4 : 남쪽, 5 : 밑면
        switch (direction) {
            case 1:
                nextDice[1] = dice[0];
                nextDice[5] = dice[1];
                nextDice[0] = dice[2];
                nextDice[2] = dice[5];

                nextDice[3] = dice[3];
                nextDice[4] = dice[4];
                break;
            case 2:
                nextDice[2] = dice[0];
                nextDice[5] = dice[2];
                nextDice[0] = dice[1];
                nextDice[1] = dice[5];

                nextDice[3] = dice[3];
                nextDice[4] = dice[4];
                break;
            case 3:
                nextDice[3] = dice[0];
                nextDice[5] = dice[3];
                nextDice[4] = dice[5];
                nextDice[0] = dice[4];

                nextDice[1] = dice[1];
                nextDice[2] = dice[2];
                break;
            case 4:
                nextDice[4] = dice[0];
                nextDice[5] = dice[4];
                nextDice[3] = dice[5];
                nextDice[0] = dice[3];

                nextDice[1] = dice[1];
                nextDice[2] = dice[2];
                break;
        }


        dice = nextDice;

        x += dx[direction];
        y += dy[direction];

        if (map[x][y] == 0) {
            map[x][y] = dice[5];
        } else {
            dice[5] = map[x][y];
            map[x][y] = 0;
        }

//        System.out.println(Arrays.toString(nextDice));
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }
//
//        System.out.println("return : " + dice[0]);

        return dice[0];
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}
