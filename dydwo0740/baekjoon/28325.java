import java.io.*;

import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N];
        int[] empty = new int[N]; // 만약 절대 안들어가는 노드면 -1

        long ans = 0;
        int start = -1;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            ans += num;

            if (num != 0) {
                empty[i] = -1;
                visited[i] = true;
                start = i;
            }
        }

        if(start == -1){
            start = 0;
        }
        for (int i = 0; i < N; i++) {
            int idx = i + start >= N ? (i + start) % N : i + start;
            if (!visited[idx]) {
                visited[idx] = true;
                int left = idx - 1;
                int right = idx + 1;
                if (idx == 0) {
                    left = N - 1;
                } else if (idx == N - 1) {
                    right = 0;
                }

                if (empty[left] == -1 && empty[right] == -1) {
                    empty[idx] = 1;
                    ans++;
                } else if (empty[left] == 1 || empty[right] == 1) {

                } else {
                    empty[idx] = 1;
                    ans++;
                }

                //bw.write(i + " " + ans+" " + empty[left] + " " + empty[right] + "\n");
            }
        }

        bw.write(ans + "\n");

        bw.flush();
    }


}
