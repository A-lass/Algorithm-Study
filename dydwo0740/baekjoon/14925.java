import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;

    static int[][] down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        down = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<M;i++){
            if(board[N-1][i] == 0){
                down[N-1][i] = 1;
            }
        }

        for(int i=0;i<M;i++){
            for(int j=N-2;j>=0;j--){
                if(board[j][i] != 0){
                    continue;
                }

                if(board[j + 1][i] == 0){
                    down[j][i] = down[j + 1][i] + 1;
                } else{
                    down[j][i] = 1;
                }
            }
        }

       /*for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                bw.write(down[i][j] + " ");
            }
            bw.write("\n");
        }*/




        bw.write(binarySearch(board) + "\n");


        bw.flush();
    }

    public static int binarySearch(int[][] board) {
        int left = 1;
        int right = Math.min(board.length, board[0].length);

        while (left <= right) {
            int mid = (left + right) / 2;
            if (isTrue(board, mid)) {
                //System.out.println("mid = " + mid);
                left = mid + 1;
            } else{
                right = mid - 1;
            }

        }

        return left - 1;
    }

    public static boolean isTrue(int[][] board, int len) {
        for (int i = 0; i + len <= N; i++) {
            for (int j = 0; j + len <= M; j++) {
                int cnt = 0;
                for(int k=j;k<j+len;k++){
                    if (down[i][k] < len) {
                        cnt++;
                    }
                }

                if(cnt == 0){
                    return true;
                }
            }
        }

        return false;
    }


}
