package test

import util.createNewFileWithParent
import value.StringValue
import java.io.File

fun main() {
    a3.a3()
}

object a3 {
    fun a3(){
        val link = arrayListOf<String>()
        link.add("123")
        link.add("124")
        println(link)
    }

    fun a2() {
        val hashMap = HashMap<String, Int>()

        hashMap["aaa"]=1
        if (hashMap["aaa"] == null) {
            println(11)
        }else{
            println(hashMap["aaa"])
        }
    }

    fun a1() {
        val split = "主演：翁虹,冯雷,温心".split("：")[1].split(",")
        println(split)
    }
}

object a2 {
    fun a1() {
        var i = 0
        while (i <= 225) {
            println(i)
            i += 25
        }
    }

    fun a2() {
        (0..225 step 25).map { i ->
            println(i)
        }
    }

    fun a3() {
        (0..225 step 25).withIndex().withIndex().map {
            println(it.value.value)
        }
    }
}

object a1 {
    fun a3() {
        println(StringValue.DirPath.`out`)
    }

    fun a2() {//文件名结尾的斜杠不影响文件名，文件夹名
        val name = StringValue.DirPath.test + "StringValueTest\\"
        println(name)
        File(name).mkdirs()
    }

    fun a1() {
        val f = File("data\\test\\createNewFileWithParentTest.txt")
        f.createNewFileWithParent()
    }

}