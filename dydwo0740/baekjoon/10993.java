import java.io.*;
import java.util.*;

public class Main {

	static int[] height = new int[11];
	static int[] width = new int[11];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int idx = Integer.parseInt(st.nextToken());
		
		int origin = idx;

		height[1] = 1;
		width[1] = 1;
		for (int i = 2; i <= 10; i++) {
			height[i] = (height[i - 1] * 2) + 1;
			width[i] = (width[i - 1] + 2) * 2 - 1;
		}

		int N = height[idx];
		int M = width[idx];

		int[][] board = new int[N][M];

		if (idx == 1) {
			bw.write("*");
			bw.flush();
			return;
		}

		int x = 0;
		int y = 0; 

		boolean flag = true;
		if (idx % 2 == 1) {
			x = N - 1;
			flag = false;
		}

		while (idx >= 2) {
			if (idx == 2) {
				int index = 0;
				for (int i = x; i < x + height[idx]; i++) {
					for (int j = y; j < y + width[idx] - index; j++) {
						board[i][j] = 1;
					}
					y++;
					index += 2;
				}
				break;
			}

			int index = 2;
			if (!flag) {
				int beforeX = x;
				int beforeY = y;
				for (int j = y; j < y + width[idx]; j++) {
					board[x][j] = 1;
				}
				x--;
				y++;
				for (int i = x; i > x - height[idx]; i--) {
					for (int j = y; j < y + width[idx] - index; j++) {
						if (j == y || j + 1 == y + width[idx] - index) {
							board[i][j] = 1;
						}
					}
					y++;
					index += 2;
				}
				x = (-height[idx] + 2 * beforeX + 1) / 2;
				y = (y + beforeY) / 2;
				flag = !flag;
				idx--;
				continue;
			}
			int beforeX = x;
			int beforeY = y;
			for (int j = y; j < y + width[idx]; j++) {
				board[x][j] = 1;
			}
			x++;
			y++;
			for (int i = x; i < x + height[idx]; i++) {
				for (int j = y; j < y + width[idx] - index; j++) {
					if (j == y || j + 1 == y + width[idx] - index) {
						board[i][j] = 1;
					}
				}
				y++;
				index += 2;
			}

			x = (height[idx] + 2 * beforeX - 1) / 2;
			y = (y + beforeY) / 2;
			flag = !flag;
			idx--;
		}
		if (origin % 2 == 1) {
			for(int i=0;i<M;i++) {
				if(board[0][i] == 1) {
					M = i;
					break;
				}
			}
			int index = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M + index; j++) {
					if (board[i][j] == 1) {
						bw.write("*");
					} else {
						bw.write(" ");
					}
				}
				index++;
				bw.write("\n");
			}
		} else {
			int index = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M - index; j++) {
					if (board[i][j] == 1) {
						bw.write("*");
					} else {
						bw.write(" ");
					}
				}
				index++;
				bw.write("\n");
			}
		}

		bw.flush();
	}
}
