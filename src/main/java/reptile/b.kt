package reptile

import org.jsoup.Jsoup
import util.FileUtil
import java.io.*
import java.net.URL
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

val dirPath= FileUtil.dirPath

fun main() {

    var url: String
    url="https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%20%20%20%20%20%20%20%20%20%20%20%20while%20((length%20%3D%20bis.read(bytes%2C%200%2C%20bytes.size))%20!%3D%20-1)%20%7B%20%20%20kotlin&oq=while%2520((length%2520%253D%2520bis.read(bytes%252C%25200%252C%2520bytes.size))%2520!%253D%2520-1)%2520%257B%2520kotlin&rsv_pq=ff7db4440018a180&rsv_t=9014lEW%2B0rzgJppCRxhc3%2FOlJnJ1AqLMeGzQQPYlBpxhmgj8RTXFuo%2B%2FqJw&rqlang=cn&rsv_enter=0"
    url="https://www.jianshu.com/u/bf21189c27f6"
    url="https://www.baidu.com/"
    url = "http://news.sina.com.cn/hotnews/?q_kkhha"
    // 解析本地html文件
    b.saveHtml(url,dirPath)
    Thread.sleep(5000)
    b.getLocalHtml(dirPath)

}

object b {
    /**
     *
     * @Title: saveHtml
     * @Description: 将抓取过来的数据保存到本地或者json文件
     * @param url
     * @return void 返回类型
     * @author liangchu
     * @date 2017-12-28 下午12:23:05
     * @throws
     */
    fun saveHtml(url: String, dirPath: String) {
        try {
            // 这是将首页的信息存入到一个html文件中 为了后面分析html文件里面的信息做铺垫
//            val dest = File("temp\\${TimeUtil.nowDateTime()}\\${FileUtil.getFileNameByUrl(url, contentType)}")

            val dest = File(dirPath + "a1.html")

            // 接收字节输入流
            val `is`: InputStream
            // 字节输出流
            val fos = FileOutputStream(dest)
            val temp = URL(url)
            // 这个地方需要加入头部 避免大部分网站拒绝访问
            // 这个地方是容易忽略的地方所以要注意
            val uc = temp.openConnection()
            // 因为现在很大一部分网站都加入了反爬虫机制 这里加入这个头信息
            uc.addRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 "
                            + "(iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) "
                            + "AppleWebKit/533.17.9"
                            + " (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5")
            `is` = temp.openStream()
            // 为字节输入流加入缓冲
            val bis = BufferedInputStream(`is`)
            // 为字节输出流加入缓冲
            val bos = BufferedOutputStream(fos)
            var length: Int = -1
            val bytes = ByteArray(1024 * 20)

            while (length.run {
                        length = bis.read(bytes, 0, bytes.size)
                        length != -1
                    }) {
                fos.write(bytes, 0, length)
            }
            bos.close()
            fos.close()
            bis.close()
            `is`.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    /*
     * 解析本地的html文件获取对应的数据
     */
    fun getLocalHtml(path: String) {
        // 读取本地的html文件
        val file = File(path)
        // 获取这个路径下的所有html文件
        val files = file.listFiles()
//        val news = ArrayList<New>()
        val response: HttpServletResponse? = null
        val request: HttpServletRequest? = null
        var tmp = 1
        // 循环解析所有的html文件
        try {
            for (i in files!!.indices) {
                // 首先先判断是不是文件
                if (files[i].isFile) {
                    // 获取文件名
                    val filename = files[i].name
                    // 开始解析文件

                    val doc = Jsoup.parse(files[i], "UTF-8")
                    // 获取所有内容 获取新闻内容
                    val contents = doc.getElementsByClass("ConsTi")
                    for (element in contents) {
                        val e1 = element.getElementsByTag("a")
                        for (element2 in e1) {
                            // System.out.print(element2.attr("href"));
                            // 根据href获取新闻的详情信息
                            val newText = desGetUrl(element2.attr("href"))
                            // 获取新闻的标题
                            val newTitle = element2.text()
                            exportFile(newTitle, newText)
                            println("抓取成功。。。$tmp")
                            tmp++

                        }
                    }
                }

            }
            //excelExport(news, response, request);
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
    /**
     *
     * @Title: desGetUrl
     * @Description: 根据url获取连接地址的详情信息
     * @param @param url 参数
     * @return void 返回类型
     * @author liangchu
     * @date 2017-12-28 下午1:57:45
     * @throws
     */
    fun desGetUrl(url: String): String {
        var newText = ""
        try {
            val doc = Jsoup
                    .connect(url)
                    .userAgent(
                            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)")
                    .get()
            // System.out.println(doc);
            // 得到html下的所有东西
            //Element content = doc.getElementById("article");
            val contents = doc.getElementsByClass("article")
            if (contents != null && contents.size > 0) {
                val content = contents[0]
                newText = content.text()
            }
            //System.out.println(content);
            //return newText;
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return newText
    }
    /*
     * 将新闻标题和内容写入文件
     */
    fun exportFile(title: String, content: String) {

        try {
            val file = File("$dirPath\\xinwen.txt")

            if (!file.parentFile.exists()) {//判断路径是否存在，如果不存在，则创建上一级目录文件夹
                file.parentFile.mkdirs()
            }
            val fileWriter = FileWriter(file, true)
            fileWriter.write("$title----------")
            fileWriter.write(content + "\r\n")
            fileWriter.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
