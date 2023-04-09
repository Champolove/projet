package projet;

import java.util.ArrayList;
import java.util.Random;

public class GestionDonnee {
	
	ArrayList<Personne> listePersonne=new ArrayList<>();
	ArrayList<Personne> listeHomme=new ArrayList<>();
	ArrayList<Personne> listeFemme=new ArrayList<>();
	public class creePersonne {
		String[] prenomH= {"henri"};
		String[] prenomF= {"jeanne"};
		String[] nom= {"Boisjoli"};
		String[] choixSexe={"homme","femme"};
		String[] choixAttributs= {"bouclé"};
		String[] choixPréférences= {"chat"};
		String[] choixRejets= {"chien"};
		Personne p;
		public creePersonne() {
			Random r=new Random();
			String sexe=choixSexe[r.nextInt(2)];
			String prenom;
			if(sexe=="homme") {
				prenom=prenomH[r.nextInt(prenomH.length)];
			}
			else {
				prenom=prenomF[r.nextInt(prenomF.length)];
			}
			ArrayList<String> attributs=new ArrayList<String>();
			ArrayList<String> préférences=new ArrayList<String>();
			ArrayList<String> rejets=new ArrayList<String>();
			p=new Personne(prenom+r.nextInt(50),nom[r.nextInt(nom.length)],r.nextInt(47)+18,sexe,80,choixSexe[r.nextInt(2)],(r.nextInt(7)+14)/10, attributs,préférences,rejets);
		}
	}
	
	public ArrayList<Personne> rechercheSexe(String sexe) {
		if (sexe=="homme") {
			return this.listeHomme;
		}
		return this.listeFemme;
	}
	
	public GestionDonnee() {
		for(int i=0;i<50;i++) {
			Personne perso=new creePersonne().p;
			this.listePersonne.add(perso);
			if (perso.sexe=="homme") {
				this.listeHomme.add(perso);
			}
			else {
				this.listeFemme.add(perso);
			}
				
		}
		System.out.println(this.listePersonne);
	}
}
