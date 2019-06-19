package util

import value.StringValue
import java.lang.StringBuilder
import java.util.regex.Pattern

fun main() {
    ScannerUtil.doWhile({
        val rs=StringUtil.b(it)
        print(rs)
    })
}

object StringUtil {
    fun b(s: String): String {
        var rs=s
        rs=rs.replace("\t","")
        rs=rs.replace(" ","")
        rs=rs.replace("．",". ")
        rs=rs.replace("，",", ")
        rs=rs.replace("：",": ")
        rs=rs.replace("『","[")
        rs=rs.replace("【","[")
        rs=rs.replace("／","/")
        rs=rs.replace("】","]")
         return rs
    }
    fun a(s: String): String {
        var dest=""
        val pattern = Pattern.compile("\\s*|\t|\r|\n")
        val matcher = pattern.matcher(s)
        dest=matcher.replaceAll("")

        return dest
    }
}