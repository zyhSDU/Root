package util

import page.Page

import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/*  本类主要是 下载那些已经访问过的文件*/
object FileTool {
     val dirPath: String
        get() {
            val a="data\\temp\\${TimeUtil.nowDateTime()}\\"
            val fileDir = File(a)
            if (!fileDir.exists()) {
                fileDir.mkdir()
            }
            return a
        }
    /**
     * getMethod.getResponseHeader("Content-Type").getValue()
     * 根据 URL 和网页类型生成需要保存的网页的文件名，去除 URL 中的非文件名字符
     */
    private fun getFileNameByUrl(url: String, contentType: String): String {
        var url = url
        //去除 http://
        url = url.substring(7)
        //text/html 类型
        return if (contentType.indexOf("html") != -1) {
            url = url.replace("[\\?/:*|<>\"]".toRegex(), "_") + ".html"
            url
        } else {
            url.replace("[\\?/:*|<>\"]".toRegex(), "_") + "." +
                    contentType.substring(contentType.lastIndexOf("/") + 1)
        }//如 application/pdf 类型
    }

    /**
     * 保存网页字节数组到本地文件，filePath 为要保存的文件的相对地址
     */
    fun saveToLocal(page: Page) {

        val fileName = getFileNameByUrl(page.url, page.contentType)
        val filePath = dirPath + fileName
        val data = page.content
        try {
            //Files.lines(Paths.get("D:\\jd.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
            val out = DataOutputStream(FileOutputStream(File(filePath)))
            for (i in data!!.indices) {
                out.write(data[i].toInt())
            }
            out.flush()
            out.close()
            println("文件：" + fileName + "已经被存储在" + filePath)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
