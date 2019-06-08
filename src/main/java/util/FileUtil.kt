package util

import java.io.*

object FileUtil {
    fun returnBufferedReader(file: File): BufferedReader {
        return BufferedReader(FileReader(file))
    }

    fun returnBufferedReader(pathname: String): BufferedReader {
        return returnBufferedReader(File(pathname))
    }

    fun whileBufferedReader(bufferedReader: BufferedReader, unitOfLine: (line: String) -> Unit = {}, arrayList: ArrayList<String> = ArrayList()): ArrayList<String> {
        var line: String? = null

        while (line.run {
                    line = bufferedReader.readLine()
                    line != null
                }) {
            unitOfLine(line!!)
            arrayList.add(line!!)
        }
        return arrayList
    }


    fun returnBufferedWriter(file: File): BufferedWriter {
        return BufferedWriter(FileWriter(file))
    }

    fun returnBufferedWriter(pathname: String): BufferedWriter {
        return returnBufferedWriter(File(pathname))
    }
}