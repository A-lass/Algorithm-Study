import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		List<Bus> buses = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			Bus bus = new Bus(start, end, cost);
			buses.add(bus);
		}

		buses.sort((a, b) -> {
			if (a.start == b.start) {
				return a.end - b.end;
			}
			return a.start - b.start;
		});

		int start = buses.get(0).start;
		int end = buses.get(0).end;
		int cost = buses.get(0).cost;

		List<Bus> answer = new ArrayList<>();
		for (int i = 1; i < buses.size(); i++) {
			int oppStart = buses.get(i).start;
			int oppEnd = buses.get(i).end;
			int oppCost = buses.get(i).cost;

			if (oppStart > end) {
				answer.add(new Bus(start, end, cost));
				start = oppStart;
				end = oppEnd;
				cost = oppCost;
			} else {
				if (cost > oppCost)
					cost = oppCost;
				if (end < oppEnd)
					end = oppEnd;
			}
		}
		answer.add(new Bus(start, end, cost));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(answer.size() + " ");
		bw.newLine();
		for (Bus bus : answer) {
			bw.write(bus.start + " " + bus.end + " " + bus.cost);
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	private static class Bus {
		public int start;
		public int end;
		public int cost;

		public Bus(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
}
