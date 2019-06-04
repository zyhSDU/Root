package web.a;

import java.sql.Connection;
import java.sql.DriverManager;

public class Shujuku {
    public static Connection conn(){
        String url="jdbc:sqlserver://localhost:1433;DatabaseName=YourShujukuName";//填写你的数据库名
        String userName="sa";//填写你的用户名，我的是sa
        String userPwd="tzk19991029";//填写你的密码,我的是tzk19991029
        Connection con=null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");

        }catch(Exception e){

            e.printStackTrace();

            System.out.println("加载驱动失败！");
        }
        try{
            con=DriverManager.getConnection(url,userName,userPwd);
            System.out.println("连接数据库成功！");
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("SQL Server连接失败！");
        }
        return con;
    }
}