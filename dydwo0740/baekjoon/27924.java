import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer>[] g;
	static Set<Integer> leafNodes;
	static int[][] dist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		g = new ArrayList[N + 1];
		leafNodes = new HashSet<>();
		dist = new int[3][N + 1];
		
		for(int i=0;i<3;i++) {
			Arrays.fill(dist[i], -1);
		}
		
		for(int i=1;i<=N;i++) {
			g[i] = new ArrayList<>();
		}
		
		for(int i=0;i<N - 1;i++) {
			st = new StringTokenizer(br.readLine());
			
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			g[v1].add(v2);
			g[v2].add(v1);
		}
		
		for(int i=1;i<=N;i++) {
			if(g[i].size() == 1) {
				leafNodes.add(i);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int z = Integer.parseInt(st.nextToken());
		
		initialize(0, x, 0);
		initialize(1, y, 0);
		initialize(2, z, 0);
		
		Iterator<Integer> iter = leafNodes.iterator();
		
		boolean flag = true;
		
		while(iter.hasNext()) {
			int next = iter.next();
			
			if(dist[0][next] < dist[1][next] && dist[0][next] < dist[2][next]) {
				flag = false;
				break;
			}
		}
		
		if(flag) {
			bw.write("NO");
		} else {
			bw.write("YES");
		}

		bw.flush();
	}
	
	public static void initialize(int from, int to, int depth) {
		dist[from][to] = depth;
		for(int i = 0;i<g[to].size();i++) {
			if(dist[from][g[to].get(i)] != -1) {
				continue;
			}
			initialize(from, g[to].get(i), depth + 1);
		}
	}
	
}
