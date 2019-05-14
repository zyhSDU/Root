package learnKotlin

//https://www.cnblogs.com/xwgblog/p/5245200.html
fun main() {
//    a1.b3_2()
//    a1.b4_2()
    a1.b4_3()
}

object a1 {

    /*
    a1.Kotlin学习系列(一)
    b0.基本类型
    在Kotlin中任何事物都是对象你可以在任何变量上调用相应的方法或属性。Kotlin的一些内置类型如下：
        Number: 包含整形与浮点型
        Character: 字符(Chat)
        Boolean: 布尔值
        String: 字符串
        Array: 数组
    在Kotlin内置的类型个人觉得更加像Java中的一些包装类，
    如果在学习之前将思维转换成Java包装类的思想我觉得学起来更容易理解一些。
    当然它们还与Java多少有些不同。
    b1.Number类型
    Kotlin包含如下number类型:
        Double
        Float
        Long
        Int
        Short
        Byte
    怎么样有没有感觉跟Java里面的java.lang.Double、java.lang.Long这些包装类差不多用起来也差不多。
    还有在Kotlin的数字常量可以用十进制、十六进制、指数形式小数、二进制但是没有八进制。Kotlin中是没有自动向上转型的。
     */
    fun b1_1() {
        //内置数据类型
        var d: Double = 64.0
        var i: Int = 32
        var l: Long = 64
        var f: Float = 32.0F
        var b: Byte = 8
        var s: Short = 16
        //数字常量
        i = 1  //十进制
        i = 0x1 //十六进制
//    i = 01 //八进制不允许
        i = 0b00000 //二进制
        d = 1.2E10 //指数形式
        f = 1.2E10F
        //没有自动向上转换
//    l = i   //Int不能自动转换成Long
//    d = f  //同样Float也不能自动转换
    }

    //    不过上面说了Kotlin中任何事物都是对象那么我们可以调用相应的方法来显示转换。
    fun b1_2() {
        val i: Int = 5
        var l: Long = i.toLong()
    }

    /*
    b2.字符类型
    字符类型在Kotlin中用Char来表示，它不能直接去应用一个number类型如var ch: Int = 'c'是错误的
    必需要显示的转换var ch: Int = 'c'.toInt()同样Char也支持转意字符\n、\b、\r等跟Java中的差不多。
    b3.数组
    在Kotlin数组分为基本类型的数组和其他类型数据，
    基本数组类型是指数组中的元素类型都是基本数据类型如： Int、Long、Float
    这些类型的数组在Kotlin中分别用IntArray、LongArray、FloatArray来表示
    可以通过Kotlin内置的方法来创建<*>ArrayOf(values)里面的*号代表具体的元素类型。
    */
    fun b3_1() {
        var intArray: IntArray = intArrayOf(1, 3, 4)
        var longArray: LongArray = longArrayOf(1L, 2L)
    }

    //其他类型的数组可以使用Array通过传入大小与生成对象的方法来创建数组。
    fun b3_2() {
        val str: Array<String> = Array(5) { index -> index.toString() }//01234
    }

    /*
    b4.字符串
    字符串用String表示同Java一样是不可变的同样内部也用字符组成，访问内部字符可以通过s[i]和循环来遍历。
     */
    fun b4_1() {
        val hello = "HelloWorld"
        for (index in 0 until hello.length) {
            println(hello[index])
        }
        for (ch in hello) {
            println(ch)
        }
    }

    //字符串可分为能够转意一些特殊字符的字符串和原始的字符串。
    fun b4_2() {
        val text1 = "Hello\tWorld"
        val text2 = """
        Hello\bWorld
        """//结尾有一个换行符
        println(text1)
        println(text2)
    }

    /*
    在text1中\t会转间成一个制表符但在text2不但\b不会转意且任何内容都会照样输出。原始字符串类似XMl中的CDATA。
    字符串中可以使用模版，就像Java中的String.format的一样但在Kotlin中使用$号开始后面可以加变量名来替换内容。
     */
    fun b4_3() {
        val name = "Kotlin"
        val greet = "Hi, $name"
        println(greet)
    }
}
