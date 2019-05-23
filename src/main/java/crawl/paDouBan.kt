package crawl

import org.jsoup.Jsoup
import java.util.*

fun main() {
    val s1 = "https://movie.douban.com/top250?start="
    val s2 = "&filter="
    var link = ""  // 电影的链接
    var title = ""  // 电影名称
    var score = ""  // 电影评分
    var num = ""   // 获取评价人数
    // 存储待爬取的网址url链接
    val list = ArrayList<String>()
    var i = 0
    while (i <= 225) {
        list.add(s1 + i + s2)
        i += 25
    }
    // 遍历url集合 爬取网页数据
    for (url in list) {
        val doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:60.0) Gecko/20100101 Firefox/60.0").timeout(6000).get()
        val content = doc.getElementById("content")
        val elements = content.getElementsByClass("info")
        for (element in elements) {
            val links = element.getElementsByTag("a")[0]
            val star = element.getElementsByClass("star")[0]
            link = links.attr("href")        // 获取电影的链接
            title = links.child(0).html()    // 获取电影名称
            score = star.child(1).html()     // 获取电影评分
            num = star.child(3).html()       // 获取评价人数
            println("$link\t$title\t评分$score\t$num")
        }
    }
}
