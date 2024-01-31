public class Main {
    public static void main(String[] args) {

        Jeu jeu = new Jeu(5);
        jeu.listJoueur.get(0).setNom("Maxence");
        jeu.listJoueur.get(1).setNom("Nicolas");
        jeu.listJoueur.get(2).setNom("Alban");
        jeu.listJoueur.get(3).setNom("Naomi");
        jeu.listJoueur.get(4).setNom("Yann");

        jeu.jouer();

        for (int i = 0; i < 5; i++) {
            System.out.println(jeu.listJoueur.get(i).nom + " ---------------");
            jeu.listJoueur.get(i).mainJoueur.setComboMain();
            System.out.println(jeu.listJoueur.get(i).mainJoueur.comboMain);
            jeu.listJoueur.get(i).mainJoueur.Afficher();
        }
    }
}