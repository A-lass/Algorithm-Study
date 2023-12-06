import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		List<Integer> killo3 = new ArrayList<>();
		List<Integer> killo5 = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int killo = Integer.parseInt(st.nextToken());
			int sugar = Integer.parseInt(st.nextToken());

			if (killo == 3) {
				killo3.add(sugar);
			} else {
				killo5.add(sugar);
			}
		}

		Collections.sort(killo3, Collections.reverseOrder());
		Collections.sort(killo5, Collections.reverseOrder());

		long[] sum3 = new long [killo3.size() + 1];
		long[] sum5 = new long [killo5.size() + 1];

		for (int i = 1; i <= killo3.size(); i++) {
			sum3[i] = killo3.get(i -1) + sum3[i-1];
		}

		for (int i = 1; i <= killo5.size(); i++) {
			sum5[i] = killo5.get(i -1) + sum5[i-1];
		}

		long answer =0;
		int index3 = Math.min(killo3.size(), w/3);
		while (index3 >= 0) {
			int index5 = Math.min((w - 3 * index3) / 5, killo5.size());
			answer = Math.max(sum3[index3] + sum5[index5] , answer);
			index3--;
		}

		System.out.println(answer);
	}
}