import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.text.Font;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
	
	public class cellSelect extends ListCell<Personne> {

	    @Override
	    protected void updateItem(Personne item, boolean empty) {
	        super.updateItem(item, empty);
	        setText(null);
	        if (!empty && item != null) {
	            final String text = String.format("%s %s", item.prenom, item.nom);
	            setText(text);
	        }
	    }
	}
	//change l'image de la personne de gauche	
	public class afficheProfil{
		ImageView imagePersonne = new ImageView();
		Label prenomNom;
		public afficheProfil(Personne p,Group root) throws FileNotFoundException{
			InputStream stream = new FileInputStream(p.URLPhoto);
			imagePersonne.setImage(new Image(stream));
			imagePersonne.setX(170);
			imagePersonne.setY(20);
			imagePersonne.setFitWidth(200);
			imagePersonne.setPreserveRatio(true);
			prenomNom=new Label("Nom: "+p.nom+"\n"+"Prenom: "+p.prenom);
			prenomNom.setTranslateX(170);
			prenomNom.setTranslateY(230);
			prenomNom.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 18));
			root.getChildren().add(prenomNom);
			root.getChildren().add(imagePersonne);
		}
		public void update(Personne p) throws FileNotFoundException{
			InputStream stream = new FileInputStream(p.URLPhoto);
			imagePersonne.setImage(new Image(stream));
			prenomNom.setText("Nom: "+p.nom+"\n"+"Prenom: "+p.prenom);
		}
	}

	public class RichCarListCell extends ListCell<Personne> { 
		private final GridPane gridPane = new GridPane(); 
		private final Label labelNP = new Label(); 
		private final Label labelAge = new Label(); 
		private final ImageView imagePersonne = new ImageView(); 
		private final AnchorPane content = new AnchorPane(); 
	
    public RichCarListCell() { 
        imagePersonne.setFitWidth(75); 
        imagePersonne.setPreserveRatio(true); 
        GridPane.setConstraints(imagePersonne, 0, 0, 1, 3); 
        GridPane.setValignment(imagePersonne, VPos.TOP); 
        // 
        labelNP.setStyle("-fx-font-size: 1.5em;"); 
        GridPane.setConstraints(labelNP, 1, 0); 

        // 
        labelAge.setStyle("-fx-opacity: 0.75;"); 
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
            labelNP.setText("prenom: "+item.prenom+"\nnom: "+item.nom);
			try {
				InputStream stream = new FileInputStream(item.URLPhoto);
				imagePersonne.setImage(new Image(stream));
			} catch (IOException e) {
				e.printStackTrace();
			}
			labelAge.setText(String.format("%d ans", item.age)); 
            setText(null); 
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY); 
        } 
    } 
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			GraphicsDevice ecranTaille = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			Group root = new Group();
			Scene scene = new Scene(root,ecranTaille.getDisplayMode().getWidth(),ecranTaille.getDisplayMode().getHeight()-70);
			scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent mouseEvent) {
			        //System.out.println("mouse click detected! " + mouseEvent.getSource());
			    }
			});
			primaryStage.setTitle("Test javafx");
			Button b=new Button();
			//InputStream stream = new FileInputStream("D:\images\elephant.jpg");
			//Image image = new Image(stream);
			b.setLayoutX(scene.getWidth()/2);
			b.setLayoutY(scene.getHeight()/2);
			b.setText("allo");
			b.setOnAction(e-> System.out.println("hello"));
			root.getChildren().add(b);
			GestionDonnee gd=new GestionDonnee();
			final ComboBox<Personne> comboBox = new ComboBox<Personne>();
			afficheProfil aP=new afficheProfil(gd.listePersonne.get(0), root);
			comboBox.setButtonCell(new cellSelect());
			comboBox.setValue(gd.listePersonne.get(0));
			comboBox.setCellFactory(listView -> new RichCarListCell()); //changer ici pour les cases affichées
			for(int i=0;i<gd.listePersonne.size();i++) {
				comboBox.getItems().addAll(gd.listePersonne.get(i)); ///setall à la place de addAll pour remplacer les valeurs
			}
			comboBox.valueProperty().addListener(observable -> {
				try {
					aP.update(gd.dicoPersonne.get(comboBox.getValue().toString()));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			});
			root.getChildren().add(comboBox);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}
}
