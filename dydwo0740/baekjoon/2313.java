import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] sum;
    static int[] length;
    static int[][] answer;

    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        sum = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            sum[i] = new ArrayList<>();
        }

        length = new int[N];

        answer = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            length[i] = size;

            st = new StringTokenizer(br.readLine());


            int num = Integer.parseInt(st.nextToken());
            sum[i].add(0);
            sum[i].add(num);

            for (int j = 1; j < size; j++) {
                num = Integer.parseInt(st.nextToken());
                sum[i].add(num + sum[i].get(j));
            }
        }



        for (int i = 0; i < N; i++) {
            int max = Integer.MIN_VALUE;
            int left = -1;
            int right = -1;
            for (int j = 1; j <= length[i]; j++) {
                for (int k = j; k <= length[i]; k++) {
                    if (max < (sum[i].get(k) - sum[i].get(j - 1))) {
                        left = j;
                        right = k;
                        max = sum[i].get(k) - sum[i].get(j - 1);
                    } else if (max == (sum[i].get(k) - sum[i].get(j - 1)) && (right - left) > (k - j)) {
                        left = j;
                        right = k;
                    }
                }
            }

            ans += max;
            answer[i][0] = left;
            answer[i][1] = right;
        }

        bw.write(ans+"\n");

        for (int i = 0; i < N; i++) {
            bw.write(answer[i][0] + " " + answer[i][1] + "\n");
        }

        bw.flush();
    }

}
