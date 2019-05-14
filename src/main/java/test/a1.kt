package test

import util.createNewFileWithParent
import value.StringValue
import java.io.File

fun main() {
    a1.a3()
}

object a1 {
    fun a3(){
        println(StringValue.FilePath.`out`)
    }
    fun a2(){//文件名结尾的斜杠不影响文件名，文件夹名
        val name = StringValue.FilePath.test + "StringValueTest\\"
        println(name)
        File(name).mkdirs()
    }

    fun a1() {
        val f = File("data\\test\\createNewFileWithParentTest.txt")
        f.createNewFileWithParent()
    }

}