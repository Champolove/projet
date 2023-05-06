import javafx.scene.Group;
import javafx.scene.control.Button;

public class CreateButton {
    Button button;
    public CreateButton(int x,int y,String texte,Group root){
        button=new Button(texte);
        button.setTranslateX(x);
        button.setTranslateY(y);
        root.getChildren().add(button);
    }
    
}
