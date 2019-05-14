package page

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import util.CharsetDetector

import java.io.UnsupportedEncodingException

/*
 * page
 *   1: 保存获取到的响应的相关内容;
 * */
class Page(val content: ByteArray?, val url: String//url路径
           , val contentType: String// 内容类型
) {
    private var html: String? = null  //网页源码字符串
    private var doc: Document? = null//网页Dom文档
    var charset: String? = null
        private set//字符编码

    /**
     * 返回网页的源码字符串
     *
     * @return 网页的源码字符串
     */
    fun getHtml(): String? {
        if (html != null) {
            return html
        }
        if (content == null) {
            return null
        }
        if (charset == null) {
            charset = CharsetDetector.guessEncoding(content) // 根据内容来猜测 字符编码
        }
        return try {
            this.html = java.lang.String(content, charset!!).toString()
            html
        } catch (ex: UnsupportedEncodingException) {
            ex.printStackTrace()
            null
        }

    }

    /*
     *  得到文档
     * */
    fun getDoc(): Document? {
        if (doc != null) {
            return doc
        }
        return try {
            this.doc = Jsoup.parse(getHtml(), url)
            doc
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }

    }


}