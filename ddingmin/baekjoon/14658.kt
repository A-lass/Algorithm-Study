import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val sout = BufferedWriter(OutputStreamWriter(System.out))

    var line = readLine().split(" ").map { it.toInt() }
    var l = line[2]
    var k = line[3]

    var arr = Array(k) {
        readLine().split(" ").map { it.toInt() }
    }

    sout.appendLine(solve(l, k, arr))
    sout.close()
}

fun solve(l: Int, k: Int, arr: Array<List<Int>>): String {
    var count = 0

    for (star1 in arr) {
        for (star2 in arr) {
            var sx = minOf(star1[0], star2[0])
            var sy = minOf(star1[1], star2[1])
            var cnt = 0

            for (star in arr) {
                if (sx <= star[0] && star[0] <= sx + l &&
                    sy <= star[1] && star[1] <= sy + l
                ) {
                    cnt += 1
                }
            }
            count = maxOf(count, cnt)
        }
    }

    return (k - count).toString()
}
