package hantizlabs.jeuduroi.Model;

import java.util.ArrayList;
import java.util.List;

import hantizlabs.jeuduroi.*;

/**
 * Created by Corentin on 27/11/2016.
 */

public class Partie {

    private List<Joueur> listPlayers;
    private Paquet cardStack;

    public Partie() {
    }

    public Partie(List<Joueur> listPlayers) {
        this.listPlayers = listPlayers;
        List<Carte> listCartes = new ArrayList<Carte>();
        cardStack = new Paquet(52, listCartes);
    }

    public Joueur getNextJoueur(){
        return new Joueur("name");
    }
}
