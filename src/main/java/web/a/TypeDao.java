//package web.a;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TypeDao {
//    public boolean add(types type) {//添加
//        String sql = "insert into message(name, teacher, classroom) values('" + type.getName() + "','" + type.getTeacher() + "','" + type.getClassroom() + "')";
//        Connection con =Shujuku.conn() ;
//        Statement st= null;
//        boolean f = false;
//        int a = 0;
//
//        try {
//            st = con.createStatement();
//            st.executeUpdate(sql);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Shujuku.close(st, con);
//        }
//
//        if (a > 0) {
//            f = true;
//        }
//        return f;
//    }
//    public boolean delete(String name) {//删除
//        String sql="delete from message where name='"+name+"'";
//        Connection con =Shujuku.conn() ;
//        Statement st= null;
//        boolean f = false;
//        int a = 0;
//        try {
//            st = con.createStatement();
//            st.executeUpdate(sql);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Shujuku.close(st, con);
//        }
//
//        if (a > 0) {
//            f = true;
//        }
//        return f;
//    }
//    public boolean update(types type) {//修改
//        String sql="update type set name='"+type.getName()+"',teacher='"+type.getTeacher()+"', classroom='" + type.getClassroom()+ "' where id='" + type.getId() + "'";
//        Connection con =Shujuku.conn() ;
//        Statement st= null;
//        boolean f = false;
//        int a = 0;
//        try {
//            st = con.createStatement();
//            st.executeUpdate(sql);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Shujuku.close(st, con);
//        }
//
//        if (a > 0) {
//            f = true;
//        }
//        return f;
//    }
//    /**
//     * 通过id得到类
//     * @param id
//     * @return
//     */
//    public types byId(int id) {//按照id查询，返回一个types对象
//        String sql = "select * from course where id ='" + id + "'";
//        Connection con =Shujuku.conn() ;
//        Statement st= null;
//        ResultSet rs = null;//返回一个结果集
//        types type=null;
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery(sql);
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String teacher = rs.getString("teacher");
//                String classroom = rs.getString("classroom");
//                type = new types(id, name, teacher, classroom);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Shujuku.close(rs,st, con);
//        }
//        return type;
//    }
//    /**
//     * 通过名字得到类
//     * @param name
//     * @return
//     */
//    public types byName(String name) {//按照名字查询基本与id查询一样，sql语句不同
//        String sql = "select * from course where name ='" + name + "'";
//        Connection con =Shujuku.conn() ;
//        Statement st= null;
//        ResultSet rs = null;
//        types type=null;
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String teacher = rs.getString("teacher");
//                String classroom = rs.getString("classroom");
//                type = new types(id, name, teacher, classroom);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Shujuku.close(rs,st, con);
//        }
//        return type;
//    }
//
//    public List<types> select(String name, String teacher, String classroom){//模糊查询
//        String sql="select*from message where";
//        if (name != "") {
//            sql += "name like '%" + name + "%'";
//        }
//        if (teacher != "") {
//            sql += "teacher like '%" + teacher + "%'";
//        }
//        if (classroom != "") {
//            sql += "classroom like '%" + classroom + "%'";
//        }
//        List<types> list = new ArrayList<types>();
//        Connection con =Shujuku.conn() ;
//        Statement st= null;
//        ResultSet rs = null;
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery(sql);
//            types bean=null;
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name2 = rs.getString("name");
//                String teacher2 = rs.getString("teacher");
//                String classroom2 = rs.getString("classroom");
//                bean = new types(id, name2, teacher2, classroom2);
//                list.add(bean);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Shujuku.close(rs,st, con);
//        }
//        return list;
//    }
//    /**
//     * 列表全部数据
//     * @param name
//     * @param teacher
//     * @param classroom
//     * @return
//     */
//    public List<types> list(){
//        String sql = "select * from message";
//        List<types> list = new ArrayList<types>();
//        Connection con =Shujuku.conn() ;
//        Statement st= null;
//        ResultSet rs = null;
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery(sql);
//            types bean=null;
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name2 = rs.getString("name");
//                String teacher2 = rs.getString("teacher");
//                String classroom2 = rs.getString("classroom");
//                bean = new types(id, name2, teacher2, classroom2);
//                list.add(bean);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Shujuku.close(rs,st, con);
//        }
//        return list;
//    }
//    /**
//     * 验证课程名称是否唯一
//     * true --- 不唯一
//     * @param name
//     * @return
//     */
//    public  boolean name(String name) {
//        String sql = "select * from course where name ='" + name + "'";
//        Connection con =Shujuku.conn() ;
//        Statement st= null;
//        ResultSet rs = null;
//        boolean flag=false;
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery(sql);
//            while (rs.next()) {
//                flag = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Shujuku.close(rs,st, con);
//        }
//        return flag;
//
//    }
//}