
import javafx.scene.image.ImageView;

public class User {
    String name ;
    int credit ;
    String password;
    int match_times;
    ImageView view ;

    public User(String name ,int credit ,String password,int match_times,ImageView img){
        this.name = name;
        this.credit = credit;
        this.password = password;
        this.match_times=match_times;
        this.view = img;

    }
    public String getName() {
        return name;
    }
    public int getCredit() {
        return credit;
    }
    public int getMatch_times() {
        return match_times;
    }
    public String getPassword() {
        return password;
    }

    public ImageView getView(){

        return view;
    }

    @Override
    public String toString() {
        return this.name  +
                ","+ this.credit +
                "," + this.password +
                "," + this.match_times ;
    }
}
