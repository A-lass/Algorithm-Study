import java.io.*;
import java.util.*;

public class Main {

    static class Node{
        int v;
        int cost;
        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static int N;
    static int M;
    static List<Node>[] g;
    static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            M = Integer.parseInt(st.nextToken());

            g = new ArrayList[M];


            for(int i=0;i<M;i++){
                g[i] = new ArrayList<>();
            }

            prev = new int[M];
            Arrays.fill(prev, -1);

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                g[v1].add(new Node(v2, cost));
                g[v2].add(new Node(v1, cost));
            }

            int[] dist = dijkstra();
            StringBuilder sb = new StringBuilder();

            Stack<Integer> stack = new Stack<>();
            stack.push(M - 1);
            for (int i = (M-1); i > 0;) {
                stack.push(i = prev[i]);
            }

            if (dist[M-1] == Integer.MAX_VALUE) {
                bw.write("Case #" + test + ": " + -1 + "\n");
                continue;
            }

            bw.write("Case #" + test + ":");
            while(!stack.empty()) {
                bw.write(" " + stack.pop());
            }
            bw.write("\n");



        }


        bw.flush();
    }

    public static int[] dijkstra() {
        int[] dist = new int[M];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        pq.add(new Node(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dist[cur.v]) {
                continue;
            }

            for (int i = 0; i < g[cur.v].size(); i++) {
                int next = g[cur.v].get(i).v;
                int cost = g[cur.v].get(i).cost;
                if (dist[next] > cur.cost + cost) {
                    dist[next] = cur.cost + cost;
                    prev[next] = cur.v;
                    pq.add(new Node(next, dist[next]));
                }
            }
        }

        return dist;
    }

}
