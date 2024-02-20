import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String[] split;
	static String line;

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		Graph graph = new Graph(n + 1);
		for (int i = 0; i < n - 1; ++i) {
			split = br.readLine().split(" ");
			int u = Integer.parseInt(split[0]);
			int v = Integer.parseInt(split[1]);
			graph.addEdge(u, v);
		}
		split = br.readLine().split(" ");
		int a = Integer.parseInt(split[0]);
		int b = Integer.parseInt(split[1]);
		int c = Integer.parseInt(split[2]);

		List<Integer> leafs = graph.getLeafs();
		int[] distanceFromA = graph.bfs(a);
		int[] distanceFromB = graph.bfs(b);
		int[] distanceFromC = graph.bfs(c);

		String ans = "NO";
		for (Integer u : leafs) {
			if (distanceFromA[u] < distanceFromB[u] && distanceFromA[u] < distanceFromC[u]) {
				ans = "YES";
				break;
			}
		}
		System.out.println(ans);
	}

	static class Graph {
		int n;
		List<List<Integer>> adjList;

		Graph(int n) {
			this.n = n;
			adjList = new ArrayList<>();
			for (int i = 0; i < n; ++i) {
				adjList.add(new ArrayList<>());
			}
		}

		void addEdge(int u, int v) {
			adjList.get(u).add(v);
			adjList.get(v).add(u);
		}

		List<Integer> getLeafs() {
			List<Integer> leafs = new ArrayList<>();
			for (int i = 0; i < n; ++i) {
				if (adjList.get(i).size() == 1) {
					leafs.add(i);
				}
			}
			return leafs;
		}

		int[] dijkstra(int start) {
			int[] distance = new int[n];
			Arrays.fill(distance, Integer.MAX_VALUE);
			PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
			distance[start] = 0;
			pq.offer(new int[] { start, 0 });
			while (!pq.isEmpty()) {
				int[] poll = pq.poll();
				int u = poll[0];
				int d = poll[1];
				if (d > distance[u]) {
					continue;
				}
				for (Integer v : adjList.get(u)) {
					if (d + 1 < distance[v]) {
						distance[v] = d + 1;
						pq.offer(new int[] { v, d + 1 });
					}
				}
			}
			return distance;
		}

		int[] bfs(int start) {
			int[] distance = new int[n];
			boolean[] visited = new boolean[n];
			Queue<int[]> queue = new ArrayDeque<>();
			visited[start] = true;
			queue.offer(new int[] { start, 0 });
			while (!queue.isEmpty()) {
				int[] poll = queue.poll();
				int u = poll[0];
				int d = poll[1];
				for (Integer v : adjList.get(u)) {
					if (!visited[v]) {
						distance[v] = d + 1;
						visited[v] = true;
						queue.offer(new int[] { v, distance[v] });
					}
				}
			}
			return distance;
		}
	}
}
