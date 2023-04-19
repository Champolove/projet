package projet;

import java.util.ArrayList;
import java.util.Random;

public class GestionDonnee {
	static enum animal{
		chat,
		chien,
		tortue,
		lapin,
		serpent
	}
	ArrayList<Personne> listePersonne=new ArrayList<>();
	ArrayList<Personne> listeHomme=new ArrayList<>();
	ArrayList<Personne> listeFemme=new ArrayList<>();
	public class creePersonne {
		String[] prenomF= {"Charlotte", "Francis", "Karen", "Ethel", "Catherine", "Rhonda", "Rene", "Virginia", "Elizabeth", "Samantha", "Elisha", "Tiffany", "Amy", "Erika", "Shelley", "Patricia", "Sharon", "Sally", "Kimberly", "Anna", "Gisele", "Dinah", "Ashley", "Dee", "Kimberly", "Jennifer", "Christie", "Ana", "Lynda", "Blanche", "Gladys", "Lu", "Beverly", "Kimberly", "Kathleen", "Claudia", "Nanette", "Debra", "Grace", "Lisa", "Kenna", "Linda", "Pat", "Angelica", "Laura", "Joan", "Marie", "Joanne", "Ana", "Gloria", "Stefanie", "Ella", "Nicole", "Sylvia", "Charlotte", "Carol", "Melissa", "Anne", "Leslie", "Candace", "Joy", "Vernita", "Lynne", "Eleanor", "Julie", "Iris", "Christina", "Audrey", "Linda", "Patricia", "Michelle", "Nichole", "Julia", "Deborah", "Tiffany", "Marion", "Ester", "Rosemary", "April", "Rebecca", "Michelle", "Sarah", "Misty", "Kim", "Jeannie", "Helen", "Vera", "Velva", "Nancy", "Marianne", "Jean", "Evelyn", "Shana", "Dorothy", "Irma", "Sandra", "Susan", "Nora", "Mary", "Wanda"};
		String[] prenomH= {"Donald", "Anthony", "James", "Jerry", "Michael", "Anton", "Steven", "Christian", "Tony", "James", "James", "Charles", "Gerard", "Philip", "Carlos", "Peter", "William", "Gary", "Derek", "Joseph", "Keith", "Philip", "Melvin", "Jean", "Robert", "Lewis", "Roy", "Daniel", "Adrian", "Allan", "Paul", "Dustin", "Edward", "Harley", "Joseph", "Lee", "Kenneth", "Kevin", "John", "Thomas", "Tyler", "James", "James", "Patrick", "Stephen", "John", "Gary", "Eric", "Curtis", "Albert", "Jayson", "John", "Kelly", "Jorge", "Vernon", "Evan", "Nathan", "Stan", "Michael", "Daniel", "Ronald", "Adrian", "Herman", "Paul", "Johnny", "Steven", "Rafael", "Edward", "Richard", "Jeffery", "Robert", "Daniel", "Thomas", "James", "James", "Michael", "Marshall", "Michael", "Peter", "Gary", "Steve", "James", "Carey", "Samuel", "George", "Robert", "Ashley", "Larry", "Robert", "Gary", "Wyatt", "John", "Ronald", "Steve", "Mark", "Jeffrey", "Ricky", "Anthony", "Daniel", "Sam"};
		String[] nom= {"Taylor", "Ginn", "Myers", "Winslow", "Conroy", "Gebhart", "Anello", "Thompson", "Mirabal", "Wilson", "Blanding", "Mccray", "Hunter", "Koroma", "Wynne", "Wallace", "Grover", "Miller", "Skinner", "Alcorn", "Thomas", "Huffman", "Johnston", "Mathis", "Bernier", "Johnson", "Anspach", "Thao", "Scott", "Wright", "Delgado", "Alm", "Bryan", "Genet", "Ramirez", "Wilder", "Sutton", "Maloney", "Rahe", "Griffith", "Deane", "Warren", "Trantham", "Jones", "Wagner", "Gary", "Mcilhinney", "Dennis", "Washington", "Marino", "Guerrero", "Owens", "Rose", "Dorsey", "Scott", "Eye", "Wilson", "Lewis", "Lassiter", "Roberts", "Mathis", "Walker", "Cervera", "Perez", "Jackson", "Niesman", "Grullon", "Neely", "Stegall", "Fonseca", "Cheek", "Carpenter", "Bautista", "Smith", "Knight", "Vanschaick", "Michel", "Strahle", "Koverman", "Thomas", "Rae", "Boutelle", "Galindo", "Wasson", "Rojas", "Hill", "Carlson", "Peterson", "Baker", "Folk", "Thomas", "Williams", "Gilbert", "Velovic", "Ferrell", "Gonzalez", "Mash", "Atwood", "Shults", "Lewandowski"};
		String[] choixSexe={"0","1"};
		
		//String a=String.valueOf(animal.values()[new Random().nextInt(animal.values().length)]);  return a random value of an enum
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
			p=new Personne(prenom,nom[r.nextInt(nom.length)],r.nextInt(47)+18,choixSexe[r.nextInt(2)],choixSexe[r.nextInt(2)],String.valueOf(r.nextInt(2)),String.valueOf(r.nextInt(2)),String.valueOf(r.nextInt(2)),String.valueOf(r.nextInt(2)),String.valueOf(r.nextInt(2)),String.valueOf(r.nextInt(2)));
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
