package hantizlabs.jeuduroi.Model;

/**
 * Created by Thomas Guigard on 27/11/2016.
 */

public class Joueur {

    private String prenom;
    private double score;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Joueur(String prenom) {
        this.prenom = prenom;
        this.score = 0;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "prenom='" + prenom + '\'' +
                ", score=" + score +
                '}';
    }
}
