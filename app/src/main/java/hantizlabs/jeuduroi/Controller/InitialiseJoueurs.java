package hantizlabs.jeuduroi.Controller;

/**
 * Created by Thomas Guigard on 27/11/2016.
 */

import android.app.Application;

import android.util.Log;

import java.util.ArrayList;

import hantizlabs.jeuduroi.Model.Joueur;

/**
 * Created by Thomas on 28/06/2016.
 */
public class InitialiseJoueurs extends Application {

    private ArrayList<Joueur> MesJoueurs = new ArrayList<Joueur>();

    // Récup d'un joueur de la liste
    public ArrayList<Joueur> getMesJoueurs() {
        return MesJoueurs;
    }

    public Joueur getMonJoueur(int pPosition) {

        return MesJoueurs.get(pPosition);
    }

    // Ajout d'un joueur de manière dynamique
    public void setMesJoueurs(Joueur MyPlayer) {

        MesJoueurs.add(MyPlayer);

    }

    // Celle ci sera peut-être à modifier pour mettre la pos mais en vrai on s'en balec
    // On a qu'a supprimer le tableau si il veut refaire une partie pis en vrai je vois pas a quel moment il risque d'enlever un joueur
    public void removePlayer(Joueur MyPlayer) {
        Log.d("Remove Menu ", MyPlayer.toString());
        MesJoueurs.remove(MyPlayer);

    }

    // Retourne le nombre de spermato dans tes couilles
    public int getPlayerSize() {

        return MesJoueurs.size();
    }

    @Override
    public String toString() {
        return "InitialiseJoueurs{" +
                "MesJoueurs=" + MesJoueurs +
                '}';
    }



}

