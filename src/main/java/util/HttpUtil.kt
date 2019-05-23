package util

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpStatus
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.params.HttpMethodParams
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import page.Page
import util.HttpUtil.httpClient
import value.StringValue

object HttpUtil {

    val httpClient = HttpClients.createDefault()!!

    object Get {
        fun create(url: String): HttpGet {
            return HttpGet(url).setHeader()
        }

        fun setHeader(httpGet: HttpGet): HttpGet {
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml," + "application/xml;q=0.9,image/webp,*/*;q=0.8")
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch, br")
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8")
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36" + " (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
            return httpGet
        }
    }

    //???奇怪的东西
    fun sendRequestAndGetResponse(url: String): Page? {
        var page: Page? = null
        // 1.生成 HttpClient 对象并设置参数
        val httpClient = HttpClient()
        // 设置 HTTP 连接超时 5s
        httpClient.httpConnectionManager.params.connectionTimeout = 5000
        // 2.生成 GetMethod 对象并设置参数
        val getMethod = GetMethod(url)
        // 设置 Get 请求超时 5s
        getMethod.params.setParameter(HttpMethodParams.SO_TIMEOUT, 5000)
        // 设置请求重试处理
        getMethod.params.setParameter(HttpMethodParams.RETRY_HANDLER, DefaultHttpMethodRetryHandler())
        // 3.执行 HTTP GET 请求
        val statusCode = httpClient.executeMethod(getMethod)
        // 判断访问的状态码
        if (statusCode != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + getMethod.statusLine)
        } else {
            // 4.处理 HTTP 响应内容
            val responseBody = getMethod.responseBody// 读取为字节 数组
            val contentType = getMethod.getResponseHeader("Content-Type").value // 得到当前返回类型
            page = Page(responseBody, url, contentType) //封装成为页面
            getMethod.releaseConnection()
        }
        return page
    }
}

fun HttpGet.setHeader(): HttpGet {
    return HttpUtil.Get.setHeader(this)
}

fun HttpGet.response(): CloseableHttpResponse {
    return httpClient.execute(this)!!
}

fun CloseableHttpResponse.ifSuccess(success:()->String,fail:()->Unit={}): String {
    return if (statusLine.statusCode == 200) {
        success()
    }else{
        fail()
        ""
    }
}

fun CloseableHttpResponse.entityString(): String {
    return ifSuccess(success = {
        EntityUtils.toString(entity, "utf-8")
    },fail = {
        EntityUtils.consume(entity)
    })
}

fun HttpPost.next(): CloseableHttpResponse {
    return httpClient.execute(this)!!
}