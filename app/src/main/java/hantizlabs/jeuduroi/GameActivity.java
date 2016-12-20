package hantizlabs.jeuduroi;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import hantizlabs.jeuduroi.Controller.InitialiseJoueurs;
import hantizlabs.jeuduroi.Model.Carte;
import hantizlabs.jeuduroi.Model.Joueur;
import hantizlabs.jeuduroi.Model.Paquet;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private CardStack mCardStack;
    private CardsDataAdapter mCardAdapter;
    private TextView cartesRestantes;
    private TextView txtRegle;
    private TextView txtProchainJoueur;
    private InitialiseJoueurs listeJoueurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        cartesRestantes = (TextView) findViewById(R.id.cartesRestantes);
        txtRegle = (TextView) findViewById(R.id.regleCarte);
        txtProchainJoueur = (TextView) findViewById(R.id.prochainJoueur);
        listeJoueurs = (InitialiseJoueurs) getApplicationContext();

        //Valeurs pas défaut des textviews
        initView();

        FloatingActionButton buttonRatePlayStore= (FloatingActionButton) findViewById(R.id.buttonRatePlayStore);
        buttonRatePlayStore.setOnClickListener(this);

        FloatingActionButton restartGameFromScratch = (FloatingActionButton) findViewById(R.id.newGameFromScratch);
        restartGameFromScratch.setOnClickListener(this);

        FloatingActionButton restartGame = (FloatingActionButton) findViewById(R.id.restartGame);
        restartGame.setOnClickListener(this);

        //Initialisation des paramètres du jeu
        Paquet currentPaquet = new Paquet();
        //Remplissage du paquet
        currentPaquet.initializePaquet();


        //Display of the card stack
        mCardStack = (CardStack)findViewById(R.id.container);
        mCardStack.setContentResource(R.layout.card_content);
        mCardStack.setStackMargin(20);
        mCardStack.setGameActivityContext(this);
        mCardAdapter = new CardsDataAdapter(getApplicationContext());
        mCardAdapter.add(currentPaquet.coverCard());
        Log.i("Nb cartes avant ajout", String.valueOf(currentPaquet.getNombreCartes()));
        //Ajout des cartes du paquet
        for(int i = 0; i < currentPaquet.getNombreCartes(); i++){
            //Log.i("ajout d'une carte",currentPaquet.getListe_cartes().get(i).toString());
            Log.i("Nombre cartes", String.valueOf(currentPaquet.getNombreCartes()));
            mCardAdapter.add(currentPaquet.randomCarte());
        }

        mCardStack.setAdapter(mCardAdapter);
        //cartesRestantes.setText(mCardStack.getStackSize());
        if(mCardStack.getAdapter() != null) {
            Log.i("Game Activity", "Card Stack size: " + mCardStack.getAdapter().getCount());
        }
    }

    public void updateNombreCartes(int nbCartes){
            cartesRestantes.setText("Restant : " + nbCartes);
        Log.i("updateNombreCartes","from game activity" + nbCartes);
    }

    public void updateRegleCarte(String regle){
        txtRegle.setText(regle);
    }

    public void updateNextJoueur(Joueur j){
        txtProchainJoueur.setText("Prochain : " + j.getPrenom());
    }

    public void initView(){
        cartesRestantes.setText("");
        txtRegle.setText("Retourne la première carte pour entamer la cuite !");
        txtProchainJoueur.setText("");
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
                Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                }
                break;
            case R.id.newGameFromScratch:
                Intent intentStart = new Intent(getApplicationContext(), StartActivity.class);
                //Reset de la liste des joueurs
                listeJoueurs.clearAllPlayers();
                startActivity(intentStart);
                break;
            case R.id.restartGame:
                Paquet newPaquet = new Paquet();
                mCardStack.reset(true);
                mCardStack.getAdapter().clear();
                mCardAdapter.add(newPaquet.coverCard());
                for(int i = 0; i < newPaquet.getNombreCartes(); i++){
                    //Log.i("ajout d'une carte",newPaquet.getListe_cartes().get(i).toString());
                    Carte randomCarte = newPaquet.randomCarte();
                    mCardAdapter.add(randomCarte);
                }

                mCardStack.setAdapter(mCardAdapter);
                initView();

                break;
        }

    }


}
