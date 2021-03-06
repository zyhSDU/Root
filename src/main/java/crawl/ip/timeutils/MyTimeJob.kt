package crawl.ip.timeutils

import crawl.ip.database.MyRedis
import crawl.ip.htmlparse.IpPool
import crawl.ip.htmlparse.IpThread
import crawl.ip.htmlparse.UrlFetcher
import crawl.ip.ipFilter.IpFilter
import crawl.ip.ipFilter.IpUtils
import crawl.ip.ipModel.IpMessage
import java.util.*

/**
 * ip代理池里面最少保存200个代理ip
 * 多线程主要考虑的就是合理的任务分配以及线程安全性。
 * implements Job
 */

class MyTimeJob : TimerTask() {
    private var redis = MyRedis()

    override fun run() {
        //首先清空redis数据库中的key
        redis.deleteKey("IpPool")

        //存放爬取下来的ip信息
        var ipMessages: MutableList<IpMessage> = ArrayList()
        val urls = ArrayList<String>()
        //对创建的子线程进行收集
        val threads = ArrayList<Thread>()

        //首先使用本机ip爬取xici代理网第一页
        ipMessages = UrlFetcher.urlParse(ipMessages).toMutableList()

        //对得到的IP进行筛选，将IP速度在两秒以内的并且类型是https的留下，其余删除
        ipMessages = IpFilter.filter(ipMessages).toMutableList()

        //对拿到的ip进行质量检测，将质量不合格的ip在List里进行删除
        IpUtils.ipIsAble(ipMessages)

        //构造种子url(4000条ip)
        for (i in 2..41) {
            urls.add("http://www.xicidaili.com/nn/$i")
        }

        /**
         * 对urls进行解析并进行过滤,拿到所有目标IP(使用多线程)
         *
         * 基本思路是给每个线程分配自己的任务，在这个过程中List<IpMessage> ipMessages
         * 应该是共享变量，每个线程更新其中数据的时候应该注意线程安全
        </IpMessage> */
        val ipPool = IpPool(ipMessages)
        for (i in 0..19) {
            //给每个线程进行任务的分配
            val ipThread = IpThread(urls.subList(i * 2, i * 2 + 2), ipPool)
            threads.add(ipThread)
            ipThread.start()
        }

        for (thread in threads) {
            thread.join()
        }

        for (ipMessage in ipMessages) {
            println(ipMessage)
        }

        //将爬取下来的ip信息写进Redis数据库中(List集合)
        redis.setIpToList(ipMessages)

        //从redis数据库中随机拿出一个IP
        val ipMessage = redis.ipByList
        println(ipMessage)
        redis.close()
    }
}
