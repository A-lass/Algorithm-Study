import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            pq.add(new int[]{d, t});
        }

        int[] cur = pq.poll();

        int std = cur[1] - cur[0];

        while (!pq.isEmpty()) {
            cur = pq.poll();

            if (std < cur[1]) {
                std -= cur[0];
            } else{
                std = cur[1] - cur[0];
            }
        }

        bw.write(std+"\n");

        bw.flush();
    }

}
