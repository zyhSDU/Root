package crawl.ip.httpbrowser

import org.apache.http.HttpHost
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import java.io.IOException
import java.lang.Exception

/**
 * Created by paranoid on 17-4-10.
 * 进行代理访问
 *
 * setConnectTimeout：设置连接超时时间，单位毫秒.
 * setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒.
 * 这个属性是新加的属性，因为目前版本是可以共享连接池的.
 * setSocketTimeout：请求获取数据的超时时间，单位毫秒.如果访问一个接口，多少时间内无法返回数据，
 * 就直接放弃此次调用。
 */

object MyHttpResponse {
    fun getHtml(url: String, ip: String, port: String): String? {
        var entity: String? = null
        val httpClient = HttpClients.createDefault()

        //设置代理访问和超时处理
        println("此时线程: " + Thread.currentThread().name + " 爬取所使用的代理为: " + ip + ":" + port)
        val proxy = HttpHost(ip, Integer.parseInt(port))
        val config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).setSocketTimeout(3000).build()
        val httpGet = HttpGet(url)
        httpGet.config = config

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" + "q=0.9,image/webp,*/*;q=0.8")
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch")
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8")
        httpGet.setHeader("Cache-Control", "no-cache")
        httpGet.setHeader("Connection", "keep-alive")
        httpGet.setHeader("Host", "www.xicidaili.com")
        httpGet.setHeader("Pragma", "no-cache")
        httpGet.setHeader("Upgrade-Insecure-Requests", "1")
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " + "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")

        //客户端执行httpGet方法，返回响应
        try {
            val httpResponse = httpClient.execute(httpGet)
            //得到服务响应状态码
            if (httpResponse.statusLine.statusCode == 200) {
                entity = EntityUtils.toString(httpResponse.entity, "utf-8")
            }
        } catch (e: Exception) {
            entity = null
        }

        return entity
    }

    //对上一个方法的重载，使用本机ip进行网站爬取
    fun getHtml(url: String): String? {
        var entity: String? = null
        val httpClient = HttpClients.createDefault()

        //设置超时处理
        val config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000).build()
        val httpGet = HttpGet(url)
        httpGet.config = config

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" + "q=0.9,image/webp,*/*;q=0.8")
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch")
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8")
        httpGet.setHeader("Cache-Control", "no-cache")
        httpGet.setHeader("Connection", "keep-alive")
        httpGet.setHeader("Host", "www.xicidaili.com")
        httpGet.setHeader("Pragma", "no-cache")
        httpGet.setHeader("Upgrade-Insecure-Requests", "1")
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " + "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")

        //客户端执行httpGet方法，返回响应
        val httpResponse = httpClient.execute(httpGet)
        //得到服务响应状态码
        if (httpResponse.statusLine.statusCode == 200) {
            entity = EntityUtils.toString(httpResponse.entity, "utf-8")
        }

        return entity
    }
}
