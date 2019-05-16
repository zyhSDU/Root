package crawl.ip.htmlparse

import crawl.ip.IPModel.IpMessage
import crawl.ip.httpbrowser.MyHttpResponse
import org.jsoup.Jsoup

/**
 * Created by paranoid on 17-4-10.
 */

object URLFecter {
    //使用代理进行爬取
    fun urlParse(url: String, ip: String, port: String, ipMessages1: MutableList<IpMessage>): Boolean {
        //调用一个类使其返回html源码
        val html = MyHttpResponse.getHtml(url, ip, port)

        if (html != null) {
            //将html解析成DOM结构
            val document = Jsoup.parse(html)

            //提取所需要的数据
            val trs = document.select("table[id=ip_list]").select("tbody").select("tr")

            for (i in 1 until trs.size) {
                val ipMessage = IpMessage()
                val ipAddress = trs[i].select("td")[1].text()
                val ipPort = trs[i].select("td")[2].text()
                val ipType = trs[i].select("td")[5].text()
                val ipSpeed = trs[i].select("td")[6].select("div[class=bar]").attr("title")

                ipMessage.ipAddress = ipAddress
                ipMessage.ipPort = ipPort
                ipMessage.ipType = ipType
                ipMessage.ipSpeed = ipSpeed

                ipMessages1.add(ipMessage)
            }
            return true
        } else {
            println("$ip: $port 代理不可用")
            return false
        }
    }

    //使用本机IP爬取xici代理网站的第一页
    fun urlParse(ipMessages: MutableList<IpMessage>): List<IpMessage> {
        val url = "http://www.xicidaili.com/nn/1"
        val html = MyHttpResponse.getHtml(url)

        //将html解析成DOM结构
        val document = Jsoup.parse(html)

        //提取所需要的数据
        val trs = document.select("table[id=ip_list]").select("tbody").select("tr")

        for (i in 1 until trs.size) {
            val ipMessage = IpMessage()
            val ipAddress = trs[i].select("td")[1].text()
            val ipPort = trs[i].select("td")[2].text()
            val ipType = trs[i].select("td")[5].text()
            val ipSpeed = trs[i].select("td")[6].select("div[class=bar]").attr("title")

            ipMessage.ipAddress = ipAddress
            ipMessage.ipPort = ipPort
            ipMessage.ipType = ipType
            ipMessage.ipSpeed = ipSpeed

            ipMessages.add(ipMessage)
        }
        return ipMessages
    }
}
