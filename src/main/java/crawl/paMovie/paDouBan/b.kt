package crawl.paMovie.paDouBan

import com.gargoylesoftware.htmlunit.html.HtmlElement
import util.PageUtil

//PageUtil的尝试使用
fun main() {
    val page = PageUtil.getPage("https://movie.douban.com/")
    page.getByXPath<HtmlElement>("//div[@class='gaia gaia-lite gaia-movie slide-mode']/a")
}