import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new int[]{start, end});
        }

        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(list.get(0)[1]);

        int max = 1;
        int start = list.get(0)[0];
        int end = list.get(0)[1];

        for (int i = 1; i < N; i++) {
            int[] cur = list.get(i);

            while (!pq.isEmpty() && pq.peek() <= cur[0]) {
                pq.poll();
            }

            pq.add(cur[1]);

            if (max < pq.size()) {
                start = cur[0];
                end = pq.peek();
                max = pq.size();
            } else if (max == pq.size()) {
                if (end == cur[0]) {
                    end = pq.peek();
                }
            }
        }

        bw.write(max + "\n");
        bw.write(start + " " + end + "\n");

        bw.flush();
    }


}
