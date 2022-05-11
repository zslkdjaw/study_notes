package sample;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
public class Controller extends Application {

    private final ObservableList<Person> data = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableView<Person> tableview;
    @FXML
    private TableColumn<Person,String> Address;
    @FXML
    private TableColumn<Person,String> Name;
    @FXML
    private TableColumn<Person,String> Number;
    @FXML
    private TextField p;
    @FXML
    private Button add;
    @FXML
    private TextField addname;
    @FXML
    private TextField address;

    @FXML
    private Button delete;
    @FXML
    private Button in;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private Button out;
    @FXML
    private TextField phone;
    @FXML
    private Button research;
    @FXML
    private TextField ad;
    @FXML
    private Button change;
    @FXML
    private TextField na;
    @FXML
    private TextField phone2;
    @FXML
    private TextField path;
    @FXML
    public void initialize() throws IOException {
        Address.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));

        Name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));

        Number.setCellValueFactory(new PropertyValueFactory<Person, String>("number"));

        tableview.setItems(data);

        assert Address != null : "fx:id=\"Address\" was not injected: check your FXML file 'sample.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'sample.fxml'.";
        assert Number != null : "fx:id=\"Number\" was not injected: check your FXML file 'sample.fxml'.";
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'sample.fxml'.";
        assert addname != null : "fx:id=\"addname\" was not injected: check your FXML file 'sample.fxml'.";
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'sample.fxml'.";
        assert delete != null : "fx:id=\"delete\" was not injected: check your FXML file 'sample.fxml'.";
        assert in != null : "fx:id=\"in\" was not injected: check your FXML file 'sample.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'sample.fxml'.";
        assert out != null : "fx:id=\"out\" was not injected: check your FXML file 'sample.fxml'.";
        assert phone != null : "fx:id=\"phone\" was not injected: check your FXML file 'sample.fxml'.";
        assert research != null : "fx:id=\"research\" was not injected: check your FXML file 'sample.fxml'.";
        assert change != null : "fx:id=\"change\" was not injected: check your FXML file 'sample.fxml'.";
        assert research != null : "fx:id=\"research\" was not injected: check your FXML file 'sample.fxml'.";
        add.setOnAction(e->{
            boolean is = add();
            if(is){
                complete();
            }else{
                warming("请输入完整");
            }
        });
        delete.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("警告");
            alert.setHeaderText("确定删除？");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK){
                boolean is = delete();
                if(is){
                    complete();
                }else{
                    warming("不存在此用户");
                }
            }

        });
        research.setOnAction(e->{
            research();
        });
        change.setOnAction(e->{
            change();
        });
        out.setOnAction(e->{
            try {
                out();
                complete();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        in.setOnAction(e->{
            try {
                in();
                complete();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }

    public void warming(String a){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("警告");
        alert.headerTextProperty().set(a);
        alert.showAndWait();
    }

    public void complete(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("信息");
        alert.headerTextProperty().set("完成");
        alert.showAndWait();
    }

    public boolean add() {
        if (addname.getText() !="" && address.getText() !="" && phone.getText() !="") {
            //获得文本框的输入
            Person person = new Person(addname.getText(), address.getText(), phone.getText());
            //添加到表格中
            data.add(person);
            addname.clear();
            address.clear();
            phone.clear();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean delete() {
        int size = data.size();
        if (size <= 0) {
            return false;
        }
            for (int i = 0; i < size; i++) {
                Person person = data.get(i);
                if (person.name.equals(name.getText())||person.number.equals(phone2.getText())) {
                    data.remove(person);
                    name.clear();
                    phone2.clear();
                    return true;
                }
            }
        return false;
    }

    public void research()  {
        int size = data.size();
        if (size <= 0) {
            warming("通讯录中没有添加内容");
        }
        else{
            for (int i = 0; i < size; i++) {
                Person person = data.get(i);
                if (person.name.equals(name.getText())||person.number.equals(phone2.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("查询成功");
                    alert.setContentText("地址:"+person.address+"\n"+"电话"+person.number+"\n"+"编号"+i);
                    alert.headerTextProperty().set(person.name);
                    alert.showAndWait();
                    name.clear();
                    phone2.clear();
                }
            }
        }
    }

    public void change(){
        String ID = id.getText();
        int size = data.size();
        if (size <= 0) {
            warming("通讯录中没有添加内容");
        }
        else if(ID==""){
            warming("编号必填！");
        }
        else{
            int id = Integer.valueOf(ID).intValue();
            for (int i = 0; i < size; i++) {
                if (i==id) {
                    String name ;
                    String address;
                    String po;
                    if(na.getText()!=""){
                        name = na.getText();
                    }
                    else{
                        name=data.get(i).name;
                    }
                    if(ad.getText()!=""){
                        address=ad.getText();
                    }
                    else{
                        address=data.get(i).address;
                    }
                    if(p.getText()!=""){
                        po=p.getText();
                    }
                    else{
                        po=data.get(i).number;
                    }
                    Person person = new Person(name,address,po);
                    data.add(person);
                    Collections.swap(data,i,data.size()-1);
                    data.remove(data.get(data.size()-1));

                }
            }
            na.clear();
            p.clear();
            ad.clear();
            complete();
        }
    }

    public void out() throws IOException {
           File file = new File("text.txt");
           PrintWriter pw = new PrintWriter(file);
           for(int i = 0 ; i < data.size() ; i++){
               String a = data.get(i).name;
               String b = data.get(i).address;
               String c = data.get(i).number;
               pw.println(a+"\t"+b+"\t"+c);
           }
           pw.close();
    }

    public void in() throws IOException {
        File file = new File(path.getText());
        Scanner input = new Scanner(file);
        while(input.hasNext()){
            Person person = new Person(input.next(),input.next(),input.next());
            data.add(person);
        }
        input.close();
    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene =new Scene(root);
        primaryStage.setTitle("终极无敌强的通讯录");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

