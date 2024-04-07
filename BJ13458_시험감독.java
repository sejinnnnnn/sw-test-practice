import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이런 거 나오면 좋겠다
 */
public class BJ13458_시험감독 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] classes = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            classes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long answer = N;

        for (int i = 0; i < N; i++) {
            classes[i] -= B;
            if (classes[i] > 0) {
                answer += classes[i] / C;
                if (classes[i] % C != 0) ++answer;
            }
        }

        System.out.println(answer);

    }

}
