import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> left = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
		PriorityQueue<int[]> right = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			if(S < v) {
				right.add(new int[] {v, w});
				continue;
			}
			
			left.add(new int[] {v, w});
		}
		
		int cost = 0;
		int hold = 0;
		int dist = 0;
		while(!left.isEmpty()) {
			if(left.peek()[1] + hold <= K) {// 만약 이거 다 더해도 K 가 안될때
				hold += left.peek()[1];
				dist = Math.max(dist, S - left.peek()[0]);
				left.poll();
				continue;
			}
			
			int[] cur = left.poll();
			cur[1] -= (K - hold);
			left.add(cur);
			
			dist = Math.max(dist, S - cur[0]);
			cost += (dist * 2);
			
			hold = 0;
			dist = 0;
		}
		
		cost += (2 * dist); 
		
		hold = 0;
		dist = 0;
		while(!right.isEmpty()) {
			if(right.peek()[1] + hold <= K) {// 만약 이거 다 더해도 K 가 안될때
				hold += right.peek()[1];
				dist = Math.max(dist, right.peek()[0] - S);
				right.poll();
				continue;
			}
			
			int[] cur = right.poll();
			cur[1] -= (K - hold);
			right.add(cur);
			
			dist = Math.max(dist, cur[0] - S);
			cost += (2 * dist);
			
			hold = 0;
			dist = 0;
		}
		
		cost += (2 * dist);
		
		bw.write(cost + "\n");
		
		bw.flush();
	}
}
