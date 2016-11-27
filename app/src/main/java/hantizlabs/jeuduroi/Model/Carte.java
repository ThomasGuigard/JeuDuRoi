package hantizlabs.jeuduroi.Model;

/**
 * Created by Thomas on 25/06/2016.
 */
public class Carte {



    private String valeur;
    private Famille famille;
    private String description;
    private String filepath;


    public Carte(String valeur, Famille famille, String description, String filepath){
        this.valeur = valeur;
        this.famille = famille;
        this.description = description;
        this.filepath = filepath;
        Paquet.liste_cartes.add(this);

    }



    public String getValeur(){
        return valeur;
    }

    public Famille getFamille(){
        return famille;
    }

    public String getDescription(){
        return description;
    }

    public void setValeur(String nb){
        this.valeur = nb;
    }

    public void setFamille(Famille fam){
        this.famille = fam;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    @Override
    public String toString() {
        return valeur + " de " + famille + "\n";
    }


    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

}
