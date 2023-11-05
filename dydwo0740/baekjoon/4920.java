import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {


    static int[][] row = {{0, 0, 0}, {0, 1, 0}, {0, 0, 1}, {0, 0, 1}, {0, 1, 0}};
    static int[][] col = {{1, 1, 1}, {1, 0, 1}, {1, 1, 0}, {1, 1, -1}, {1, 0, -1}};

    static int[][] board;
    static int N;
    static int max;
    static int test = 1;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            if (N == 0) {
                break;
            }

            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    solve(i, j);
                }
            }
            bw.write(test++ + ". " + max + "\n");
        }

        bw.flush();
    }

    public static void solve(int x, int y) {
        for (int i = 0; i < row.length; i++) {
            int type = 0;
            while (type++ < 4) {
                int sum = board[x][y];
                boolean flag = false;
                rotate(i);
                int nx = x;
                int ny = y;
                for (int j = 0; j < row[i].length; j++) {
                    nx += row[i][j];
                    ny += col[i][j];
                    if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                        sum += board[nx][ny];
                    } else {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    continue;
                }
                if (max < sum) {
                    max = sum;
                }
            }


        }
    }

    public static void rotate(int index) {
        for (int i = 0; i < row[index].length; i++) {
            int tempRow = row[index][i];
            int tempCol = col[index][i];

            row[index][i] = tempCol;
            col[index][i] = -tempRow;
        }
    }
}
