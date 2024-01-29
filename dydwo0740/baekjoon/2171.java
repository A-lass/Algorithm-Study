
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] xxx = new int[N];
        int[] yyy = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            xxx[i] = Integer.parseInt(st.nextToken());
            yyy[i] = Integer.parseInt(st.nextToken());
        }

        int[] x = Arrays.copyOf(xxx, N);
        int[] y = Arrays.copyOf(yyy, N);

        boolean[][] isHere = new boolean[5000][5000];
        int[][] loc = new int[N][2];

        Arrays.sort(x);
        Arrays.sort(y);

        for (int i = 0; i < N; i++) {
            int nx = lowerBound(x, xxx[i]);
            int ny = lowerBound(y, yyy[i]);

            loc[i][0] = nx;
            loc[i][1] = ny;

            bw.write(nx + " " + ny + "\n");

            isHere[nx][ny] = true;
        }

        int ans = 0;

        for(int i=0;i<N;i++){
            for (int j = i + 1; j < N; j++) {
                if (loc[i][0] == loc[j][0] || loc[i][1] == loc[j][1]) {
                    continue;
                }

                if (isHere[loc[i][0]][loc[j][1]] && isHere[loc[j][0]][loc[i][1]]) {
                    bw.write(i + " " + j+"\n");
                    ans++;
                }
            }
        }

        bw.write((ans / 2)+"\n");

        bw.flush();
    }

    public static int lowerBound(int[] seq, int std){
        int left = 0;
        int right = seq.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (seq[mid] < std) {
                left = mid + 1;
            } else{
                right = mid;
            }
        }

        return left;
    }



}
