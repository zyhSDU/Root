package crawl.paMovie.paMaoYan

import util.HttpUtil
import util.entityString
import util.response

fun paMaoYan(): ArrayList<MovieItem> {
    val arrayList = ArrayList<MovieItem>()
    for (i in arrayOf(1, 2, 4, 6, 7)) {
        println(i)
        for (j in 0..9) {
            //1 2 4 6 7
            val url = "https://maoyan.com/board/$i?offset=${j}0"
            val entity = HttpUtil.Get.create(url).response().entityString()
            val movieList = MovieItem.getListFromEntity(entity)
            for (movie in movieList) {
                println(movie)
                arrayList.add(movie)
            }
        }
    }
    return arrayList
}
