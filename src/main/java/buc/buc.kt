package buc

import util.CSVUtil

const val minCount = 3
val kks = ArrayList<List<String>>()
val ks = CSVUtil.read("data\\in\\buc_in.csv")
val size = ks[0].split(",").size//维数
val vvs = Array(size) { ArrayList<String>() }
val vs = ArrayList<Int>()
val result = LinkedHashMap<List<String>, Int>()
val list = Array(size) { "" }
fun main() {
    ks.map { k ->
        val split = k.split(",")
        kks.add(split)
        var q = 0
        split.map {
            if (it !in vvs[q]) {
                vvs[q].add(it)
            }
            q++
        }
    }
    vvs.map {
        vs.add(it.size)
    }
    buc(0)
    result.map {
        println(it)
    }
    val resultList = result.map {
        it.toString()
    }as ArrayList<String>
    CSVUtil.write("data\\out\\buc_out.csv",resultList)
}

fun buc(a: Int) {
    (a until size).map { i ->
        (0 until vs[i]).map { j ->
            list[i] = vvs[i][j]
            val count = count()
            if (count >= minCount) {
                result[list.map { it }] = count
                buc(a + 1)
            }
            list[i] = ""
        }
    }
}

fun count(): Int {
    var count = 0
    for (i in kks.indices) {
        var b = true
        (list.indices).map { j ->
            if (list[j] != "" && list[j] != kks[i][j]) {
                b = false
            }
        }
        if (b) {
            count++
        }
    }
    return count
}
