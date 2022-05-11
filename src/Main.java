import javafx.application.Application;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.*;
import java.nio.BufferOverflowException;
import java.sql.SQLException;

/**
 @author zhang hao
 @date  2021.11.17
 在Mysql数据库中创建数据库java_db,同时创建表users，表字段内容参考下图，使用Swing/awt/JavaFx创建下面图表，使用JDBC知识完成以下功能：
 	显示所有数据表users中的全部记录；
 	根据昵称查询users符合条件的用户列表；
 	在完成查询编程内容后，尝试完成“添加”功能。

 **/
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Application.launch(Controller.class);

    }
}
