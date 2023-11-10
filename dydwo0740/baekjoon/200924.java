import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    static int N;
    static int root;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 우선 기가 노드를 탐색하고 기가 노드에서 루트, 기가 노드에서 dfs로 가장 긴 거리 탐색

        N = Integer.parseInt(st.nextToken());

        root = Integer.parseInt(st.nextToken());

        List<Node>[] g = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            g[v1].add(new Node(v2, dist));
            g[v2].add(new Node(v1, dist));
        }

        for (int i = 0; i <= N; i++) {
            g[i].sort(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.v - o2.v;
                }
            });
        }

        boolean[] visited = new boolean[N + 1];
        int start = findGigaNode(g, root, visited);
        //System.out.println("start = " + start);

        dfs(g, visited, 0, start);
        bw.write(getHeight(root, start, g) + " " + ans + "\n");

        bw.flush();
    }

    public static int findGigaNode(List<Node>[] g, int root, boolean[] visited) {
        while (true) {
            visited[root] = true;
            int count = 0;
            int next = 0;
            for (int i = 0; i < g[root].size(); i++) {
                if (!visited[g[root].get(i).v]) {
                    next = g[root].get(i).v;
                    count++;
                }
            }
            //System.out.println("next = " + next);
            if (count >= 2) {
                return root;
            }

            if(count == 0){
                return root;
            }
            root = next;
        }
    }

    public static int getHeight(int start, int end, List<Node>[] g) {
        boolean[] visited = new boolean[N + 1];
        int sum = 0;
        while (true) {
            if (start == end) {
                return sum;
            }

            visited[start] = true;
            int next = 0;

            for (int i = 0; i < g[start].size(); i++) {
                if (!visited[g[start].get(i).v]) {
                    next = g[start].get(i).v;
                    sum += g[start].get(i).dist;
                    break;
                }
            }
            start = next;
        }
    }

    public static void dfs(List<Node>[] g, boolean[] visited, int depth, int cur) {
        visited[cur] = true;
        ans = Math.max(ans, depth);
        for (int i = 0; i < g[cur].size(); i++) {
            if (!visited[g[cur].get(i).v]) {
                dfs(g, visited, depth + g[cur].get(i).dist, g[cur].get(i).v);
            }
        }
    }


}
