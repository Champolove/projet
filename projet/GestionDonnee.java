import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;


import java.util.HashMap;


public class GestionDonnee {
	HashMap<String,Personne> dicoPersonne=new HashMap<String,Personne>();
	static HashMap<String,Integer> dicoAnimal=new HashMap<>();
	static HashMap<String,Integer> dicoHobbies=new HashMap<>();
	static enum animal{
		chat,
		chien,
		tortue,
		lapin,
		serpent,
		poisson,
		cheval,
		chevre
	}
	static enum hobbies{
		jardinage,
		cuisine,
		dance,
		dessin,
		couture,
		photographie,
		sport,
		peinture,
		theatre,
		lecture,
		diy,
		musique,
		peche,
		voyage
	}
	
	ArrayList<Personne> listePersonne=new ArrayList<>();
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
			String attirance;
			if(sexe=="0") {
				prenom=prenomH[r.nextInt(prenomH.length)];
				attirance=choixSexe[1-r.nextInt(100)/90];
			}
			else {
				prenom=prenomF[r.nextInt(prenomF.length)];
				attirance=choixSexe[r.nextInt(100)/90];
			}
			String aA=String.valueOf(String.valueOf(animal.values()[new Random().nextInt(animal.values().length)]));
			String ad=String.valueOf(String.valueOf(animal.values()[new Random().nextInt(animal.values().length)]));
			if(aA.equals(ad)){
				ad=null;
			}
			String h=String.valueOf(String.valueOf(hobbies.values()[new Random().nextInt(hobbies.values().length)]));
			String hm=String.valueOf(String.valueOf(hobbies.values()[new Random().nextInt(hobbies.values().length)]));
			if(h.equals(hm)){
				hm=null;
			}
			p=new Personne(prenom,nom[r.nextInt(nom.length)],r.nextInt(32)+18,sexe,attirance,aA,ad,String.valueOf(r.nextInt(2)),String.valueOf(r.nextInt(2)),String.valueOf(r.nextInt(2)),String.valueOf(r.nextInt(2)),h,hm);
		}
	}


	public GestionDonnee() throws IOException {
		int cpt=0;
        for(GestionDonnee.animal s: animal.values()){
            String a=s.toString();
            dicoAnimal.put(a,cpt);
            cpt+=1;
        }
		cpt=0;
        for(GestionDonnee.hobbies s: hobbies.values()){
            String a=s.toString();
            dicoHobbies.put(a,cpt-7);
            cpt+=1;
        }
		int[] compteur={0,0,0,0};
		int j=0;
		String workingDir = System.getProperty("user.dir");
		while(j<50){
			try{
				Personne perso=new creePersonne().p;
				if(perso.age<40){
					if(perso.sexe=="1"){
						Path p=Paths.get(workingDir+File.separator+"projet"+File.separator+"images"+File.separator+"photo-female-young-adult"+compteur[0]+".png");
						perso.URLPhoto=p.toString();
						compteur[0]+=1;
					}
				else{
					Path p=Paths.get(workingDir+File.separator+"projet"+File.separator+"images"+File.separator+"photo-male-young-adult"+compteur[2]+".png");
						perso.URLPhoto=p.toString();
					compteur[2]+=1;
				}
				}
				else{
					if(perso.sexe=="1"){
						Path p=Paths.get(workingDir+File.separator+"projet"+File.separator+"images"+File.separator+"photo-female-adult"+compteur[1]+".png");
						perso.URLPhoto=p.toString();

						compteur[1]+=1;
					}
					else{
						Path p=Paths.get(workingDir+File.separator+"projet"+File.separator+"images"+File.separator+"photo-male-adult"+compteur[3]+".png");
						perso.URLPhoto=p.toString();
						compteur[3]+=1;
					}
				}
				String np=perso.nom+" "+perso.prenom;
				dicoPersonne.put(np,perso);
				this.listePersonne.add(perso);
				j+=1;
			}

			

			catch (IndexOutOfBoundsException e){
				
			}
		}
	}
}
