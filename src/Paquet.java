import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paquet {
    private List<Carte> list = new ArrayList<>();

    public Paquet() {
        for(Carte.couleur couleur : Carte.couleur.values()) {
            for (Carte.valeur valeur : Carte.valeur.values()) {
                Carte carte = new Carte(valeur, couleur);
                //System.out.println(carte.valeurCarte.toString());
                //System.out.println(carte.couleurCarte.toString());
                this.list.add(carte);
            }
        }
    }

    public void melanger () {
        Collections.shuffle(list);
    }

    public List<Carte> Piocher(int nbrPiochee){
        List<Carte> newList = null;
        for (int i=0; i < nbrPiochee; i++){
            newList.add(this.list.get(i));
            this.list.remove(i);
        }
        return newList;
    }

    public List<Carte> distribuer(int nbrJoueur){
        this.melanger();
        List<Carte> newList = new ArrayList<>();
        for (int i=0; i < nbrJoueur*5; i++){
            newList.add(this.list.get(i));
            this.list.remove(i);
        }
        return newList;
    }

    public List<Carte> Trier (List<Carte> list){
        List<Carte> newList = null;
        for(Carte.valeur valeur : Carte.valeur.values()) {
            for (Carte.couleur couleur : Carte.couleur.values()) {
                Carte carte = new Carte(valeur, couleur);
                for (int i=0; i<list.size(); i++){
                    if (carte.valeurCarte == list.get(i).valeurCarte && carte.couleurCarte == list.get(i).couleurCarte )
                        newList.add(carte);
                }
            }
        }
        return newList;
    }

    public void Afficher (){
        System.out.println("-----------------PAQUET MÉLANGÉ-------------------");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).valeurCarte + " DE " + this.list.get(i).couleurCarte);
        }
    }

    public List<Carte> getList() {
        return list;
    }
}
