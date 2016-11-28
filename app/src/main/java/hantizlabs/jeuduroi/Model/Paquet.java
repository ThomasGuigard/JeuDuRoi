package hantizlabs.jeuduroi.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hantizlabs.jeuduroi.Model.Carte;
import hantizlabs.jeuduroi.Model.Famille;

/**
 * Created by Thomas on 25/06/2016.
 */
public class Paquet {

    private int nombreCartes;
    public static List<Carte> liste_cartes = new ArrayList<Carte>();
    private int nombreJoueurs;
    private int nombreRois;
    private Regle mesRegles;

    //CONSTRUCTEUR
    public Paquet(int nombreCartes, List<Carte> liste_cartes)
    {
        this.nombreCartes = nombreCartes;
        this.liste_cartes = liste_cartes;
        this.nombreRois = 4;
    }

    //ACCESSEURS

    public int getNombreCartes()  {

        return nombreCartes;

    }

    public int getNombreRois(){

        return nombreRois;
    }

    public int getNombreJoueurs()  {

        return nombreJoueurs;

    }

    public List<Carte> getListe_cartes()  {

        return liste_cartes;

    }

    public void setNombreRois(int nb){
        this.nombreRois = nb;
    }

    public void setNombreCartes(int nb)
    {
        nombreCartes = nb;

    }

    public void setNombreJoueurs(int nb)
    {
        nombreJoueurs = nb;

    }


    public void setListe_cartes(List<Carte> liste)
    {
        liste_cartes = liste;

    }

    public Regle getMesRegles() {
        return mesRegles;
    }

    public void setMesRegles(Regle mesRegles) {
        this.mesRegles = mesRegles;
    }

    public List<Carte> initializePaquet(){

        //CREATION DU PAQUET

        //CREATION STANDARDE DES REGLES
        Regle mesRegles = new Regle();
        mesRegles = mesRegles.standardRules();

        //AJOUT DES CARTES DANS LA LISTE



        //INIT FAMILLES

        Famille coeur = new Famille("Coeur","./drawable/coeur.png");
        Famille pique = new Famille("Pique","./drawable/pique.png");
        Famille trefle = new Famille("Trefle","./drawable/trefle.png");
        Famille carreau = new Famille("Carreau","./drawable/carreau.png");

        //INIT FAMILLE COEUR
        ArrayList<Carte> liste = new ArrayList<Carte>();
        setListe_cartes(liste);
        Carte coeur_1 = new Carte("1",  coeur, mesRegles.getMaRegle(0),"as_coeur");
        Carte coeur_2 = new Carte("2", coeur, mesRegles.getMaRegle(1),"a_coeur");
        Carte coeur_3 = new Carte("3", coeur, mesRegles.getMaRegle(2),"b_coeur");
        Carte coeur_4 = new Carte("4", coeur, mesRegles.getMaRegle(3),"c_coeur");
        Carte coeur_5 = new Carte("5", coeur, mesRegles.getMaRegle(4),"d_coeur");
        Carte coeur_6 = new Carte("6", coeur, mesRegles.getMaRegle(5),"e_coeur");
        Carte coeur_7 = new Carte("7", coeur, mesRegles.getMaRegle(6),"f_coeur");
        Carte coeur_8 = new Carte("8", coeur, mesRegles.getMaRegle(7),"g_coeur");
        Carte coeur_9 = new Carte("9", coeur, mesRegles.getMaRegle(8),"h_coeur");
        Carte coeur_10 = new Carte("10", coeur, mesRegles.getMaRegle(9),"i_coeur");
        Carte coeur_valet = new Carte("Valet", coeur, mesRegles.getMaRegle(10),"valet_coeur");
        Carte coeur_dame = new Carte("Dame", coeur, mesRegles.getMaRegle(11),"reine_coeur");
        Carte coeur_roi = new Carte("Roi", coeur, mesRegles.getMaRegle(12),"roi_coeur");

        //AJOUT FAMILLE COEUR

        liste.add(coeur_1);
        liste.add(coeur_2);
        liste.add(coeur_3);
        liste.add(coeur_4);
        liste.add(coeur_5);
        liste.add(coeur_6);
        liste.add(coeur_7);
        liste.add(coeur_8);
        liste.add(coeur_9);
        liste.add(coeur_10);
        liste.add(coeur_valet);
        liste.add(coeur_dame);
        liste.add(coeur_roi);

        //INIT FAMILLE PIQUE

        Carte pique_1 = new Carte("1", pique, mesRegles.getMaRegle(0),"as_pique");
        Carte pique_2 = new Carte("2", pique, mesRegles.getMaRegle(1),"a_pique");
        Carte pique_3 = new Carte("3", pique, mesRegles.getMaRegle(2),"b_pique");
        Carte pique_4 = new Carte("4", pique, mesRegles.getMaRegle(3),"c_pique");
        Carte pique_5 = new Carte("5", pique, mesRegles.getMaRegle(4),"d_pique");
        Carte pique_6 = new Carte("6", pique, mesRegles.getMaRegle(5),"e_pique");
        Carte pique_7 = new Carte("7", pique, mesRegles.getMaRegle(6),"f_pique");
        Carte pique_8 = new Carte("8", pique, mesRegles.getMaRegle(7),"g_pique");
        Carte pique_9 = new Carte("9", pique, mesRegles.getMaRegle(8),"h_pique");
        Carte pique_10 = new Carte("10", pique, mesRegles.getMaRegle(9),"i_pique");
        Carte pique_valet = new Carte("Valet", pique, mesRegles.getMaRegle(10),"valet_pique");
        Carte pique_dame = new Carte("Dame", pique, mesRegles.getMaRegle(1),"reine_pique");
        Carte pique_roi = new Carte("Roi", pique, mesRegles.getMaRegle(12),"roi_pique");

        //AJOUT FAMILLE PIQUE

        liste.add(pique_1);
        liste.add(pique_2);
        liste.add(pique_3);
        liste.add(pique_4);
        liste.add(pique_5);
        liste.add(pique_6);
        liste.add(pique_7);
        liste.add(pique_8);
        liste.add(pique_9);
        liste.add(pique_10);
        liste.add(pique_valet);
        liste.add(pique_dame);
        liste.add(pique_roi);

        //INIT FAMILLE TREFLE

        Carte trefle_1 = new Carte("1", trefle, mesRegles.getMaRegle(0),"as_treffle");
        Carte trefle_2 = new Carte("2", trefle, mesRegles.getMaRegle(1),"a_treffle");
        Carte trefle_3 = new Carte("3", trefle, mesRegles.getMaRegle(2),"b_treffle");
        Carte trefle_4 = new Carte("4", trefle, mesRegles.getMaRegle(3),"c_treffle");
        Carte trefle_5 = new Carte("5", trefle, mesRegles.getMaRegle(4),"d_treffle");
        Carte trefle_6 = new Carte("6", trefle, mesRegles.getMaRegle(5),"e_trefle");
        Carte trefle_7 = new Carte("7", trefle, mesRegles.getMaRegle(6),"f_treffle");
        Carte trefle_8 = new Carte("8", trefle, mesRegles.getMaRegle(7),"g_treffle");
        Carte trefle_9 = new Carte("9", trefle, mesRegles.getMaRegle(8),"h_treffle");
        Carte trefle_10 = new Carte("10", trefle, mesRegles.getMaRegle(9),"i_treffle");
        Carte trefle_valet = new Carte("Valet", trefle, mesRegles.getMaRegle(10),"valet_treffle");
        Carte trefle_dame = new Carte("Dame", trefle, mesRegles.getMaRegle(11),"reine_treffle");
        Carte trefle_roi = new Carte("Roi", trefle, mesRegles.getMaRegle(12),"roi_treffle");

        //AJOUT FAMILLE TREFLE

        liste.add(trefle_1);
        liste.add(trefle_2);
        liste.add(trefle_3);
        liste.add(trefle_4);
        liste.add(trefle_5);
        liste.add(trefle_6);
        liste.add(trefle_7);
        liste.add(trefle_8);
        liste.add(trefle_9);
        liste.add(trefle_10);
        liste.add(trefle_valet);
        liste.add(trefle_dame);
        liste.add(trefle_roi);

        //INIT FAMILLE CARREAU

        Carte carreau_1 = new Carte("1", carreau, mesRegles.getMaRegle(0),"as_carreau");
        Carte carreau_2 = new Carte("2", carreau, mesRegles.getMaRegle(1),"a_carreau");
        Carte carreau_3 = new Carte("3", carreau, mesRegles.getMaRegle(2),"b_carreau");
        Carte carreau_4 = new Carte("4", carreau, mesRegles.getMaRegle(3),"c_carreau");
        Carte carreau_5 = new Carte("5", carreau, mesRegles.getMaRegle(4),"d_carreau");
        Carte carreau_6 = new Carte("6", carreau, mesRegles.getMaRegle(5),"e_carreau");
        Carte carreau_7 = new Carte("7", carreau, mesRegles.getMaRegle(6),"f_carreau");
        Carte carreau_8 = new Carte("8", carreau, mesRegles.getMaRegle(7),"g_carreau");
        Carte carreau_9 = new Carte("9", carreau, mesRegles.getMaRegle(8),"h_carreau");
        Carte carreau_10 = new Carte("10", carreau, mesRegles.getMaRegle(9),"i_carreau");
        Carte carreau_valet = new Carte("Valet", carreau, mesRegles.getMaRegle(10),"valet_carreau");
        Carte carreau_dame = new Carte("Dame", carreau, mesRegles.getMaRegle(11),"reine_carreau");
        Carte carreau_roi = new Carte("Roi", carreau, mesRegles.getMaRegle(12),"roi_carreau");

        //AJOUT FAMILLE CARREAU

        liste.add(carreau_1);
        liste.add(carreau_2);
        liste.add(carreau_3);
        liste.add(carreau_4);
        liste.add(carreau_5);
        liste.add(carreau_6);
        liste.add(carreau_7);
        liste.add(carreau_8);
        liste.add(carreau_9);
        liste.add(carreau_10);
        liste.add(carreau_valet);
        liste.add(carreau_dame);
        liste.add(carreau_roi);

        return liste;

        //for(Carte carte : liste_cartes)

    }

    public Carte randomCarte(){

        int indiceRandom = (int) (Math.random()*(liste_cartes.size()));

        Carte carteTiree =  liste_cartes.get(indiceRandom);

        this.setNombreCartes(nombreCartes-1);

        if(carteTiree.getValeur() == "Roi"){
            setNombreRois(nombreRois -1);

            if(getNombreRois() == 0){
                carteTiree.setDescription("Dernier roi tiré ! CUL-SEC ! (tu peux rajouter du diluant)");
            }
        }

        return carteTiree;

    }

    public void retirerCarte(Carte carteTiree){

        Iterator<Carte> it = this.getListe_cartes().iterator();
        while (it.hasNext()) {
            Carte card = it.next();
            if (card.equals(carteTiree)) {
                it.remove();
            }
        }


    }


}
