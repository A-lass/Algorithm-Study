import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //벽에 부딪힌 경우
        // 개미에 부딪힌 경우 -> L 은 왼쪽부터, D는 오른쪽부터

        List<Integer> answer = new ArrayList<>();

        int L = Integer.parseInt(st.nextToken());

        int T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int cur = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            char head = s.charAt(0);


            if (head == 'L') {
                int total = T + L - cur;
                if (total < L) {
                    answer.add(L - total);
                } else {
                    if ((total / L) % 2 == 0) {
                        answer.add(L - total % L);
                    } else {
                        answer.add(total % L);
                    }
                }
            } else {
                int total = T + cur;
                if (total < L) {
                    answer.add(total);
                } else {
                    if ((total / L) % 2 == 0) {
                        answer.add(total % L);
                    } else {
                        answer.add(L - total % L);
                    }
                }
            }
        }


        answer.sort(Comparator.naturalOrder());

        for(int i=0;i<answer.size();i++){
            bw.write(answer.get(i) + " ");
        }

        bw.flush();

    }


}
