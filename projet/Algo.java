import java.io.IOException;
import java.util.ArrayList;

public class Algo {

	public Algo() {

	}
	public int match(Personne p1,Personne p2) {
		int somme=0;
		String[] p1H=p1.hash.split("");
		String[] p2H=p2.hashMatch.split("");
		int[] prio= {1,1,2,2,1,1,1,0};
		for(int i=0;i<p1H.length;i++) {
			if(p1H[i].equals(p2H[i])) {
				somme+=1*prio[i];
			}
			else {
				if(0<=i && i<2) {
					return -10;
				}
				somme-=1*prio[i];
			}
		}
		return somme;
	}
	public ArrayList<Personne> listeMatch(Personne p1,ArrayList<Personne> lp){
		ArrayList<Personne> res=new ArrayList<Personne>();
		int[] points={-1,-1,-1,-1,-1};
		int j;
		for(int i=0;i<lp.size();i++){
			Personne p2=lp.get(i);
			if(p1.nom!=p2.nom && p1.prenom!=p2.prenom){
				int valeur=match(p1,p2);
				j=0;
				while(j<points.length){
					if(points[j]<valeur){
						points[j]=valeur;
						if(res.size()<5){
							res.add(j,p2);
						}
						else{
							res.remove(res.get(j));
							res.add(j, p2);
						}
						j=points.length;
					}
					j+=1;
				}
			}
		}
		return res;
	}
	public static void main(String[] args) throws IOException {
		GestionDonnee gd=new GestionDonnee();
		Personne p1=gd.listePersonne.get(0);
		Algo a=new Algo();

	}

}
