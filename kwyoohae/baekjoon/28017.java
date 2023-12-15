import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] game = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				game[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < M; k++) {
					if (i == 0) {
						dp[i][k] = game[i][k];
						continue;
					}

					if (j != k) {
						if (dp[i][k] == 0) {
							dp[i][k] = dp[i-1][j] + game[i][k];
						} else {
							dp[i][k] = Math.min(dp[i][k], dp[i-1][j] + game[i][k]);
						}
					}
				}
			}
		}

		int answer = dp[N-1][0];
		for (int i = 1; i < M; i++) {
			answer = Math.min(answer, dp[N-1][i]);
		}
		System.out.println(answer);
	}

	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}
