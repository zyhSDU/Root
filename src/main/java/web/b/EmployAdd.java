package web.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class EmployAdd {

    public boolean addEmploy(Employ employ) {
        String name=employ.getName();
        String sex=employ.getSex();
        int age=employ.getAge();
        String cla=employ.getCla();

        Connection conn=null;
        PreparedStatement pstat=null;
        //注册驱动
        String driver = "com.mysql.cj.jdbc.Driver";
        //URL指向要访问的数据库名mysql
        String url = "jdbc:mysql://localhost:3306/mysql?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
        //MySQL配置时用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123456";
        try {
            Class.forName(driver); //加载驱动程序
            conn=DriverManager.getConnection(url, user, password);//获取数据库链接
            String sql="insert into student (name,age,sex,cla) values (?,?,?,?)";
            pstat=conn.prepareStatement(sql); //创建prepareStatement类对象，用来执行SQL语句
            pstat.setString(1, name);
            pstat.setInt(2, age);
            pstat.setString(3, sex);
            pstat.setString(4, cla);
            pstat.executeUpdate();//执行sql语句

            pstat.close();
            conn.close();
        } catch(ClassNotFoundException e) {
            System.out.println("加载驱动异常!");
            e.printStackTrace();
        } catch(SQLException e) {
            System.out.println("获取连接异常!");
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return true;
    }
}
