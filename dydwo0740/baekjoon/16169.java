
import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int weight;
		int index;

		Node(int weight, int index) {
			this.weight = weight;
			this.index = index;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		List<Node>[] list = new ArrayList[101];
		for (int i = 1; i <= 100; i++) {
			list[i] = new ArrayList<>();
		}
		int[][][] rank = new int[101][101][2]; // 각 전송 시간과 노드의 index를
		int[][] dp = new int[101][101]; // 각 노드에 대해 최소 타임
		
		for(int i=1;i<=100;i++) {
			Arrays.fill(dp[i], Integer.MIN_VALUE);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			list[start].add(new Node(weight, i + 1));
		}

		for (int i = 0; i < list[1].size(); i++) {
			dp[1][i] = list[1].get(i).weight;
		}

		int idx = 2;
		while (!list[idx].isEmpty()) {
			for(int i=0;i<list[idx - 1].size();i++) {
				for(int j=0;j<list[idx].size();j++) {
					dp[idx][j] = Math.max(dp[idx][j], dp[idx - 1][i] + (int) Math.pow(Math.abs(list[idx].get(j).index - list[idx-1].get(i).index), 2) + list[idx].get(j).weight);
				}
			}
			idx++;
		}
		int ans = 0;
		for(int i=0;i<list[idx-1].size();i++) {
			ans = Math.max(ans, dp[idx - 1][i]);
		}
		bw.write(ans + "\n");
		bw.flush();
	}
}
