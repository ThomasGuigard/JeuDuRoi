package hantizlabs.jeuduroi.Model;

/**
 * Created by Thomas on 25/06/2016.
 */
public class Famille {

    private String nom;
    private String pathImage;



    public String getNom() {
        return nom;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }



    public String getPathImage() {
        return pathImage;
    }



    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }




    public Famille(String nom, String pathImage) {
        super();
        this.nom = nom;
        this.pathImage = pathImage;
    }



    @Override
    public String toString() {
        return "Famille [nom=" + nom + ", pathImage=" + pathImage + "]";
    }



    public Famille() {
        // TODO Auto-generated constructor stub

    }

}
