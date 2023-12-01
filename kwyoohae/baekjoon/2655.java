import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());

		StringTokenizer st;
		Block[] blocks = new Block[num + 1];
		int[] dp = new int[num + 1];

		blocks[0] = new Block(0, 0, 0,0 );
		for (int i = 1; i <= num; i++) {
			st = new StringTokenizer(br.readLine());

			int area = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			blocks[i] = new Block(i, area, height, weight);
		}

		Arrays.sort(blocks, (a,b) -> a.area - b.area);

		int maxHeight = 0;
		for (int i = 1; i <= num; i++) {
			for (int j = 0; j < i; j++) {

				if (blocks[i].weight > blocks[j].weight) {
					dp[i] = Math.max(dp[i], dp[j] + blocks[i].height);
				}
			}
			maxHeight = Math.max(maxHeight, dp[i]);
		}

		List<Integer> answer = new ArrayList<>();
		for (int i = num ; i > 0; i--) {
			if (maxHeight == dp[i]) {
				answer.add(blocks[i].num);
				maxHeight -= blocks[i].height;
			}
		}

		System.out.println(answer.size());
		for (int i = answer.size() - 1; i >= 0; i--){
			System.out.println(answer.get(i));
		}
	}

	public static class Block {
		public int num;
		public int area;
		public int height;
		public int weight;

		public Block(int num, int area, int height, int weight) {
			this.num = num;
			this.area = area;
			this.height = height;
			this.weight = weight;
		}
	}
}
