import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        line = br.readLine().split(" ");
        long[] cows = new long[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Long.parseLong(line[i]);
        }
        line = br.readLine().split(" ");
        outer:
        for (int i = 0; i < m; i++) {
            long amount = Integer.parseInt(line[i]);
            long height = 0L;
            for (int j = 0; j < n; j++) {
                if (height >= cows[j]) {
                    continue;
                }
                long nextAmount = amount + height - cows[j];
                if (nextAmount < 0) {
                    cows[j] += amount;
                    continue outer;
                }
                long tmp = height;
                height = cows[j];
                cows[j] += cows[j] - tmp;
                amount = nextAmount;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(cows[i]).append("\n");
        }
        System.out.println(sb);
    }
}
