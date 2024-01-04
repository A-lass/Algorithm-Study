import java.io.*;
import java.util.*;

public class Main {

    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();

        int len = str.length();

        int[] dp = new int[len];

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String sub = st.nextToken();
            int weight = Integer.parseInt(st.nextToken());

            if (sub.length() < weight) {
                map.put(sub, weight);
            }
        }

        if (map.containsKey(String.valueOf(str.charAt(0)))) {
            dp[0] = map.get(String.valueOf(str.charAt(0)));
        } else{
            dp[0] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                String comp = str.substring(j, i + 1);
                int value = map.getOrDefault(comp, comp.length());

                if (j == 0) {
                    if (value > dp[i]) {
                        dp[i] = value;
                    }
                    continue;
                }

                value += dp[j - 1];

                if (value > dp[i]) {
                    dp[i] = value;
                }
            }
        }


        bw.write(dp[len - 1] + "\n");

        bw.flush();
    }


}
