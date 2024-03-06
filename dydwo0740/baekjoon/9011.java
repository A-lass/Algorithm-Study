package swexperttest;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for(int test = 1;test<=T;test++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			int[] seq = new int[N];
			
			for(int i=0;i<N;i++) {
				seq[i] = Integer.parseInt(st.nextToken());
			}
			
			StringBuilder sb = new StringBuilder();
			
			List<Integer> list = new ArrayList<>();
			
			for(int i=N;i>=1;i--) {
				list.add(i);
			}
			
			int index = N - 1;
			int[] answer = new int[N];
			boolean flag = false;
			for(int i=N-1;i>=0;i--) {
				int diff = index - seq[i];
				
				if(diff < 0) {
					flag = true;
					break;
				}
				
				index--;
				answer[i] = list.remove(diff);
			}
			
			if(flag) {
				bw.write("IMPOSSIBLE\n");
			} else {
				for(int i=0;i<N;i++) {
					bw.write(answer[i] + " ");
				}
				bw.write("\n");
			}
		}

		
		bw.flush();
	}
}
