package util

import java.sql.Connection
import java.sql.DriverManager

fun main() {
    MySQLUtil.connect1()
}

object MySQLUtil {
    private val JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"
    private val DB_URL = "jdbc:mysql://localhost:3306/temp?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC"
    private val USER = "root"
    private val PASS = "167943"

    val connection: Connection
        get() {
            Class.forName(JDBC_DRIVER)
            return DriverManager.getConnection(DB_URL, USER, PASS)!!
        }

    fun connect1() {
        val stmt = connection.createStatement()
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
}

