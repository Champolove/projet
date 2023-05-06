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
import javafx.scene.shape.Rectangle;
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
			primaryStage.setTitle("Champolove");
			primaryStage.getIcons().add(new Image("/images/icon.png"));

			Rectangle r = new CreateRectangle(30,(int) scene.getHeight()-400,450,270,20,"#ECE8E7").rectangle;
			Rectangle r2 = new CreateRectangle(300,15,300,550,20,"#ECE8E7").rectangle;
			Rectangle r3 = new CreateRectangle((int) scene.getWidth()-600,15,300,550,20,"#ECE8E7").rectangle;
			Label label = new CreateLabel(75, (int) (r.getY() + r.getHeight() -300), root,"Attributs pour la recherche inversée","-fx-font-size: 20px; -fx-text-fill: black;").label;
			root.getChildren().addAll(r,r2,r3);
			
			
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
			Button b=new CreateButton((int)scene.getWidth()/2, (int) scene.getHeight()/2, "Matching", root).button;
			b.setOnAction(e-> {
				ArrayList<Personne> p=algo.listeMatch(comboBox.getValue(), gd.listePersonne);
				comboBox2.getItems().setAll(p.get(0));
				for(int i=1;i<p.size();i++) {
					comboBox2.getItems().addAll(p.get(i)); ///setall à la place de addAll pour remplacer les valeurs
				}
				comboBox2.setValue(p.get(0));
			});
			root.getChildren().add(comboBox);
			root.getChildren().add(comboBox2);
			
			TextField textField1 = new TextField();
			Button buttonRechercheinv = new CreateButton((int) scene.getWidth()/2-24, (int) scene.getHeight()/2+50, "Recherche Inversée", root).button;
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
			root.getChildren().addAll(textField1);
			String[] listeRecherche = {"non renseigné","non renseigné","non renseigné","non renseigné","non renseigné","non renseigné","non renseigné","non renseigné"};
			ComboBox<String> rechercheHobby= new CreateComboBox(50,(int)scene.getHeight()-380,0,listeRecherche).comboBox;
			ComboBox<String> rechercheHobbyMoins= new CreateComboBox(50,(int)scene.getHeight()-310,1,listeRecherche).comboBox;
			ComboBox<String> rechercheAnimal= new CreateComboBox(300,(int)scene.getHeight()-380,2,listeRecherche).comboBox;
			ComboBox<String> rechercheAnimalMoins= new CreateComboBox(300,(int)scene.getHeight()-310,3,listeRecherche).comboBox;
			for(Map.Entry s: GestionDonnee.dicoHobbies.entrySet()) {
				rechercheHobby.getItems().addAll(String.valueOf(s.getKey()));
				rechercheHobbyMoins.getItems().addAll(String.valueOf(s.getKey()));
			}
			rechercheHobby.getItems().add("non renseigné");
			rechercheHobbyMoins.getItems().add("non renseigné");
			for(Map.Entry s: GestionDonnee.dicoAnimal.entrySet()) {
				rechercheAnimal.getItems().addAll(String.valueOf(s.getKey())); 
				rechercheAnimalMoins.getItems().addAll(String.valueOf(s.getKey()));
					}
			rechercheAnimal.getItems().add("non renseigné");
			rechercheAnimalMoins.getItems().add("non renseigné");
			ComboBox<String> cbLire = new CreateComboBox(50,(int)scene.getHeight()-240,4,listeRecherche).comboBox;
			ComboBox<String> cbJeuS= new CreateComboBox(300,(int)scene.getHeight()-240,5,listeRecherche).comboBox;
			ComboBox<String> cbJeuV = new CreateComboBox(50,(int)scene.getHeight()-170,6,listeRecherche).comboBox;
			ComboBox<String> cbEnfants = new CreateComboBox(300,(int)scene.getHeight()-170,7,listeRecherche).comboBox;
			String[] choix = {"oui", "non", "non renseigné" };
			for(String s: choix){
				cbLire.getItems().addAll(s);
				cbJeuS.getItems().addAll(s);
				cbJeuV.getItems().addAll(s);
				cbEnfants.getItems().addAll(s);
			}
			String[] listenom={"Hobby préféré","Hobby détesté","Aime Lire","Aime les jeux vidéos","Animal préféré","Animal détesté","Aime les jeux de sociétés","A des enfants"};
			for(int x=0;x<2;x++){
				for(int y=0;y<4;y++){
					Label cL=new CreateLabel(250*x+50, (int)scene.getHeight()-400+y*70, root, listenom[x*4+y]).label;
				}
			}
			root.getChildren().addAll(rechercheHobby,rechercheHobbyMoins,rechercheAnimal,rechercheAnimalMoins,cbLire,cbJeuS,cbJeuV,cbEnfants);
			Button bouttonRechercheSelect=new CreateButton((int) scene.getWidth()/2-24, (int) scene.getHeight()/2+150, "Recherche sélective",root).button;
			Label erreur=new CreateLabel((int)scene.getWidth()/2-100, (int)scene.getHeight()/2+180, root).label;
			erreur.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 14));
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
			Button bouttonReset=new CreateButton((int) scene.getWidth()/2-24, (int) scene.getHeight()/2+250, "Reset la recherche",root).button;
			bouttonReset.setOnAction(e -> {
			 comboBox.getItems().setAll(gd.listePersonne.get(0));
			 for(int i=1;i<gd.listePersonne.size();i++) {
				comboBox.getItems().addAll(gd.listePersonne.get(i));
			}
			});
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
