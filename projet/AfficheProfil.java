import javafx.scene.control.TextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AfficheProfil{
    ImageView imagePersonne = new ImageView();
    Label labelDescriptionShort;
    Label labelDescriptionLong;
    Label labelDescriptionHobby;
    TextField textFieldnom;
    TextField textFieldprenom;
    TextField textFieldage;
    TextField textFieldaime;
    TextField textFielddeteste;
    TextField textFieldaL;
    TextField textFieldaS;
    TextField textFieldaJV;
    TextField textFieldaE;
    TextField textFieldh;
    TextField textFieldhm;
    String[] rep={"oui","non"};

    public AfficheProfil(Personne p,Group root,int multi,Scene scene) throws FileNotFoundException{
        
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
        String s="Nom: "+"\nPrenom: "+"\nAge: "+"\nAnimal:"+"\nA peur de:";
        int x=(int) (320+(scene.getWidth()-895)*multi);
        int y=227;
        int minWidth=170;
        labelDescriptionShort=new CreateLabel(x, y+2, root, s,18).label;
        labelDescriptionShort.setMaxWidth(150);
        x+=100;
        textFieldnom=new CreateTextField(x, y+5, minWidth, p.nom,root).textfield;
        textFieldprenom=new CreateTextField(x, y+5+29, minWidth, p.prenom,root).textfield;
        textFieldage=new CreateTextField(x, y+5+58, minWidth, String.valueOf(p.age),root).textfield;
        textFieldaime=new CreateTextField(x, y+5+87, minWidth, p.animalAime,root).textfield;
        textFielddeteste=new CreateTextField(x, y+5+116, minWidth, p.animalDeteste,root).textfield;

        s="Aime lire:"+"\nAime les jeux de société:"+"\nAime les jeu vidéos:"+"\nA des enfants:";
        labelDescriptionLong=new CreateLabel(x-100, y+145, root, s,18).label;
        minWidth=50;
        x+=120;
        
        textFieldaL=new CreateTextField(x, y+5+143, minWidth, rep[Integer.valueOf(p.aimeLire)],root).textfield;
        textFieldaS=new CreateTextField(x, y+5+172, minWidth, rep[Integer.valueOf(p.aimeJouerS)],root).textfield;
        textFieldaJV=new CreateTextField(x, y+5+201, minWidth, rep[Integer.valueOf(p.aimeJouerJV)],root).textfield;
        textFieldaE=new CreateTextField(x, y+5+230, minWidth, rep[Integer.valueOf(p.aEnfant)],root).textfield;
        
        s="Hobby:"+"\nN'aime pas:";
        x-=120;
        minWidth=160;
        labelDescriptionHobby=new CreateLabel(x-100, y+261, root, s,18).label;
        textFieldh=new CreateTextField(x+10, y+5+259, minWidth, p.hobbi,root).textfield;
        textFieldhm=new CreateTextField(x+10, y+5+288, minWidth, p.hobbiM,root).textfield;
        root.getChildren().add(imagePersonne);
    }
    public void update(Personne p) throws FileNotFoundException{
        InputStream stream = new FileInputStream(p.URLPhoto);
        imagePersonne.setImage(new Image(stream));
        
        textFieldnom.setText(p.nom);
        textFieldprenom.setText(p.prenom);
        textFieldage.setText(String.valueOf(p.age));
        textFieldaime.setText(p.animalAime);
        textFielddeteste.setText(p.animalDeteste);
        textFieldaL.setText(rep[Integer.valueOf(p.aimeLire)]);
        textFieldaS.setText(rep[Integer.valueOf(p.aimeJouerS)]);
        textFieldaJV.setText(rep[Integer.valueOf(p.aimeJouerJV)]);
        textFieldaE.setText(rep[Integer.valueOf(p.aEnfant)]);
        textFieldh.setText(p.hobbi);
        textFieldhm.setText(p.hobbiM);
    }

    public void modification(Personne p,GestionDonnee gd){
        gd.dicoPersonne.remove(p.nom+" "+p.prenom);
        int indiceListe=-1;
        int i=0;
        while(i<gd.listePersonne.size() && indiceListe==-1){
            if(gd.listePersonne.get(i).nom==p.nom && gd.listePersonne.get(i).prenom==p.prenom){
                indiceListe=i;
            }
            i+=1;
        }
        if(textFieldnom.getText()!=null && !(textFieldnom.getText().trim().isEmpty())){
            p.nom=textFieldnom.getText().toLowerCase();
            p.nom=String.valueOf(p.nom.charAt(0)).toUpperCase()+p.nom.substring(1).toLowerCase();
        }
        if(textFieldprenom.getText()!=null && !(textFieldprenom.getText().trim().isEmpty())){
            p.prenom=textFieldprenom.getText();
            p.prenom=String.valueOf(p.prenom.charAt(0)).toUpperCase()+p.prenom.substring(1).toLowerCase();
        }
        if(textFieldage.getText()!=null && !(textFieldage.getText().trim().isEmpty())){
            p.age= Integer.valueOf(textFieldage.getText());
        }
        if(textFieldaime.getText()!=null && !(textFieldaime.getText().trim().isEmpty()) && GestionDonnee.dicoAnimal.get(textFieldaime.getText().toLowerCase())!=null){
            if(!p.animalDeteste.equals(textFieldaime.getText().toLowerCase()) || !textFieldaime.getText().toLowerCase().equals(textFielddeteste.getText().toLowerCase())){
                p.animalAime=textFieldaime.getText().toLowerCase();
            }
        }
        if(textFielddeteste.getText()!=null && !(textFielddeteste.getText().trim().isEmpty())  && GestionDonnee.dicoAnimal.get(textFielddeteste.getText().toLowerCase())!=null){
            if(!p.animalAime.equals(textFielddeteste.getText().toLowerCase())|| !textFielddeteste.getText().toLowerCase().equals(textFieldaime.getText().toLowerCase())){
                p.animalDeteste=textFielddeteste.getText().toLowerCase();
            }
        }
        if(textFieldaL.getText()!=null && !(textFieldaL.getText().trim().isEmpty())){
            p.aimeLire="1";
            if(textFieldaL.getText().equals("oui")){
                p.aimeLire="0";
            }
        }
        if(textFieldaS.getText()!=null && !(textFieldaS.getText().trim().isEmpty())){
            p.aimeJouerS="1";
            if(textFieldaS.getText().equals("oui")){
                p.aimeJouerS="0";
            }
        }
        if(textFieldaJV.getText()!=null && !(textFieldaJV.getText().trim().isEmpty())){
            p.aimeJouerJV="1";
            if(textFieldaJV.getText().equals("oui")){
                p.aimeJouerJV="0";
            }
        }
        if(textFieldaE.getText()!=null && !(textFieldaE.getText().trim().isEmpty())){
            p.aEnfant="1";
            if(textFieldaE.getText().equals("oui")){
                p.aEnfant="0";
            }
        }
        if(textFieldh.getText()!=null && !(textFieldh.getText().trim().isEmpty()) && GestionDonnee.dicoHobbies.get(textFieldhm.getText())!=null){
            if(!p.hobbiM.equals(textFieldh.getText().toLowerCase()) || !textFieldh.getText().toLowerCase().equals(textFieldhm.getText().toLowerCase())){
                p.hobbi=textFieldh.getText().toLowerCase();
                }
        }
        if(textFieldhm.getText()!=null && !(textFieldhm.getText().trim().isEmpty()) && GestionDonnee.dicoHobbies.get(textFieldhm.getText())!=null){
            if(!p.hobbi.equals(textFieldhm.getText().toLowerCase())|| !textFieldhm.getText().toLowerCase().equals(textFieldh.getText().toLowerCase())){
            p.hobbiM=textFieldhm.getText().toLowerCase();
            }
        }
        p.hash();
        p.hashMatch();
        gd.dicoPersonne.put(p.nom+" "+p.prenom,p);
        gd.listePersonne.set(indiceListe,p);
        try {
             update(p);
         } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

