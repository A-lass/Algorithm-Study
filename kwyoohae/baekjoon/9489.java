import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int K = Integer.parseInt(input[1]);

			if (N == 0 && K == 0) {
				break;
			}

			input = br.readLine().split(" ");

			int[] nodes = new int[N + 1];
			int[] parents = new int[N + 1];
			parents[0] = -1;

			int kIndex = 0;
			for (int i = 1; i <= N; i++) {
				nodes[i] = Integer.parseInt(input[i-1]);
				if (nodes[i] == K)
					kIndex = i;
			}

			int parentIndex = 1;
			parents[1] = 0;
			for (int i = 2; i <= N; i++) {
				parents[i] = parentIndex;
				if (i < N && nodes[i] + 1 != nodes[i + 1])
					parentIndex++;
			}

			int answer = 0;
			for (int i = 1; i <= N; i++) {
				if (parents[parents[i]] == parents[parents[kIndex]] && parents[i] != parents[kIndex]) {
					answer++;
				}
			}

			System.out.println(answer);
		}
	}

}

/*
13 1
1 3 4 5 7 8 9 11 12 14 17 18 20
 */