import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};

    static int N;
    static int M;
    static int Q;
    static int[][] board;
    static int[][] quest;

    static char[][] shape;
    static int answer = Integer.MAX_VALUE;
    static String ans = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;

        // 0이면 갈 수 있고, 1이면 못갑니다.

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j=0;j<M;j++){
                if (str.charAt(j) == '.') {

                } else if (str.charAt(j) == '@') {
                    board[i][j] = 1;
                } else if (str.charAt(j) == 'D') {
                    startX = i;
                    startY = j;
                } else{
                    endX = i;
                    endY = j;
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        Q = Integer.parseInt(st.nextToken());

        quest = new int[Q][2];
        shape = new char[Q][2];

        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int index = 0;

            String str1 = st.nextToken();
            String str2 = st.nextToken();

            if (str1.equals("W")) {
                shape[i][index] = 'W';
                quest[i][index++] = 1;
            } else if (str1.equals("A")) {
                shape[i][index] = 'A';
                quest[i][index++] = 3;
            } else if (str1.equals("S")) {
                shape[i][index] = 'S';
                quest[i][index++] = 0;
            } else{
                shape[i][index] = 'D';
                quest[i][index++] = 2;
            }

            if (str2.equals("W")) {
                shape[i][index] = 'W';
                quest[i][index++] = 1;
            } else if (str2.equals("A")) {
                shape[i][index] = 'A';
                quest[i][index++] = 3;
            } else if (str2.equals("S")) {
                shape[i][index] = 'S';
                quest[i][index++] = 0;
            } else{
                shape[i][index] = 'D';
                quest[i][index++] = 2;
            }
        }

        boolean[][] visited = new boolean[N][M];
        visited[startX][startY] = true;

        dfs(startX, startY, 0, endX, endY, "");

        bw.write(answer == Integer.MAX_VALUE ? "NO" : "YES" +"\n");

        if (answer != Integer.MAX_VALUE) {
            bw.write(ans+"\n");
        }

        bw.flush();
    }

    public static void dfs(int curX, int curY, int depth, int endX, int endY, String move){
        if(curX == endX && curY == endY && depth <= Q && answer > depth){
            answer = depth;
            ans = move;
            return;
        }

        if(depth < Q){
            for(int i=0;i<2;i++){
                int nx = curX + row[quest[depth][i]];
                int ny = curY + col[quest[depth][i]];

                if(0<= nx && nx < N && 0 <= ny && ny < M){
                    if (board[nx][ny] == 0) {
                        dfs(nx, ny, depth + 1, endX, endY, move + String.valueOf(shape[depth][i]));
                    }
                }
            }
        }

    }

    public static String change(int i){
        if(i==0){
            return "S";
        } else if(i == 1){
            return "W";
        } else if(i==2){
            return "D";
        }

        return "A";
    }


}
