import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	static Map<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		List<Integer> list = new ArrayList<>();
		List<Integer> answer = new ArrayList<>();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
				continue;
			}

			map.put(num, 1);
			list.add(num);
		}

		list.sort(Comparator.naturalOrder());
		answer.addAll(list);
		
		int max = 0;
		for (int i = 0; i < list.size(); i++) {
			max += (list.get(i) * map.get(list.get(i)));
		}

		int desc = 0;
		for(int i=0;i<list.size();i++) {
			desc = Math.max(desc, list.get(i) * map.get(list.get(i)));
		}
		
		max += desc;
		
		for (int i = 1; i < list.size(); i++) {
			int head = list.get(list.size() - i);
			list.remove(list.size() - i);
			list.add(head);
			int asc = 0;
			desc = 0;
			for(int j=0;j<list.size() - i;j++) {
				asc += (list.get(j) * map.get(list.get(j)));
			}
			
			for(int j=list.size() - i;j<list.size();j++) {
				desc += (list.get(j) * map.get(list.get(j)));
			}
			
			
			if(max < asc + desc) {
				max = asc + desc;
				answer.addAll(list);
			}
		}
		
		bw.write(max + "\n");
		
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<map.get(list.get(i));j++) {
				bw.write(list.get(i) + " ");
			}
		}

		bw.flush();
	}
}
