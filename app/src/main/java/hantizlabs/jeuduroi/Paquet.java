package hantizlabs.jeuduroi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas on 25/06/2016.
 */
public class Paquet {

    private int nombreCartes;
    public static List<Carte> liste_cartes = new ArrayList<Carte>();
    private int nombreJoueurs;
    private int nombreRois;

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


    public List<Carte> initializePaquet(){

        //CREATION DU PAQUET



        //AJOUT DES CARTES DANS LA LISTE



        //INIT FAMILLES

        Famille coeur = new Famille("Coeur","./drawable/coeur.png");
        Famille pique = new Famille("Pique","./drawable/pique.png");
        Famille trefle = new Famille("Trefle","./drawable/trefle.png");
        Famille carreau = new Famille("Carreau","./drawable/carreau.png");

        //INIT FAMILLE COEUR
        ArrayList<Carte> liste = new ArrayList<Carte>();
        setListe_cartes(liste);
        Carte coeur_1 = new Carte("1",  coeur, "Tu distribues 1 gorgée","as_coeur");
        Carte coeur_2 = new Carte("2", coeur, "Tu distribues 2 gorgées","a_coeur");
        Carte coeur_3 = new Carte("3", coeur, "Tu distribues 3 gorgées","b_coeur");
        Carte coeur_4 = new Carte("4", coeur, "Tu distribues 4 gorgées","c_coeur");
        Carte coeur_5 = new Carte("5", coeur, "Tu distribues 5 gorgées","d_coeur");
        Carte coeur_6 = new Carte("6", coeur, "Inventes une règle ! Ecris-la en dessous.","e_coeur");
        Carte coeur_7 = new Carte("7", coeur, "Passe ton tour..","f_coeur");
        Carte coeur_8 = new Carte("8", coeur, "Choisis un thème. Le sens change.","g_coeur");
        Carte coeur_9 = new Carte("9", coeur, "Choisis une rime !","h_coeur");
        Carte coeur_10 = new Carte("10", coeur, "Pierre - Feuille - Ciseaux avec ton voisin de gauche ! Le premier en 3 gagne.","i_coeur");
        Carte coeur_valet = new Carte("Valet", coeur, "Tous les mecs boivent 2 gorgées !","valet_coeur");
        Carte coeur_dame = new Carte("Dame", coeur, "Toutes les meufs boivent 2 gorgées !","reine_coeur");
        Carte coeur_roi = new Carte("Roi", coeur, "Remplissez le verre du roi d'un quart !","roi_coeur");

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

        Carte pique_1 = new Carte("1", pique, "Tu distribues 1 gorgée","as_pique");
        Carte pique_2 = new Carte("2", pique, "Tu distribues 2 gorgées","a_pique");
        Carte pique_3 = new Carte("3", pique, "Tu distribues 3 gorgées","b_pique");
        Carte pique_4 = new Carte("4", pique, "Tu distribues 4 gorgées","c_pique");
        Carte pique_5 = new Carte("5", pique, "Tu distribues 5 gorgées","d_pique");
        Carte pique_6 = new Carte("6", pique, "Inventes une règle ! Ecris-la en dessous.","e_pique");
        Carte pique_7 = new Carte("7", pique, "Passe ton tour..","f_pique");
        Carte pique_8 = new Carte("8", pique, "Choisis un thème. Le sens change.","g_pique");
        Carte pique_9 = new Carte("9", pique, "Choisis une rime !","h_pique");
        Carte pique_10 = new Carte("10", pique, "Pierre - Feuille - Ciseaux avec ton voisin de droite ! Le premier en 3 gagne.","i_pique");
        Carte pique_valet = new Carte("Valet", pique, "Tous les mecs boivent 2 gorgées !","valet_pique");
        Carte pique_dame = new Carte("Dame", pique, "Toutes les meufs boivent 2 gorgées !","reine_pique");
        Carte pique_roi = new Carte("Roi", pique, "Remplissez le verre du roi d'un quart !","roi_pique");

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

        Carte trefle_1 = new Carte("1", trefle, "Tu distribues 1 gorgée","as_treffle");
        Carte trefle_2 = new Carte("2", trefle, "Tu distribues 2 gorgées","a_treffle");
        Carte trefle_3 = new Carte("3", trefle, "Tu distribues 3 gorgées","b_treffle");
        Carte trefle_4 = new Carte("4", trefle, "Tu distribues 4 gorgées","c_treffle");
        Carte trefle_5 = new Carte("5", trefle, "Tu distribues 5 gorgées","d_treffle");
        Carte trefle_6 = new Carte("6", trefle, "Inventes une règle ! Ecris-la en dessous.","e_trefle");
        Carte trefle_7 = new Carte("7", trefle, "Passe ton tour..","f_treffle");
        Carte trefle_8 = new Carte("8", trefle, "Choisis un thème. Le sens change.","g_treffle");
        Carte trefle_9 = new Carte("9", trefle, "Choisis une rime !","h_treffle");
        Carte trefle_10 = new Carte("10", trefle, "Pierre - Feuille - Ciseaux avec ton voisin de gauche ! Le premier en 3 gagne.","i_treffle");
        Carte trefle_valet = new Carte("Valet", trefle, "Tous les mecs boivent 2 gorgées !","valet_treffle");
        Carte trefle_dame = new Carte("Dame", trefle, "Toutes les meufs boivent 2 gorgées !","reine_treffle");
        Carte trefle_roi = new Carte("Roi", trefle, "Remplissez le verre du roi d'un quart !","roi_treffle");

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

        Carte carreau_1 = new Carte("1", carreau, "Tu distribues 1 gorgée","as_carreau");
        Carte carreau_2 = new Carte("2", carreau, "Tu distribues 2 gorgées","a_carreau");
        Carte carreau_3 = new Carte("3", carreau, "Tu distribues 3 gorgées","b_carreau");
        Carte carreau_4 = new Carte("4", carreau, "Tu distribues 4 gorgées","c_carreau");
        Carte carreau_5 = new Carte("5", carreau, "Tu distribues 5 gorgées","d_carreau");
        Carte carreau_6 = new Carte("6", carreau, "Inventes une règle !","e_carreau");
        Carte carreau_7 = new Carte("7", carreau, "Passe ton tour..","f_carreau");
        Carte carreau_8 = new Carte("8", carreau, "Choisis un thème. Le sens change.","g_carreau");
        Carte carreau_9 = new Carte("9", carreau, "Choisis une rime !","h_carreau");
        Carte carreau_10 = new Carte("10", carreau, "Pierre - Feuille - Ciseaux avec ton voisin de droite ! Le premier en 3 gagne.","i_carreau");
        Carte carreau_valet = new Carte("Valet", carreau, "Tous les mecs boivent 2 gorgées !","valet_carreau");
        Carte carreau_dame = new Carte("Dame", carreau, "Toutes les filles boivent 2 gorgées !","reine_carreau");
        Carte carreau_roi = new Carte("Roi", carreau, "Remplissez le verre du roi d'un quart !","roi_carreau");

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
