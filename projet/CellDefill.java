import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

public class CellDefill extends ListCell<Personne> { 
    private final GridPane gridPane = new GridPane(); 
    private final Label labelNP = new Label(); 
    private final Label labelAge = new Label(); 
    private final ImageView imagePersonne = new ImageView(); 
    private final AnchorPane content = new AnchorPane(); 

public CellDefill() { 
    imagePersonne.setFitWidth(75); 
    imagePersonne.setPreserveRatio(true); 
    GridPane.setConstraints(imagePersonne, 0, 0, 1, 3); 
    GridPane.setValignment(imagePersonne, VPos.TOP); 
    // 
    labelNP.setStyle("-fx-font-size: 1.5em;"); 
    GridPane.setConstraints(labelNP, 1, 0); 
    // 
    labelAge.setStyle("-fx-opacity: 0.9; -fx-font-size: 1.3em;"); 
    GridPane.setConstraints(labelAge, 1, 1); 
    GridPane.setColumnSpan(labelAge, Integer.MAX_VALUE); 
    //         
    gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
    gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)); 
    gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
    gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
    gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
    gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
    gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true)); 
    gridPane.setHgap(6); 
    gridPane.setVgap(6); 
    gridPane.getChildren().setAll(imagePersonne, labelNP, labelAge); 
    AnchorPane.setTopAnchor(gridPane, 0d); 
    AnchorPane.setLeftAnchor(gridPane, 0d); 
    AnchorPane.setBottomAnchor(gridPane, 0d); 
    AnchorPane.setRightAnchor(gridPane, 0d); 
    content.getChildren().add(gridPane); 
} 

@Override 
protected void updateItem(Personne item, boolean empty) { 
    super.updateItem(item, empty); 
    setGraphic(null); 
    setText(null); 
    setContentDisplay(ContentDisplay.LEFT); 
    if (!empty && item != null) {
        try {
            labelNP.setText("Nom: "+item.nom+"\nPrenom: "+item.prenom);
            InputStream stream = new FileInputStream(item.URLPhoto);
            imagePersonne.setImage(new Image(stream));
            labelAge.setText(String.format("%d ans", item.age)); 
            setText(null); 
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
} 
}

