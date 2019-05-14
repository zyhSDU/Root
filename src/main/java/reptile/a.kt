package reptile

import page.PageParserTool
import page.RequestAndResponseTool
import util.FileUtil

//https://www.cnblogs.com/sanmubird/p/7857474.html
fun main() {
    a.crawling(arrayOf("http://www.baidu.com"))
}

object a {
    /**
     * 使用种子初始化 URL 队列
     * @param seeds 种子 URL
     * @return
     */
    private fun initCrawlerWithSeeds(seeds: Array<String>) {
        for (i in seeds.indices) {
            Links.addUnvisitedUrlQueue(seeds[i])
        }
    }

    /**
     * 抓取过程
     * @param seeds
     * @return
     */
    fun crawling(seeds: Array<String>) {
        //初始化 URL 队列
        initCrawlerWithSeeds(seeds)
        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
        while (!Links.unVisitedUrlQueueIsEmpty() && Links.visitedUrlNum <= 1000) {
            //先从待访问的序列中取出第一个；
            val visitUrl = Links.removeHeadOfUnVisitedUrlQueue()
            //根据URL得到page;
            val page = RequestAndResponseTool.sendRequestAndGetResponse(visitUrl)!!
            //对page进行处理： 访问DOM的某个标签
            val es = PageParserTool.select(page, "a")
            if (!es.isEmpty()) {
                println("下面将打印所有a标签： ")
                println(es)
            }
            //将保存文件
            FileUtil.saveToLocal(page)
            //将已经访问过的链接放入已访问的链接中；
            Links.addVisitedUrlSet(visitUrl)

            //得到超链接
            val links = PageParserTool.getLinks(page, "img")
            for (link in links) {
                Links.addUnvisitedUrlQueue(link)
                println("新增爬取路径: $link")
            }
        }
    }
}


