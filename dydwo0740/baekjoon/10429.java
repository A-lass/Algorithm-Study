import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};

    static int N;
    static int M;
    static char[][] board = new char[3][3];

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Node> list = new ArrayList<>();

    static List<Node> ans = new ArrayList<>();

    static boolean flag = false;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());


        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < 3; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ('0' <= board[i][j] && board[i][j] <= '9') {
                    boolean[][] visited = new boolean[3][3];
                    visited[i][j] = true;
                    list.add(new Node(i, j));
                    dfs(board[i][j] - '0', 1, false, i, j, visited, ' ');
                }

                if (flag) {
                    bw.write(1 + "\n");
                    for (Node node : ans) {
                        bw.write(node.x + " " + node.y + "\n");
                    }
                    bw.flush();
                    return;
                }
                list.clear();
            }
        }

        bw.write(0 + "\n");

        bw.flush();
    }

    public static void dfs(int cur, int depth, boolean isNum, int x, int y, boolean[][] visited, char ch) {
        if (depth == M && N == cur && !flag) {
            flag = true;
            for (Node node : list) {
                ans.add(node);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + row[i];
            int ny = y + col[i];

            if (0 > nx || nx >= 3 || 0 > ny || ny >= 3) {
                continue;
            }

            if (!visited[nx][ny] && (('0' <= board[nx][ny] && board[nx][ny] <= '9' && isNum))) {
                visited[nx][ny] = true;
                list.add(new Node(nx, ny));
                dfs(solve(ch, cur, board[nx][ny] - '0'), depth + 1, !isNum, nx, ny, visited, ch);
                list.remove(list.size() - 1);
                visited[nx][ny] = false;
            } else if (!visited[nx][ny] && (board[nx][ny] == '+' || board[nx][ny] == '-') && !isNum) {
                visited[nx][ny] = true;
                list.add(new Node(nx, ny));
                dfs(cur, depth, !isNum, nx, ny, visited, board[nx][ny]);
                visited[nx][ny] = false;
                list.remove(list.size() - 1);
            }
        }


    }

    public static int solve(char ch, int num1, int num2) {
        if (ch == '+') {
            return num1 + num2;
        }

        return num1 - num2;
    }

}
