import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static String[] split;
    static String line;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int[][] inputCoordinates = new int[n][2];
        Set<Integer> originalCoodinates = new TreeSet<>();
        Map<Integer, Integer> compressionMap = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int a = nextInt();
            int b = nextInt();
            inputCoordinates[i][0] = a;
            inputCoordinates[i][1] = b;
            originalCoodinates.add(a);
            originalCoodinates.add(b);
        }
        int compressionValue = 0;
        for (int e : originalCoodinates) {
            compressionMap.put(e, compressionValue++);
        }
        int m = originalCoodinates.size();
        int[] compressedArray = new int[m];
        for (int i = 0; i < inputCoordinates.length; i++) {
            int[] coordinate = inputCoordinates[i];
            int left = compressionMap.get(coordinate[0]);
            int right = compressionMap.get(coordinate[1]);
            for (int j = left; j <= right; ++j) {
                compressedArray[j] = i + 1;
            }
        }
        boolean[] visited = new boolean[n + 1];
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int posterNum = compressedArray[i];
            if (!visited[posterNum]) {
                visited[posterNum] = true;
                ++ans;
            }
        }
        System.out.println(ans);
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
