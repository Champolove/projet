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
	ArrayList<String> stat;
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
		hobbi=String.valueOf(GestionDonnee.dicoHobbies.get(h));
		hobbiM=String.valueOf(GestionDonnee.dicoHobbies.get(hm));
		hetero=s!=att;
		hash=new ArrayList<>();
		hashMatch=new ArrayList<>();
		hash.addAll(Arrays.asList(s,att,String.valueOf(GestionDonnee.dicoAnimal.get(aA)),String.valueOf(GestionDonnee.dicoAnimal.get(aD)),aL,aS,aJV,aE,String.valueOf(GestionDonnee.dicoHobbies.get(h)),String.valueOf(GestionDonnee.dicoHobbies.get(hm))));
		hashMatch.addAll(Arrays.asList(att,s,animalDeteste,animalAime,aL,aS,aJV,aE,hobbiM,hobbi));
		stat=new ArrayList<String>();
		if(aA!=null){
			stat.add("\nAime: "+aA);
		}
		else{
		stat.add("\nAime: non renseigné");
		}
		if(aD!=null){
			stat.add("\nDéteste: "+aD);
		}
		else{
		stat.add("\nDéteste: non renseigné");
		}
		if(aL!=null){
			stat.add("\nAime lire: "+vf[Integer.valueOf(aL)]);
		}
		else{
		stat.add("\nAime lire: non renseigné");
		}
		if(aS!=null){
			stat.add("\nAime les jeux de société: "+vf[Integer.valueOf(aS)]);
		}
		else{
		stat.add("\nAime les jeux de société: non renseigné");
		}
		if(aJV!=null){
			stat.add("\nAime les jeu vidéos: "+vf[Integer.valueOf(aJV)]);
		}
		else{
		stat.add("\nAime les jeu vidéos: non renseigné");
		}
		if(aE!=null){
			stat.add("\nA des enfants: "+vf[Integer.valueOf(aE)]);
		}
		else{
		stat.add("\nA des enfants: non renseigné");
		}
		if(h!=null){
			stat.add("\nHobby: "+h);
		}
		else{
		stat.add("\nHobby: non renseigné");
		}
		if(hm!=null){
			stat.add("\nN'aime pas: "+hm);
		}
		else{
		stat.add("\nN'aime pas: non renseigné");
		}

	}
	public String toString() {
		return this.nom+" "+this.prenom;
	}

}
