package a.htmlparse

import com.alibaba.fastjson.JSONArray
import org.jsoup.Jsoup
import java.util.regex.Pattern

/**
 * Created by hg_yi on 17-5-16.
 * image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word=bird&
 */

object UrlFecter {
    fun urlParse(imageUrl: MutableList<String>, list: List<String>): List<String> {
        var imageUrl = imageUrl
        //首先对网页进行链接请求，拿到网页源码
        for (i in list.indices) {
            val html = HttpUrl.request(list[i])

            println(i)
            //对网页源码进行解析，拿到当前页面的所有图片的链接
            imageUrl = getImageUrl(html, imageUrl)
        }

        return imageUrl
    }

    //使用Jsoup和Fastjson对网页源码进行解析，提取出当前页的所有图片源链接
    private fun getImageUrl(html: String?, imageUrl: MutableList<String>): MutableList<String> {
        val document = Jsoup.parse(html)
        var json: String? = null

        //首先得到图片源链接所在的json字符串，使用正则表达式
        val pattern = Pattern.compile("flip.setData\\('imgData'.*\\);")
        val matcher = pattern.matcher(document.toString())

        //将得到的东西转换为正确的Json格式
        while (matcher.find()) {
            json = matcher.group().toString()
        }

        val begin = json!!.indexOf("\"data\":")
        val last = json.indexOf("});")
        json = json.substring(begin + 7, last)

        //对json进行解析，拿到当前页面所有的所有图片源链接
        val jsonArray = JSONArray.parseArray(json)
        for (i in 0 until jsonArray.size - 1) {
            val temp = jsonArray.getJSONObject(i)
            val url = temp.getString("objURL")

            imageUrl.add(url)
        }

        return imageUrl
    }
}
