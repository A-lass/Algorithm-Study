import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N + 1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 1];
		
		dp[1] = seq[1];
		
		if(N >= 2) {
			dp[2] = Math.max(seq[1], seq[2]) * 2;
		}
		
		for(int i=3;i<=N;i++) {
			int max = seq[i];
			
			for(int j=0;j<K;j++) {
				if(j == i) {
					break;
				}
				max = Math.max(max, seq[i - j]);
				dp[i] = Math.max(dp[i], dp[i-(j+1)] + max * (j + 1));
			}
			
		}
		
		
		
		
		bw.write(dp[N] + "\n");
		bw.flush();
		
	}

}
