package hantizlabs.jeuduroi;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

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
    private TextView txtJoueurActuel;
    private InitialiseJoueurs listeJoueurs;
    private FloatingActionsMenu menuButton;
    private RelativeLayout menuEnd;
    private Button playAgainButton;
    private Button changeNamesButton;
    private Button newModeButton;
    InterstitialAd adRestart;
    InterstitialAd adRestartFromScratch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        cartesRestantes = (TextView) findViewById(R.id.cartesRestantes);
        txtRegle = (TextView) findViewById(R.id.regleCarte);
        txtProchainJoueur = (TextView) findViewById(R.id.prochainJoueur);
        listeJoueurs = (InitialiseJoueurs) getApplicationContext();
        txtJoueurActuel = (TextView) findViewById(R.id.joueurActuel);
        menuButton = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuEnd = (RelativeLayout) findViewById(R.id.menuEnd);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        changeNamesButton = (Button) findViewById(R.id.changeNamesButton);
        newModeButton = (Button) findViewById(R.id.newModeButton);

        //Valeurs pas défaut des textviews
        initView();

        FloatingActionButton buttonRatePlayStore= (FloatingActionButton) findViewById(R.id.buttonRatePlayStore);
        buttonRatePlayStore.setOnClickListener(this);

        FloatingActionButton restartGameFromScratch = (FloatingActionButton) findViewById(R.id.newGameFromScratch);
        restartGameFromScratch.setOnClickListener(this);

        FloatingActionButton restartGame = (FloatingActionButton) findViewById(R.id.restartGame);
        restartGame.setOnClickListener(this);

        playAgainButton.setOnClickListener(this);
        changeNamesButton.setOnClickListener(this);
        newModeButton.setOnClickListener(this);

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

        //Gestion des pubs
        adRestart = new InterstitialAd(this);
        adRestart.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        adRestart.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
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
            }
        });

        adRestartFromScratch = new InterstitialAd(this);
        adRestartFromScratch.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        adRestartFromScratch.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                Intent intentStart = new Intent(getApplicationContext(), StartActivity.class);
                //Reset de la liste des joueurs
                listeJoueurs.clearAllPlayers();
                startActivity(intentStart);
            }
        });


        requestNewInterstitial();
    }

    public void updateNombreCartes(int nbCartes){
        if(nbCartes != 0) {
            cartesRestantes.setText(nbCartes + " / 52");
            Log.i("updateNombreCartes", "from game activity" + nbCartes);
        }else{
            displayEndGame();
        }
    }

    public void displayEndGame(){
        cartesRestantes.setText(" Fini !");
        txtRegle.setText("Voilà, maintenant vous êtes ronds. Si vous ne l'êtes pas, recommencez ;)");
        txtProchainJoueur.setText("");
        txtJoueurActuel.setText("");
        menuEnd.setVisibility(View.VISIBLE);
    }

    public void updateRegleCarte(String regle){
        txtRegle.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
        txtRegle.setText(regle);
        txtRegle.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
    }

    public void updateNextJoueur(Joueur i, Joueur j){
        String s = "<b>" + i.getPrenom() + " >> </b>" + j.getPrenom();
        txtJoueurActuel.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
        txtJoueurActuel.setText(Html.fromHtml(s));
        txtJoueurActuel.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        //txtProchainJoueur.setText(j.getPrenom());
        //txtProchainJoueur.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
    }

    public void initView(){
        cartesRestantes.setText("- / 52");
        txtRegle.setText("Retourne la première carte pour entamer la cuite !");
        txtProchainJoueur.setText("");
        txtJoueurActuel.setText("");
        menuButton.performClick();
        menuEnd.setVisibility(View.GONE);
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
                if (adRestartFromScratch.isLoaded()) {
                    adRestartFromScratch.show();
                } else {
                    Intent intentStart = new Intent(getApplicationContext(), StartActivity.class);
                    //Reset de la liste des joueurs
                    listeJoueurs.clearAllPlayers();
                    startActivity(intentStart);
                }
                break;
            case R.id.restartGame:

                if (adRestart.isLoaded()) {
                    adRestart.show();
                } else {
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
                }

                break;
            case R.id.playAgainButton:
                if (adRestart.isLoaded()) {
                    adRestart.show();
                } else {
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
                }
                break;
            case R.id.changeNamesButton:
                if (adRestartFromScratch.isLoaded()) {
                    adRestartFromScratch.show();
                } else {
                    Intent intentStart = new Intent(getApplicationContext(), StartActivity.class);
                    //Reset de la liste des joueurs
                    listeJoueurs.clearAllPlayers();
                    startActivity(intentStart);
                }
                break;
            case R.id.newModeButton:
                Toast.makeText(getApplicationContext(), "Fonctionnalité en développement", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("12F65E92050C76C69774DF794F7387E4")
                .build();

        adRestart.loadAd(adRequest);

        AdRequest adRequest2 = new AdRequest.Builder()
                .addTestDevice("12F65E92050C76C69774DF794F7387E4")
                .build();

        adRestartFromScratch.loadAd(adRequest2);
    }


}
