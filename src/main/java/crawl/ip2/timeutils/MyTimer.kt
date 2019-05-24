package crawl.ip2.timeutils

import java.util.*

/**
 * Created by paranoid on 17-4-13.
 */

object MyTimer {
    @JvmStatic
    fun main(args: Array<String>) {
        //设置定时任务，从现在开始，每24小时执行一次
        Timer().schedule(MyTimeJob(), Calendar.getInstance().time, (24 * 60 * 60 * 1000).toLong())
    }
}
