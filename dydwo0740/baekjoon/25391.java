import java.io.*;
import java.util.*;

public class Main {

    static class Point{
        int honor;
        int referee;

        public Point(int honor, int referee) {
            this.honor = honor;
            this.referee = referee;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o2.referee == o1.referee) {
                    return o2.honor - o1.honor;
                }
                return o2.referee -  o1.referee;
            }
        });

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int honor = Integer.parseInt(st.nextToken());
            int referee = Integer.parseInt(st.nextToken());

            pq.add(new Point(honor, referee));
        }

        long ans = 0;

        for(int i=0;i<K;i++){
            ans += pq.poll().honor;
        }

        PriorityQueue<Integer> honor = new PriorityQueue<>(Comparator.reverseOrder());

        while (!pq.isEmpty()) {
            honor.add(pq.poll().honor);
        }

        for(int i=0;i<M;i++){
            ans += honor.poll();
        }

        bw.write(ans + "\n");

        bw.flush();
    }

}
