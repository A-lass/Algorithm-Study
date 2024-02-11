import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String[] split;
    static String line;

    static String[][] splits;


    public static void main(String[] args) throws IOException {
        split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[][] dp = new int[n][m];
        int[][] nums = new int[n][m];

        splits = new String[m][];
        for (int i = 0; i < m; i++) {
            splits[i] = br.readLine().split(" ");
            nums[0][i] = Integer.parseInt(splits[i][0]);
            dp[0][i] = nums[0][i];
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = Integer.parseInt(splits[j][i]);
            }

            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    if (j != k) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + nums[i][j]);
                    }
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + nums[i][j] / 2);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            ans = Math.max(ans, dp[n - 1][i]);
        }
        System.out.println(ans);
    }

}
