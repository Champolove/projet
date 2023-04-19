

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
					return 0;
				}
				somme-=1*prio[i];
			}
		}
		return somme;
	}
	public static void main(String[] args) {
		Personne p1=new Personne("charlie", "charlie", 18, "1","0","1","2","1","1","1","0");
		Personne p2=new Personne("charlie", "charlie", 18, "0","1","2","0","1","0","1","0");
		Algo a=new Algo();
		System.out.println(a.match(p1, p2));

	}

}
