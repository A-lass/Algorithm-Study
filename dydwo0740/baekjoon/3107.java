import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String str = st.nextToken();
		
		String[] s = str.split(":");
		
		if(s.length == 0) {
			String ans = "";
			for(int i=0;i<7;i++) {
				ans += "0".repeat(4) + ":";
			}
			ans += "0".repeat(4);
			bw.write(ans+"\n");
			bw.flush();
			return;
		}
		
		if(s.length == 8) {
			for(int i=0;i<s.length;i++) {
				s[i] = "0".repeat(4 - s[i].length()) + s[i];
			}
			
			for(int i=0;i<s.length;i++) {
				if(i==s.length-1) {
					bw.write(s[i]+"\n");
					continue;
				}
				bw.write(s[i]+":");
			}
		} else {
			String[] ans = new String[8];
			for(int i=0;i<8;i++) {
				ans[i] = "0".repeat(4);
			}
			int index = 0;
			while(index < s.length && !s[index].isEmpty()) {
				ans[index] = "0".repeat(4 - s[index].length()) + s[index];
				index++;
			}
			int end = 7;
			int idx = s.length - 1;
			while(index < idx) {
				ans[end] = "0".repeat(4 - s[idx].length()) + s[idx];
				idx--;
				end--;
			}
			
			
			for(int i=0;i<ans.length;i++) {
				if(i==ans.length-1) {
					bw.write(ans[i]+"\n");
					continue;
				}
				bw.write(ans[i]+":");
			}
		}
		
		

		bw.flush();

	}
	
	
}
