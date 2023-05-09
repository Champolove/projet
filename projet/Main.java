import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			//setup
			GraphicsDevice ecranTaille = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			Group root = new Group();
			Scene scene = new Scene(root,ecranTaille.getDisplayMode().getWidth(),ecranTaille.getDisplayMode().getHeight()-70);
			primaryStage.setTitle("Champolove");
			primaryStage.getIcons().add(new Image("/images/coeur-plein.png"));
			Algo algo=new Algo();
			File file=new File("projet/data.txt");
			File file2=new File("projet/contexte.txt");
			GestionDonnee gd;
			if(file.exists() && file2.exists()){
				gd=new GestionDonnee(file,file2);
			}
			else{
				gd=new GestionDonnee();
			}
			
			TextArea textArea = new TextArea();
			textArea.setTranslateX((int) scene.getWidth()/2-240);
			textArea.setTranslateY((int) scene.getHeight()/2-20);
			textArea.setPromptText("Rentrez les informations du rendez-vous");
			root.getChildren().add(textArea);
			Label erreurHistorique=new CreateLabel((int) scene.getWidth()/2-190, 350, root).label;
			Label erreurRechercheinversee=new CreateLabel((int) scene.getWidth()/2-180,(int) scene.getHeight()/2+180, root).label;
			Label erreur=new CreateLabel((int)scene.getWidth()/2-135, (int)scene.getHeight()/2+180, root).label;
			erreurHistorique.setStyle("-fx-text-fill: #FF0000;");
			
			//côté gauche
			Rectangle r2 = new CreateRectangle(300,15,300,550,20,"#ECE8E7").rectangle;
			root.getChildren().add(r2);
			final ComboBox<Personne> comboBox = new ComboBox<Personne>();
			AfficheProfil aP=new AfficheProfil(gd.listePersonne.get(0), root,0,scene);
			comboBox.setButtonCell(new CellSelect());
			comboBox.setValue(gd.listePersonne.get(0));
			comboBox.setCellFactory(listView -> new CellDefill()); //changer ici pour les cases affichées
			
			//modifications côté gauche
			Button bouttonModifGauche=new CreateButton(365, 580, "Enregistrer les modifications", root).button;
			bouttonModifGauche.setOnAction(e->{
				Personne p=gd.dicoPersonne.get(comboBox.getValue().toString());
				aP.modification(p,gd);
			});

			//côté droit
			Rectangle r3 = new CreateRectangle((int) scene.getWidth()-600,15,300,550,20,"#ECE8E7").rectangle;
			root.getChildren().add(r3);
			final ComboBox<Personne> comboBox2 = new ComboBox<Personne>();
			AfficheProfil aP2=new AfficheProfil(gd.listePersonne.get(29), root,1,scene);
			comboBox2.setButtonCell(new CellSelect());
			comboBox2.setValue(gd.listePersonne.get(29));
			comboBox2.setCellFactory(listView -> new CellDefill()); //changer ici pour les cases affichées
			for(int i=0;i<gd.listePersonne.size();i++) {
				comboBox.getItems().add(gd.listePersonne.get(i));
				comboBox2.getItems().add(gd.listePersonne.get(i)); ///setall à la place de addAll pour remplacer les valeurs
			}
			//observable sur les comboBox
			comboBox.valueProperty().addListener(observable -> {
				try {
					erreurHistorique.setText("");
					erreur.setText("");
					aP.update(gd.dicoPersonne.get(comboBox.getValue().toString()));
					Personne p;
					Personne p2;
					textArea.setText("");
					for(Historique h: gd.dicoHistorique.values()){
						p=gd.dicoPersonne.get(comboBox.getValue().toString());
						p2=gd.dicoPersonne.get(comboBox2.getValue().toString());
						if((h.personne1.nom==p.nom && h.personne1.prenom==p.prenom &&
						   h.personne2.nom==p2.nom && h.personne2.prenom==p2.prenom)||(h.personne1.nom==p2.nom && h.personne1.prenom==p2.prenom &&
						   h.personne2.nom==p.nom && h.personne2.prenom==p.prenom) ){
							textArea.setText(h.contexte);
						}
					}
				} catch (FileNotFoundException e1) {
					
				}catch(java.lang.NullPointerException e1){
				}
			});
			comboBox2.valueProperty().addListener(observable -> {
				try {
					erreurHistorique.setText("");
					erreur.setText("");
					aP2.update(gd.dicoPersonne.get(comboBox2.getValue().toString()));
					Personne p;
					Personne p2;
					textArea.setText("");
					for(Historique h: gd.dicoHistorique.values()){
						p=gd.dicoPersonne.get(comboBox.getValue().toString());
						p2=gd.dicoPersonne.get(comboBox2.getValue().toString());
						if((h.personne1.nom==p.nom && h.personne1.prenom==p.prenom &&
						   h.personne2.nom==p2.nom && h.personne2.prenom==p2.prenom)||
						   (h.personne1.nom==p2.nom && h.personne1.prenom==p2.prenom &&
						   h.personne2.nom==p.nom && h.personne2.prenom==p.prenom)){
							textArea.setText(h.contexte);
						}
					}
				} catch (FileNotFoundException e1) {
				}catch(java.lang.NullPointerException e1){
				}
			});
			comboBox2.setLayoutX(scene.getWidth()-300);
			Button b=new CreateButton((int)scene.getWidth()/2-75, 250, "Matching", root).button;
			b.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 20));
			b.setMinHeight(40);
			b.setMinWidth(90);
			b.setOnAction(e-> {
				ArrayList<Personne> p=algo.listeMatch(comboBox.getValue(), gd.listePersonne);
				comboBox2.getItems().setAll(p.get(0));
				for(int i=1;i<p.size();i++) {
					comboBox2.getItems().add(p.get(i)); ///setall à la place de addAll pour remplacer les valeurs
				}
				comboBox2.setValue(p.get(0));
			});
			root.getChildren().addAll(comboBox,comboBox2);
			//modifications côté droit
			Button bouttonModifdroite=new CreateButton((int) scene.getWidth()-530, 580, "Enregistrer les modifications", root).button;
			bouttonModifdroite.setOnAction(e->{
				Personne p=gd.dicoPersonne.get(comboBox2.getValue().toString());
				aP2.modification(p,gd);
			});

			//Historique
			Button bouttonHistorique=new CreateButton((int) scene.getWidth()/2-90, (int)scene.getHeight()/2-50,"Modification d'historique", root).button;
			bouttonHistorique.setOnAction(e ->{
				erreurHistorique.setText("");
				erreur.setText("");
				erreurRechercheinversee.setText("");
				Personne p;
				Personne p2;
				boolean flag=true;
				int i=0;
				Historique h;
				while(flag && i<gd.dicoHistorique.size()){
					h=gd.dicoHistorique.get(i);
					p=gd.dicoPersonne.get(comboBox.getValue().toString());
					p2=gd.dicoPersonne.get(comboBox2.getValue().toString());
					if(h.personne1.nom==p.nom && h.personne1.prenom==p.prenom &&
					   h.personne2.nom==p2.nom && h.personne2.prenom==p2.prenom){
						h.contexte=textArea.getText();
						flag=false;
					}
					else{
						i+=1;
					}
				}
				if(flag && !comboBox.getValue().toString().equals(comboBox2.getValue().toString())){
					gd.dicoPersonne.get(comboBox.getValue().toString()).listeRencontre.add(gd.dicoHistorique.size());
					gd.dicoPersonne.get(comboBox2.getValue().toString()).listeRencontre.add(gd.dicoHistorique.size());
					gd.dicoHistorique.put(gd.dicoHistorique.size(),new Historique(gd.dicoPersonne.get(comboBox.getValue().toString()), gd.dicoPersonne.get(comboBox2.getValue().toString()),textArea.getText()));
					}
				else{
					erreurHistorique.setText("Vous avez sélectionné la même personne des 2 côtés");
					erreur.setText("");
				}
			});
			//Recherche inversée
			Rectangle r1 = new CreateRectangle((int) scene.getWidth()-290,(int) scene.getHeight()-157,197,90,20,"#ECE8E7").rectangle;
			root.getChildren().add(r1);
			Button buttonRechercheinv = new CreateButton((int) scene.getWidth()-250, (int) scene.getHeight()-150, "Recherche Inversée", root).button;
			TextField textField1 = new CreateTextField((int) scene.getWidth()-278, (int) scene.getHeight()-100, 175).textfield;
			textField1.setStyle("-fx-prompt-text-fill: #AAAAAA;");
			textField1.setPromptText("Entrez le nom et le prénom");
			root.getChildren().add(textField1);
			erreurRechercheinversee.setStyle("-fx-text-fill: #FF0000;");
			buttonRechercheinv.setOnAction(e -> {
				try {
					erreurRechercheinversee.setText("");
					erreur.setText("");
					String name = textField1.getText();
					Personne p=gd.dicoPersonne.get(name);
					if(p!=null){
					comboBox.setValue(p);
					aP.update(p);
					}
					else{
						erreurRechercheinversee.setText("   Personne ne correspond à ce nom et ce prénom\nVeuillez remplir le champ sous la forme Nom Prenom");
						erreur.setText("");
					}
				} catch (FileNotFoundException e1) {
				}catch(java.lang.NullPointerException e1){
				}
			 });
			

			//Recherche selective
			Rectangle r = new CreateRectangle(30,(int) scene.getHeight()-360,450,270,20,"#ECE8E7").rectangle;
			Label label = new CreateLabel(82, (int) (r.getY() + r.getHeight() -300), root,"Attributs pour la recherche sélective","-fx-font-size: 20px; -fx-text-fill: black;").label;
			root.getChildren().add(r);
			String[] listeRecherche = {"non renseigné","non renseigné","non renseigné","non renseigné","non renseigné","non renseigné","non renseigné","non renseigné"};
			ComboBox<String> rechercheHobby= new CreateComboBox(50,(int)scene.getHeight()-340,0,listeRecherche).comboBox;
			ComboBox<String> rechercheHobbyMoins= new CreateComboBox(50,(int)scene.getHeight()-270,1,listeRecherche).comboBox;
			ComboBox<String> rechercheAnimal= new CreateComboBox(300,(int)scene.getHeight()-340,2,listeRecherche).comboBox;
			ComboBox<String> rechercheAnimalMoins= new CreateComboBox(300,(int)scene.getHeight()-270,3,listeRecherche).comboBox;
			rechercheHobby.setVisibleRowCount(9);
			rechercheHobbyMoins.setVisibleRowCount(9);
			rechercheAnimal.setVisibleRowCount(9);
			rechercheAnimalMoins.setVisibleRowCount(9);
			for(Map.Entry s: GestionDonnee.dicoHobbies.entrySet()) {
				rechercheHobby.getItems().add(String.valueOf(s.getKey()));
				rechercheHobbyMoins.getItems().add(String.valueOf(s.getKey()));
			}
			rechercheHobby.getItems().add("non renseigné");
			rechercheHobbyMoins.getItems().add("non renseigné");
			for(Map.Entry s: GestionDonnee.dicoAnimal.entrySet()) {
				rechercheAnimal.getItems().add(String.valueOf(s.getKey())); 
				rechercheAnimalMoins.getItems().add(String.valueOf(s.getKey()));
					}
			rechercheAnimal.getItems().add("non renseigné");
			rechercheAnimalMoins.getItems().add("non renseigné");
			ComboBox<String> cbLire = new CreateComboBox(50,(int)scene.getHeight()-200,4,listeRecherche).comboBox;
			ComboBox<String> cbJeuS= new CreateComboBox(300,(int)scene.getHeight()-200,5,listeRecherche).comboBox;
			ComboBox<String> cbJeuV = new CreateComboBox(50,(int)scene.getHeight()-130,6,listeRecherche).comboBox;
			ComboBox<String> cbEnfants = new CreateComboBox(300,(int)scene.getHeight()-130,7,listeRecherche).comboBox;
			String[] choix = {"oui", "non", "non renseigné" };
			for(String s: choix){
				cbLire.getItems().add(s);
				cbJeuS.getItems().add(s);
				cbJeuV.getItems().add(s);
				cbEnfants.getItems().add(s);
			}
			String[] listenom={"Hobby préféré","N'aime pas","Aime Lire","Aime les jeux vidéos","Animal préféré","A peur de","Aime les jeux de sociétés","A des enfants"};
			//Label recherche sélective
			for(int x=0;x<2;x++){
				for(int y=0;y<4;y++){
					Label cL=new CreateLabel(250*x+50, (int)scene.getHeight()-360+y*70, root, listenom[x*4+y]).label;
				}
			}
			root.getChildren().addAll(rechercheHobby,rechercheHobbyMoins,rechercheAnimal,rechercheAnimalMoins,cbLire,cbJeuS,cbJeuV,cbEnfants);
			Button bouttonRechercheSelect=new CreateButton(175, (int) scene.getHeight()-80, "Recherche sélective",root).button;
			//Erreur no one found
			erreur.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 14));
			erreur.setStyle("-fx-text-fill: #FF0000;");
			bouttonRechercheSelect.setOnAction(e -> {
				erreur.setText("");
				erreurRechercheinversee.setText("");
				erreurHistorique.setText("");
				ArrayList<Personne> listeRep=new ArrayList<Personne>();
					for(Personne p: gd.listePersonne){
						if((listeRecherche[0]=="non renseigné" || listeRecherche[0]=="" || listeRecherche[0].equals(p.hobbi)) && 
						(listeRecherche[1]=="non renseigné" || listeRecherche[1]==""|| listeRecherche[1].equals(p.hobbiM)) &&
						(listeRecherche[2]=="non renseigné" || listeRecherche[2]==""|| listeRecherche[2].equals(p.animalAime)) && 
						(listeRecherche[3]=="non renseigné" || listeRecherche[3]==""|| listeRecherche[3].equals(p.animalDeteste)) && 
						(listeRecherche[4]=="non renseigné" || listeRecherche[4]==""|| listeRecherche[4].equals(p.aimeLire)) && 
						(listeRecherche[5]=="non renseigné" || listeRecherche[5]==""|| listeRecherche[5].equals(p.aimeJouerS)) && 
						(listeRecherche[6]=="non renseigné" || listeRecherche[6]==""|| listeRecherche[6].equals(p.aimeJouerJV)) && 
						(listeRecherche[7]=="non renseigné" || listeRecherche[7]==""|| listeRecherche[7].equals(p.aEnfant))){
							listeRep.add(p);
						}
					}
					if(listeRep.size()>0){
					comboBox.getItems().setAll(listeRep.get(0));
					for(int i=1;i<listeRep.size();i++) {
						comboBox.getItems().add(listeRep.get(i));
					}
					comboBox.setValue(listeRep.get(0));
					}
					else{
						erreur.setText("Personne ne correspond à ces attributs");
						erreurRechercheinversee.setText("");
					}
			 });

			//Reset 
			Button bouttonReset=new CreateButton((int) scene.getWidth()/2-60, (int) scene.getHeight()/2+250, "Reset la recherche",root).button;
			bouttonReset.setOnAction(e -> {
			comboBox.getItems().setAll(gd.listePersonne.get(0));
				for(int i=1;i<gd.listePersonne.size();i++) {
					comboBox.getItems().add(gd.listePersonne.get(i));
				}
				rechercheAnimal.setValue("");
				rechercheAnimalMoins.setValue("");
				rechercheHobby.setValue("");
				rechercheHobbyMoins.setValue("");
				cbLire.setValue("");
				cbJeuS.setValue("");
				cbJeuV.setValue("");
				cbEnfants.setValue("");
				textArea.setText("");
				erreurHistorique.setText("");
				erreurRechercheinversee.setText("");
				erreur.setText("");

			});

			//sauvegarde
			Button quitter=new CreateButton((int)scene.getWidth()/2-75, (int)scene.getHeight()-50, "Sauvegarder et quitter", root).button;
			quitter.setOnAction(e-> {
				Alert alert=new Alert(AlertType.CONFIRMATION,"Sauvegarder?", ButtonType.YES,ButtonType.NO);
				Image image = new Image("/images/coeur-casse.png");
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(50);
				imageView.setFitWidth(50);
				alert.setGraphic(imageView);
				alert.setTitle("Fermeture du logiciel");
				alert.setHeaderText("");
				alert.getDialogPane().setStyle("-fx-background-color: #BAFF90;");
				if (alert.showAndWait().filter(t -> t == ButtonType.YES).isPresent()) {
					try{
						bouttonHistorique.fire();
						Personne p1=gd.dicoPersonne.get(comboBox.getValue().toString());
						aP.modification(p1,gd);
						Personne p2=gd.dicoPersonne.get(comboBox2.getValue().toString());
						aP2.modification(p2,gd);
						File outFile = new File("projet/data.txt");
						FileWriter fileWriter = new FileWriter(outFile);
						String s;
						for(Personne p: gd.listePersonne){
							s=p.prenom+";&"+p.nom+";&"+p.age+";&"+p.sexe+";&"+p.attirance+";&"+p.animalAime+";&"+p.animalDeteste+";&"+p.aimeLire+";&"+p.aimeJouerS+";&"+
							p.aimeJouerJV+";&"+p.aEnfant+";&"+p.hobbi+";&"+p.hobbiM+";&"+p.URLPhoto;
							for(Integer i: p.listeRencontre){
								s+=";&"+i;
							}
							fileWriter.write(s);
							fileWriter.write("\n");
						}
						fileWriter.close();
						File outFile2 = new File("projet/contexte.txt");
						FileWriter fileWriter2 = new FileWriter(outFile2);
						for(Historique h: gd.dicoHistorique.values()){
							fileWriter2.write(h.personne1.toString()+";&"+h.personne2.toString()+";&"+h.contexte);
							fileWriter2.write("\n");
						}
						fileWriter2.close();

					}catch(IOException e2){
					}
					System.exit(1);
				}
			});
			//setScene
			scene.setFill(javafx.scene.paint.Color.valueOf("#c7fde0"));
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
