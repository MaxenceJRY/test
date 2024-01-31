import java.util.ArrayList;
import java.util.List;

public class Jeu {
    public Paquet paquet;

    public List<Joueur> listJoueur;

    public Jeu (int nbrJoueur) {
        this.paquet = new Paquet();
        this.paquet.melanger();
        // paquet.Afficher();
        this.listJoueur = new ArrayList<>(nbrJoueur);

        for (int i = 0; i < nbrJoueur; i++) {
            Joueur joueur = new Joueur();
            this.listJoueur.add(joueur);
        }
    }

    public void jouer(){
        List<Carte> list = this.paquet.distribuer(listJoueur.size());
        for (int i = 0; i < listJoueur.size(); i++) {
            List<Carte> carteJoueur = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                carteJoueur.add(list.get(i+j*listJoueur.size()));
            }
            listJoueur.get(i).initializeMainJoueur(new MainPoker(carteJoueur));
        }

    }
}
