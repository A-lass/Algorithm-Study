import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val sout = BufferedWriter(OutputStreamWriter(System.out))
    var n = readLine().toInt()
    var adj = Array(n) {
        ArrayList<Int>()
    }
    repeat(n) {
        readLine().split(" ").map { num ->
            if (num.toInt() > 0) adj[it].add(num.toInt() - 1)
        }
    }
    var m = readLine().toInt()
    var start = readLine().split(" ").map { it.toInt() - 1 }
    sout.appendLine(solve(n, adj, start))
    sout.close()
}

fun solve(n: Int, adj: Array<ArrayList<Int>>, start: List<Int>): String {
    var answer = IntArray(n) { -1 }
    var count = IntArray(n)
    var visit = BooleanArray(n)

    start.map {
        answer[it] = 0
        visit[it] = true
        adj[it].map { nxt ->
            count[nxt] += 1
        }
    }

    var dq = ArrayDeque(start)

    var time = 1
    while (dq.isNotEmpty()) {
        var temp = ArrayList<Int>()
        for (i in 1..dq.size) {
            var cur = dq.removeFirst()

            for (nxt in adj[cur]) {
                if (visit[nxt]) continue
                if (count[nxt] * 2 >= adj[nxt].size) {
                    visit[nxt] = true
                    dq.add(nxt)
                    answer[nxt] = time
                    temp.add(nxt)
                }
            }

        }
        temp.map {
            adj[it].map { nxt ->
                count[nxt] += 1
            }
        }
        time += 1
    }

    return answer.joinToString(" ")
}
