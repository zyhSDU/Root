package test

import com.gargoylesoftware.htmlunit.html.HtmlElement
import util.PageUtil

fun main() {
    val page = PageUtil.getPage("https://www.csdn.net/")
    /**
     * Xpath:级联选择 ✔
     * ① //：从匹配选择的当前节点选择文档中的节点，而不考虑它们的位置
     * ② h3：匹配<h3>标签
     * ③ [@class='company_name']：属性名为class的值为company_name
     * ④ a：匹配<a>标签
    </a></h3> */
    val spanList = page.getByXPath<HtmlElement>("//h3[@class='company_name']/a")
    for (i in spanList.indices) {
        //asText ==> innerHTML ✔
        println((i + 1).toString() + "、" + spanList[i].asText())
    }
}
