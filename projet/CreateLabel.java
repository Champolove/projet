import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class CreateLabel{
    Label label;
    
    public CreateLabel(int x,int y,Group root){
        label=new Label();
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        root.getChildren().add(label);
    }
    public CreateLabel(int x,int y,Group root,String texte){
        label=new Label(texte);
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        root.getChildren().add(label);
    }
    public CreateLabel(int x,int y,Group root,String texte,String style){
        label=new Label(texte);
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        label.setStyle(style);
        root.getChildren().add(label);
    }
}
