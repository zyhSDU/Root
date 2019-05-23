package util
import com.gargoylesoftware.htmlunit.Page
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
fun main() {
    // 创建webclient
    val webClient = WebClient()
    // 取消 JS 支持
    webClient.options.isJavaScriptEnabled = false
    // 取消 CSS 支持
    webClient.options.isCssEnabled = false
    // 获取指定网页实体
    val page = webClient.getPage<Page>("https://www.so.com/") as HtmlPage
    // 获取搜索输入框
    val input = page.getHtmlElementById<HtmlElement>("input") as HtmlInput
    // 往输入框 “填值”
    input.valueAttribute = "larger5"
    // 获取搜索按钮
    val btn = page.getHtmlElementById<HtmlElement>("search-button") as HtmlInput
    // “点击” 搜索
    val page2 = btn.click<HtmlPage>()
    // 选择元素
    val spanList = page2.getByXPath<HtmlElement>("//h3[@class='res-title']/a")
    for (i in spanList.indices) {
        // 输出新页面的文本
        println((i + 1).toString() + "、" + spanList[i].asText())
    }
}
object PageUtil{

}
