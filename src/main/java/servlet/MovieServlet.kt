package servlet

import dao.MovieDaoImpl
import dao.Paging
import entity.Movie

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.lang.Exception
import java.util.ArrayList


class MovieServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    public override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val text: String = try {
            request.getParameter("text")
        } catch (e: Exception) {
            ""
        }
        val queryAll = MovieDaoImpl.query(text)!!
        page(request, response, queryAll)
        request.getRequestDispatcher("/movie.jsp").forward(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    public override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request, response)
    }

}
fun page(request: HttpServletRequest, response: HttpServletResponse, list: List<Movie>) {
    //页面当前页
    var page = 0
    //得到传过来的当前页
    val str_page = request.getParameter("page")
    /**
     * 创建分页的关于一些内容的工具bean
     *
     * */
    val paging = Paging()
    paging.list = list//从数据库得到数据存入的list集合
    paging.setCount()//数据总数
    paging.pagesize = 20//一个页面的数据多少条
    paging.setPagenumber()//总的页面数
    paging.setEndpage()//最后一页
    paging.indexpage = 1//第一页
    if (str_page != null) {
        //将页转换整型判断其大小
        val pag = Integer.parseInt(str_page)
        //当大于零，将传过来的pag值赋给当前页page
        if (pag >= 0) {
            page = pag
            //如果小于最大值时则，将其传过来的值减1在赋值给当前页，让其一直在最后一页
            if (pag > paging.pagenumber - 1) {
                page = pag - 1
            }
        }
    }
    paging.page = page//最终确认当前页
    val list_page = ArrayList<Any>()
    //将当前页的值传给新的list_page集合中，list集合是全部数据综合，用i调用其中的几条数据给list_page
    var i = paging.page * paging.pagesize
    while (i < (paging.page + 1) * paging.pagesize && i < list.size) {
        list_page.add(list[i])
        i++
    }
    //将paging对象其设置在作用域中，以便后面页面调用
    request.setAttribute("paging", paging)
    request.setAttribute("userAll", list_page)


}
