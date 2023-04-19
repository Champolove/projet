

import java.util.ArrayList;
import java.util.HashMap;

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
	String hash;
	String hashMatch;


	public Personne(String n, String pn, int a, String s, String att,String aA,String aD,String aL,String aS,String aJV,String aE) {
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
		hash=s+att+aA+aD+aL+aS+aJV;
		hashMatch=att+s+aD+aA+aL+aS+aJV;
	}
	public String toString() {
		return this.prenom+" "+this.nom;
	}

}
