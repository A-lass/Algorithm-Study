import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] seq = new int[N];

        st = new StringTokenizer(br.readLine());

        String str = st.nextToken();

        for (int i = 0; i < N; i++) {
            if (str.charAt(i) == 'R') {
                seq[i] = 0;
            } else if (str.charAt(i) == 'G') {
                seq[i] = 1;
            } else {
                seq[i] = 2;
            }
        }

        int ans = Integer.MAX_VALUE;

        boolean flag = false;


        int cas = 0;
        while (cas < 3) {
            int[] temp = new int[seq.length];
            for (int i = 0; i < seq.length; i++) {
                temp[i] = seq[i];
            }
            int std = temp[0] = (temp[0] + cas) % 3;
            temp[1] = (temp[1] + cas) % 3;
            temp[2] = (temp[2] + cas) % 3;

            int sum = cas;

            for (int i = 1; i <= N - 3; i++) {
                int up = findMin(std, temp[i]);
                temp[i] = (temp[i] + up) % 3;
                temp[i + 1] = (temp[i + 1] + up) % 3;
                temp[i + 2] = (temp[i + 2] + up) % 3;
                sum += up;
            }

            if (temp[N - 1] == temp[N - 2] && std == temp[N - 2]) {
                flag = true;
                ans = Math.min(ans, sum);
            }

            cas++;
        }


        if (flag) {
            bw.write(ans + "\n");
        } else {
            bw.write(-1 + "\n");
        }

        bw.flush();
    }

    public static int findMin(int std, int comp) {

        for (int i = 0; i < 3; i++) {
            if (std == (comp + i) % 3) {
                return i;
            }
        }

        return 0;
    }

}
