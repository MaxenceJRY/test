public class Carte {
    public valeur valeurCarte;
    public  couleur couleurCarte;
    public enum valeur {
        DEUX("2", 2),
        TROIS("3", 3),
        QUATRE("4", 4),
        CINQ("5", 5),
        SIX("6", 6),
        SEPT("7", 7),
        HUIT("8", 8),
        NEUF("9", 9),
        DIX("10", 10),
        VALET("J", 11),
        DAME("Q", 12),
        ROI("K", 13),
        AS("A", 14);
        private String symbol;
        private int valeur;

        valeur(String symbol, int valeur) {
            this.symbol = symbol;
            this.valeur = valeur;
        }

        public String getSymbol() {
            return symbol;
        }

        public int getValeur() {
            return valeur;
        }
    }
    public enum couleur {
        CARREAU("Carreau"), COEUR("Coeur"), TREFLE("Trèfle"), PIQUE("Pique");
        private String coul;
        couleur(String coul) {
            this.coul = coul;
        }
    }

    public Carte(valeur valeur, couleur couleur) {
        this.valeurCarte = valeur;
        this.couleurCarte = couleur;
    }

    public Carte(Carte carte){
        this.couleurCarte = carte.couleurCarte;
        this.valeurCarte = carte.valeurCarte;

    }
}
