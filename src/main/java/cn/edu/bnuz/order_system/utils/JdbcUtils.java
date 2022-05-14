package cn.edu.bnuz.order_system.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author Zhang Hao
 * @create 2022-05-07-15:35
 */
public class JdbcUtils {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    //从配置文件configuration.properties中初始化常量
    static {

        try{
            //两种加载配置文件方法都行
            InputStream input=JdbcUtils.class.getClassLoader().getResourceAsStream("configuration.properties");
//            InputStream input = new FileInputStream("src/main/resources/configuration.properties");
            Properties properties = new Properties();
            properties.load(input);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * function annotation:
     * 获取数据库连接
     * @return Connection
     * @date
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,username,password);

        return connection;
    }
    /**
     * function annotation:
     * 释放资源
     * @return void
     * @date
     */
    public static void release( Connection connection, Statement statement) throws SQLException {
        if (statement != null){
            //资源连接关闭
            //避免泄露
            statement.close();
            //GC回收
            //节省内存
            statement = null;
        }
        if (connection != null){
            connection.close();
            connection = null;
        }
    }
    public static void release(ResultSet resultSet, Connection connection, Statement statement) throws SQLException {
        if (resultSet != null){
            resultSet.close();
            resultSet=null;
        }
        release(connection,statement);
    }

}
