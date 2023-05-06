import java.util.ArrayList;
import java.util.Arrays;

public class Personne {
	public static ArrayList<String> attributs=new ArrayList<String>();
	String prenom;
	String nom;
	int age;
	String sexe;
	//sexe d'attirance
	String attirance;
	String URLPhoto;
	String animalAime;
	String animalDeteste;
	String aimeLire;
	String aimeJouerS;
	String aimeJouerJV;
	String aEnfant;
	String hobbi;
	String hobbiM;
	ArrayList<String> hash;
	ArrayList<String> hashMatch;
	boolean hetero;
	String profil;
	String[] vf={"oui","non"};

	public Personne(String n, String pn, int a, String s, String att,String aA,String aD,String aL,String aS,String aJV,String aE,String h,String hm) {
		prenom=n;
		nom=pn;
		age=a;
		sexe=s;
		attirance=att;
		animalAime=aA;
		animalDeteste=aD;
		aimeLire=aL;
		aimeJouerS=aS;
		aimeJouerJV=aJV;
		aEnfant=aE;
		hobbi=h;
		hobbiM=hm;
		hetero=s!=att;
		hash=new ArrayList<>();
		hashMatch=new ArrayList<>();
		hash.addAll(Arrays.asList(s,att,String.valueOf(GestionDonnee.dicoAnimal.get(aA)),String.valueOf(GestionDonnee.dicoAnimal.get(aD)),aL,aS,aJV,aE,String.valueOf(GestionDonnee.dicoHobbies.get(h)),String.valueOf(GestionDonnee.dicoHobbies.get(hm))));
		hashMatch.addAll(Arrays.asList(att,s,animalDeteste,animalAime,aL,aS,aJV,aE,hobbiM,hobbi));
		profil="";
		if(aA!=null){
			profil+="\nAime: "+aA;
		}
		else{
		profil+="\nAime: non renseigné";
		}
		if(aD!=null){
			profil+="\nDéteste: "+aD;
		}
		else{
		profil+="\nDéteste: non renseigné";
		}
		if(aL!=null){
			profil+="\nAime lire: "+vf[Integer.valueOf(aL)];
		}
		else{
		profil+="\nAime lire: non renseigné";
		}
		if(aS!=null){
			profil+="\nAime les jeux de société: "+vf[Integer.valueOf(aS)];
		}
		else{
		profil+="\nAime les jeux de société: non renseigné";
		}
		if(aJV!=null){
			profil+="\nAime les jeu vidéos: "+vf[Integer.valueOf(aJV)];
		}
		else{
		profil+="\nAime les jeu vidéos: non renseigné";
		}
		if(aE!=null){
			profil+="\nA des enfants: "+vf[Integer.valueOf(aE)];
		}
		else{
		profil+="\nA des enfants: non renseigné";
		}
		if(h!=null){
			profil+="\nHobby: "+h;
		}
		else{
		profil+="\nHobby: non renseigné";
		}
		if(hm!=null){
			profil+="\nN'aime pas: "+hm;
		}
		else{
		profil+="\nN'aime pas: non renseigné";
		}

	}
	public String toString() {
		return this.nom+" "+this.prenom;
	}

}
