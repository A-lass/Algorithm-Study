import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	private static int[] parents;
	private static PriorityQueue<Node> pq;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String[] input = br.readLine().split(" ");
			int V = Integer.parseInt(input[0]);

			if (V == 0) {
				break;
			}
			parents = new int[V + 1];
			pq = new PriorityQueue<>();

			for (int i = 0; i < V - 1; i++) {
				input = br.readLine().split(" ");
				int from = convertToInt(input[0]);
				int edgeNum = Integer.parseInt(input[1]);
				for (int j = 1; j <= edgeNum; j++) {
					int to = convertToInt(input[j* 2]);
					int weight = Integer.parseInt(input[j * 2 + 1]);
					pq.add(new Node(from, to, weight));
				}
			}

			// Initialize
			for (int i = 1; i <= V; i++) {
				parents[i] = i;
			}

			int sum = 0;
			while (!pq.isEmpty()) {
				Node node = pq.poll();
				if (find(node.from) != find(node.to)) {
					union(node.from, node.to);
					sum += node.weight;
				}
			}
			System.out.println(sum);
		}
	}

	private static int convertToInt(String str) {
		return str.charAt(0) - 64;
	}

	private static void union(int from, int to) {
		int p1 = find(from);
		int p2 = find(to);

		if (p1 < p2)
			parents[p2] = p1;
		else
			parents[p1] = p2;

	}

	private static int find(int v) {
		if(parents[v] == v)
			return v;
		else
			return parents[v] = find(parents[v]);
	}
	private static class Node implements Comparable<Node> {
		public int from;
		public int to;
		public int weight;

		public Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
