import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		
		
		int[][] seq = new int[N][2];
		
		List<Integer> list = new ArrayList<>();
		Set<Integer> visited = new HashSet<Integer>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			seq[i][0] = Integer.parseInt(st.nextToken());
			seq[i][1] = Integer.parseInt(st.nextToken());
			
			if(!visited.contains(seq[i][0])) {
				visited.add(seq[i][0]);
				list.add(seq[i][0]);
			}
			
			if(!visited.contains(seq[i][1])) {
				visited.add(seq[i][1]);
				list.add(seq[i][1]);
			}
			
		}
		
		list.sort(Comparator.naturalOrder());
		
		//bw.write(list.size() + "\n");
		
		int[] answer = new int[list.size()];
		
		for(int i=0;i<N;i++) {
			int l = list.indexOf(seq[i][0]);
			int r = list.indexOf(seq[i][1]);
			
			while(l <= r) {
				answer[l++] = (i+1); 
			}
		}
		
		
		
		Set<Integer> set = new HashSet<>();
		
		for(int i=0;i<list.size();i++) {
			//bw.write(answer[i] + " " );
			set.add(answer[i]);
		}
		
		
		bw.write(set.size() + "\n");
		
		bw.flush();

	}

}
