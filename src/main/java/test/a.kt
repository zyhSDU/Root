package test

import util.createNewFileWithParent
import java.io.File

fun main() {
    a.a()
}

object a {
    fun a() {
        val f = File("data\\test\\createNewFileWithParentTest.txt")
        f.createNewFileWithParent()
    }

}