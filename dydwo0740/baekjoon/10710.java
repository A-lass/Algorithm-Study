import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        int[] dist = new int[N];
        int[] cost = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            dist[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][M]; // N 번째 장소를 M에 지나옴!

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }


        for (int i = 0; i < M; i++) {
            dp[0][i] = dist[0] * cost[i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                for(int k=0; k<j; k++){
                    if (dp[i - 1][k] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i-1][k] + cost[j] * dist[i], dp[i][j]);

                }
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = N - 1; i < M; i++) {
            min = Math.min(min, dp[N - 1][i]);
        }

        bw.write(min + "\n");

        bw.flush();

    }
}
