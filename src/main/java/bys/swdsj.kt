package bys

import util.FileUtil
import util.ScannerUtil
import value.StringValue
import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

const val dir = StringValue.DirPath.`in` + "swdsj\\"
val tag = arrayOf('g', 'a', 'v', 'l', 'i', 'f', 'w', 'y', 'd', 'n', 'e', 'k', 'q', 'm', 's', 't', 'c', 'p', 'h', 'r', 'x', 'u')
val tagSize = tag.size//22

fun main() {
//    main1()
//    main3()
//    main5()
    main6()
}

fun main6() {
    val bufferedReader = FileUtil.returnBufferedReader(dir + "column.txt")
    var line = bufferedReader.readLine()
    val num = line.split(",").subList(0, 3).map { it.toInt() }
    var numSum = 0
    num.map {
        numSum += it
    }
    val numP = num.map { 1.0 * it / numSum }
    val ubList = MutableList(tagSize) { MutableList(3) { Pair(0.0, 0.0) } }
    (0 until tagSize).map { t ->
        line = bufferedReader.readLine()
        val split = line.split(",")
        (0 until 3).map {
            ubList[t][it] = Pair(split[it * 2].toDouble(), split[it * 2 + 1].toDouble())
        }
    }

    ScannerUtil.doWhile({
        val list = MutableList(tagSize) { 0 }
        for (c in it) {
            for (t in tag.withIndex()) {
                if (c == t.value) {
                    list[t.index]++
                }
            }
        }

        val numType = 3
        val totalP = MutableList(numType) { 1.0 }
        val p = MutableList(numType) { MutableList(tagSize) { 0.0 } }
        (0 until numType).map { i ->
            (0 until tagSize).map { j ->
                p[i][j] = p(list[j].toDouble(), ubList[j][i].first, ubList[j][i].second)
            }
        }
        p.withIndex().map { i ->
            totalP[i.index] *= numP[i.index]
            i.value.subList(0, 20).map { j ->
                totalP[i.index] *= j
                println("${i.index},$j")
            }
        }
        val hashMap = HashMap<Double, Int>()
        totalP.withIndex().map {
            hashMap[it.value] = it.index
        }
        var maxP = 0.0
        totalP.withIndex().map {
            with(it.value) {
                if (this > maxP) {
                    maxP = this
                }
            }
        }
        hashMap.map {
            println(it)
        }
        println("tpye:${hashMap[maxP]}")

    })

}

fun main5() {
    val bufferedReader = BufferedReader(FileReader(File(dir + "column.txt")))
    var line = bufferedReader.readLine()
    val num = line.split(",").map {
        if (it == "") {
            0
        } else {
            it.toInt()

        }
    }.subList(0, 2)

    val bufferedWriter = BufferedWriter(FileWriter(File(dir + "ub.txt")))

    (0 until 22).map { _ ->
        line = bufferedReader.readLine()
        val split = line.split(",").map {
            if (it == "") {
                0
            } else {
                it.toInt()
            }
        }
        var indexSplit = 0
        num.map { n ->
            var u = 0.0
            var b = 0.0
            (0 until n).map {
                u += split[indexSplit++]
            }
            u /= n
            indexSplit -= n
            (0 until n).map {
                b += Math.pow(split[indexSplit++] - u, 2.0)
            }
            b /= n
            b = Math.sqrt(b)
            bufferedWriter.write("$u,$b,")
        }
        bufferedWriter.newLine()
    }
    bufferedReader.close()
    bufferedWriter.close()
}

fun main4() {
    val q = 0.697 + 0.774 + 0.634 + 0.608 + 0.556 + 0.403 + 0.481 + 0.437
    println(q)
    println(q / 8)

}

fun p(x: Double, u: Double, b: Double): Double {
    val q0 = x - u
    val q1 = Math.pow(q0, 2.0)
    val q2 = 2 * Math.pow(b, 2.0)
    val q3 = -(q1 / q2)
    val q4 = Math.exp(q3)
    val q5 = 2 * Math.PI
    val q6 = Math.sqrt(q5) * b
    return q4 / q6
}


fun main3() {
    val columnList = MutableList(tagSize) { ArrayList<Int>() }

    val file = arrayOf("H", "L", "M").map { File("$dir$it.txt") }
    val num = arrayOf(0, 0, 0)

    val tagFile = File(dir + "tag.txt")
    val tagFileBufferedWriter = BufferedWriter(FileWriter(tagFile))

    val columnFile = File(dir + "column.txt")
    val columnFileBufferedWriter = BufferedWriter(FileWriter(columnFile))

    (0..2).map { i ->
        val lineList = ArrayList<List<Int>>()
        val bufferedReader = BufferedReader(FileReader(file[i]))
        var stringLine = bufferedReader.readLine()
        num[i] = stringLine.split("：")[1].toInt()
        while (stringLine.run {
                    stringLine = bufferedReader.readLine()
                    stringLine != null
                }) {
            val list = MutableList(tagSize) { 0 }
            for (c in stringLine) {
                for (t in tag.withIndex()) {
                    if (c == t.value) {
                        list[t.index]++
                    }
                }
            }
            list.withIndex().map {
                columnList[it.index].add(it.value)
            }
            lineList.add(list)
        }

        lineList.map { line ->
            tagFileBufferedWriter.write("$i,")
            line.map {
                tagFileBufferedWriter.write("$it,")
            }
            tagFileBufferedWriter.newLine()
        }
    }

    num.map {
        columnFileBufferedWriter.write("$it,")
    }
    columnFileBufferedWriter.newLine()
    columnList.map { column ->
        column.map {
            columnFileBufferedWriter.write("$it,")
        }
        columnFileBufferedWriter.newLine()
    }

    columnFileBufferedWriter.close()
    tagFileBufferedWriter.close()

}

fun main2() {
    val arrayOf = arrayOf("HLM", "g", "a", "v", "l", "i", "f", "w", "y", "d", "n", "e", "k", "q", "m", "s", "t", "c", "p", "h", "r", "x", "u")
    println(arrayOf.size)
    arrayOf.map {
        print(it)
    }
}

fun main1() {
    val bufferedReader = FileUtil.returnBufferedReader(StringValue.DirPath.`in` + "swdsj\\H.txt")

    FileUtil.whileBufferedReader(bufferedReader, {
        println(it)
    })

}