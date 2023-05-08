public class Historique {
    Personne personne1;
    Personne personne2;
    String contexte;

    public Historique(Personne p1,Personne p2,String s){
        personne1=p1;
        personne2=p2;
        contexte=s;
    }
    public void modifierContexte(String s){
        contexte=s;
    }
    
}
