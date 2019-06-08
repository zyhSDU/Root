package leapfrog

fun main() {
    main1()
}

fun main1() {
    val a = 0.1
    val t = 0.001
    val at = a * t
    val x = ArrayList<Double>()//x[1]=x(0.001)
    val v = ArrayList<Double>()//v[1]=v(0.0005)
    x.add(0.0)
    v.add(-0.5 * a * t)
    (1..10000).map {
        v.add(v[it - 1] + at)
        print("v(${f4((it - 0.5) * t)})=${f5(v[it])}\t")
        x.add( x[it - 1] + v[it] * t)
        println("x(${f3(it * t)})=${f8(x[it])}")
    }
}

fun f3(double: Double):String{
    return String.format("%.3f", double)
}
fun f4(double: Double):String{
    return String.format("%.4f", double)
}
fun f5(double: Double):String{
    return String.format("%.5f", double)
}
fun f8(double: Double):String{
    return String.format("%.8f", double)
}

//v(0.0005)=0.00005	x(0.001)=0.00000005
//v(0.0015)=0.00015	x(0.002)=0.00000020
//v(0.0025)=0.00025	x(0.003)=0.00000045
//v(0.0035)=0.00035	x(0.004)=0.00000080
//                ......
//v(9.9965)=0.99965	x(9.997)=4.99700045
//v(9.9975)=0.99975	x(9.998)=4.99800020
//v(9.9985)=0.99985	x(9.999)=4.99900005
//v(9.9995)=0.99995	x(10.000)=5.00000000