import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] amount;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        amount = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            amount[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            int index1 = Integer.parseInt(st.nextToken());
            int index2 = Integer.parseInt(st.nextToken());

            if (type == 1) {
                union(index1, index2);
            } else {
                fight(index1, index2);
            }
        }


        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=1;i<=N;i++){
            if (parent[i] == i && amount[i] != 0) {
                pq.add(amount[i]);
            }
        }

        bw.write(pq.size()+"\n");


        while (!pq.isEmpty()) {
            bw.write(pq.poll() + " ");
        }

        bw.flush();
    }

    public static int getParent(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = getParent(parent[x]);
    }

    public static void union(int x, int y) {
        x = getParent(x);
        y = getParent(y);

        amount[y] += amount[x];
        parent[x] = y;
    }

    public static void fight(int x, int y) {
        x = getParent(x);
        y = getParent(y);

        if(amount[x] == amount[y]){
            amount[x] = 0;
            amount[y] = 0; //멸망
        } else if (amount[x] > amount[y]) {
            amount[x] -= amount[y];
            parent[y] = x;
        } else{
            amount[y] -= amount[x];
            parent[x] = y;
        }
    }


}
