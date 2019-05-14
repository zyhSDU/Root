package reptile

import java.util.HashSet
import java.util.LinkedList
/*
 *  Link主要功能;
 *  1: 存储已经访问过的URL路径 和 待访问的URL 路径;
 * */
object Links {
    //已访问的 url 集合  已经访问过的 主要考虑 不能再重复了 使用set来保证不重复;
    private val visitedUrlSet = HashSet<String>()
    //待访问的 url 集合  待访问的主要考虑 1:规定访问顺序;2:保证不提供重复的带访问地址;
    //获得 待访问的 url 集合
    private val unVisitedUrlQueue = LinkedList<String>()
    //获得已经访问的 URL 数目
    val visitedUrlNum: Int
        get() = visitedUrlSet.size
    //添加到访问过的 URL
    fun addVisitedUrlSet(url: String) {
        visitedUrlSet.add(url)
    }
    //移除访问过的 URL
    fun removeVisitedUrlSet(url: String) {
        visitedUrlSet.remove(url)
    }

    // 添加到待访问的集合中  保证每个 URL 只被访问一次
    fun addUnvisitedUrlQueue(url: String?) {
        if (url != null && url.trim { it <= ' ' } != "" && !visitedUrlSet.contains(url) && !unVisitedUrlQueue.contains(url)) {
            unVisitedUrlQueue.add(url)
        }
    }
    //删除 待访问的url
    fun removeHeadOfUnVisitedUrlQueue(): String {
        return unVisitedUrlQueue.removeFirst()
    }
    //判断未访问的 URL 队列中是否为空
    fun unVisitedUrlQueueIsEmpty(): Boolean {
        return unVisitedUrlQueue.isEmpty()
    }
}
