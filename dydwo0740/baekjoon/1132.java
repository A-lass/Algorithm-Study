import java.io.*;
import java.util.*;

public class Main {

    static class Alpha {
        char ch;
        long weight;

        public Alpha(char ch, long weight) {
            this.ch = ch;
            this.weight = weight;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        long[] alpha = new long[26];
        String[] s = new String[N];
        Set<Character> first = new HashSet<>();
        Set<Character> alp = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();
            s[i] = str;
            for (int j = 0; j < str.length(); j++) {
                if (j == 0) {
                    first.add(str.charAt(j));
                }
                alp.add(str.charAt(j));
                alpha[str.charAt(j) - 'A'] += (long)Math.pow(10, str.length() - 1 - j);
            }
        }

        PriorityQueue<Alpha> pq = new PriorityQueue<>(new Comparator<Alpha>() {
            @Override
            public int compare(Alpha o1, Alpha o2) {
                return Long.compare(o2.weight, o1.weight);
            }
        });

        PriorityQueue<Alpha> temp = new PriorityQueue<>(new Comparator<Alpha>() {
            @Override
            public int compare(Alpha o1, Alpha o2) {
                return Long.compare(o1.weight, o2.weight);
            }
        });

        for (int i = 0; i < 26; i++) {
            if (alpha[i] > 0) {
                pq.add(new Alpha((char) ('A' + i), alpha[i]));
                temp.add(new Alpha((char) ('A' + i), alpha[i]));
            }
        }

        long num = 9;
        long ans = 0;

        char ch = '&';

        if (alp.size() == 10) {
            while (!temp.isEmpty()) {
                Alpha head = temp.poll();
                if (!first.contains(head.ch)) {
                    ch = head.ch;
                    break;
                }
            }
        }

        while (!pq.isEmpty()) {
            Alpha cur = pq.poll();
            if (cur.ch == ch) {
                continue;
            }
            ans += (long)(cur.weight * num--);
        }





        bw.write(ans+"\n");

        bw.flush();
    }
}
