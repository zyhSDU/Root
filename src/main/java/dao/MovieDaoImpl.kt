package dao

import entity.Movie
import util.MySQLUtil
import java.lang.Exception
import java.sql.SQLException
import java.util.*

object MovieDaoImpl {
    fun query(text: String): List<Movie>? {
        val list = ArrayList<Movie>()
        try {
            val sql = if (text == "") {
                "select * from movie"
            } else {
                "select * from movie where name like '%$text%'"
            }
            val rs = MySQLUtil.selectSql(sql)
            while (rs!!.next()) {
                with(Movie()) {
                    id = rs.getInt("id")
                    name = rs.getString("name")
                    try {
                        val map = rs.getString("url").split("[")[1].split("]")[0].split(",").map {
                            it.trim()
                        }
                        url=map[0]
                        url2=map[1].replace("m//","m/")
                    } catch (e: Exception) {
                    }
                    douban_pingfeng = rs.getString("douban_pingfeng")
                    douban_pingfeng_renshu = rs.getString("douban_pingfeng_renshu")
                    star = rs.getString("star")
                    releaseTime = rs.getString("releaseTime")
                    try {
                        releaseTime = releaseTime.split("：")[2]
                    } catch (e: Exception) {
                    }
                    list.add(this)
                }
            }
            MySQLUtil.closeConn()
            return list
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }
}