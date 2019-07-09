package util

import java.sql.*

fun main() {
    MySQLUtil.connect1()
}

object MySQLUtil {
    private val JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"
    private val DB_URL = "jdbc:mysql://localhost:3306/temp?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC"
    private val USER = "root"
    private val PASS = "167943"

    private var rs: ResultSet? = null
    private var ps: PreparedStatement? = null

    val conn: Connection?
        get() {
            Class.forName(JDBC_DRIVER)
            return try {
                DriverManager.getConnection(DB_URL, USER, PASS)!!
            } catch (e: Exception) {
                println("init [SQL驱动程序初始化失败！]")
                e.printStackTrace()
                null
            }
        }

    fun connect1() {
        val stmt = conn!!.createStatement()
        val sql = "SELECT id, name, url FROM websites"
        val rs = stmt!!.executeQuery(sql)
        // 展开结果集数据库
        while (rs.next()) {
            // 通过字段检索
            val id = rs.getInt("id")
            val name = rs.getString("name")
            val url = rs.getString("url")
            // 输出数据
            print("ID: $id")
            print(", 站点名称: $name")
            print(", 站点 URL: $url")
            print("\n")
        }
    }

    fun addUpdDel(sql: String): Int {
        var i = 0
        try {
            ps = conn!!.prepareStatement(sql)
            i = ps!!.executeUpdate()
        } catch (e: SQLException) {
            println("sql数据库增删改异常")
            e.printStackTrace()
        }

        return i
    }

    fun selectSql(sql: String): ResultSet? {
        try {
            ps = conn!!.prepareStatement(sql)
            rs = ps!!.executeQuery(sql)
        } catch (e: SQLException) {
            println("sql数据库查询异常")
            e.printStackTrace()
        }

        return rs
    }

    fun closeConn() {
        try {
            conn!!.close()
        } catch (e: SQLException) {
            println("sql数据库关闭异常")
            e.printStackTrace()
        }

    }
}
