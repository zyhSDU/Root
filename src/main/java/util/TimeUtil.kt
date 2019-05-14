package util

import java.util.*
import java.text.*


fun main() {
    val now = TimeUtil.nowDateTime()
    println(now)
}

object TimeUtil {

    //Current Date: 周六 2019.05.11 at 02:20:59 下午 CST
    fun a() {
        val now = Date()
        val ft = SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz")
        println("Current Date: " + ft.format(now))
    }

    //周六 2019.05.11 at 02:20:59 下午 CST
    fun now(): String {
        return SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz").format(Date())
    }

    fun nowDateTime(): String {
        return SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(Date())
    }
}
