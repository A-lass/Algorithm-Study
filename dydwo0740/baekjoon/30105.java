import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] seq = new int[N];

        st = new StringTokenizer(br.readLine());


        Set<Integer> origin = new HashSet<>();

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
            origin.add(seq[i]);
        }

        int[] diff = new int[N - 1];

        for (int i = 1; i < N; i++) {
            diff[i - 1] = seq[i] - seq[0];
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            int differ = diff[i];
            Set<Integer> set = new HashSet<>(origin);
            boolean flag = true;

            for (int j = 0; j < N - 1; j++) {
                if(!set.contains(seq[j])){
                    if (set.contains(seq[j] + differ)) {
                        set.remove(seq[j] + differ);
                    }
                    continue;
                }

                if (!set.contains(seq[j] + differ)) {
                    flag = false;
                    break;
                }

                set.remove(seq[j]);
                set.remove(seq[j] + differ);
            }

            if (flag && set.isEmpty()) {
                list.add(differ);
            }
        }

        if (list.size() == 0) {
            bw.write(0+"\n");
            bw.flush();
            return;
        }

        bw.write(list.size() + "\n");

        for (int i = 0; i < list.size(); i++) {
            bw.write(list.get(i) + " ");
        }

        bw.flush();
    }


}
