import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int value;
		int index;

		Node(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}

	/*
	 * 우선 각각 역으로 sorting index도 같이 저장합니다. 만약 value 가 같다면 ..? 먼저 오는 것으로
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		PriorityQueue<Node> seqA = new PriorityQueue<>((o1, o2) -> {
			if (o1.value == o2.value) {
				return o1.index - o2.index;
			}

			return o2.value - o1.value;
		});

		PriorityQueue<Node> seqB = new PriorityQueue<>((o1, o2) -> {
			if (o1.value == o2.value) {
				return o1.index - o2.index;
			}

			return o2.value - o1.value;
		});

		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());
			seqA.add(new Node(value, i));
		}

		st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			int value = Integer.parseInt(st.nextToken());
			seqB.add(new Node(value, i));
		}

		int prevA = -1;
		int prevB = -1;

		List<Integer> list = new ArrayList<>();

		while (!seqB.isEmpty() && !seqA.isEmpty()) {
			
			boolean flag = false;
			
			if(seqA.peek().index < prevA) {
				flag = true;
				seqA.poll();
			} 
			
			if(seqB.peek().index < prevB) {
				flag = true;
				seqB.poll();
			}
			
			if(flag) {
				continue;
			}

			if (seqA.peek().value == seqB.peek().value) {
				prevA = seqA.peek().index;
				prevB = seqB.peek().index;
				list.add(seqA.peek().value);
				seqA.poll();
				seqB.poll();
			} else if (seqA.peek().value > seqB.peek().value) {
				seqA.poll();
			} else {
				seqB.poll();
			}
		}
		
		bw.write(list.size() + "\n");
		
		for(int i=0;i<list.size();i++) {
			bw.write(list.get(i) + " ");
		}

		bw.flush();
	}
}
