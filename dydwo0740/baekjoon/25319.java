import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int S;
    static String id;


    static int[] row = {-1, 1, 0, 0};
    static int[] col = {0, 0, -1, 1};
    static char[] alpha = {'U', 'D', 'L', 'R'};

    static class Location {
        int x;
        int y;
        String move;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Location(int x, int y, String move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    static int ans = Integer.MAX_VALUE;
    static Map<Character, List<Location>> info = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());

        S = Integer.parseInt(st.nextToken());

        char[] count = new char[27];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < M; j++) {
                if (!info.containsKey(str.charAt(j))) {
                    List<Location> list = new ArrayList<>();
                    list.add(new Location(i, j));
                    info.put(str.charAt(j), list);
                    continue;
                }
                List<Location> list = info.get(str.charAt(j));
                list.add(new Location(i, j));
            }
        }


        st = new StringTokenizer(br.readLine());

        id = st.nextToken();

        for (int i = 0; i < id.length(); i++) {
            count[id.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 27; i++) {
            if (count[i] > 0) {
                if(!info.containsKey((char) ('a' + i))){
                    ans = 0;
                    continue;
                }
                ans = Math.min(ans, info.get((char) ('a' + i)).size() / count[i]);
            }
        }

        String move = "";
        Location start = new Location(0, 0);

        for (int i = 0; i < ans; i++) {
            for (int j = 0; j < id.length(); j++) {
                int[] s = new int[2];
                int[] e = new int[2];
                Location end = info.get(id.charAt(j)).get(0);
                move += (bfs(new int[]{start.x, start.y}, new int[]{end.x, end.y}) + "P");
                start = new Location(end.x, end.y);
                info.get(id.charAt(j)).remove(0);
            }
        }

        move += bfs(new int[]{start.x, start.y}, new int[]{N-1, M-1});
        bw.write(ans + " " + move.length() + "\n");
        bw.write(move + "\n");
        bw.flush();
    }


    public static String bfs(int[] s, int[] e) {
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(s[0], s[1], ""));
        boolean[][] visited = new boolean[N][M];
        visited[s[0]][s[1]] = true;

        while (!queue.isEmpty()) {
            Location cur = queue.poll();

            if (cur.x == e[0] && cur.y == e[1]) {
                return cur.move;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + row[i];
                int ny = cur.y + col[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new Location(nx, ny, cur.move + String.valueOf(alpha[i])));
                    }
                }
            }
        }

        return null;
    }
}
