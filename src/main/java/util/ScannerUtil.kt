package util

import java.util.*

object ScannerUtil {
    fun doWhile(unitOfEach: (line:String) -> Unit,scanner:Scanner = Scanner(System.`in`)) {
        while (scanner.hasNext()) {
            val next = scanner.next()!!
            unitOfEach(next)
        }
    }
}