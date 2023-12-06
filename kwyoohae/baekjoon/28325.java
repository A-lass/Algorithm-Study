import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long []ants = new long[N];
		String []input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			ants[i] = Long.parseLong(input[i]);
		}

		boolean []visited = new boolean[N];
		long answer1 = ants[0];
		if (ants[0] == 0) {
			visited[0] = true;
			answer1 = 1;
		}
		for (int i = 1; i < N; i++) {
			if (ants[i] > 0) {
				answer1 += ants[i];
			} else if (ants[i] == 0 && !visited[getIndex(N, i, 0)] && !visited[getIndex(N, i, 1)]) {
				visited[i]  = true;
				answer1++;
			}
		}

		long answer2 = 0;
		visited = new boolean[N];
		for (int i = 1; i < N; i++) {
			if (ants[i] > 0) {
				answer2 += ants[i];
			} else if (ants[i] == 0 && !visited[getIndex(N, i, 0)] && !visited[getIndex(N, i, 1)]) {
				visited[i]  = true;
				answer2++;
			}
		}

		System.out.println(Math.max(answer2, answer1));
	}

	public static int getIndex(int N, int index, int direction) {
		if (direction == 0) {
			if (index == 0)
				return N -1;
			return index - 1;
		}

		if (index == N -1)
			return 0;
		return index + 1;
	}
}