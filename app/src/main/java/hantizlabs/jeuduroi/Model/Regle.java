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
        standardRules.setMesRegles("Tu distribues 2 gorgée");
        standardRules.setMesRegles("Tu distribues 3 gorgée");
        standardRules.setMesRegles("Tu distribues 4 gorgée");
        standardRules.setMesRegles("Tu distribues 5 gorgée");
        standardRules.setMesRegles("Inventes une règle ! Ecris-la en dessous.");
        standardRules.setMesRegles("Passe ton tour..");
        standardRules.setMesRegles("Choisis un thème. Le sens change.");
        standardRules.setMesRegles("Choisis une rime !");
        standardRules.setMesRegles("Pierre - Feuille - Ciseaux avec ton voisin de gauche ! Le premier en 3 gagne.");
        standardRules.setMesRegles("Tous les mecs boivent 2 gorgées !");
        standardRules.setMesRegles("Toutes les filles boivent 2 gorgées !");
        standardRules.setMesRegles("Remplissez le verre du roi d'un quart !");


        return standardRules;
    }
}
