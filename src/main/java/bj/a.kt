package bj

import value.StringValue
import java.io.*

fun main() {
    val br = BufferedReader(FileReader(File(StringValue.DirPath.`in` + "bj_in.txt")))
    val arrayList = ArrayList<String>()
    var line: String? = ""
    while (line.run {
                line = br.readLine()
                line != null
            }) {
        arrayList.add(line!!)
    }
//    removeHead(arrayList)
    addHead(arrayList)
}

val az= Array(25){('b'+it).toString()}
val ints = Array(25) { 1 }

private fun addHead(arrayList: ArrayList<String>) {
    val bw = BufferedWriter(FileWriter(File(StringValue.DirPath.out + "bj_out.txt")))
    var formerCount=24
    arrayList.map {
        var count = 0
        for (i in it) {
            if (i == '\t') {
                count++
            }else{
                break
            }
        }
        if (count<formerCount){
            (count+1..formerCount).map {c ->
                ints[c]=1
            }
        }
        (0 until count).map{
            bw.write("\t")
        }
        bw.write(az[count])
        bw.write(('0'+ ints[count]).toString())
        ints[count]= ints[count]+1
        bw.write(".")
        bw.write(it.substring(count))
        bw.newLine()
        formerCount=count
    }
    bw.flush()
}

private fun removeHead(arrayList: ArrayList<String>) {
    val bw = BufferedWriter(FileWriter(File(StringValue.DirPath.out + "bj_out.txt")))
    arrayList.map {
        val split = it.split("[a-z][0-9]\\.".toRegex())
        if (split.size == 1) {
            bw.write(it)
        } else {
            val sp = split[0].length
            val q = it.subSequence(0, sp).toString() + it.subSequence(sp + 3, it.length)
            bw.write(q)
        }
        bw.newLine()
    }
    bw.flush()
}

