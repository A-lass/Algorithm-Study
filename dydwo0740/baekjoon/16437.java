import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        long count;
        public Node(long count) {
            this.count = count;
        }
    }

    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        List<Integer>[] g = new ArrayList[N + 1];
        nodes = new Node[N + 1];

        nodes[1] = new Node(0);

        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();
            long count = Long.parseLong(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            g[v].add(i);

            if (str.equals("S")) {
                nodes[i] = new Node(count);
                continue;
            }
            nodes[i] = new Node(-count);
        }

        long ans = getCount(g, 1);

        bw.write(ans + "\n");

        bw.flush();
    }

    public static long getCount(List<Integer>[] g, int v) {
        long sum = 0;
        for (int cur : g[v]) {
            sum += getCount(g, cur);
        }

        if (sum + nodes[v].count >= 0) {
            return nodes[v].count + sum;
        }

        return 0;
    }


}
