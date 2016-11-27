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
}
