import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static String[] split;
    static String line;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int q = nextInt();
        DisjointSet set = new DisjointSet(n + 1);
        int[] paint = new int[n + 1];

        for (int i = 0; i < q; ++i) {
            int a = nextInt();
            int b = nextInt();
            int x = nextInt();

            while (a <= b) {
                if (paint[a] == 0) {
                    set.union(a, b);
                    paint[a] = x;
                    ++a;
                } else {
                    a = set.find(a);
                    set.union(a, b);
                    ++a;
                }
            }

        }
        for (int i = 1; i <= n; ++i) {
            sb.append(paint[i]).append(" ");
        }
        System.out.println(sb);

    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static class DisjointSet {
        int n;
        int[] parent;

        DisjointSet(int n) {
            this.n = n;
            parent = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        int find(int u) {
            if (u == parent[u]) {
                return u;
            }
            return parent[u] = find(parent[u]);
        }

        void union(int u, int v) {
            u = find(u);
            v = find(v);
            if (u < v) {
                parent[u] = v;
                return;
            }
            if (u > v) {
                parent[v] = u;
            }
        }
    }

}
