package util

import com.sun.net.httpserver.Authenticator
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
import util.RequestUtil.httpClient
import value.StringValue
import java.rmi.activation.ActivateFailedException

//整理httpClient
object RequestUtil {
    val httpClient = HttpClients.createDefault()!!

    object Get {
        fun setHeaderAsBrowser(httpGet: HttpGet) {
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml," + "application/xml;q=0.9,image/webp,*/*;q=0.8")
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch, br")
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8")
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36" + " (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
        }

        fun returnHttpGetAsBrowser(url: String = StringValue.URL.baidu): HttpGet {
            return HttpGet(url).setHeaderAsBrowser()
        }

        fun execute(url: String = StringValue.URL.baidu): CloseableHttpResponse? {
            return HttpGet(url).execute()
        }

        fun returnEntity(url: String = StringValue.URL.baidu): String? {
            val httpGet = returnHttpGetAsBrowser()
            val closeableHttpResponse = httpGet.execute()
            return closeableHttpResponse.returnEntity()
        }
    }

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

fun HttpGet.setHeaderAsBrowser(): HttpGet {
    RequestUtil.Get.setHeaderAsBrowser(this)
    return this
}

fun HttpGet.execute(): CloseableHttpResponse {
    return httpClient.execute(this)!!
}

fun CloseableHttpResponse.execute(success: () -> Unit , fail: () -> Unit = {}): Any {
    return if (statusLine.statusCode == 200) {
        println("CloseableHttpResponse.execute.success")
        success()
    } else {
        println("CloseableHttpResponse.execute.fail")
        fail()
    }
}

fun CloseableHttpResponse.returnEntity(): String? {
    return this.execute(
            success = {
                EntityUtils.toString(this.entity, "utf-8")
            }
    ) as String
}


fun HttpPost.execute(): CloseableHttpResponse {
    return httpClient.execute(this)!!
}