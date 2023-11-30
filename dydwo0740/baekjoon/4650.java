import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;
    static class Node{
        int v1;
        int v2;
        int dist;

        public Node(int v1, int v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    static int ans = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            if (N == 0) {
                break;
            }


            PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.dist - o2.dist;
                }
            });

            parent = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());

                String str = st.nextToken();

                int cur = str.charAt(0) - 'A' + 1;

                int M = Integer.parseInt(st.nextToken());

                for (int j = 0; j < M; j++) {
                    str = st.nextToken();

                    int next = str.charAt(0) - 'A' + 1;
                    int dist = Integer.parseInt(st.nextToken());

                    pq.add(new Node(cur, next, dist));

                }
            }
            int sum = 0;
            while (!pq.isEmpty()) {
                Node node = pq.poll();
                sum += union(node.v1, node.v2, node.dist);
            }

            bw.write(sum+"\n");

        }


        bw.flush();
    }

    public static int getParent(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = getParent(parent[x]);
    }

    public static int union(int x, int y, int dist) {
        x = getParent(x);
        y = getParent(y);

        if(x == y){
            return 0;
        }

        if(x < y){
            parent[y] = x;
        } else {
            parent[x] = y;
        }

        return dist;
    }



}
