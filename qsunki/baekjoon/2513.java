import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int ans;
	static int n;
	static int k;
	static int s;
	static int[] students;
	static int maxLoc = 0;
	static int minLoc = 100_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		n = Integer.parseInt(split[0]);
		k = Integer.parseInt(split[1]);
		s = Integer.parseInt(split[2]);
		students = new int[100_001];
		for (int i = 0; i < n; ++i) {
			split = br.readLine().split(" ");
			int loc = Integer.parseInt(split[0]);
			int cnt = Integer.parseInt(split[1]);
			students[loc] = cnt;
			maxLoc = Integer.max(maxLoc, loc);
			minLoc = Integer.min(minLoc, loc);
		}
		fromMax();
		fromMin();

		System.out.println(ans);
	}

	static void fromMax() {
		if (maxLoc < s) {
			return;
		}
		int cap = k;
		int cur = maxLoc;
		int tmpMax = cur;
		while (cur > s) {
			if (students[cur] > 0) {
				if (cap == k) {
					tmpMax = cur;
				}
				if (cap >= students[cur]) {
					cap -= students[cur];
					students[cur] = 0;
					--cur;
				} else {
					students[cur] -= cap;
					cap = 0;
				}
				if (cap == 0) {
					ans += (tmpMax - s) * 2;
					cap = k;
				}
			} else {
				--cur;
			}
		}
		if (cap != k) {
			ans += (tmpMax - s) * 2;
		}
	}

	static void fromMin() {
		if (minLoc > s) {
			return;
		}
		int cap = k;
		int cur = minLoc;
		int tmpMin = cur;
		while (cur < s) {
			if (students[cur] > 0) {
				if (cap == k) {
					tmpMin = cur;
				}
				if (cap >= students[cur]) {
					cap -= students[cur];
					students[cur] = 0;
					++cur;
				} else {
					students[cur] -= cap;
					cap = 0;
				}
				if (cap == 0) {
					ans += (s - tmpMin) * 2;
					cap = k;
				}
			} else {
				++cur;
			}
		}
		if (cap != k) {
			ans += (s - tmpMin) * 2;
		}
	}
}
