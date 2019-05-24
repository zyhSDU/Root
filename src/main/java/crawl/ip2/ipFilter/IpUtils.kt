package crawl.ip2.ipFilter

import crawl.ip2.ipModel.IpMessage
import org.apache.http.HttpHost
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients

import java.io.IOException

/**
 * Created by paranoid on 17-4-21.
 * 测试此IP是否有效
 */

object IpUtils {
    fun ipIsAble(ipMessages1: MutableList<IpMessage>) {
        val httpClient = HttpClients.createDefault()
        var response: CloseableHttpResponse? = null

        var i = 0
        while (i < ipMessages1.size) {
            val ip = ipMessages1[i].ipAddress
            val port = ipMessages1[i].ipPort

            val proxy = HttpHost(ip, Integer.parseInt(port))
            val config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(5000).setSocketTimeout(5000).build()
            val httpGet = HttpGet("https://www.baidu.com")
            httpGet.config = config

            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" + "q=0.9,image/webp,*/*;q=0.8")
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch")
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8")
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit" + "/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")

            try {
                response = httpClient!!.execute(httpGet)
            } catch (e: IOException) {
                println("不可用代理已删除" + ipMessages1[i].ipAddress + ": " + ipMessages1[i].ipPort)
                ipMessages1.remove(ipMessages1[i])
                i--
            }
            i++
        }
        try {
            httpClient?.close()
            response?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
