import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	static long[] seq;
	static long[] dp;
	static boolean[] prime;
	static Set<Integer>[] set;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		set = new HashSet[N + 1];
		
		for(int i=1;i<=N;i++) {
			set[i] = new HashSet<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			set[v1].add(v2);
			set[v2].add(v1);
		}
		
		st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		
		List<Integer> city = new ArrayList<>();
		boolean[] visited = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<K;i++) {
			city.add(Integer.parseInt(st.nextToken()));
		}
		city.sort(Comparator.naturalOrder());
		List<Integer> res = new ArrayList<>();
		for(int i=1;i<=N;i++) {
            if(binarySearch(i, city)){
                continue;
            }
			List<Integer> deleteList = new ArrayList<>();
			Iterator<Integer> iter = set[i].iterator();
			boolean flag = true;
			while(iter.hasNext()) {
				int index = iter.next();
				if(!binarySearch(index, city)) {
					flag = false;
					break;
				}
				deleteList.add(index);
			}
			
			if(flag) {
				res.add(i);
				for(int idx : deleteList) {
					visited[idx] = true;
				}
                visited[i] = true;
			}
		}
		
		if(isPossible(city, visited)) {
			bw.write(res.size() + "\n");
			for(int i=0;i<res.size();i++) {
				bw.write(res.get(i) + " ");
			}
		} else {
			bw.write(-1 + "\n");
		}

		bw.flush();
	}
	
	public static boolean binarySearch(int target, List<Integer> city) {
		int left = 0;
		int right = city.size() - 1;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			if(city.get(mid) == target) {
				return true;
			} else if(city.get(mid) > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return false;
	}
	
	public static boolean isPossible(List<Integer> city, boolean[] visited) {
		for(int i=0;i<city.size();i++) {
			if(!visited[city.get(i)]) {
				return false;
			}
		}
		return true;
	}
}
