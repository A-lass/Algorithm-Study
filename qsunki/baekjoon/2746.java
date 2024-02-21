import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String[] split;
    static String line;

    static int n;
    static int[] nums;
    static long[] numCnts;
    static boolean[] visited;
    static long ans;
    static long allSum;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        numCnts = new long[1_000_001];
        visited = new boolean[1_000_001];
        split = br.readLine().split(" ");
        for (int i = 0; i < n; ++i) {
            nums[i] = Integer.parseInt(split[i]);
            allSum += nums[i];
            ++numCnts[nums[i]];
        }
        Arrays.sort(nums);
        // n - 1번이 최대
        for (int i = 0; i < n - 1; ++i) {
            int a = nums[i];
            long com = allSum - 2 * nums[n - 1] - a;
            if (com >= nums[n - 1]) {
                continue;
            }
            if (com < 0) {
                break;
            }
            int b = (int) com;

            if (!visited[a]) {
                if (a == b) {
                    long cnt = numCnts[a];
                    ans += cnt * (cnt - 1) / 2;
                    visited[a] = true;
                } else if (numCnts[b] != 0) {
                    visited[a] = true;
                    visited[b] = true;
                    ans += numCnts[a] * numCnts[b];
                }
            }
        }

        // n - 2번이 최대
        for (int i = 0; i < n - 2; ++i) {
            long sum = allSum - nums[n - 1] - nums[n - 2] - nums[i];
            if (sum == nums[n - 2]) {
                ++ans;
            } else if (sum < nums[n - 2]) {
                break;
            }
        }

        // n - 3번이 최대
        if (allSum - nums[n - 1] - nums[n - 2] - nums[n - 3] == nums[n - 3]) {
            ++ans;
        }
        System.out.println(ans);
    }

}
