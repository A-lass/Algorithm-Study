import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder sb = new StringBuilder();
        int c = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Deque<Pair> stack = new LinkedList<>();
        stack.push(new Pair(0, 0));
        for (int num : nums) {
            // 올바른 보고서가 아님
            if (num - stack.getFirst().num > 1) {
                System.out.println(-1);
                return;
            }
            // 자식일 때
            if (num > stack.getFirst().num) {
                stack.push(new Pair(num, 1));
                sb.append(1).append(' ');
                continue;
            }// 형제일 때
            if (num == stack.getFirst().num) {
                int idx = stack.getFirst().idx + 1;
                stack.push(new Pair(num, idx));
                sb.append(idx).append(' ');
                continue;
            }
            // 조상의 형제일 때
            if (num < stack.getFirst().num) {
                while (stack.getFirst().num != num) {
                    stack.pop();
                }
                int idx = stack.getFirst().idx + 1;
                stack.push(new Pair(num, idx));
                sb.append(idx).append(' ');
            }
        }
        System.out.println(sb);
    }

    private static class Pair {
        int num;

        int idx;

        public Pair(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}
