import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val sout = BufferedWriter(OutputStreamWriter(System.out))

    var nm = readLine().split(" ").map { it.toInt() }

    var arr = Array(nm[0]) {
        readLine().split(" ").map { it.toInt() }
    }

    sout.appendLine(solve(nm, arr))
    sout.close()
}

fun solve(nm: List<Int>, arr: Array<List<Int>>): String {
    var ans = 0
    var dp = Array(nm[0] + 1) {
        IntArray(nm[1] + 1)
    }

    for (i in 1..nm[0]) {
        for (j in 1..nm[1]) {
            if (arr[i - 1][j - 1] == 0) dp[i][j] = minOf(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
            ans = maxOf(ans, dp[i][j])
        }
    }

    return ans.toString()
}
