import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static int N;
	static int M;
	static int[][] board;
	static int[] parent;
	static Map<Integer, Integer> map = new HashMap<>(); // key 는 parent입니다. 그리고 value 는 넓이
	
	static int[] row = {0, 0, 1, -1};
	static int[] col = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M]; // 이걸 union-find로 변환합니다. 그리고 비교를 합니다. 
		parent = new int[N*M];
		boolean[][] visited = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				parent[i * M + j] = i * M + j;
			}
		}
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 계속 1 1000 * 1000 * 1000^2 = TLE
		
		//일단 bfs로 구현을 합니다
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(board[i][j] == 1 && !visited[i][j]) {
					bfs(i, j, visited);
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		for(int x=0;x<N;x++) {
			for(int y=0;y<M;y++) {
				if(board[x][y] == 0) {
					int cnt = 1;
					Set<Integer> set = new HashSet<>();
					for(int i=0;i<4;i++) {
						int nx = x + row[i];
						int ny = y + col[i];
						
						if(0 <= nx && nx < N && 0 <= ny && ny < M) {
							if(board[nx][ny] == 1 && !set.contains(getParent(nx * M + ny))) {
								cnt += (map.get(getParent(nx * M + ny)));
								set.add(getParent(nx * M + ny));
							}
						}
					}
					
					//bw.write(x + " " + y + " " + cnt +"\n");
					max = Math.max(max, cnt);
				}
			}
		}
		
		bw.write(max + "\n");

		bw.flush();
	}
	
	public static int getParent(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return parent[x] = getParent(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = getParent(x);
		y = getParent(y);
		
		if(x == y) {
			return;
		}
		
		if(x < y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
	}
	
	
	
	public static void bfs(int x, int y, boolean[][] visited) {
		Queue<int[]> queue = new LinkedList<>();
		
		int cnt = 0;
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			cnt++;
			for(int i=0;i<4;i++) {
				int nx = cur[0] + row[i];
				int ny = cur[1] + col[i];
				
				if(0 <= nx && nx < N && 0 <= ny && ny < M) {
					if(board[nx][ny] == 1 && !visited[nx][ny]) {
						visited[nx][ny] = true;
						queue.add(new int[] {nx, ny});
						union(cur[0] * M + cur[1], nx * M + ny);
					}
				}
			}
		}
		
		map.put(getParent(x * M + y), cnt);
	}
}
