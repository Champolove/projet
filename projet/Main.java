import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

public class Main extends Application {

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
			primaryStage.setTitle("Champolove");
			primaryStage.getIcons().add(new Image("/images/icon.png"));
			Rectangle r = new Rectangle();
			Label label = new Label("Attributs pour la recherche inversée");
			label.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
			r.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
			r.setFill(javafx.scene.paint.Color.valueOf("#ECE8E7"));
			r.setX(30); 
			r.setY((int) scene.getHeight()-400); 
			r.setWidth(450); 
			r.setHeight(270); 
			r.setArcWidth(20); 
			r.setArcHeight(20); 
			label.setLayoutX(75);
			label.setLayoutY(r.getY() + r.getHeight() -300);
			root.getChildren().add(r);
			root.getChildren().add(label);

			Rectangle r2 = new Rectangle();
			r2.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
			r2.setFill(javafx.scene.paint.Color.valueOf("#ECE8E7"));
			r2.setX(300); 
			r2.setY(15); 
			r2.setWidth(300); 
			r2.setHeight(550); 
			r2.setArcWidth(20); 
			r2.setArcHeight(20); 
			root.getChildren().add(r2);

			Rectangle r3 = new Rectangle();
			r3.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
			r3.setFill(javafx.scene.paint.Color.valueOf("#ECE8E7"));
			r3.setX(scene.getWidth()-600); 
			r3.setY(15); 
			r3.setWidth(300); 
			r3.setHeight(550); 
			r3.setArcWidth(20); 
			r3.setArcHeight(20); 
			root.getChildren().add(r3);

			Button b=new Button();
			Algo algo=new Algo();
			GestionDonnee gd=new GestionDonnee();
			//côté gauche
			final ComboBox<Personne> comboBox = new ComboBox<Personne>();
			AfficheProfil aP=new AfficheProfil(gd.listePersonne.get(0), root,0,scene);
			comboBox.setButtonCell(new CellSelect());
			comboBox.setValue(gd.listePersonne.get(0));
			comboBox.setCellFactory(listView -> new CellDefill()); //changer ici pour les cases affichées
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
			AfficheProfil aP2=new AfficheProfil(gd.listePersonne.get(1), root,1,scene);
			comboBox2.setButtonCell(new CellSelect());
			comboBox2.setValue(gd.listePersonne.get(29));
			comboBox2.setCellFactory(listView -> new CellDefill()); //changer ici pour les cases affichées
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
			b.setText("Matching");
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
			
			TextField textField1 = new TextField();
			Button buttonRechercheinv = new Button("Recherche Inversée");
			buttonRechercheinv.setTranslateX(scene.getWidth()/2-24);
			buttonRechercheinv.setTranslateY(scene.getHeight()/2+50);
			textField1.setTranslateX(scene.getWidth()/2-40);
			textField1.setTranslateY(scene.getHeight()/2+100);
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
		
			String[] listeRecherche = {"non renseigné","non renseigné","non renseigné","non renseigné","non renseigné","non renseigné","non renseigné","non renseigné"};
			ComboBox<String> rechercheAnimal= new ComboBox<>();
			ComboBox<String> rechercheHobby= new ComboBox<>();
			ComboBox<String> rechercheAnimalMoins= new ComboBox<>();
			ComboBox<String> rechercheHobbyMoins= new ComboBox<>();
			rechercheAnimal.setButtonCell(new CellChoix());
			rechercheAnimal.setCellFactory(listView -> new CellChoix());
			rechercheAnimalMoins.setButtonCell(new CellChoix());
			rechercheAnimalMoins.setCellFactory(listView -> new CellChoix());
			for(Map.Entry s: GestionDonnee.dicoAnimal.entrySet()) {
				rechercheAnimal.getItems().addAll(String.valueOf(s.getKey())); 
				rechercheAnimalMoins.getItems().addAll(String.valueOf(s.getKey()));
					}
			rechercheHobby.setCellFactory(listView -> new CellChoix());
			rechercheHobbyMoins.setCellFactory(listView -> new CellChoix());
			for(Map.Entry s: GestionDonnee.dicoHobbies.entrySet()) {
					rechercheHobby.getItems().addAll(String.valueOf(s.getKey()));
					rechercheHobbyMoins.getItems().addAll(String.valueOf(s.getKey()));
				}
			rechercheHobby.setLayoutX(50);
			rechercheHobby.setLayoutY(scene.getHeight()-380);
			rechercheAnimal.setLayoutX(300);
			rechercheAnimal.setLayoutY(scene.getHeight()-380);
			rechercheHobbyMoins.setLayoutX(50);
			rechercheHobbyMoins.setLayoutY(scene.getHeight()-310);
			rechercheAnimalMoins.setLayoutX(300);
			rechercheAnimalMoins.setLayoutY(scene.getHeight()-310);
			rechercheHobby.valueProperty().addListener(observable -> {
				listeRecherche[0]=rechercheHobby.getValue();
			});
			
			rechercheHobbyMoins.valueProperty().addListener(observable -> {
				listeRecherche[1]=rechercheHobbyMoins.getValue();
			});
			rechercheAnimal.valueProperty().addListener(observable -> {
				listeRecherche[2]=rechercheAnimal.getValue();
				
			});
			rechercheAnimalMoins.valueProperty().addListener(observable -> {
				listeRecherche[3]=rechercheAnimalMoins.getValue();
				
			});
			ComboBox<String> cbLire = new ComboBox<>();
			ComboBox<String> cbJeuS= new ComboBox<>();
			ComboBox<String> cbJeuV = new ComboBox<>();
			ComboBox<String> cbEnfants = new ComboBox<>();
			String[] choix = {"oui", "non", "non renseigné" };
			cbLire.setButtonCell(new CellChoix());
			cbLire.setCellFactory(listView -> new CellChoix());
			cbJeuS.setButtonCell(new CellChoix());
			cbJeuS.setCellFactory(listView -> new CellChoix());
			cbJeuV.setButtonCell(new CellChoix());
			cbJeuV.setCellFactory(listView -> new CellChoix());
			cbEnfants.setButtonCell(new CellChoix());
			cbEnfants.setCellFactory(listView -> new CellChoix());
			for(String s: choix){
				cbLire.getItems().addAll(s);
				cbJeuS.getItems().addAll(s);
				cbJeuV.getItems().addAll(s);
				cbEnfants.getItems().addAll(s);
			}
			
			cbLire.valueProperty().addListener(observable -> {
				listeRecherche[4]=cbLire.getValue();
			});
			cbJeuS.valueProperty().addListener(observable -> {
				listeRecherche[5]=cbJeuS.getValue();
			});
			cbJeuV.valueProperty().addListener(observable -> {
				listeRecherche[6]=cbJeuV.getValue();
			});
			cbEnfants.valueProperty().addListener(observable -> {
				listeRecherche[7]=cbEnfants.getValue();
			});
			cbLire.setLayoutX(50);
			cbLire.setLayoutY(scene.getHeight()-240);
			cbJeuS.setLayoutX(300);
			cbJeuS.setLayoutY(scene.getHeight()-240);
			cbJeuV.setLayoutX(50);
			cbJeuV.setLayoutY(scene.getHeight()-170);
			cbEnfants.setLayoutX(300);
			cbEnfants.setLayoutY(scene.getHeight()-170);
			String[] listenom={"Hobby préféré","Hobby détesté","Aime Lire","Aime les jeux vidéos","Animal préféré","Animal détesté","Aime les jeux de sociétés","A des enfants"};
			for(int x=0;x<2;x++){
				for(int y=0;y<4;y++){
					CreateLabel cL=new CreateLabel(250*x+50, (int)scene.getHeight()-400+y*70, root, listenom[x*4+y]);
				}
			}
			
			root.getChildren().addAll(rechercheHobby,rechercheHobbyMoins,rechercheAnimal,rechercheAnimalMoins,cbLire,cbJeuS,cbJeuV,cbEnfants);
			Button bouttonRechercheSelect=new Button("Recherche sélective");
			bouttonRechercheSelect.setTranslateX(scene.getWidth()/2-24);
			bouttonRechercheSelect.setTranslateY(scene.getHeight()/2+150);
			Label erreur=new Label("");
			erreur.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 14));
			erreur.setTranslateX((int)scene.getWidth()/2-100);
			erreur.setTranslateY((int)scene.getHeight()/2+180);
			root.getChildren().add(erreur);
			bouttonRechercheSelect.setOnAction(e -> {
				erreur.setText("");
				ArrayList<Personne> listeRep=new ArrayList<Personne>();
					for(Personne p: gd.listePersonne){
						if((listeRecherche[0]=="non renseigné" || listeRecherche[0]==p.hobbi) && 
						(listeRecherche[1]=="non renseigné" || listeRecherche[1]==p.hobbiM) &&
						(listeRecherche[2]=="non renseigné" || listeRecherche[2]==p.animalAime) && 
						(listeRecherche[3]=="non renseigné" || listeRecherche[3]==p.animalDeteste) && 
						(listeRecherche[4]=="non renseigné" || listeRecherche[4]==p.aimeLire) && 
						(listeRecherche[5]=="non renseigné" || listeRecherche[5]==p.aimeJouerS) && 
						(listeRecherche[6]=="non renseigné" || listeRecherche[6]==p.aimeJouerJV) && 
						(listeRecherche[7]=="non renseigné" || listeRecherche[7]==p.aEnfant)){
							listeRep.add(p);
						}
					}
					if(listeRep.size()>0){
					comboBox.getItems().setAll(listeRep.get(0));
					for(int i=1;i<listeRep.size();i++) {
						comboBox.getItems().addAll(listeRep.get(i));
					}
					comboBox.setValue(listeRep.get(0));
					}
					else{
						erreur.setText("Personne ne correspond à ces attributs");
					}
				
			 });
			Button bouttonReset=new Button("Reset la recherche");
			bouttonReset.setTranslateX(scene.getWidth()/2-24);
			bouttonReset.setTranslateY(scene.getHeight()/2+250);
			bouttonReset.setOnAction(e -> {
			 comboBox.getItems().setAll(gd.listePersonne.get(0));
			 for(int i=1;i<gd.listePersonne.size();i++) {
				comboBox.getItems().addAll(gd.listePersonne.get(i));
			}
			});
			root.getChildren().addAll(bouttonRechercheSelect,bouttonReset);
			primaryStage.setScene(scene);
			primaryStage.show();

	}
		catch(Exception e) {
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
