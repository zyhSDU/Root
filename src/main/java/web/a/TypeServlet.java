//package web.a;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class TypeServlet extends HttpServlet{
//    private static final long serialVersionUID = 1L;
//
//    TypeService ts=new TypeService();
//    /**
//     * 方法选择
//     */
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");
//        String method = req.getParameter("method");
//        if ("add".equals(method)) {
//            add(req, resp);
//        } else if ("delete".equals(method)) {
//            delete(req, resp);
//        } else if ("update".equals(method)) {
//            update(req, resp);
//        } else if ("select".equals(method)) {
//            select(req, resp);
//        } else if ("byId".equals(method)) {
//            byId(req, resp);
//        } else if ("byName".equals(method)) {
//            byName(req, resp);
//        } else if ("list".equals(method)) {
//            list(req, resp);
//        }
//    }
//
//    /**
//     * 添加
//     * @param req
//     * @param resp
//     * @throws IOException
//     * @throws ServletException
//     */
//    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        req.setCharacterEncoding("utf-8");
//        String name = req.getParameter("name");
//        String teacher = req.getParameter("teacher");
//        String classroom = req.getParameter("classroom");
//        types type= new types(name, teacher, classroom);
//
//        //添加后消息显示
//        if(ts.add(type)) {
//            req.setAttribute("message", "添加成功");//setAttribute方法用于将内容保存在对象中，传到下一个页面中
//            req.getRequestDispatcher("add.jsp").forward(req,resp);//getRequestDispatcher方法用于进入下一个页面
//        } else {
//            req.setAttribute("message", "课程名称重复，请重新录入");
//            req.getRequestDispatcher("add.jsp").forward(req,resp);
//        }
//    }
//    /**
//     * 删除
//     * @param req
//     * @param resp
//     * @throws IOException
//     * @throws ServletException
//     */
//    private void delete(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
//        req.setCharacterEncoding("utf-8");
//        String name = req.getParameter("name");
//        ts.delete(name);
//        req.setAttribute("message", "课程信息删除成功");
//        req.getRequestDispatcher("delete.jsp").forward(req,resp);
//    }
//    /**
//     * 列表全部
//     * @param req
//     * @param resp
//     * @throws ServletException
//     */
//    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
//        req.setCharacterEncoding("utf-8");
//        List<types> type = ts.list();
//        req.setAttribute("type",type);
//        req.getRequestDispatcher("list.jsp").forward(req,resp);
//    }
//    /**
//     * 通过ID得到type
//     * 跳转至修改
//     * @param req
//     * @param resp
//     * @throws ServletException
//     */
//    private void byId(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
//        req.setCharacterEncoding("utf-8");
//        int id = Integer.parseInt(req.getParameter("id"));
//        types type = ts.byId(id);
//        req.setAttribute("type", type);
//        req.getRequestDispatcher("detail.jsp").forward(req,resp);
//    }
//    /**
//     * 通过名字查找
//     * 跳转至删除
//     * @param req
//     * @param resp
//     * @throws IOException
//     * @throws ServletException
//     */
//    private void byName(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
//        req.setCharacterEncoding("utf-8");
//        String name = req.getParameter("name");
//        types type = ts.byName(name);
//        if(type == null) {
//            req.setAttribute("message", "查无此课程！");
//            req.getRequestDispatcher("delete.jsp").forward(req,resp);
//        } else {
//            req.setAttribute("type", type);
//            req.getRequestDispatcher("detail2.jsp").forward(req,resp);
//        }
//    }
//    /**
//     * 修改
//     * @param req
//     * @param resp
//     * @throws IOException
//     * @throws ServletException
//     */
//    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
//        req.setCharacterEncoding("utf-8");
//        int id = Integer.parseInt(req.getParameter("id"));
//        String name = req.getParameter("name");
//        String teacher = req.getParameter("teacher");
//        String classroom = req.getParameter("classroom");
//        types type = new types(id, name, teacher, classroom);
//
//        ts.update(type);
//        req.setAttribute("message", "修改成功");
//        req.getRequestDispatcher("TypeServlet?method=list").forward(req,resp);
//    }
//    /**
//     * 查找
//     * @param req
//     * @param resp
//     * @throws ServletException
//     */
//    private void select(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
//        req.setCharacterEncoding("utf-8");
//        String name = req.getParameter("name");
//        String teacher = req.getParameter("teacher");
//        String classroom = req.getParameter("classroom");
//        List<types> type = ts.select(name, teacher, classroom);
//        req.setAttribute("type", type);
//        req.getRequestDispatcher("selectlist.jsp").forward(req,resp);
//    }
//}