package test

import util.createNewFileWithParent
import value.StringValue
import java.io.File
import org.ansj.splitWord.analysis.ToAnalysis
import org.ansj.splitWord.analysis.BaseAnalysis
import java.util.*
import kotlin.collections.HashMap


fun main() {
   a4.a2()
}

object a4{
    fun a2(){
        val scanner = Scanner(System.`in`)
        println("请输入要分词的段落：")
        while (scanner.hasNext()){
            val s=scanner.next()
            println("分词结果：")
            println(ToAnalysis.parse(s))
            println()
            println("请输入要分词的段落：")
        }
    }
    fun a1(){
        val str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!"
        println(ToAnalysis.parse(str))
        val str2 = "洁面仪配合洁面深层清洁毛孔 清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹 脸颊毛孔修复的看不见啦 草莓鼻历史遗留问题没辙 脸和脖子差不多颜色的皮肤才是健康的 长期使用安全健康的比同龄人显小五到十岁 28岁的妹子看看你们的鱼尾纹"

        println(BaseAnalysis.parse(str2))
    }
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