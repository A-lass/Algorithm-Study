import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        BigInteger[] dpOneStart = new BigInteger[n + 1];
        BigInteger[] dpZeroStart = new BigInteger[n + 1];

        dpOneStart[1] = BigInteger.ZERO;
        dpZeroStart[1] = BigInteger.ONE;
        for (int i = 2; i <= n; ++i) {
            dpZeroStart[i] = dpOneStart[i - 1].add(dpZeroStart[i - 1]);

            if (i % 2 == 0) {
                dpOneStart[i] = dpOneStart[i - 1].add(dpZeroStart[i - 1]);
            } else {
                dpOneStart[i] = dpOneStart[i - 1].add(dpZeroStart[i - 1]).subtract(BigInteger.ONE);
            }
        }

        System.out.println(dpOneStart[n]);
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
}
