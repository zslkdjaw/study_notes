import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import java.io.IOException;


public class Controller extends Application {
    @FXML
    private Pagination page;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;
    @FXML
    private TableView<User> UserV;
    @FXML
    private TableColumn<User,Integer> credit;

    @FXML
    private TextField input;

    @FXML
    private TableColumn<User, Integer> match_times;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, String> profile_picture;


    @FXML
    private Button search;
    @FXML
    public void search(ActionEvent event) {
        for(User p : data){
            if(p.name.equals(input.getText())){
                alert("查询成功","积分： "+p.credit+"\n"+"密码： "+p.getPassword()+"\n"+
                        "比赛次数： "+p.getMatch_times(),p.getName());
                return;
            }
        }
        alert("查询失败"," ","查无此人");
    }
    @FXML
    public void add(ActionEvent event) throws IOException {
        //通过弹出的新界面来添加记录进入数据库
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("add_ui.fxml"));
        Scene scene =new Scene(root);
        primaryStage.setTitle("添加用户");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("add_ui 加载成功");
    }
    //可视化数据
    public  static ObservableList<User> data = FXCollections.observableArrayList();
    //新建数据库操作类 实例
    Using_database database = new Using_database();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, IOException {

        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        credit.setCellValueFactory(new PropertyValueFactory<User, Integer>("credit"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        match_times.setCellValueFactory(new PropertyValueFactory<User,Integer>("match_times"));
        profile_picture.setCellValueFactory(new PropertyValueFactory<User,String>("view"));
        //数据导入
        UserV.setItems(data);

        assert UserV != null : "fx:id=\"UserV\" was not injected: check your FXML file 'ui.fxml'.";
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'ui.fxml'.";
        assert credit != null : "fx:id=\"credit\" was not injected: check your FXML file 'ui.fxml'.";
        assert input != null : "fx:id=\"input\" was not injected: check your FXML file 'ui.fxml'.";
        assert match_times != null : "fx:id=\"match_times\" was not injected: check your FXML file 'ui.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'ui.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'ui.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'ui.fxml'.";
        assert profile_picture != null : "fx:id=\"profile_picture\" was not injected: check your FXML file 'ui.fxml'.";
        // 1 2 3 4 5（max）
        page.setMaxPageIndicatorCount(5);
        //设置为5行记录为一页
        page.setPageFactory(new Callback<Integer, Node>() {
            @Override
            //interger 为当前页面索引下标（从0开始）
            public Node call(Integer integer) {
                data.clear();
                //数据库连接
                try {
                    database.connect();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //获得数据库的数据集输出
                List<User> result = null;
                try {
                    result = database.five_page(integer);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //循环数据集输出
                for(User p : result ){
                    //添加数据集输出 到 data
                    data.add(p);
                }
                System.out.println(integer);
                return UserV;
            }
        });
    }

    //提示弹窗 title content header
    public void alert(String title , String content , String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set(title);
        alert.setContentText(content);
        alert.headerTextProperty().set(header);
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new Controller());
        loader.setLocation(getClass().getResource("ui.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        Parent root = loader.load();
        Scene scene =new Scene(root);
        primaryStage.setTitle("梭哈游戏排行榜");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
