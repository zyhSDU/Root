package crawl

import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import util.RequestUtil
import util.execute
import util.setHeaderAsBrowser
import value.StringValue.URL.baidu
import value.StringValue.URL.renrenLogin
import java.util.*

//https://blog.csdn.net/championhengyi/article/details/64618454

fun main() {
//    main1()
//    main2()
//    main3()
    main4()
}

fun main1() {
    val httpClient = HttpClients.createDefault()    // 创建默认的客户端实例
    val httpGet = HttpGet(baidu)    // 创建get请求实例
    println("13\texecuting request " + httpGet.uri)//+++
    val response = httpClient.execute(httpGet)    // 客户端执行get请求返回响应
    println("18\t" + response.statusLine.toString())    // 服务器响应状态行+++
    val heads = response.allHeaders//+++
    println("20\t" + Arrays.toString(response.getHeaders("Content-Type")))//+++
    for (h in heads) {    // 打印所有响应头
        println("23\t" + h.name + ":" + h.value)
    }
}

fun main2() {
    val closeableHttpClient = HttpClients.createDefault()
    val httpGet = RequestUtil.Get.returnHttpGetAsBrowser()
    val closeableHttpResponse = closeableHttpClient.execute(httpGet)
    if (closeableHttpResponse.statusLine.statusCode == 200) {
        val entity = EntityUtils.toString(closeableHttpResponse.entity, "utf-8")
        println(entity)
    } else {
        //如果是其他状态码则做其他处理
    }
}

fun main3() {
    val closeableHttpResponse = HttpGet(baidu).setHeaderAsBrowser().execute()
    closeableHttpResponse.execute({
        val headers = closeableHttpResponse.allHeaders
        for (header in headers) {
            println(header.name + ": " + header.value)
        }
    })
}

fun main4() {
    //创建Post请求实例
    val httpPost = HttpPost(renrenLogin)
    //创建参数列表
    val nvps = ArrayList<NameValuePair>()
    nvps.add(BasicNameValuePair("domain", "renren.com"))
    nvps.add(BasicNameValuePair("isplogin", "true"))
    nvps.add(BasicNameValuePair("submit", "登录"))
    nvps.add(BasicNameValuePair("email", "18340018780"))
    nvps.add(BasicNameValuePair("passwd", "zscx167943"))

    //向对方服务器发送Post请求
    //将参数进行封装，提交到服务器端
    httpPost.entity = UrlEncodedFormEntity(nvps, "UTF8")
    val httpResponse = httpPost.execute()

    //如果模拟登录成功
    println(httpResponse.statusLine.statusCode)
    if (httpResponse.statusLine.statusCode == 200) {
        val headers = httpResponse.allHeaders
        for (header in headers) {
            println(header.name + ": " + header.value)
        }
    }
}
