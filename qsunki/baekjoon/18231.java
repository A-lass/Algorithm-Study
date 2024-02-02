import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		Graph graph = new Graph(n + 1);
		for (int i = 0; i < m; ++i) {
			line = br.readLine().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			graph.addEdge(u, v);
		}
		int k = Integer.parseInt(br.readLine());
		boolean[] destroyed = new boolean[n + 1];
		int[] destroyedCities = new int[k];
		boolean[] visited = new boolean[n + 1];
		List<Integer> ans = new ArrayList<>();
		line = br.readLine().split(" ");
		for (int i = 0; i < k; ++i) {
			int destroyedCity = Integer.parseInt(line[i]);
			destroyedCities[i] = destroyedCity;
			destroyed[destroyedCity] = true;
		}

		outer: for (int city : destroyedCities) {
			for (Integer neighbor : graph.getNeighbors(city)) {
				if (!destroyed[neighbor]) {
					continue outer;
				}
			}
			for (Integer neighbor : graph.getNeighbors(city)) {
				visited[neighbor] = true;
			}
			visited[city] = true;
			ans.add(city);
		}

//		System.out.println("ans: " + ans);
//		System.out.print("visited: ");
//		for (int i = 1; i <= n; ++i) {
//			if (visited[i]) {
//				System.out.print(i + " ");
//			}
//		}
//		System.out.println();
		if (!Arrays.equals(destroyed, visited)) {
			System.out.println(-1);
			return;
		}
		sb.append(ans.size()).append("\n");
		for (Integer e : ans) {
			sb.append(e).append(" ");
		}
		System.out.println(sb);
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

		List<Integer> getNeighbors(int u) {
			return adjList.get(u);
		}
	}

}
