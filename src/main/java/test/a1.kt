package test

import util.createNewFileWithParent
import value.StringValue
import java.io.File
import java.util.ArrayList

fun main() {
    a2.a3()
}

object a2{
    fun a1(){
        var i = 0
        while (i <= 225) {
            println(i)
            i += 25
        }
    }
    fun a2(){
        (0..225 step 25).map {i->
            println(i)
        }
    }
    fun a3(){
        (0..225 step 25).withIndex().withIndex().map {
            println(it.value.value)
        }
    }
}

object a1 {
    fun a3(){
        println(StringValue.DirPath.`out`)
    }
    fun a2(){//文件名结尾的斜杠不影响文件名，文件夹名
        val name = StringValue.DirPath.test + "StringValueTest\\"
        println(name)
        File(name).mkdirs()
    }

    fun a1() {
        val f = File("data\\test\\createNewFileWithParentTest.txt")
        f.createNewFileWithParent()
    }

}