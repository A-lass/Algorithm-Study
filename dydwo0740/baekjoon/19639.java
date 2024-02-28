import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main {
	static class Node{
		int index;
		int value;
		
		Node(int index, int value){
			this.index = index;
			this.value = value;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int origin = M;
		
		List<Node> opposite = new ArrayList<>();
		List<Node> item = new ArrayList<>();
		
		for(int i=0;i<X;i++) {
			st = new StringTokenizer(br.readLine());
			
			int value = Integer.parseInt(st.nextToken());
			
			opposite.add(new Node(i + 1, value));
		}
		
		for(int i=0;i<Y;i++) {
			st = new StringTokenizer(br.readLine());
			
			int value = Integer.parseInt(st.nextToken());
			
			item.add(new Node(i + 1, value));
		}
		
		boolean flag = true;
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<X;i++) {
			Node target = opposite.get(i);
			
			M -= target.value;
			
			if(M <= 0) {
				flag = false;
				break;
			}
			
			sb.append(-target.index + "\n");
			
			while(true) {
				Node node = lowerBound(item, origin - M);
				
				int adder = node.value;
				int idx = node.index;
				
				if(adder == -1) {
					break;
				}
				
				sb.append(idx + "\n");
				M += adder;
			}
		}
		
		while(!item.isEmpty()) {
			sb.append(item.remove(0).index + "\n");
		}
		
		if(flag) {
			bw.write(sb.toString());
		} else {
			bw.write(0 + "\n");
		}
		
		
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static Node lowerBound(List<Node> nodes, int target) {
		int left = 0;
		int right = nodes.size();
		
		while(left < right) {
			int mid = (left + right) / 2;
			
			if(nodes.get(mid).value <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
			
		}
		
		if(left == 0) {
			return new Node(-1, -1);
		}
		
		return nodes.remove(left - 1);
	}

}
