import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class CreateLabel{
    public CreateLabel(int x,int y,Group root,String texte){
        Label labelDesc=new Label(texte);
        labelDesc.setTranslateX(x);
        labelDesc.setTranslateY(y);
        labelDesc.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        root.getChildren().add(labelDesc);
    }
}
