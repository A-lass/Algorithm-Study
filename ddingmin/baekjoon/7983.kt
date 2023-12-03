import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val sout = BufferedWriter(OutputStreamWriter(System.out))

    var n = readLine().toInt()
    var arr = Array(n) {
        readLine().split(" ").map { it.toLong() }
    }

    sout.appendLine(solve(n, arr))
    sout.close()
}

fun solve(n: Int, arr: Array<List<Long>>): String {
    var ans = Long.MAX_VALUE
    arr.sortBy { -it[1] }

    for (values in arr) {
        var doing = values[0]
        var deadline = values[1]
        ans = minOf(ans, deadline) - doing
    }


    return ans.toString()
}
