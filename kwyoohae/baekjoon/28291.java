import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static int[] dw = {1, -1, 0, 0};
	public static int[] dh = {0, 0, 1, -1};

	private static final int DUST = 1;
	private static final int BLOCK = 2;
	private static final int LAMP = 3;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int W = Integer.parseInt(input[0]);
		int H = Integer.parseInt(input[1]);

		int repeatNumber = Integer.parseInt(br.readLine());

		List<Position> lamps = new ArrayList<>();
		List<Position> blocks = new ArrayList<>();
		int [][]map = new int[W][H];
		int [][]light = new int[W][H];
		boolean [][]visited = new boolean[W][H];

		for (int i = 0 ; i < repeatNumber; i++) {
			input = br.readLine().split(" ");
			int w = Integer.parseInt(input[1]);
			int h = Integer.parseInt(input[2]);

			if (input[0].equals("redstone_dust"))
				map[w][h] = DUST;
			else if (input[0].equals("redstone_block")) {
				map[w][h] = BLOCK;
				light[w][h] = 15;
				blocks.add(new Position(w, h));
				for (int j = 0; j < 4; j++) {
					int tw = w + dw[j];
					int th = h + dh[j];
					if (tw < 0 || tw > W - 1 || th < 0 || th > H - 1)
						continue;
					light[tw][th] = 15;
				}
			}
			else {
				map[w][h] = LAMP;
				lamps.add(new Position(w, h));
			}
		}

		Queue<Position> q = new LinkedList<>();
		for (Position block : blocks) {
			q.add(block);
		}

		while(!q.isEmpty()) {
			Position pollPosition = q.poll();
			int pollW = pollPosition.w;
			int pollH = pollPosition.h;
			if (!visited[pollW][pollH]) {
				visited[pollW][pollH] = true;
				int energy = light[pollW][pollH];
				for (int i = 0; i < 4; i++) {
					int w = pollW + dw[i];
					int h = pollH + dh[i];

					if (w < 0 || w > W - 1 || h < 0 || h > H - 1)
						continue;
					else if (map[w][h] == 0)
						continue;

					if ((map[w][h] == DUST || map[w][h] == LAMP) && light[w][h] < energy - 1) {
						light[w][h] = energy - 1;
					}
					if (map[w][h] == DUST)
						q.add(new Position(w, h));
				}

				if (checkLightOn(light, lamps)) {
					System.out.println("success");
					return;
				}
			}
		}

		System.out.println("failed");
	}

	private static boolean checkLightOn(int[][] light, List<Position> lamps) {
		for (Position lamp : lamps) {
			if (light[lamp.w][lamp.h] == 0)
				return false;
		}
		return true;
	}

	private static class Position {
		public int w;
		public int h;
		public Position(int w, int h) {
			this.w = w;
			this.h = h;
		}
	}
}
