import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] cnts = new int[n + 1];
		String[] line = br.readLine().split(" ");
		int maxNum = 0;
		for (int i = 0; i < n; ++i) {
			int num = Integer.parseInt(line[i]);
			++cnts[num];
			if (maxNum * cnts[maxNum] < num * cnts[num]) {
				maxNum = num;
			}
		}
		int ans = maxNum * cnts[maxNum];
		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j < cnts[i]; ++j) {
				sb.append(i).append(" ");
				ans += i;
			}
		}
		System.out.println(ans);
		System.out.println(sb);
	}

}
