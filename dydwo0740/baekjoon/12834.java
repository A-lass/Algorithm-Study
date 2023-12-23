import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int V = Integer.parseInt(st.nextToken());

        int E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());

        int B = Integer.parseInt(st.nextToken());

        int[] home = new int[N];

        List<Node>[] g = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            g[i] = new ArrayList<>();
        }


        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            g[v1].add(new Node(v2, dist));
            g[v2].add(new Node(v1, dist));
        }
        int ans = 0;

        for(int i=0;i<N;i++){
            ans += dijkstra(g, home[i], A, B, V);
        }

        bw.write(ans + "\n");

        bw.flush();
    }

    public static int dijkstra(List<Node>[] g, int start, int A, int B, int V) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.add(new int[]{start, 0});
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[1] > dist[cur[0]]) {
                continue;
            }

            for (int i = 0; i < g[cur[0]].size(); i++) {
                Node next = g[cur[0]].get(i);
                if (dist[next.v] > cur[1] + next.dist) {
                    dist[next.v] = cur[1] + next.dist;
                    pq.add(new int[]{next.v, dist[next.v]});
                }
            }
        }

        int sum = 0;

        sum += (dist[A] == Integer.MAX_VALUE) ? -1 : dist[A];
        sum += (dist[B] == Integer.MAX_VALUE) ? -1 : dist[B];

        return sum;
    }


}
