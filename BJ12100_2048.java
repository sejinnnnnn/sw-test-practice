import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이정도는 .. 테케 많이 주어지면 합격할 수 있을 듯요 ..
 */
public class BJ12100_2048 {

    static int N;
    static int[][] map;

    static final int MOVE_COUNT = 5;

    static int maxBlock = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxBlock = Math.max(maxBlock, map[i][j]);
            }
        }

        moveBlocks(map, 1);
        System.out.println(maxBlock);

    }

    private static void moveBlocks(int[][] currentMap, int count) {

        if (count > MOVE_COUNT) return;

//        System.out.printf("moveBlocks (%d)\n", count);
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(currentMap[i]));
//        }

        for (int d = 0; d < 4; d++) {
            int[][] nextMap = new int[N][];
            for (int i = 0; i < N; i++) {
                nextMap[i] = Arrays.copyOf(currentMap[i], N);
            }

            boolean[][] alreadyMerged = new boolean[N][N];

            if (d == 0) {
                // 밑에 비어있는 만큼 해당 열을 떨어트리기
                for (int j = 0; j < N; j++) {
                    for (int i = N - 1; i >= 0; i--) {
                        if (nextMap[i][j] == 0) {
                            int diff = 1;
                            while (i - diff >= 0 && nextMap[i - diff][j] == 0) ++diff;
                            if (i - diff >= 0) {
                                nextMap[i][j] = nextMap[i - diff][j];
                                nextMap[i - diff][j] = 0;
                            }
                        }

                        if (i != N - 1 && nextMap[i + 1][j] == nextMap[i][j] && nextMap[i][j] != 0 && !alreadyMerged[i + 1][j]) {
                            nextMap[i + 1][j] *= 2;
                            nextMap[i][j] = 0;
                            maxBlock = Math.max(maxBlock, nextMap[i + 1][j]);
                            alreadyMerged[i + 1][j] = true;
                            i++;
                        }

                    }
                }

            } else if (d == 1) {

                for (int j = 0; j < N; j++) {
                    for (int i = 0; i < N; i++) {
                        if (nextMap[i][j] == 0) {
                            int diff = 1;
                            while (i + diff < N && nextMap[i + diff][j] == 0) ++diff;
                            if (i + diff < N) {
                                nextMap[i][j] = nextMap[i + diff][j];
                                nextMap[i + diff][j] = 0;
                            }
                        }

                        if (i != 0 && nextMap[i - 1][j] == nextMap[i][j] && nextMap[i][j] != 0 && !alreadyMerged[i - 1][j]) {
                            nextMap[i - 1][j] *= 2;
                            nextMap[i][j] = 0;
                            maxBlock = Math.max(maxBlock, nextMap[i - 1][j]);
                            alreadyMerged[i - 1][j] = true;
                            i--;
                        }

                    }

                }

            } else if (d == 2) {

                for (int i = 0; i < N; i++) {
                    for (int j = N - 1; j >= 0; j--) {
                        if (nextMap[i][j] == 0) {
                            int diff = 1;
                            while (j - diff >= 0 && nextMap[i][j - diff] == 0) ++diff;

                            if (j - diff >= 0) {
                                nextMap[i][j] = nextMap[i][j - diff];
                                nextMap[i][j - diff] = 0;
                            }
                        }

                        if (j != N - 1 && nextMap[i][j + 1] == nextMap[i][j] && nextMap[i][j] != 0 && !alreadyMerged[i][j + 1]) {
                            nextMap[i][j + 1] *= 2;
                            nextMap[i][j] = 0;
                            maxBlock = Math.max(maxBlock, nextMap[i][j + 1]);
                            alreadyMerged[i][j + 1] = true;
                            j++;
                        }

                    }
                }

            } else {

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (nextMap[i][j] == 0) {
                            int diff = 1;
                            while (j + diff < N && nextMap[i][j + diff] == 0) ++diff;
                            if (j + diff < N) {
                                nextMap[i][j] = nextMap[i][j + diff];
                                nextMap[i][j + diff] = 0;
                            }
                        }

                        if (j != 0 && nextMap[i][j - 1] == nextMap[i][j] && nextMap[i][j] != 0 && !alreadyMerged[i][j - 1]) {
                            nextMap[i][j - 1] *= 2;
                            nextMap[i][j] = 0;
                            maxBlock = Math.max(maxBlock, nextMap[i][j - 1]);
                            alreadyMerged[i][j - 1] = true;
                            j--;
                        }

                    }
                }

            }

//            System.out.println("nextMap");
//            for (int i = 0; i < N; i++) {
//                System.out.println(Arrays.toString(nextMap[i]));
//            }

            moveBlocks(nextMap, count + 1);
        }

    }

}
