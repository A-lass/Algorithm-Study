import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int [][] homework = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			homework[i][0] = Integer.parseInt(st.nextToken());
			homework[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(homework, (a, b) -> b[1] - a[1]);

		int remainTime = homework[0][1];
		for (int i = 0; i < n; i++) {
			remainTime = Math.min(homework[i][1], remainTime) - homework[i][0];
		}
		System.out.println(remainTime);
	}
}