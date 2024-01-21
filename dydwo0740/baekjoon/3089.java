
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> row = new HashMap<>();
    static Map<Integer, List<Integer>> col = new HashMap<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        // 초기화

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (!row.containsKey(x)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(y);
                row.put(x, list);
            } else {
                List<Integer> list = row.get(x);
                list.add(y);
            }

            if (!col.containsKey(y)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(x);
                col.put(y, list);
            } else {
                List<Integer> list = col.get(y);
                list.add(x);
            }
        }

        st = new StringTokenizer(br.readLine());

        String str = st.nextToken();

        int x = 0;
        int y = 0;

        for (Map.Entry<Integer, List<Integer>> entry : row.entrySet()) {
            List<Integer> list = entry.getValue();
            list.sort(Comparator.naturalOrder());
        }

        for (Map.Entry<Integer, List<Integer>> entry : col.entrySet()) {
            List<Integer> list = entry.getValue();
            list.sort(Comparator.naturalOrder());
        }

        for (int i = 0; i < M; i++) {
            char ch = str.charAt(i);

            if (ch == 'U') {
                List<Integer> list = row.get(x);
                int value = binarySearch(y, list);
                y = list.get(value + 1);
            } else if (ch == 'D') {
                List<Integer> list = row.get(x);
                int value = binarySearch(y, list);
                y = list.get(value - 1);
            } else if (ch == 'R') {
                List<Integer> list = col.get(y);
                int value = binarySearch(x, list);
                x = list.get(value + 1);
            } else {
                List<Integer> list = col.get(y);
                int value = binarySearch(x, list);
                x = list.get(value - 1);
            }

            //System.out.println("x + \" \" + y = " + x + " " + y);
        }

        bw.write(x + " " + y + "\n");


        bw.flush();
    }

    public static int binarySearch(int target, List<Integer> list) {
        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (list.get(mid) < target) {
                start = mid + 1;
            } else if (list.get(mid) == target) {
                return mid;
            } else {
                end = mid - 1;
            }
        }

        return 0;
    }
}
