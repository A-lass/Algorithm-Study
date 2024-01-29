import java.io.*;
import java.util.*;

public class Main {
	static List<Node>[] g;
	static int N;
	static int[] count;

	static class Node {
		int v;
		int w;

		Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		g = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());

			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			int rest = (e - 10) >= 0 ? (e - 10) : 0;

			g[v1].add(new Node(v2, (rest) * d + c));
		}

		int ans = dijkstra();
		if (ans == Integer.MAX_VALUE) {
			bw.write("It is not a great way.");
			bw.flush();
			return;
		}
		bw.write(ans + " " + count[N]);
		bw.flush();
	}

	public static int dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1, int[] o2) -> o1[1] - o2[1]);
		pq.add(new int[] {1, 0, 1});
		int[] dist = new int[N + 1];
		count = new int[N +1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(count, Integer.MAX_VALUE);
		dist[1] = 0;
		count[1] = 1;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(cur[1] > dist[cur[0]]) {
				continue;
			}
			
			for(int i=0;i<g[cur[0]].size();i++) {
				Node next = g[cur[0]].get(i);
				
				if(cur[1] + next.w < dist[next.v]) {
					dist[next.v]= cur[1] + next.w; 
					pq.add(new int[] {next.v, dist[next.v], cur[2] + 1});
					count[next.v] = cur[2] + 1; 
				} else if(cur[1] + next.w == dist[next.v]) {
					dist[next.v]= cur[1] + next.w; 
					pq.add(new int[] {next.v, dist[next.v], cur[2] + 1});
					count[next.v] = Math.min(cur[2] + 1, count[next.v]);
				}
			}
		}
		return dist[N];
	}

}
