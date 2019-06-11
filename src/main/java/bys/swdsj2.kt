package bys

import util.FileUtil

object swdsj2{
    fun main1() {
        val list = (1..11).map { i ->
            (0..1).map { j ->
                val mutableList = MutableList(12100 + 1) { 0 }
                mutableList[0] = i
                mutableList[1] = j
                mutableList
            }
        }
        val bufferedReader = FileUtil.returnBufferedReader(dir + "癌症基因数据集.csv")
        val a=bufferedReader.readLine()

        val typeList = MutableList(11) { 0 }
        FileUtil.whileBufferedReader(bufferedReader,{string->
            val split = string.split(",").map { it.toInt() }
            val type = split[0]
            typeList[type-1]++
            split.withIndex().map {
                if (it.index>0){
                    list[type-1][it.value][it.index+1]++
                }
            }
        })
        bufferedReader.close()

        val bufferedWriter = FileUtil.returnBufferedWriter(dir + "癌症基因数据集out.csv")
        typeList.map {
            bufferedWriter.write("$it,")
        }
        bufferedWriter.newLine()

        list.map {i->
            i.map {j->
                j.map {
                    bufferedWriter.write("$it,")
                }
                bufferedWriter.newLine()
            }
        }
        bufferedWriter.close()
    }
}
fun main() {
    swdsj2.main1()
}
