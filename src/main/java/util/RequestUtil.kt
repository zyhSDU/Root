package util

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpException
import org.apache.commons.httpclient.HttpStatus
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.params.HttpMethodParams
import page.Page
import java.io.IOException

object RequestUtil {
    fun sendRequestAndGetResponse(url: String): Page? {
        var page: Page? = null
        // 1.生成 HttpClient 对象并设置参数
        val httpClient = HttpClient()
        // 设置 HTTP 连接超时 5s
        httpClient.httpConnectionManager.params.connectionTimeout = 5000
        // 2.生成 GetMethod 对象并设置参数
        val getMethod = GetMethod(url)
        // 设置 get 请求超时 5s
        getMethod.params.setParameter(HttpMethodParams.SO_TIMEOUT, 5000)
        // 设置请求重试处理
        getMethod.params.setParameter(HttpMethodParams.RETRY_HANDLER, DefaultHttpMethodRetryHandler())
        // 3.执行 HTTP GET 请求
        try {
            val statusCode = httpClient.executeMethod(getMethod)
            // 判断访问的状态码
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + getMethod.statusLine)
            }
            // 4.处理 HTTP 响应内容
            val responseBody = getMethod.responseBody// 读取为字节 数组
            val contentType = getMethod.getResponseHeader("Content-Type").value // 得到当前返回类型
            page = Page(responseBody, url, contentType) //封装成为页面
        } catch (e: HttpException) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            println("Please check your provided http address!")
            e.printStackTrace()
        } catch (e: IOException) {
            // 发生网络异常
            e.printStackTrace()
        } finally {
            // 释放连接
            getMethod.releaseConnection()
        }
        return page
    }
}