package crawl.paMovie

class MovieItem {
    var title = ""  // 电影名称
    var link = arrayListOf<String>()  // 电影的链接

    var score = ""  // 电影评分//豆瓣
    var num = ""   // 获取评价人数//豆瓣

    var releaseTime = ""//上映时间
    var star: List<String>? = null//主演

}
