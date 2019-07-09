package crawl.a.mainmethon

import crawl.a.httpbrowser.CreateUrl
import crawl.a.savefile.ImageFile

import java.util.ArrayList

import java.lang.System.out

/**
 * Created by hg_yi on 17-5-16.
 *
 * 测试数据：image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word=bird&
 *
 * 在多线程进行下载时，需要向线程中传递参数，此时有三种方法，我选择的第一种，设计构造器
 */

object major {
    @JvmStatic
    fun main(args: Array<String>) {
        var sum = 0
        val urlMains: List<String> = CreateUrl.createMainUrl()
        val imageUrls: List<String>

        //首先得到10个页面

        out.println(urlMains.size)
        for (urlMain in urlMains) {
            out.println(urlMain)
        }

        //使用Jsoup和FastJson解析出所有的图片源链接
        imageUrls = CreateUrl.createImageUrl(urlMains)

        for (imageUrl in imageUrls) {
            out.println(imageUrl)
        }

        //先创建出每个图片所属的文件夹
        ImageFile.createDir()

        val average = imageUrls.size / 10
        //对图片源链接进行下载（使用多线程进行下载）创建进程
        for (i in 0..9) {
            val begin = sum
            sum += average
            val last = sum

            var image: Thread?
            image = if (i < 9) {
                Thread(ImageFile(begin, last,
                        imageUrls as ArrayList<String>))
            } else {
                Thread(ImageFile(begin, imageUrls.size,
                        imageUrls as ArrayList<String>))
            }
            image.start()
        }
    }
}
