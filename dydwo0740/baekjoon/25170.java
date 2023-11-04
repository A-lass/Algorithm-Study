import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            int idx = Integer.parseInt(st.nextToken());
            if(map.containsKey(idx)){
                if(map.get(idx) == 2){
                    continue;
                }
                map.put(idx, 2);
            } else{
                map.put(idx, 1);
            }
        }

        List<Integer> list = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > 1){
                list.add(entry.getKey());
                list.add(entry.getKey());
            } else{
                list.add(entry.getKey());
            }
        }

        combination(new int[2], new boolean[N], list.stream()
                .mapToInt(Integer::intValue)
                .toArray(), 0, 0);
        bw.write(ans+"\n");
        bw.flush();
    }

    public static void combination(int[] output, boolean[] visited, int[] seq, int depth, int start){
        if(output.length == depth){
            ans = Math.max(ans, getValue(output[0] * output[1]));
            return;
        }

        for(int i = start;i<seq.length;i++){
            if(!visited[i]){
                visited[i] = true;
                output[depth] = seq[i];
                combination(output, visited, seq, depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }


    public static int getValue(int num){
        int ans = 0;

        while(num > 0){
            ans += (num % 10);
            num /= 10;
        }

        return ans;
    }


}
