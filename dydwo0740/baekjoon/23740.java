import java.io.*;
import java.util.*;

public class Main {

    static class Bus{
        long start;
        long end;
        long cost;

        public Bus(long start, long end, long cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        List<Bus> list = new ArrayList<>();
        List<Bus> res = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            long s = Long.parseLong(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            list.add(new Bus(s, e, cost));
        }

        list.sort(new Comparator<Bus>() {
            @Override
            public int compare(Bus o1, Bus o2) {
                if (o1.start == o2.start) {
                    Long.compare(o1.end, o2.end);
                }
                return Long.compare(o1.start, o2.start);
            }
        });

        Bus bus = list.get(0);

        long start = bus.start;
        long end = bus.end;
        long cost = bus.cost;
        boolean flag = false;

        if (N == 1) {
            res.add(new Bus(start, end, cost));
        }

        for (int i = 1; i < N; i++) {
            Bus cur = list.get(i);

            if (i == N - 1) {
                flag = true;
            }

            if (cur.start <= end) {
                end = Math.max(end, cur.end);
                cost = Math.min(cost, cur.cost);

                if (flag) {
                    res.add(new Bus(start, end, cost));
                }

                continue;
            }

            res.add(new Bus(start, end, cost));
            start = cur.start;
            end = cur.end;
            cost = cur.cost;

            if (flag) {
                res.add(new Bus(start, end, cost));
            }
        }

        res.sort(new Comparator<Bus>() {
            @Override
            public int compare(Bus o1, Bus o2) {
                return Long.compare(o1.start, o2.start);
            }
        });

        bw.write(res.size() + "\n");

        for (Bus b : res) {
            bw.write(b.start + " " + b.end + " " + b.cost + "\n");
        }

        bw.flush();
    }


}
