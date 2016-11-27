package hantizlabs.jeuduroi;

/**
 * Created by Thomas on 28/06/2016.
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
    }

    @Override
    public String toString() {
        return " " + prenom + " ";
    }
}
