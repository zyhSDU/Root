package crawl.ip2.ipFilter

import crawl.ip2.ipModel.IpMessage

import java.util.*

/**
 * Created by paranoid on 17-4-14.
 * 对得到的IP进行筛选，将IP速度在两秒以内的并且类型是https的留下，其余删除
 */

object IpFilter {
    //对IP进行过滤
    fun filter(ipMessages1: List<IpMessage>): List<IpMessage> {
        val newIPMessages = ArrayList<IpMessage>()
        for (i in ipMessages1.indices) {
            val ipType = ipMessages1[i].ipType
            var ipSpeed = ipMessages1[i].ipSpeed

            ipSpeed = ipSpeed!!.substring(0, ipSpeed.indexOf('秒'))
            val speed = java.lang.Double.parseDouble(ipSpeed)

            if (ipType == "HTTPS" && speed <= 2.0) {
                newIPMessages.add(ipMessages1[i])
            }
        }
        return newIPMessages
    }
}
