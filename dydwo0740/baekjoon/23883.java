
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] seq = new int[N];
        int[] sorted = new int[N];

        boolean flag = false;

        for(int i=0;i<N;i++) {
            seq[i] = Integer.parseInt(st.nextToken());
            sorted[i] = seq[i];
            map.put(seq[i], i);
        }

        Arrays.sort(sorted);

        int change = 0;
        for(int i=N-1;i>=0;i--) {

            if(seq[i] == sorted[i]) {
                continue;
            }

            int index = map.get(sorted[i]);

            map.put(seq[i], index);
            map.put(sorted[i], i);

            int temp = seq[i];
            seq[i] = seq[index];
            seq[index] = temp;

            if(++change == M) {
                bw.write(temp + " " + seq[i] + "\n");
                flag = !flag;
                break;
            }

        }

        if(!flag) {
            bw.write(-1+"\n");
        }

        bw.flush();
    }
}
