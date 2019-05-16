package crawl.ip.htmlparse

/**
 * Created by hg_yi on 17-8-8.
 */
class IpThread(private val urls: List<String>, private val ipPool: IpPool) : Thread() {

    override fun run() {
        //进行ip的抓取
        for (url in urls) {
            println(Thread.currentThread().name + "爬取的地址为：" + url)
        }
        ipPool.getIp(urls)
    }
}
