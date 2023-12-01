import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int idx;
        int size;
        int h;
        int w;

        public Node(int idx, int size, int h, int w) {
            this.idx = idx;
            this.size = size;
            this.h = h;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Node> seq = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        int[] height = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            seq.add(new Node(i + 1, size, h, w));
            height[i + 1] = h;
        }

        seq.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.size - o2.size;
            }
        });


        // LIS

        int[] dp = new int[N];
        int max = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = seq.get(i).h;
            for (int j = 0; j < i; j++) {
                if (seq.get(i).w > seq.get(j).w) {
                    dp[i] = Math.max(dp[i], dp[j] + seq.get(i).h);
                }
            }

            max = Math.max(max, dp[i]);
        }


        Stack<Integer> stack = new Stack<>();

        for (int i = N - 1; i >= 0; i--) {
            if (dp[i] == max) {
                stack.push(seq.get(i).idx);
                max -= seq.get(i).h;
            }
        }

        bw.write(stack.size()+"\n");
        while (!stack.isEmpty()) {
            bw.write(stack.pop() + "\n");
        }


        bw.flush();
    }


}
