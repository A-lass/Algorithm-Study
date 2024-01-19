
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static Map<Integer, Integer> map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] seq = new int[N];
		
		long sum = 0;
		
		for(int i=0;i<N;i++) {
			seq[i] = Integer.parseInt(st.nextToken());
			sum += (long) seq[i];
		}

		// 만약 마이너스가 아주 작다면 오히려 같은 그룹에 묶어서 배수의 효과를 봅니다.
		
		Arrays.sort(seq);
		
		long ans = sum * N;
		
		if(seq[0] < 0) {
			int index = 0;
			long before = 0;
			while(index < N && seq[index] < 0) {
				sum -= (long)seq[index];
				before += (long)seq[index];
				ans = Math.max(ans, sum * (N - index - 1) + before);
				index++;
			}
		}
		
		bw.write(ans+"\n");
		
		bw.flush();
	}
}
