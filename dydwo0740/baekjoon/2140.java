import java.io.*;
import java.util.*;

public class Main {

    static int[] row = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] col = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();

            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Character.isDigit(board[i][j])) {
                    changeBoard(board, i, j);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '#' || board[i][j] == '*') {
                    ans++;
                }
            }
        }
        bw.write(ans + "\n");
        bw.flush();
    }

    public static void changeBoard(char[][] board, int x, int y) {
        int N = board.length;
        int cur = board[x][y] - '0';

        for (int i = 0; i < 8; i++) {
            int nx = x + row[i];
            int ny = y + col[i];

            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                if (board[nx][ny] == '#' && cur > 0) {
                    board[nx][ny] = '*';
                    cur--;
                } else if (board[nx][ny] == '*' && cur != 0) {
                    cur--;
                } else if (board[nx][ny] == '#' && cur == 0) {
                    board[nx][ny] = '-';
                }
            }
        }
    }


}
