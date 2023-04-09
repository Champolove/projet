package projet;

import java.util.ArrayList;
import java.util.HashMap;
public class Personne {
	public static ArrayList<String> attributs=new ArrayList<String>();
	String prenom;
	String nom;
	int age;
	String sexe;
	int poids;
	//sexe d'attirance
	String attirance;
	float taille;
	String URLPhoto;
	HashMap<String,Integer> dicoAttribut=new HashMap<String,Integer>();
	HashMap<String,Integer> dicoGout=new HashMap<String,Integer>();
	
	public Personne(String n, String pn, int a, String s, int poi, String att, float t,ArrayList<String> attri, ArrayList<String> pref, ArrayList<String> rej) {
		prenom=n;
		nom=pn;
		age=a;
		sexe=s;
		poids=poi;
		attirance=att;
		taille=t;
		for(String str: attri) {
			dicoAttribut.put(str, 1);
		}
		for(String str: pref) {
			dicoGout.put(str, 1);
		}
		for(String str: rej) {
			if(dicoGout.get(str)==2) {
				dicoGout.put(str, 0);
			}
		}
	}
	public String toString() {
		return this.prenom+" "+this.nom;
	}

}
