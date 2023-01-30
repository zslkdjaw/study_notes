import java.sql.*;
/*
@author zhang hao
@date  2021.11.17
 */
public class use_database {
    public static void main(String[] args) throws Exception{
        //加载驱动器
        Class.forName("com.mysql.cj.jdbc.Driver");
        //注册MySQL驱动器
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        //连接数据库
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_db?serverTimezone=GMT","root",
                "MYSQL");
        //创建一个Statement对象
        Statement stmt = con.createStatement();
        //增加记录
        //stmt.executeUpdate("insert into game(name,credit,passward,match_times)"+"values ('Lucy,',123,'cba',2)");
        //查询记录
        ResultSet rs = stmt.executeQuery("select  name,credit,passward,match_times from game");
        //输出记录
        while(rs.next()){
            String name = rs.getString(1);
            int credit = rs.getInt(2);
            String password = rs.getString(3);
            int match_times = rs.getInt(4);
            System.out.println(name+" "+credit+" "+password+" "+match_times);
        }
        //删除记录
        //stmt.executeUpdate("DELETE FROM game WHERE name='xx'");

        //关闭资源
        rs.close();
        stmt.close();
        con.close();


    }
}
