package crawl.ip.htmlparse

import crawl.ip.ipModel.IpMessage
import crawl.ip.ipFilter.IpFilter
import crawl.ip.ipFilter.IpUtils

import java.util.ArrayList

/**
 * Created by hg_yi on 17-8-3.
 */
class IpPool(//成员变量（非线程安全）
        private val ipMessages: MutableList<IpMessage>) {

    fun getIp(urls: List<String>) {
        var ipAddress: String
        var ipPort: String

        var i = 0
        while (i < urls.size) {
            /** 随机挑选代理IP(仔细想了想，本步骤由于其他线程有可能在位置确定之后对ipMessages数量进行
             * 增加，虽说不会改变已经选择的ip代理的位置，但合情合理还是在对共享变量进行读写的时候要保证
             * 其原子性，否则极易发生脏读)
             */
            //每个线程先将自己抓取下来的ip保存下来并进行过滤与检测
            var ipMessages1: List<IpMessage> = ArrayList()
            val url = urls[i]

            synchronized(ipMessages) {
                val rand = (Math.random() * ipMessages.size).toInt()
                println("当前线程 " + Thread.currentThread().name + " rand值: " + rand +
                        " ipMessages 大小: " + ipMessages.size)

                ipAddress = ipMessages[rand].ipAddress!!
                ipPort = ipMessages[rand].ipPort!!
            }

            //这里要注意Java中非基本类型的参数传递方式，实际上都是同一个对象
            val status = UrlFetcher.urlParse(url, ipAddress, ipPort, ipMessages1.toMutableList())
            //如果ip代理池里面的ip不能用，则切换下一个IP对本页进行重新抓取
            
            if (!status) {
                i--
                i++
                continue
            } else {
                println("线程：" + Thread.currentThread().name + "已成功抓取 " + url + " ipMessage1：" + ipMessages1.size)
            }

            //对ip重新进行过滤，只要速度在两秒以内的并且类型为HTTPS的
            ipMessages1 = IpFilter.filter(ipMessages1)

            //对ip进行质量检测，将质量不合格的ip在List里进行删除
            IpUtils.ipIsAble(ipMessages1.toMutableList())

            //将质量合格的ip合并到共享变量ipMessages中，进行合并的时候保证原子性
            synchronized(ipMessages) {
                println("线程" + Thread.currentThread().name + "已进入合并区 " +
                        "待合并大小 ipMessages1：" + ipMessages1.size)
                ipMessages.addAll(ipMessages1)
            }
            i++
        }
    }
}
