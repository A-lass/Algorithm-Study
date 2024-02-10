import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String[] split;
    static String line;

    static int[] nums;
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        split = br.readLine().split(" ");
        nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = Integer.parseInt(split[i]);
        }
        int low = 1;
        int high = 1_000_000;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (check(mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(low - 1);
    }

    static boolean check(int mid) {
        for (int i = 0, t = 0; i < n; ++i) {
            if (nums[i] < mid) {
                t = 0;
            } else {
                ++t;
            }
            if (t == mid) {
                return true;
            }
        }
        return false;
    }
}
