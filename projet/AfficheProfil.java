import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class AfficheProfil{
    ImageView imagePersonne = new ImageView();
    Label labelDescription;
    public AfficheProfil(Personne p,Group root,int multi,Scene scene) throws FileNotFoundException{
        String s="nom: "+p.nom+"\nprenom: "+p.prenom+"\nage: "+p.age;
        InputStream stream = new FileInputStream(p.URLPhoto);
        imagePersonne.setImage(new Image(stream));
        if(multi==1){
            imagePersonne.setX(scene.getWidth()-570);
        }
        else{
            imagePersonne.setX(320);
        }
        
        imagePersonne.setY(30);
        imagePersonne.setFitWidth(200);
        imagePersonne.setPreserveRatio(true);
        for(String valeur: p.stat){
            s+=valeur;
;			}
        labelDescription=new Label(s);
        if(multi==1){
            labelDescription.setTranslateX(scene.getWidth()-570);
        }
        else{
            labelDescription.setTranslateX(320);
        }
        labelDescription.setTranslateY(230);
        labelDescription.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        root.getChildren().add(labelDescription);
        root.getChildren().add(imagePersonne);
    }
    public void update(Personne p) throws FileNotFoundException{
        String s="nom: "+p.nom+"\nprenom: "+p.prenom+"\nage: "+p.age;
        InputStream stream = new FileInputStream(p.URLPhoto);
        imagePersonne.setImage(new Image(stream));
        for(String valeur: p.stat){
            s+=valeur;
;			}
        labelDescription.setText(s);
    }
}
