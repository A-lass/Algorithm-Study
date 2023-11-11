import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int Q = Integer.parseInt(st.nextToken());

        int[] seq = new int[N + 1];
        count = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            seq[i] = Integer.parseInt(st.nextToken());
            count[i] = seq[i];
        }

        Arrays.sort(count);

        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());

            int y = Integer.parseInt(st.nextToken());

            bw.write(findValue(seq, x, y) + "\n");
        }





        bw.flush();
    }

    public static int findValue(int[] seq, int x, int y) {
        int size = seq[y];

        int sum = (size - x) + 1;

        int index = binarySearch(x);

        //System.out.println(sum + " " + index);

        sum += ((seq.length - index) - y);

        if(sum < 0){
            return 0;
        }

        return sum;
    }

    public static int binarySearch(int target){
        int left = 1;
        int right = count.length;

        while(left < right){
            int mid = (left + right) / 2;

            if (count[mid] < target) {
                left = mid + 1;
            } else{
                right = mid;
            }
        }

        return left;
    }
}
