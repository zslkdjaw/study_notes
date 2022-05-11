import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class add_Controller  extends Controller{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button submit;

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private TextField t3;

    @FXML
    private TextField t4;

    @FXML
    private TextField t0;

    @FXML
    void sub(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        //判断用户输入
        //用正则表达式判断 t2 t4能否转化
        //判断文件是否存在，
        if(t2.getText().matches("\\d+") && t4.getText().matches("\\d+" ) && new File(t0.getText()).exists()){
            //是否超出Blob类型的最大存储字节
            //TinyBlob 最大 255 Blob 最大 65K MediumBlob 最大 16 LongBlob 最大 4G
            if (new File(t0.getText()).length() > 60000){
                alert("", "添加失败，输入不符要求","");
            }
            else{
                //设置显示头像大小
                ImageView v = new ImageView(new Image("file:\\"+t0.getText()));
                v.setFitWidth(30);
                v.setFitHeight(27);
                User user = new User(t1.getText(),Integer.parseInt(t2.getText()),t3.getText(),Integer.parseInt(t4.getText()),v);
                //添加数据显示到 ui界面
                data.add(user);
                //连接数据库
                database.connect();
                //添加的数据先入到数据库保存
                database.insert(user,t0.getText());
                //提示
                alert("", "添加成功","");
            }
        }
        else{
            alert("", "添加失败，输入不符要求","");
        }
        //提示
        //然后关闭窗口
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }
    @Override
    @FXML
    public void initialize() {
        assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'add_ui.fxml'.";
        assert t1 != null : "fx:id=\"t1\" was not injected: check your FXML file 'add_ui.fxml'.";
        assert t2 != null : "fx:id=\"t2\" was not injected: check your FXML file 'add_ui.fxml'.";
        assert t3 != null : "fx:id=\"t3\" was not injected: check your FXML file 'add_ui.fxml'.";
        assert t4 != null : "fx:id=\"t4\" was not injected: check your FXML file 'add_ui.fxml'.";
    }
    @Override
    public void alert(String title , String content , String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.titleProperty().set(title);
        alert.setContentText(content);
        alert.headerTextProperty().set(header);
        alert.showAndWait();
    }
}
