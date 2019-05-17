package util

import java.io.*
import java.util.ArrayList

fun main() {
    CsvUtil.read("in\\a1.csv")
}

object CsvUtil {
    fun read(path: String): ArrayList<String> {
        val arrayList = ArrayList<String>()

        val br = BufferedReader(FileReader(File(path)))
        var line: String? = ""
        while (line.run {
                    line = br.readLine()
                    line != null
                }) {
            arrayList.add(line!!)
        }

        return arrayList
    }
    fun write(path: String,list:ArrayList<String>){
        val bw = BufferedWriter(FileWriter(File(path)))
        list.map {
            bw.write(it)
            bw.write("\n")
        }
        bw.flush()
    }
}
