import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;
    static char[] alpha = {'L', 'R', 'U', 'D'};
    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};
    static int answer = Integer.MIN_VALUE;
    static String ans = "";


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        board = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sX = N / 2;
        int sY = N / 2;

        int eX = N / 2 + 1;
        int eY = eX;

        dfs(sX, sY, eX, eY, 0, "");

        bw.write(answer + "\n");
        bw.write(ans + "\n");
        bw.flush();
    }

    public static void dfs(int sX, int sY, int eX, int eY, int sum, String move) {
        if (sum > answer) {
            answer = sum;
            ans = move;
        }

        int type = -1;
        int max = -1;

        if (sX - 1 >= 1 && rowSum(sY, eY, sX - 1) >= 0) {
            if (max < rowSum(sY, eY, sX - 1)) {
                type = 0;
                max = rowSum(sY, eY, sX - 1);
            }
        }

        if (eX + 1 <= N && rowSum(sY, eY, eX + 1) >= 0) {
            if (max < rowSum(sY, eY, eX + 1)) {
                type = 1;
                max = rowSum(sY, eY, eX + 1);
            }
        }

        if (sY - 1 >= 1 && colSum(sX, eX, sY - 1) >= 0) {
            if (max < colSum(sX, eX, sY - 1)) {
                type = 2;
                max = colSum(sX, eX, sY - 1);
            }
        }

        if (eY + 1 <= N && colSum(sX, eX, eY + 1) >= 0) {
            if (max < colSum(sX, eX, eY + 1)) {
                type = 3;
                max = colSum(sX, eX, eY + 1);
            }
        }


        if (type == -1) {
            return;
        }

        if (type == 0) {
            dfs(sX - 1, sY, eX, eY, sum + rowSum(sY, eY, sX - 1), move + "U");
        } else if (type == 1) {
            dfs(sX, sY, eX + 1, eY, sum + rowSum(sY, eY, eX + 1), move + "D");
        } else if (type == 2) {
            dfs(sX, sY - 1, eX, eY, sum + colSum(sX, eX, sY - 1), move + "L");
        } else {
            dfs(sX, sY, eX, eY + 1, sum + colSum(sX, eX, eY + 1), move + "R");
        }


    }

    public static int rowSum(int start, int end, int row) {
        int sum = 0;
        while (start <= end) {
            sum += board[row][start++];
        }

        return sum;
    }

    public static int colSum(int start, int end, int col) {
        int sum = 0;
        while (start <= end) {
            sum += board[start++][col];
        }

        return sum;
    }


}
