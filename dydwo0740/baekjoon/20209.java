
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Map<Integer, int[]> map = new HashMap<>();
	static Set<String> visited = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(st.nextToken());

		int[] seq = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int size = Integer.parseInt(st.nextToken());

			int[] index = new int[size];

			for (int j = 0; j < size; j++) {
				index[j] = Integer.parseInt(st.nextToken());
			}

			map.put(i + 1, index);
		}
		
		int answer = bfs(seq);
		
		bw.write(answer+"\n");

		bw.flush();
	}

	public static int bfs(int[] seq) {
		Queue<int[]> queue = new LinkedList<>();

		visited.add(getString(seq));
		queue.add(seq);
		int cnt = 0;

		while (!queue.isEmpty()) {
			Queue<int[]> temp = new LinkedList<>(queue);
			queue.clear();
			while (!temp.isEmpty()) {
				int[] cur = temp.poll();

				if (isAllSame(cur)) {
					return cnt;
				}
				
				for(Map.Entry<Integer, int[]> entry : map.entrySet()) {
					int[] add = entry.getValue();
					
					int[] index = Arrays.copyOf(cur, cur.length);
					
					for(int i=0;i<add.length;i++) {
						index[add[i]] += entry.getKey();
						index[add[i]] %= 5;
					}
					
					if(visited.contains(getString(index))) {
						continue;
					}
					
					visited.add(getString(index));
					queue.add(index);
				}
			}
			
			cnt++;
		}
		
		return -1;

	}

	public static String getString(int[] seq) {
		String str = "";

		for (int num : seq) {
			str += String.valueOf(num);
		}

		return str;
	}

	public static boolean isAllSame(int[] seq) {
		int std = seq[1];

		for (int i = 1; i < seq.length; i++) {
			if (seq[i] != std) {
				return false;
			}
		}

		return true;
	}

}
