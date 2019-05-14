package reptile

import org.apache.http.NameValuePair
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.util.*
import java.util.ArrayList




fun main() {
//    main1()
//    main2()
//    main3()
    main4()
}

fun main1() {
    // 创建默认的客户端实例
    val httpClient = HttpClients.createDefault()
    // 创建get请求实例
    val httpGet = HttpGet("http://www.baidu.com")
    println("13\texecuting request " + httpGet.uri)
    httpClient.use {
        // 客户端执行get请求返回响应
        val response = it.execute(httpGet)
        // 服务器响应状态行
        println("18\t" + response.statusLine.toString())
        val heads = response.allHeaders
        println("20\t" + Arrays.toString(response.getHeaders("Content-Type")))
        // 打印所有响应头
        for (h in heads) {
            println("23\t" + h.name + ":" + h.value)
        }
    }
}

fun main2() {
    //创建客户端
    val closeableHttpClient = HttpClients.createDefault()
    //创建请求Get实例
    val httpGet = HttpGet("https://www.baidu.com")

    //添加头部信息模拟浏览器访问
    httpGet.setHeader("Accept", "text/html,application/xhtml+xml," + "application/xml;q=0.9,image/webp,*/*;q=0.8")
    httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch, br")
    httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8")
    httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36" + " (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")

    //客户端执行httpGet方法，返回响应
    val closeableHttpResponse = closeableHttpClient.execute(httpGet)

    //得到服务响应状态码
    if (closeableHttpResponse.statusLine.statusCode == 200) {
        //得到响应实体
        val entity = EntityUtils.toString(closeableHttpResponse.entity, "utf-8")
    } else {
        //如果是其他状态码则做其他处理
    }
}

fun main3() {
    //创建客户端
    val closeableHttpClient = HttpClients.createDefault()

    //创建请求Get实例
    val httpGet = HttpGet("https://www.baidu.com")

    //设置头部信息进行模拟登录（添加登录后的Cookie）
    httpGet.setHeader("Accept", "text/html,application/xhtml+xml," + "application/xml;q=0.9,image/webp,*/*;q=0.8")
    httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch, br")
    httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8")
    //httpGet.setHeader("Cookie", ".......");
    httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36" + " (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")

    //客户端执行httpGet方法，返回响应
    val closeableHttpResponse = closeableHttpClient.execute(httpGet)

    //得到服务响应状态码
    if (closeableHttpResponse.statusLine.statusCode == 200) {
        //打印所有响应头
        val headers = closeableHttpResponse.allHeaders
        for (header in headers) {
            println(header.name + ": " + header.value)
        }
    } else {
        //如果是其他状态码则做其他处理
    }

}

fun main4() {
    //创建默认客户端
    val closeableHttpClient = HttpClients.createDefault()

    //创建Post请求实例
    val httpPost = HttpPost("http://www.renren.com/")

    //创建参数列表
    val nvps = ArrayList<NameValuePair>()
    nvps.add(BasicNameValuePair("domain", "renren.com"))
    nvps.add(BasicNameValuePair("isplogin", "true"))
    nvps.add(BasicNameValuePair("submit", "登录"))
    nvps.add(BasicNameValuePair("email", ""))
    nvps.add(BasicNameValuePair("passwd", ""))

    //向对方服务器发送Post请求
    //将参数进行封装，提交到服务器端
    httpPost.entity = UrlEncodedFormEntity(nvps, "UTF8")
    val httpResponse = closeableHttpClient.execute(httpPost)

    //如果模拟登录成功
    println(httpResponse.statusLine.statusCode)
    if (httpResponse.statusLine.statusCode == 200) {
        val headers = httpResponse.allHeaders
        for (header in headers) {
            println(header.name + ": " + header.value)
        }
    }
}
