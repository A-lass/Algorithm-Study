import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String[] split;
    static String line;

    static int[] alphabetCnt;
    static int[] numCnt;
    static char[][] numToCharArray = new char[10][];
    static Map<Character, Integer> charToNum = new HashMap<>();

    static {
        numToCharArray[0] = "ZERO".toCharArray();
        numToCharArray[1] = "ONE".toCharArray();
        numToCharArray[2] = "TWO".toCharArray();
        numToCharArray[3] = "THREE".toCharArray();
        numToCharArray[4] = "FOUR".toCharArray();
        numToCharArray[5] = "FIVE".toCharArray();
        numToCharArray[6] = "SIX".toCharArray();
        numToCharArray[7] = "SEVEN".toCharArray();
        numToCharArray[8] = "EIGHT".toCharArray();
        numToCharArray[9] = "NENE".toCharArray();

        /*
        Z-> 0
        W -> 2
        U -> 4
        X -> 6
        G -> 8
         */
        charToNum.put('Z', 0);
        charToNum.put('W', 2);
        charToNum.put('U', 4);
        charToNum.put('X', 6);
        charToNum.put('G', 8);
        /*
        ONE
        THREE
        FIVE
        SEVEN
        NINE

        O -> 1
        H -> 3
        F -> 5
        S -> 7
        E -> 9
         */
        charToNum.put('O', 1);
        charToNum.put('H', 3);
        charToNum.put('F', 5);
        charToNum.put('S', 7);
        charToNum.put('E', 9);

    }


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; ++t) {
            alphabetCnt = new int['Z' + 1];
            numCnt = new int[10];
            for (char c : br.readLine().toCharArray()) {
                ++alphabetCnt[c];
            }
            for (char alphabet : "ZWUXGOHFSE".toCharArray()) {
                int cnt = alphabetCnt[alphabet];
                if (cnt == 0) {
                    continue;
                }
                int num = charToNum.get(alphabet);
                numCnt[num] = cnt;
                for (char alphabet2 : numToCharArray[num]) {
                    alphabetCnt[alphabet2] -= cnt;
                }
            }
            sb.append("Case #").append(t).append(": ");
            for (int i = 0; i < 10; ++i) {
                for (int j = 0; j < numCnt[i]; ++j) {
                    sb.append(i);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
