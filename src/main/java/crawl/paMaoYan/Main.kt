package crawl.paMaoYan

import util.HttpUtil
import util.entityString
import util.response

fun main() {
    for (i in 0..9) {
        val url = "https://maoyan.com/board/4?offset=${i}0"
        val entity = HttpUtil.Get.create(url).response().entityString()
        val movieList = MovieItem.getListFromEntity(entity)
        for (movie in movieList) {
            println(movie)
        }
    }
}
