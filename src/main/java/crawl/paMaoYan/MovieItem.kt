package crawl.paMaoYan

import org.jsoup.Jsoup
import java.util.ArrayList

class MovieItem {
    var movieRank=""//电影排名
    var movieName=""//电影名
    var releaseTime=""//评分
    var star:List<String>?=null//主演

    companion object{
        fun getListFromEntity(entity: String): List<MovieItem> {
            val data = ArrayList<MovieItem>()
            //采用Jsoup解析
            val doc = Jsoup.parse(entity)
            //根据页面内容分析出需要的元素
            val elements = doc.select("dl.board-wrapper").select("dd")
            for (element in elements) {
                val movie = MovieItem()
                movie.movieRank = element.select("i.board-index").text()//带有class属性的p元素
                movie.movieName = element.select("p.name").text()
                movie.star = element.select("p.star").text().split("：")[1].split(",")
                movie.releaseTime = element.select("p.releasetime").text()
                data.add(movie)
            }
            return data
        }
    }

    override fun toString(): String {
        return "MovieItem(movieRank='$movieRank', movieName='$movieName', releaseTime='$releaseTime', 主演='$star')"
    }

}
