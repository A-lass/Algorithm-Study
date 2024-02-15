import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String[] split;
	static String line;

	static int[][] grid;
	static int[][] adjArea;
	static int n;
	static int m;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };
	static int ans;

	public static void main(String[] args) throws IOException {
		split = br.readLine().split(" ");
		n = Integer.parseInt(split[0]);
		m = Integer.parseInt(split[1]);
		grid = new int[n][m];
		adjArea = new int[n][m];

		for (int i = 0; i < n; ++i) {
			split = br.readLine().split(" ");
			for (int j = 0; j < m; ++j) {
				grid[i][j] = Integer.parseInt(split[j]);
			}
		}

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (grid[i][j] == 1) {
					bfs(i, j);
				}
			}
		}

		System.out.println(ans + 1);

	}

	static void bfs(int x, int y) {
		List<int[]> blank = new ArrayList<>();
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		grid[x][y] = 2;
		int cnt = 1;
		while (!queue.isEmpty()) {
			int[] poll = queue.poll();
			int cx = poll[0];
			int cy = poll[1];
			for (int i = 0; i < 4; ++i) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || grid[nx][ny] == 2) {
					continue;
				}
				if (grid[nx][ny] == 0) {
					blank.add(new int[] { nx, ny });
					grid[nx][ny] = 2;
					continue;
				}
				grid[nx][ny] = 2;
				queue.offer(new int[] { nx, ny });
				++cnt;
			}
		}
		for (int[] xy : blank) {
			grid[xy[0]][xy[1]] = 0;
			adjArea[xy[0]][xy[1]] += cnt;
			ans = Math.max(ans, adjArea[xy[0]][xy[1]]);
		}
	}
}
