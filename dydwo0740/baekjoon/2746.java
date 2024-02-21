import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static Map<Long, Long> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		long[] seq = new long[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			seq[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(seq);

		long cnt = 0;

		// 어차피 맨뒤 or 그 앞 or 그 앞앞 즉, N, N-1, N-2 가 가장 큰 것이 됩니다

		// N 에 대해서
		long total = 0;
		map = new HashMap<>();

		for (int i = 0; i < N - 1; i++) {
			total += seq[i];
			map.put(seq[i], map.getOrDefault(seq[i], 0L) + 1);
		}

		total -= seq[N - 1];

		for (int i = 0; i < N - 1; i++) {
			long other = total - seq[i];

			if (other <= 0) {
				continue;
			}

			if (other != seq[i]) {
				if (map.containsKey(seq[i]) && map.containsKey(other)) {
					cnt += map.get(other) * map.get(seq[i]);
					map.remove(seq[i]);
					map.remove(other);
				}
			} else {
				if (map.containsKey(other) && map.get(other) > 1L) {
					cnt += getComb(map.get(seq[i]));
					map.remove(seq[i]);
				}
			}
		}

		total = 0;
		map.clear();

		for (int i = 0; i < N - 2; i++) {
			total += seq[i];
			map.put(seq[i], map.getOrDefault(seq[i], 0L) + 1);
		}

		total -= seq[N - 2];
		cnt += map.getOrDefault(total, 0L);

		total = 0;
		map.clear();

		for (int i = 0; i < N - 3; i++) {
			total += seq[i];
		}

		total -= seq[N - 3];

		if(total == 0) {
			cnt++;
		}

		
		bw.write(cnt + "\n");

		bw.flush();
	}
	
	public static long getComb(long num) {
		
		return num * (num - 1) / 2;
	}
}
