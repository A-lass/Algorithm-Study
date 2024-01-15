import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());

			String before = st.nextToken();

			st = new StringTokenizer(br.readLine());

			String after = st.nextToken();

			int ans = 0;

			int zero1 = 0;
			int one1 = 0;
			int zero2 = 0;
			int one2 = 0;

			for (int i = 0; i < before.length(); i++) {
				if (before.charAt(i) == '0') {
					zero1++;
				} else if(before.charAt(i) == '1'){
					one1++;
				}

				if (after.charAt(i) == '0') {
					zero2++;
				} else if(after.charAt(i) == '1'){
					one2++;
				}
			}

			if (one1 > one2) {
				bw.write("Case " + test + ": " + -1 + "\n");
				continue;
			}
			
			int add = 0;
			
			// before 에 1이 더 많다..
			
			Map<Integer, Character> map = new HashMap<>();
			
			for(int i=0;i<before.length();i++) {
				if(before.charAt(i) == '0' && after.charAt(i) == '1' && zero1 > zero2) {
					zero1--;
					add++;
					map.put(i, '1');
				}
			}

			int restOne = one2 - one1;
			int restZero = zero2 - zero1;

			String comp = "";
			
			

			for (int i = 0; i < before.length(); i++) {
				if (before.charAt(i) == '?') {
					if (after.charAt(i) == '0' && restZero > 0) {
						restZero--;
						comp += String.valueOf('0');
					} else if (after.charAt(i) == '1' && restOne > 0) {
						restOne--;
						comp += String.valueOf('1');
					} else {
						if (restZero > 0) {
							comp += String.valueOf('0');
						} else {
							comp += String.valueOf('1');
						}
					}
					add++;
				} else {
					if(map.containsKey(i)) {
						comp += String.valueOf(map.get(i));
						continue;
					}
					comp += String.valueOf(before.charAt(i));
				}
			}

			
			int change = 0;
			for (int i = 0; i < comp.length(); i++) {
				if (comp.charAt(i) != after.charAt(i)) {
					change++;
				}
			}

			bw.write("Case " + test + ": " + (add+ (change / 2)) + "\n");

		}

		bw.flush();

	}

}
