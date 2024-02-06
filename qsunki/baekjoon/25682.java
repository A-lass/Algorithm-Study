import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		int k = Integer.parseInt(line[2]);
		int[][] boardDiffW = new int[n + 1][m + 1];
		int[][] boardDiffB = new int[n + 1][m + 1];
		int[][] pSumW = new int[n + 1][m + 1];
		int[][] pSumB = new int[n + 1][m + 1];

		for (int i = 1; i <= n; ++i) {
			char[] chars = br.readLine().toCharArray();
			for (int j = 1; j <= m; ++j) {
				if (i % 2 != 0 && j % 2 != 0) {
					if (chars[j - 1] == 'W') {
						boardDiffB[i][j] = 1;
					} else {
						boardDiffW[i][j] = 1;
					}
				} else if (i % 2 != 0 && j % 2 == 0) {
					if (chars[j - 1] == 'W') {
						boardDiffW[i][j] = 1;
					} else {
						boardDiffB[i][j] = 1;
					}
				} else if (i % 2 == 0 && j % 2 != 0) {
					if (chars[j - 1] == 'W') {
						boardDiffW[i][j] = 1;
					} else {
						boardDiffB[i][j] = 1;
					}
				} else {
					if (chars[j - 1] == 'W') {
						boardDiffB[i][j] = 1;
					} else {
						boardDiffW[i][j] = 1;
					}
				}
			}
		}

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				pSumW[i][j] = pSumW[i - 1][j] + pSumW[i][j - 1] - pSumW[i - 1][j - 1] + boardDiffW[i][j];
				pSumB[i][j] = pSumB[i - 1][j] + pSumB[i][j - 1] - pSumB[i - 1][j - 1] + boardDiffB[i][j];
			}
		}
		int ans = 4_000_000;
		for (int i = k; i <= n; ++i) {
			for (int j = k; j <= m; ++j) {
				int tmpW = pSumW[i][j] - pSumW[i - k][j] - pSumW[i][j - k] + pSumW[i - k][j - k];
				int tmpB = pSumB[i][j] - pSumB[i - k][j] - pSumB[i][j - k] + pSumB[i - k][j - k];
				ans = Integer.min(ans, Integer.min(tmpW, tmpB));
			}
		}
		System.out.println(ans);
	}
}
