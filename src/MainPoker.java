import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MainPoker {
    public List<Carte> main;
    public String comboMain;


    public enum combo {
        CarteHaute("Carte Haute"),
        Paire("Paire"),
        DoublePaire("Double paire"),
        Brelan("Brelan"),
        Suite("Suite"),
        Couleur("Couleur"),
        Full("Full"),
        Carre("Carré"),
        QuinteFlush("Quinte Flush"),
        QuinteFlushRoyal("Quinte Flush Royal");
        private String comb;
        combo(String comb) {
            this.comb = comb;
        }
    }

    public MainPoker(List<Carte> main) {
        this.main = main;
    }

    public void Afficher (){

        for (int i = 0; i < Trier(this.main).size(); i++) {
            System.out.println(Trier(this.main).get(i).valeurCarte + " DE " + Trier(this.main).get(i).couleurCarte);
        }
    }

    public List<Carte> Trier (List<Carte> list){
        List<Carte> newList = new ArrayList<>();
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

    public void setComboMain() {
        if (aQuinteFlushRoyal()) {
            this.comboMain= "QuinteFlushRoyam";
        } else if (aQuinteFlush()) {
            this.comboMain= "QuinteFlush";
        } else if (aCarre()) {
            this.comboMain= "Carre";
        } else if (aFull()) {
            this.comboMain= "Full";
        } else if (aCouleur()) {
            this.comboMain= "Couleur";
        } else if (aSuite()) {
            this.comboMain= "Suite";
        } else if (aBrelan()) {
            this.comboMain= "Brelan";
        } else if (aDoublePaire()) {
            this.comboMain= "DoublePaire";
        } else if (aPaire()) {
            this.comboMain= "Paire";
        } else {
            this.comboMain= "CarteHaute";
        }
    }

    public boolean aQuinteFlushRoyal() {
        Carte.valeur[] valeursAttendues = {
                Carte.valeur.DIX,
                Carte.valeur.VALET,
                Carte.valeur.DAME,
                Carte.valeur.ROI,
                Carte.valeur.AS
        };

        Carte.couleur couleurAttendue = this.main.get(0).couleurCarte;

        for (int i = 0; i < valeursAttendues.length; i++) {
            if (this.main.get(i).valeurCarte != valeursAttendues[i] ||
                    this.main.get(i).couleurCarte != couleurAttendue) {
                return false;
            }
        }

        return true;
    }
    public boolean aQuinteFlush() {

        for (int i = 0; i < this.main.size() - 1; i++) {
            if (this.main.get(i).valeurCarte.ordinal() - this.main.get(i + 1).valeurCarte.ordinal() != 1) {
                return false;
            }
        }

        Carte.couleur couleurAttendue = this.main.get(0).couleurCarte;
        for (Carte carte : this.main) {
            if (carte.couleurCarte != couleurAttendue) {
                return false;
            }
        }

        return true;
    }
    public boolean aCarre() {
        for (int i = 0; i < this.main.size() - 3; i++) {
            Carte.valeur valeurCarre = this.main.get(i).valeurCarte;
            int count = 1;
            for (int j = i + 1; j < this.main.size(); j++) {
                if (this.main.get(j).valeurCarte == valeurCarre) {
                    count++;
                }
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }
    public boolean aFull() {
        // Vérifiez si la main a au moins 5 cartes
        if (this.main.size() < 5) {
            return false;
        }

        // Triez les cartes par valeur pour simplifier la vérification
        Collections.sort(this.main, (carte1, carte2) -> carte2.valeurCarte.compareTo(carte1.valeurCarte));

        boolean brelanTrouve = false;
        boolean paireTrouvee = false;

        // Parcours de toutes les combinaisons possibles de 3 cartes parmi les 5
        for (int i = 0; i < this.main.size() - 2; i++) {
            Carte.valeur valeurBrelan = this.main.get(i).valeurCarte;
            int countBrelan = 1;

            // Parcours des cartes restantes pour vérifier le brelan
            for (int j = i + 1; j < this.main.size(); j++) {
                if (this.main.get(j).valeurCarte == valeurBrelan) {
                    countBrelan++;
                }
            }

            if (countBrelan == 3) {
                // Il y a un brelan
                brelanTrouve = true;

                // Exclure les cartes du brelan pour la recherche de la paire
                List<Carte> mainSansBrelan = new ArrayList<>(this.main);
                mainSansBrelan.removeIf(carte -> carte.valeurCarte == valeurBrelan);

                // Parcours de toutes les combinaisons possibles de 2 cartes parmi les cartes restantes
                for (int k = 0; k < mainSansBrelan.size() - 1; k++) {
                    if (mainSansBrelan.get(k).valeurCarte == mainSansBrelan.get(k + 1).valeurCarte) {
                        // Il y a une paire
                        paireTrouvee = true;
                        break;
                    }
                }
            }

            if (brelanTrouve && paireTrouvee) {
                // Si on a à la fois un brelan et une paire, alors c'est un full
                return true;
            }
        }

        // Aucun full trouvé
        return false;
    }

    public boolean aCouleur() {
        Carte.couleur couleurAttendue = this.main.get(0).couleurCarte;
        for (Carte carte : this.main) {
            if (carte.couleurCarte != couleurAttendue) {
                return false;
            }
        }
        return true;
    }
    public boolean aSuite() {
        for (int i = 0; i < this.main.size() - 1; i++) {
            int valeurCarteActuelle = this.main.get(i).valeurCarte.getValeur();
            int valeurCarteSuivante = this.main.get(i + 1).valeurCarte.getValeur();

            if (valeurCarteActuelle == 14 && valeurCarteSuivante == 2) {
                continue;
            }

            if (valeurCarteActuelle - valeurCarteSuivante != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean aBrelan() {
        for (int i = 0; i < this.main.size() - 2; i++) {
            Carte.valeur valeurBrelan = this.main.get(i).valeurCarte;
            int countBrelan = 1;

            for (int j = i + 1; j < this.main.size(); j++) {
                if (this.main.get(j).valeurCarte == valeurBrelan) {
                    countBrelan++;
                }
            }

            if (countBrelan == 3) {
                return true;
            }
        }
        return false;
    }
    public boolean aDoublePaire() {
        int pairesTrouvees = 0;

        for (int i = 0; i < this.main.size() - 1; i++) {
            if (this.main.get(i).valeurCarte == this.main.get(i + 1).valeurCarte) {
                // Il y a une paire
                pairesTrouvees++;
                i++;
            }
        }
        return pairesTrouvees == 2;
    }
    public boolean aPaire() {
        for (int i = 0; i < this.main.size() - 1; i++) {
            if (this.main.get(i).valeurCarte == this.main.get(i + 1).valeurCarte) {
                return true;
            }
        }
        return false;
    }

}
