

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Main extends Application {
	public GestionDonnee getDonnee() {
		return new GestionDonnee();
	}
	public class SimplePersonneListCell extends ListCell<Personne> {

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
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,400,400);
			scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent mouseEvent) {
			        System.out.println("mouse click detected! " + mouseEvent.getSource());
			    }
			});
			primaryStage.setTitle("Test javafx");
			Button b=new Button();
			b.setLayoutX(scene.getWidth()/2);
			b.setLayoutY(scene.getHeight()/2);
			b.setText("allo");
			b.setOnAction(e-> System.out.println("hello"));
			root.getChildren().add(b);
			GestionDonnee gd=getDonnee();
			final ComboBox<Personne> comboBox = new ComboBox();
			comboBox.setButtonCell(new SimplePersonneListCell());
			comboBox.setCellFactory(listView -> new SimplePersonneListCell()); //changer ici pour les cases affichées
			for(int i=0;i<gd.listePersonne.size();i++) {
				comboBox.getItems().addAll(gd.listePersonne.get(i)); ///setall à la place de addAll pour remplacer les valeurs
			}
			root.getChildren().add(comboBox);
			comboBox.valueProperty().addListener(observable -> System.out.printf("Valeur sélectionnée: %s", comboBox.getValue()).println());
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
