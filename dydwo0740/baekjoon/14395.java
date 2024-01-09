import java.io.*;
import java.util.*;

public class Main {
    static long N;
    static long M;

    static class Step {
        long num;
        String str = "";

        public Step(long num, String str) {
            this.num = num;
            this.str = str;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());

        M = Long.parseLong(st.nextToken());

        if (N == M) {
            bw.write(0 + "\n");
            bw.flush();
            return;
        }

        String answer = bfs();

        if (answer.equals("Impossible")) {
            bw.write("-1\n");
        } else{
            bw.write(answer + "\n");
        }


        bw.flush();
    }

    public static String bfs() {
        Queue<Step> queue = new LinkedList<>();
        Set<Long> set = new HashSet<>();
        boolean flag = true;

        queue.add(new Step(N, ""));

        set.add(N);

        while (!queue.isEmpty()) {
            Queue<Step> temp = new LinkedList<>(queue);
            queue.clear();

            while (!temp.isEmpty()) {
                Step cur = temp.poll();

                String curString = cur.str;

                if (cur.num == M) {
                    return cur.str;
                }

                if (cur.num > M) {
                    continue;
                }

                if (!set.contains(cur.num * cur.num)) {
                    queue.add(new Step(cur.num * cur.num, curString + "*"));
                }

                if (!set.contains(cur.num + cur.num)) {
                    queue.add(new Step(cur.num * 2, curString + "+"));
                }


            }
            
            if(N != 1 && flag) {
                flag = false;
                queue.add(new Step(1, "/"));
                set.add(1L);
            }

        }

        return "Impossible";


    }


}
