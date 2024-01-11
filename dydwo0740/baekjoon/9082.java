import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int[] col = { 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int test = 1; test <= T; test++) {
			
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());

			int[] count = new int[N];
			char[] mine = new char[N];

			String str = br.readLine();

			for (int i = 0; i < N; i++) {
				int num = str.charAt(i) - '0';

				count[i] = num;
			}

			str = br.readLine();

			for (int i = 0; i < N; i++) {
				mine[i] = str.charAt(i);
			}
			
			int ans = 0;
			
			for(int i=0;i<N;i++) {
				if(i==0) {
					if(count[i] != 0 && count[i+1] != 0) {
						count[i]--;
						count[i+1]--;
						ans++;
					}
				} else if(i==N-1) {
					if(count[i] != 0 && count[i-1] != 0) {
						count[i]--;
						count[i-1]--;
						ans++;
					}
				} else {
					if(count[i] != 0 && count[i-1] != 0 && count[i+1] != 0) {
						count[i]--;
						count[i-1]--;
						count[i+1]--;
						ans++;
					}
				}
			}
			
			bw.write(ans+"\n");
			
		}

		bw.flush();

	}
}
