
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 형제 노드란 부모가 같습니다. 선형적으로 선정
		// 형제 노드!

		int N = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N];
		int[] count = new int[1000001];
		int[] answer = new int[N];
		
		Arrays.fill(count, 1);
		
		int max = 1;
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			seq[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max,  seq[i]);
		}
		
		int beforeNum = seq[0] - 1;
		boolean flag = true;
		
		if(N < max || beforeNum != 0) {
			flag = false;
		}
		
		for(int i=0;i<N;i++) {
			if(beforeNum == seq[i]) {
				answer[i] = ++count[seq[i]];
			} else if(beforeNum < seq[i]) {
				answer[i] = count[seq[i]] = 1;
			} else {
				answer[i] = ++count[seq[i]];
			}
			
			beforeNum = seq[i];
		}
		
		
		if(flag) {
			for(int i=0;i<N;i++) {
				bw.write(answer[i]+ " ");
			}
		} else {
			bw.write(-1+"\n");
		}
		

		bw.flush();
		br.close();
		bw.close();

	}

}
