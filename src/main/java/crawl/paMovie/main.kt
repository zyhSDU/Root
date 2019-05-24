package crawl.paMovie

import crawl.paMovie.paDouBan.paDouBan
import crawl.paMovie.paMaoYan.paMaoYan
import util.MySQLUtil

fun main() {
    val hashMap = HashMap<String, MovieItem>()

    val arrayList1 = paDouBan()
    val arrayList2 = paMaoYan()

    arrayList1.map {
        val movieItem = MovieItem()
        movieItem.title = it.title
        movieItem.link.add(it.link)
        movieItem.score = it.score
        movieItem.num = it.num
        hashMap[it.title] = movieItem
    }
    arrayList2.map {
        var movieItem = MovieItem()
        if (hashMap[it.movieName] != null) {
            movieItem = hashMap[it.movieName]!!
        }
        movieItem.title=it.movieName
        movieItem.releaseTime = it.releaseTime
        movieItem.star = it.star
        movieItem.link.add(it.link)
        hashMap[it.movieName] = movieItem
    }

    val connection = MySQLUtil.connection
    val sql = "insert into movie (name,url,douban_pingfeng ,douban_pingfeng_renshu,star,releaseTime) values (?,?,?,?,?,?)"
    val ps = connection.prepareStatement(sql)
    hashMap.map {
        with(it.value) {
            ps.setString(1, title)
            ps.setString(2, link.toString())
            try {
                ps.setDouble(3, score.toDouble())
            } catch (e: NumberFormatException) {
                ps.setDouble(3, -1.0)
            }
            try {
                ps.setInt(4, num.toInt())
            } catch (e: NumberFormatException) {
                ps.setInt(4, 0)
            }
            ps.setString(5, star.toString())
            ps.setString(6, releaseTime)
            ps.addBatch()
        }
    }
    ps.executeBatch()
}