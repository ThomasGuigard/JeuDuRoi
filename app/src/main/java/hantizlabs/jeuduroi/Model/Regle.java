package hantizlabs.jeuduroi.Model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Thomas on 08/07/2016.
 */
public class Regle {

    //////////////////////////////  REGLES ////////////////////////////////////////

    private ArrayList<String> MesRegles = new ArrayList<>();

    public ArrayList<String> getMesRegles() {
        return MesRegles;
    }

    public String getMaRegle(int pPosition) {
        if (pPosition < MesRegles.size()) {
            return MesRegles.get(pPosition);
        }
        return null;
    }

    public void setMesRegles(String Regle) {

        MesRegles.add(Regle);

    }

    public void removeRegle() {
        Log.d("Remove Menu ", "Regle");
        /*MesRegles.remove(3);
        MesRegles.remove(2);
        MesRegles.remove(1);
        MesRegles.remove(0);*/

    }

    public int getReglesSize() {

        return MesRegles.size();
    }


    public String toString() {
        return "Regle{" +
                "MesRegles=" + MesRegles +
                '}';
    }

    public Regle standardRules(){

        Regle standardRules = new Regle();
        standardRules.setMesRegles("Tu distribues 1 gorgée");
        standardRules.setMesRegles("Tu distribues 2 gorgées");
        standardRules.setMesRegles("Tu distribues 3 gorgées");
        standardRules.setMesRegles("Tu distribues 4 gorgées");
        standardRules.setMesRegles("Tu distribues 5 gorgées");
        standardRules.setMesRegles("Invente une règle !");
        standardRules.setMesRegles("Passe ton tour.. (C'est à cause de ton haleine)");
        standardRules.setMesRegles("Choisis un thème et faites un tour. (Ex : type d'alcool) 3 gorgées pour le perdant.");
        standardRules.setMesRegles("Choisis un mot, chacun votre tour vous devez trouver une rime. 3 gorgées pour le perdant.");
        standardRules.setMesRegles("Pierre - Feuille - Ciseaux avec ton voisin de gauche ! 3 manches, le perdant boit 3 gorgées.");
        standardRules.setMesRegles("Tous les mecs boivent 2 gorgées !");
        standardRules.setMesRegles("Toutes les filles boivent 2 gorgées !");
        standardRules.setMesRegles("Remplissez le verre du roi d'un quart !");


        return standardRules;
    }
}
