package hantizlabs.jeuduroi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import hantizlabs.jeuduroi.FloatingActionButton;
import hantizlabs.jeuduroi.FloatingActionsMenu;
import hantizlabs.jeuduroi.Model.Carte;
import hantizlabs.jeuduroi.Model.Paquet;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private CardStack mCardStack;
    private CardsDataAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        FloatingActionButton buttonRatePlayStore= (FloatingActionButton) findViewById(R.id.buttonRatePlayStore);
        buttonRatePlayStore.setOnClickListener(this);

        //Initialisation des paramètres du jeu
        Paquet currentPaquet = new Paquet();
        //Remplissage du paquet
        currentPaquet.initializePaquet();


        //Display of the card stack
        mCardStack = (CardStack)findViewById(R.id.container);
        mCardStack.setContentResource(R.layout.card_content);
        mCardStack.setStackMargin(20);
        mCardAdapter = new CardsDataAdapter(getApplicationContext());
        Log.i("Nb cartes avant ajout", String.valueOf(currentPaquet.getNombreCartes()));
        //Ajout des cartes du paquet
        for(int i = 0; i < currentPaquet.getNombreCartes(); i++){
            Log.i("ajout d'une carte",currentPaquet.getListe_cartes().get(i).toString());
            mCardAdapter.add(currentPaquet.randomCarte());
        }


        mCardStack.setAdapter(mCardAdapter);

        if(mCardStack.getAdapter() != null) {
            Log.i("Game Activity", "Card Stack size: " + mCardStack.getAdapter().getCount());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRatePlayStore:
                //TODO
                break;
        }

    }
}
