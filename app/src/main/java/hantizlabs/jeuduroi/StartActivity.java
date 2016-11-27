package hantizlabs.jeuduroi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Objects;

import hantizlabs.jeuduroi.Controller.InitialiseJoueurs;
import hantizlabs.jeuduroi.Model.Joueur;

public class StartActivity extends Activity implements View.OnClickListener {

    InitialiseJoueurs aController ;

    Button AddPls;
    Button Rabatitude;

    String PlayerName;
    int rand;

    RelativeLayout  myLayout;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        AddPls = (Button) findViewById(R.id.AddPlayer);
        AddPls.setOnClickListener(this);

        Rabatitude = (Button) findViewById(R.id.BeginParty);
        Rabatitude.setOnClickListener(this);

        aController = (InitialiseJoueurs) getApplicationContext();
        myLayout = (RelativeLayout) findViewById(R.id.ScrollLayout);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()){

            case R.id.BeginParty:


                Log.d(String.valueOf(myLayout),"  %:okok");
                // Ce tableau va contenir les edittext par la suite ( cpt. Obvious )
                ArrayList<EditText> myEditTextList = new ArrayList<>();

                // La tu récupères tout les edit text que t'as sur ta vue
                // Faudrait eventuellement les faire générer mais j'ai pas réussi je crois ( Voir plus bas ou dans BeforeParty => code identique mais c'est une acitvité)
                for( int i = 0; i < myLayout.getChildCount(); i++ )
                    if( myLayout.getChildAt( i ) instanceof EditText )
                        myEditTextList.add( (EditText) myLayout.getChildAt( i ) );

                // Pour chaque edit text ayant un nom fdp
                for (EditText myPlayerName :myEditTextList) {
                    PlayerName = String.valueOf(myPlayerName.getText());
                    // On vire les espaces puis on vérifie si le champ est rempli ( comme ton cul bb )
                    PlayerName = PlayerName.trim();
                    if (!Objects.equals(PlayerName, "")){
                        if(Objects.equals(PlayerName,"Coco")||(Objects.equals(PlayerName, "Corentin")) ||(Objects.equals(PlayerName, "Hantiz"))){
                            rand = (int) (Math.random() * 2 + 1);

                            switch(rand){

                                case 1:
                                    PlayerName = "M. Houdayer";

                                    break;

                                case 2:
                                    PlayerName = "280° pendant 39-45min";

                                    break;

                                case 3:
                                    PlayerName = "Coco PLS";

                                    break;

                            }

                        }
                        // Tu te souviens du controller que j'avais mis dans mon app ? Même principe, le code est dans "InitialiseJoueurs"
                        // La en gros tu créée un joueur et tu l'ajoute à ta liste qui est récupérable à n'importe quel moment dans n'importe quel activité/fragment
                        aController.setMesJoueurs(new Joueur(PlayerName));
                    } else {
                        Log.d(String.valueOf(myPlayerName.getText()), "plop");
                    }
                }

                Intent intentRoy = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentRoy);

                break;

            case R.id.AddPlayer:

                // A mettre dans une list view et régler la largeur

                // EditText myEditText = ( EditText ) myLayout.findViewById(R.id.editText);

                EditText designation1 = new EditText(getApplicationContext());
                // on modifie la couleur
                designation1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                designation1.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                designation1.setId(myLayout.generateViewId());
                designation1.setWidth(650);
                designation1.setGravity(Gravity.CENTER_HORIZONTAL);
                // doit y'avoir un bug dans le layout on est obliger de décrémenter X
                //designation1.setX(100);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                int resID = getResources().getIdentifier(String.valueOf(i), "id", getApplicationContext().getPackageName());
                // Afin de savoir ou on pose l'edit text :
                if(i > 0){
                    params.addRule(RelativeLayout.BELOW, resID);
                }else{
                    params.addRule(RelativeLayout.BELOW, R.id.background);
                }

                myLayout.addView(designation1, params);

                i++;

                break;

        }
    }
}
