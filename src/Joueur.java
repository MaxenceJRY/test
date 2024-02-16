public class Joueur {
    public MainPoker mainJoueur;
    public String nom;
    public int score;
    public enum role {
        Button("Bouton"),SmallBlind("SmallBlind"),BigBlind("BigBlind");
        private String role;
        role(String role) {
            this.role = role;
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void initializeMainJoueur(MainPoker main) {
        this.mainJoueur = main;
    }

    public Joueur() {
    }

    public Joueur(MainPoker mainJoueur, String nom) {
        this.mainJoueur = mainJoueur;
        this.nom = nom;
    }
}
