import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class CreateTextField {
    TextField textfield;
    public CreateTextField(int x,int y,int minWidth){
        textfield = new TextField();
        textfield.setTranslateX(x);
        textfield.setTranslateY(y);
        textfield.setMinWidth(minWidth);
    
    } 
    public CreateTextField(int x,int y,int minWidth,String text,Group root){
        textfield = new TextField();
        textfield.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        textfield.setTranslateX(x);
        textfield.setTranslateY(y);
        textfield.setMinWidth(minWidth);
        textfield.setMaxWidth(minWidth);
        textfield.setText(text);
        textfield.setMinHeight(27);
        textfield.setMaxHeight(27);
        textfield.setStyle("-fx-alignment: center-left;");
        root.getChildren().add(textfield);
    }
}