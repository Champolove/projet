import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
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
import java.util.ArrayList;

public class Main extends Application {
	
	public class cellSelect extends ListCell<Personne> {

	    @Override
	    protected void updateItem(Personne item, boolean empty) {
	        super.updateItem(item, empty);
	        setText(null);
	        if (!empty && item != null) {
	            final String text = String.format("%s %s", item.nom, item.prenom);
	            setText(text);
	        }
	    }
	}
	//change l'image de la personne de gauche	
	public class afficheProfil{
		ImageView imagePersonne = new ImageView();
		Label labelDescription;
		public afficheProfil(Personne p,Group root,int multi) throws FileNotFoundException{
			InputStream stream = new FileInputStream(p.URLPhoto);
			imagePersonne.setImage(new Image(stream));
			imagePersonne.setX(320+1000*multi);
			imagePersonne.setY(20);
			imagePersonne.setFitWidth(200);
			imagePersonne.setPreserveRatio(true);
			labelDescription=new Label("nom: "+p.nom+"\nprenom: "+p.prenom+"\nage: "+p.age);
			labelDescription.setTranslateX(320+1000*multi);
			labelDescription.setTranslateY(230);
			labelDescription.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 18));
			root.getChildren().add(labelDescription);
			root.getChildren().add(imagePersonne);
		}
		public void update(Personne p) throws FileNotFoundException{
			InputStream stream = new FileInputStream(p.URLPhoto);
			imagePersonne.setImage(new Image(stream));
			labelDescription.setText("nom: "+p.nom+"\nprenom: "+p.prenom+"\nage: "+p.age);
		}
	}

	public class cellDefill extends ListCell<Personne> { 
		private final GridPane gridPane = new GridPane(); 
		private final Label labelNP = new Label(); 
		private final Label labelAge = new Label(); 
		private final ImageView imagePersonne = new ImageView(); 
		private final AnchorPane content = new AnchorPane(); 
	
    public cellDefill() { 
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
				labelNP.setText("nom: "+item.nom+"\nprenom: "+item.prenom);
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
			Algo algo=new Algo();
			GestionDonnee gd=new GestionDonnee();
			//côté gauche
			final ComboBox<Personne> comboBox = new ComboBox<Personne>();
			afficheProfil aP=new afficheProfil(gd.listePersonne.get(0), root,0);
			comboBox.setButtonCell(new cellSelect());
			comboBox.setValue(gd.listePersonne.get(0));
			comboBox.setCellFactory(listView -> new cellDefill()); //changer ici pour les cases affichées
			for(int i=0;i<gd.listePersonne.size();i++) {
				comboBox.getItems().addAll(gd.listePersonne.get(i)); ///setall à la place de addAll pour remplacer les valeurs
			}
			comboBox.valueProperty().addListener(observable -> {
				try {
					aP.update(gd.dicoPersonne.get(comboBox.getValue().toString()));
				} catch (FileNotFoundException e1) {
					
				}catch(java.lang.NullPointerException e1){
				}
			});
			//côté droit
			final ComboBox<Personne> comboBox2 = new ComboBox<Personne>();
			afficheProfil aP2=new afficheProfil(gd.listePersonne.get(1), root,1);
			comboBox2.setButtonCell(new cellSelect());
			comboBox2.setValue(gd.listePersonne.get(1));
			comboBox2.setCellFactory(listView -> new cellDefill()); //changer ici pour les cases affichées
			for(int i=0;i<gd.listePersonne.size();i++) {
				comboBox2.getItems().addAll(gd.listePersonne.get(i)); ///setall à la place de addAll pour remplacer les valeurs
			}
			comboBox2.valueProperty().addListener(observable -> {
				try {
					aP2.update(gd.dicoPersonne.get(comboBox2.getValue().toString()));
				} catch (FileNotFoundException e1) {
				}catch(java.lang.NullPointerException e1){
				}
			});
			comboBox2.setLayoutX(scene.getWidth()-300);
			b.setLayoutX(scene.getWidth()/2);
			b.setLayoutY(scene.getHeight()/2);
			b.setText("matching");
			b.setOnAction(e-> {
				ArrayList<Personne> p=algo.listeMatch(comboBox.getValue(), gd.listePersonne);
				comboBox2.getItems().setAll(p.get(0));
				for(int i=1;i<p.size();i++) {
					comboBox2.getItems().addAll(p.get(i)); ///setall à la place de addAll pour remplacer les valeurs
				}
				comboBox2.setValue(p.get(0));
			});

			root.getChildren().add(b);
			root.getChildren().add(comboBox);
			root.getChildren().add(comboBox2);
			primaryStage.setScene(scene);
			primaryStage.show();
			TextField textField1 = new TextField();
			Button buttonRechercheinv = new Button("Recherche Inversée");
			buttonRechercheinv.setTranslateX(scene.getWidth()/2-24);
			buttonRechercheinv.setTranslateY(scene.getHeight()/2+100);
			textField1.setTranslateX(scene.getWidth()/2-40);
			textField1.setTranslateY(scene.getHeight()/2+50);
			buttonRechercheinv.setOnAction(e -> {
				try {
					String name = textField1.getText();
					Personne p=gd.dicoPersonne.get(name);
					if(p!=null){
					comboBox.setValue(p);
					aP.update(p);
					}
				} catch (FileNotFoundException e1) {
				}catch(java.lang.NullPointerException e1){
				}
			 });
			root.getChildren().addAll(textField1,buttonRechercheinv);
			
		} catch(Exception e) {
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
