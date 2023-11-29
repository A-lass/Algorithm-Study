import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int M = Integer.parseInt(input[0]);
		int N = Integer.parseInt(input[1]);

		int [][]land = new int[M][N];

		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(input[j]);
				if (num > 0) {
					land[i][j] = -1;
				}
			}
		}

		int answer = 0;

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (land[i][j] == -1)
					continue;
				answer = Math.max(answer, getNumber(land, new Position(i, j, 1), M, N));
			}
		}

		System.out.println(answer);
	}

	private static int getNumber(int[][] land, Position position, int M, int N) {
		for (int i = 1; i <= Math.max(M, N); i++) {
			if (!isRectangle(land, i, M, N, position.x, position.y))
				return i;
		}
		return Math.max(M, N);
	}

	private static boolean isRectangle(int[][] land, int num, int M, int N, int x, int y) {
		if (x + num > M - 1 || y + num > N - 1)
			return false;

		if (land[x + num][y + num] == -1)
			return false;

		for (int i = 0; i < num; i++) {
			if (land[x + num][y + i] == -1 || land[x + i][y + num] == -1)
				return false;
		}
		return true;
	}

	private static class Position {
		public int x;
		public int y;
		public int num;

		public Position(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
}
