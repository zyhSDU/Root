package crawl.paMovie.paDouBan

class MovieItem {
    var title = ""  // 电影名称
    var link = ""  // 电影的链接
    var score = ""  // 电影评分
    var num = ""   // 获取评价人数
    override fun toString(): String {
        return "MovieItem(title='$title', link='$link', score='$score', num='$num')"
    }

}
