package kmeans

import util.CsvUtil
import java.lang.StringBuilder

fun main() {
    kmeans()
}

fun kmeans() {
    val k = 6
    val ks = CsvUtil.read("data\\in\\kmeans_in.csv").map { it.split(",").map { it.toDouble() } }
    var sse = 0.01
    val centroids = Array(k) { ArrayList<Double>() }
    //1 随机选取k个中心点
    (0 until k).map { i ->
        ks[i].map {
            centroids[i].add(it)
        }
    }
    var clusters: Array<ArrayList<List<Double>>> = Array(k) { ArrayList<List<Double>>() }

    //4 重复2-3，直到这k个中线点不再变化（收敛了），或执行了足够多的迭代
    var sse2 = 0.00
    while (sse != sse2) {
        sse = sse2
        clusters = Array(k) { ArrayList<List<Double>>() }
        //2 遍历所有数据，将每个数据划分到最近的中心点中
        ks.map {
            var q = -1
            var distMin = Double.MAX_VALUE
            for (i in centroids.indices) {
                val centroid = centroids[i]
                var dist = 0.0
                for (j in centroid.indices) {
                    dist += (Math.pow((it[j] - centroid[j]), 2.0))
                }
                if (dist < distMin) {
                    distMin = dist
                    q = i
                }
            }
            clusters[q].add(it)
        }
        clusters.map { list ->
            list.map {
                print("$it ")
            }
            println()
        }
        println("------------------------------------")
        //3 计算每个聚类的平均值，并作为新的中心点
        for (i in centroids.indices) {
            for (j in centroids[i].indices) {
                var average = 0.0
                clusters[i].map {
                    average += it[j]
                }
                average /= clusters[i].size

                centroids[i][j] = average
            }
        }
        //计算sse
        sse2 = 0.0
        for (i in clusters.indices) {
            clusters[i].map {
                for (j in it.indices) {
                    sse2 += (Math.pow((it[j] - centroids[i][j]), 2.0))
                }
            }
        }
    }
    val resultList = clusters.map { i ->
        val sb = StringBuilder("")
        i.map {
            sb.append(it.toString()).append(" ")
        }
        sb.toString()
    } as ArrayList<String>
    CsvUtil.write("data\\out\\kmeans_out.csv", resultList)
}