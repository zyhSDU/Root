package crawl.paMovie.paDouBan

import org.jsoup.Jsoup

fun paDouBan(): ArrayList<MovieItem> {
    val arrayList = ArrayList<MovieItem>()
    val s1 = "https://movie.douban.com/top250?start="
    (0..225 step 25).map {
        val url = s1 + it
        val doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:60.0) Gecko/20100101 Firefox/60.0").timeout(6000).get()
        val content = doc.getElementById("content")
        val elements = content.getElementsByClass("info")
        for (element in elements) {
            val links = element.getElementsByTag("")[0]
            val star = element.getElementsByClass("star")[0]

            val movieItem = MovieItem()
            movieItem.link = links.attr("href")        // 获取电影的链接
            movieItem.title = links.child(0).html()    // 获取电影名称
            movieItem.score = star.child(1).html()     // 获取电影评分
            movieItem.num = star.child(3).html().split("人")[0]      // 获取评价人数
            arrayList.add(movieItem)
        }
    }

//    val conn = MySQLUtil.conn
//    val sql = "insert into movie (name,url,douban_pingfeng ,douban_pingfeng_renshu) values (?,?,?,?)"
//    val ps = conn.prepareStatement(sql)
//    arrayList.map {
//        with(it) {
//            ps.setString(1, title)
//            ps.setString(2, link)
//            ps.setDouble(3, score.toDouble())
//            ps.setInt(4, num.toInt())
//            ps.addBatch()
//        }
//    }
//    ps.executeBatch()
    return arrayList
}
