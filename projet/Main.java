import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;


import javafx.scene.text.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

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
	public class afficheProfil{
		ImageView imagePersonne = new ImageView();
		Label prenomNom;
		public afficheProfil(Personne p,Group root){
			imagePersonne.setImage(new Image(p.URLPhoto));
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
		public void update(Personne p){
			imagePersonne.setImage(new Image(p.URLPhoto));
			prenomNom.setText("Nom: "+p.nom+"\n"+"Prenom: "+p.prenom);
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
			comboBox.setCellFactory(listView -> new cellSelect()); //changer ici pour les cases affichées
			for(int i=0;i<gd.listePersonne.size();i++) {
				comboBox.getItems().addAll(gd.listePersonne.get(i)); ///setall à la place de addAll pour remplacer les valeurs
			}
			comboBox.valueProperty().addListener(observable -> aP.update(gd.dicoPersonne.get(comboBox.getValue().toString())));
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
