import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String[] split;
	static String line;
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		StringBuilder a = new StringBuilder("Aa");
		StringBuilder b = new StringBuilder("BB");
		for (int i = 2; i < n; ++i) {
			a.append("A");
			b.append("A");
		}
		System.out.println(a);
		System.out.println(b);	
	}
}
