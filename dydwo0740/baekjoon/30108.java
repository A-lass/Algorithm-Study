import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        st = new StringTokenizer(br.readLine());

        Integer[] seq = new Integer[N];

        for(int i=0;i<N;i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(seq, Collections.reverseOrder());

        long sum = 0;
        for(int i=0;i<N;i++){
            sum += (long) seq[i];

            bw.write(sum+"\n");
        }

        bw.flush();
    }

}
