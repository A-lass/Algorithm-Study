import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int restCheeses = Integer.parseInt(st.nextToken());
		int restFries = Integer.parseInt(st.nextToken());
		
		int[][] orders = new int[N + 1][2];
		
		int[][][] dp = new int[N + 1][301][301];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			orders[i][0] = Integer.parseInt(st.nextToken());
			orders[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= 300; j++) {
				for(int k=0;k<=300;k++) {
					if(j < orders[i][0] || k < orders[i][1]) {
						dp[i][j][k] = dp[i-1][j][k];
						continue;
					}
					
					dp[i][j][k] = Math.max(dp[i-1][j][k], 1 + dp[i - 1][j - orders[i][0]][k - orders[i][1]]);
				}
			}
		}
		
		
		bw.write(dp[N][restCheeses][restFries] + "\n");
		
		
		bw.flush();
		bw.close();
		br.close();
	}
}
