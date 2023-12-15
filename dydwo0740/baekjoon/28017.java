import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][M];


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < M; k++) {
                    if (j == k) {
                        continue;
                    }
                    min = Math.min(min, dp[i - 1][k]);
                }
                int num = Integer.parseInt(st.nextToken());
                dp[i][j] = min + num;
            }

        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            ans = Math.min(dp[N][i], ans);
        }
        bw.write(ans + "\n");
        bw.flush();
    }


}
