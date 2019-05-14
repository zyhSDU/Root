package page

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

import java.util.ArrayList
import java.util.HashSet

object PageParserTool {
    /* 通过选择器来选取页面的 */
    fun select(page: Page, cssSelector: String): Elements {
        return page.getDoc()!!.select(cssSelector)
    }

    /*
     *  通过css选择器来得到指定元素;
     *
     *  */
    fun select(page: Page, cssSelector: String, index: Int): Element {
        val eles = select(page, cssSelector)
        var realIndex = index
        if (index < 0) {
            realIndex = eles.size + index
        }
        return eles[realIndex]
    }
    /**
     * 获取满足选择器的元素中的链接 选择器cssSelector必须定位到具体的超链接
     * 例如我们想抽取id为content的div中的所有超链接，这里
     * 就要将cssSelector定义为div[id=content] a1
     * 放入set 中 防止重复；
     * @param cssSelector
     * @return
     */
    fun getLinks(page: Page, cssSelector: String): Set<String> {
        val links = HashSet<String>()
        val es = select(page, cssSelector)
        val iterator = es.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next() as Element
            if (element.hasAttr("href")) {
                links.add(element.attr("abs:href"))
            } else if (element.hasAttr("src")) {
                links.add(element.attr("abs:src"))
            }
        }
        return links
    }


    /**
     * 获取网页中满足指定css选择器的所有元素的指定属性的集合
     * 例如通过getAttrs("img[src]","abs:src")可获取网页中所有图片的链接
     * @param cssSelector
     * @param attrName
     * @return
     */
    fun getAttrs(page: Page, cssSelector: String, attrName: String): ArrayList<String> {
        val result = ArrayList<String>()
        val eles = select(page, cssSelector)
        for (ele in eles) {
            if (ele.hasAttr(attrName)) {
                result.add(ele.attr(attrName))
            }
        }
        return result
    }
}
