import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String[] split;
	static String line;

	public static void main(String[] args) throws IOException {
		split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int k = Integer.parseInt(split[1]);
		int[] nums = new int[n + 1];
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; ++i) {
			nums[i + 1] = Integer.parseInt(br.readLine());
		}
		for (int i = 1; i <= n; ++i) {
			int maxi = 0;
			for (int j = 0; j < k; ++j) {
				if (i <= j) {
					break;
				}
				maxi = Math.max(maxi, nums[i - j]);
				dp[i] = Math.max(dp[i], dp[i - (j + 1)] + maxi * (j + 1));
			}
		}
		System.out.println(dp[n]);

	}

}
