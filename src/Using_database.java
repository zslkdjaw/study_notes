
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库控制类
 * 简化数据库操作
 **/
public class Using_database {
    private  String url = "jdbc:mysql://localhost:3306/java_db?serverTimezone=GMT";
    private  final  static  String driver = "com.mysql.cj.jdbc.Driver";
    private String userName = "root";
    private String password = "MYSQL";
    private  Connection connection ;
    //静态查询
    private Statement statement ;
    //动态查询
    private PreparedStatement preparedStatement;

    public Using_database(){

    }
    /**
     *数据库的连接
     **/
    public void connect() throws ClassNotFoundException, SQLException {
        //加载驱动器
        Class.forName(driver);
        //注册MySQL驱动器
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        //连接数据库
         connection = DriverManager.getConnection(url,userName,password);
    }
    //通过执行SQL语句，实现数据的基本操作

    /**
     * 此方法通过sql语句获得结果集，再循环结果集保存到List数组当中并返回
     * 其中List 里的保存的基本数据是User类的示例对象
     * @return List
     * @throws SQLException
     */
    public List result_query() throws SQLException, IOException {
        //使用list 保存结果集的 输出
        List<User> list = new ArrayList<>();
        //创建statement语句
        statement = connection.createStatement();
        //通过sql语句返回结果集
        String sql = "select  name,credit,passward,match_times,picture from game";
        ResultSet rs = statement.executeQuery(sql);
        //遍历并输出结果集
        while(rs.next()){
            ////////////////////////////
            //获得Blob
            Blob blob = rs.getBlob(5);
            //blob转入流
            InputStream in = blob.getBinaryStream();
            //保存的头像名为 name+.jpg
            FileOutputStream out = new FileOutputStream(rs.getString(1)+".jpg");
            int b = -1 ;
            //循环写入
            while ((b=in.read())!=-1){
                out.write(b);
            }
            /////////////////
            //按user.name 获得用户头像
            ImageView v = new ImageView(new Image("file:\\C:\\Users\\沃德吉尔浩达\\IdeaProjects\\Database\\"+rs.getString(1)+".jpg"));
            //设置图像显示大小
            v.setFitWidth(30);
            v.setFitHeight(27);
            User p = new User(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getInt(4),v);
            //添加数据
            list.add(p);
        }
        //返回数据
        return list;
    }

    /**
     * 此方法通过sql语句插入记录，insert(User p )
     * 通过函数获得的形参User p 来插入记录
     * @throws SQLException
     */
    public void insert(User p,String path) throws SQLException, IOException {

        String sql = "INSERT INTO game(name,credit,passward,match_times,picture) VALUES (?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,p.name);
        preparedStatement.setInt(2,p.credit);
        preparedStatement.setString(3,p.password);
        preparedStatement.setInt(4,p.match_times);

        FileInputStream fis = new FileInputStream(path);
        preparedStatement.setBinaryStream(5,fis,fis.available());

        preparedStatement.executeUpdate();
        fis.close();
        preparedStatement.close();
    }
    /**
     * 此方法通过sql语句 获得数据库分页数据
     * 此程序需要5条记录作为一页
     */
    public List five_page (Integer integer) throws SQLException, ClassNotFoundException, IOException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM game limit ?,?";
        preparedStatement = connection.prepareStatement(sql);
        //页数
        preparedStatement.setInt(1,integer*5);
        //数据库每页获得的行数
        preparedStatement.setInt(2,5);
        //获得结果集
        ResultSet rs = preparedStatement.executeQuery();
        //遍历结果集
        while (rs.next()){
            Blob blob = rs.getBlob(5);
            //blob转入流
            InputStream in = blob.getBinaryStream();
            //保存的头像名为 name+.jpg
            FileOutputStream out = new FileOutputStream(rs.getString(1)+".jpg");
            int b = -1 ;
            //循环写入
            while ((b=in.read())!=-1){
                out.write(b);
            }
            /////////////////
            //按user.name 获得用户头像
            ImageView v = new ImageView(new Image("file:\\C:\\Users\\dooo\\IdeaProjects\\Database\\"+rs.getString(1)+".jpg"));
            //设置图像显示大小
            v.setFitWidth(30);
            v.setFitHeight(27);
            User p = new User(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getInt(4),v);
            //添加数据
            list.add(p);
            System.out.println(rs.getString(1));
        }
        return list;
    }


}