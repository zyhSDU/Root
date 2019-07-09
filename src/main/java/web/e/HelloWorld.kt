package web.e

// 导入必需的 java 库

import java.io.*
import javax.servlet.*
import javax.servlet.http.*

// 扩展 HttpServlet 类
class HelloWorld : HttpServlet() {

    private var message: String? = null

    @Throws(ServletException::class)
    override fun init() {
        // 执行必需的初始化
        message = "Hello World"
    }

    @Throws(ServletException::class, IOException::class)
    public override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        // 设置响应内容类型
        response.contentType = "text/html"

        // 实际的逻辑是在这里
        val out = response.writer
        out.println("<h1>$message</h1>")
    }

    override fun destroy() {
        // 什么也不做
    }
}