
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String std = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		
		String comp = st.nextToken();
		
		int N = std.length();
		int M = comp.length();
		
		int[][] dp = new int[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(std.charAt(i - 1) == comp.charAt(j - 1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
					continue;
				} 
				
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		bw.write(dp[N][M]+"\n");

		bw.flush();
		br.close();
		bw.close();
	}

}
