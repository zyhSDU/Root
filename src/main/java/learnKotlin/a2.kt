package learnKotlin

//https://www.cnblogs.com/xwgblog/p/5289823.html

fun main() {
    a2.b1_2()
}

object a2 {
    /*
    a2.Kotlin学习系列(二)
    b1.IF表达式
    if在kotlin可以当做表达式使用跟java的三元操作符类似:
    */
    fun b1() {
        val a = 1
        val b = 2
        var max = if (a > b) a else b
    }

    /*
    if分支可以使用代码块，最后一个表达式是返回值：
    */
    fun b1_2() {
        val a = 1
        val b = 2
        var max = if (a > b) {
            print(a)
            a
        } else {
            print(b)
            b
        }
    }

    /*
    b2.When表达式
    kotlin使用when来替换switch语句，
    基本特性都跟switch差不多会对列出来的分支进行匹配满足条件的分支会被执行。
    when可以当做表达式或者语句
    如果使用when当前表达式满足条件的分支会当做when的返回值。
    当when当做语句使用的时候会忽略所有分支的值。
    when的每个分支也可以是语句块最后一个表达式是这个语句块的值。
    c1.用做表达式：
    */
    fun c1() {
        val matchValue = 2
        val result = when (matchValue) {
            1 -> 1
            2 -> 2
            else -> 3
        }
        println(result)
    }

    //c2.用于语句：
    fun c2() {
        val matchValue = 3
        when (matchValue) {
            1 -> println(1)
            2 -> println(2)
            else -> println(3)
        }
    }

    //c3.匹配多少值:
    fun c3() {
        val matchValue = 3
        when (matchValue) {
            1, 2, 3 -> println(matchValue)
        }
    }

    //c4.使用is与!is:
    fun c4() {
        val matchValue = 3
        var isInt = when (matchValue) {
            is Int -> println(matchValue)
            else -> false
        }
    }

    //c5.使用in与!in:
    fun c5() {
        val matchValue = 3
        var valueIn = when (matchValue) {
            in 1..3 -> true
            else -> false
        }
    }

    //c6.当when没有提供一个值时它的分支是boolean表达式
    // when会执行为真的分支：
    fun c6() {
        val matchValue = 3
        var valueIn = when (matchValue) {
            in 1..3 -> true
            else -> false
        }
    }
/*
    b3.for循环
    c1.通过任意提供的迭代器的对象循环(迭代器与java类似)：
    for (item in collection)
    print(item)

    c2.遍历数组：
    var types = intArrayOf(1, 2, 3)
    for(index in types.indices)
        print(index.toString() + " ")
    for((index, value ) in types.withIndex())
        println("index: $index, value: $value")

    b4.while循环
    跟平常的while使用一样：
    var index = 0
    while(index < 10)
    print(index++)
    index = 0
    do{
        print(index)
    }while (index++ < 10)
    */
}
