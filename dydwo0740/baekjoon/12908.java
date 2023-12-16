import java.io.*;
import java.util.*;

public class Main {

    static class Location{
        int sx;
        int sy;
        int ex;
        int ey;

        public Location(int sx, int sy, int ex, int ey) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }
    }

    static int startX;
    static int startY;
    static int endX;
    static int endY;
    static long min = Long.MAX_VALUE;

    static List<Location>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        list = new ArrayList[6];

        for (int i = 0; i < 6; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < 5; i += 2) {
            st = new StringTokenizer(br.readLine());

            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            list[i].add(new Location(sx, sy, ex, ey));
            list[i + 1].add(new Location(ex, ey, sx, sy));
        }

        min = Math.abs(startX - endX) + Math.abs(startY - endY);

        for (int i = 1; i <= 6; i++) {
            permutation(new boolean[6], new int[i], 0);
        }


        bw.write(min + "\n");
        bw.flush();
    }

    public static void permutation(boolean[] visited, int[] output, int depth) {
        if (output.length == depth) {
            solve(output);
            return;
        }

        for (int i = 0; i < 6; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = i;
                permutation(visited, output, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void solve(int[] output) {
        long dist = 0;
        int curX = startX;
        int curY = startY;
        for (int i = 0; i < output.length; i++) {
            List<Location> lists = list[output[i]];
            dist += Math.abs(curX - lists.get(0).sx) + Math.abs(curY - lists.get(0).sy);
            dist += 10;
            curX = lists.get(0).ex;
            curY = lists.get(0).ey;
        }

        dist += Math.abs(curX - endX) + Math.abs(curY - endY);
        min = Math.min(min, dist);
    }



}
