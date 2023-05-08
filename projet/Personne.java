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
	ArrayList<Integer> listeRencontre;
	boolean hetero;

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
		listeRencontre=new ArrayList<Integer>();
	}
	public String toString() {
		return this.nom+" "+this.prenom;
	}
	public void hash(){
		hash=new ArrayList<>();
		hash.addAll(Arrays.asList(sexe,attirance,animalAime,animalDeteste,aimeLire,aimeJouerS,aimeJouerJV,aEnfant,hobbi,hobbiM));
	}
	public void hashMatch(){
		hashMatch=new ArrayList<>();
		hashMatch.addAll(Arrays.asList(attirance,sexe,animalDeteste,animalAime,aimeLire,aimeJouerS,aimeJouerJV,aEnfant,hobbiM,hobbi));
	
	}

}
