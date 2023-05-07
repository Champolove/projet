import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StatusListCell extends ListCell<String> {
    private ImageView imagePersonne = new ImageView();
    public StatusListCell(){
        imagePersonne=new ImageView();
        
    }
    protected void updateItem(String item, boolean empty){
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        if(item!=null){
            imagePersonne = new ImageView(new Image(item));
            imagePersonne.setFitWidth(40);
            imagePersonne.setFitHeight(40);
            setGraphic(imagePersonne);
            setText(null);
        }
    }

}
