package crawl.a.httpbrowser


import java.util.ArrayList
import java.util.Scanner
import crawl.a.htmlparse.UrlFecter


/**
 * Created by hg_yi on 17-5-16.
 * 本类主要构建要爬取的Url
 */

object CreateUrl {
    //首先构建需要抓取的10页百度图片页面
    fun createMainUrl(): List<String> {
        val scanner = Scanner(System.`in`)
        var urlMain = StringBuilder(scanner.nextLine())
        val urlTemp = urlMain.toString()
        val list = ArrayList<String>()

        //构建需要爬取的10页Url
        for (i in 0..9) {
            urlMain = StringBuilder("http://" + urlMain + "pn=" + i * 60)
            list.add(urlMain.toString())
            urlMain = StringBuilder(urlTemp)
        }

        return list
    }

    //创建每个要爬取图片的Url
    fun createImageUrl(list: List<String>): List<String> {
        var imageList: List<String> = ArrayList()

        imageList = UrlFecter.urlParse(imageList as MutableList<String>, list)

        return imageList
    }
}
