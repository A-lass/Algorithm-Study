import java.io.*;
import java.util.*;

public class Main {

    static class Node{
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());

        int[] A = new int[K];
        int[] B = new int[K];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        List<Node>[] g = new ArrayList[N + 1];

        for(int i=0;i<=N;i++){
            g[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            g[v1].add(new Node(v2, dist));
            g[v2].add(new Node(v1, dist));
        }

        int[] dist = dijkstra(start, g);


        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;

        for(int i=0;i<K;i++){
            minA = Math.min(minA, dist[A[i]]);
            minB = Math.min(minB, dist[B[i]]);
        }

        if (minA == Integer.MAX_VALUE && minB == Integer.MAX_VALUE) {
            bw.write(-1 + "\n");
        } else{
            if(minA <= minB){
                bw.write("A\n");
                bw.write(minA + "\n");
            } else{
                bw.write("B\n");
                bw.write(minB + "\n");
            }
        }

        bw.flush();
    }

    public static int[] dijkstra(int start, List<Node>[] g) {
        int[] dist = new int[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.dist - o2.dist;
            }
        });

        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.v] < cur.dist) {
                continue;
            }

            for(int i=0;i<g[cur.v].size();i++){
                int next = g[cur.v].get(i).v;
                int cost = g[cur.v].get(i).dist;

                if (dist[next] > cost + cur.dist) {
                    dist[next] = cost + cur.dist;
                    pq.add(new Node(next, dist[next]));
                }
            }
        }

        return dist;
    }


}
