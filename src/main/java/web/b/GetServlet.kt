package web.b

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException

class GetServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
//        response.contentType = "text/html"
//        request.characterEncoding = "utf-8"
//        val name = request.getParameter("name")
//        val sex = request.getParameter("sex")
//        val age = request.getParameter("age")
//        val cla = request.getParameter("class")
//
//        val employ = Employ()
//        employ.name = name
//        employ.sex = sex
//        employ.age = Integer.parseInt(age)
//        employ.cla = cla
//
//        val ema = EmployAdd()
//        ema.addEmploy(employ)
        println(11)
    }

}
