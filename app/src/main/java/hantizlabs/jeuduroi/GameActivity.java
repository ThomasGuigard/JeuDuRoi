package hantizlabs.jeuduroi;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import hantizlabs.jeuduroi.Controller.InitialiseJoueurs;
import hantizlabs.jeuduroi.Model.Carte;
import hantizlabs.jeuduroi.Model.Joueur;
import hantizlabs.jeuduroi.Model.Paquet;
import me.grantland.widget.AutofitHelper;

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
    private Button reglesButton;
    InterstitialAd adRestart;
    InterstitialAd adRestartFromScratch;
    private Carte currentCarte = new Carte();
    private int nombreRoisTires = 0;
    private String contenuRegles = "";
    private HashMap<String, String> customRules = new HashMap<>();
    private ArrayAdapter<String> arrayAdapter;

    Intent intentStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        reglesButton = (Button) findViewById(R.id.regleButton);

        //Valeurs pas défaut des textviews
        initView();

        FloatingActionButton buttonRatePlayStore= (FloatingActionButton) findViewById(R.id.buttonRatePlayStore);
        buttonRatePlayStore.setOnClickListener(this);

        FloatingActionButton restartGameFromScratch = (FloatingActionButton) findViewById(R.id.newGameFromScratch);
        restartGameFromScratch.setOnClickListener(this);

        FloatingActionButton restartGame = (FloatingActionButton) findViewById(R.id.restartGame);
        restartGame.setOnClickListener(this);

        menuButton.setOnClickListener(this);

        playAgainButton.setOnClickListener(this);
        changeNamesButton.setOnClickListener(this);
        newModeButton.setOnClickListener(this);
        reglesButton.setOnClickListener(this);

        //Initialisation des paramètres du jeu
        Paquet currentPaquet = new Paquet();
        //Remplissage du paquet
        currentPaquet.initializePaquet();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1);


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
                //intentStart = new Intent(getApplicationContext(), StartActivity.class);
                //Reset de la liste des joueurs
                listeJoueurs.clearAllPlayers();
                //startActivity(intentStart);
                //Log.d("adRestartFromScratch", "closed");
                //overridePendingTransition(R.animator.slide_out, R.animator.slide_in);
            }
        });


        requestNewInterstitial();
        enableFullScreen();

        //Initialisation de la resize du nom des joueurs
        AutofitHelper.create(txtJoueurActuel);
    }

    public void updateNombreCartes(int nbCartes){
        if(nbCartes != 0) {
            cartesRestantes.setText((nbCartes - 1) + " / 52");
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
        //Reset de l affichage des cartes
        cartesRestantes.setText("- / 52");
        //Reset de la regle affichee
        txtRegle.setText("Retourne la première carte pour entamer la cuite !");
        //Reset des joueurs
        txtProchainJoueur.setText("");
        txtJoueurActuel.setText("");
        //Reset du menu vertical
        menuButton.performClick();
        //Reset du menu de fin
        menuEnd.setVisibility(View.GONE);
        //Reset des regles
        contenuRegles = "";
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1);
        //Reset du softawre du smartphone
        enableFullScreen();
        //Reset des données liées aux cartes
        nombreRoisTires = 0;
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

    @Override
    public void onResume(){
        super.onResume();
        enableFullScreen();
    }

    @Override
    public void onStart(){
        super.onStart();
        enableFullScreen();
    }

    public void enableFullScreen(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.exit(0);
            }
        });
        builder.setNegativeButton("Non, j'ai encore soif !", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        builder.setTitle("Quitter ?");
        builder.setMessage("Êtes-vous sûr de vouloir quitter ce magnifique jeu ?");

        AlertDialog dialog = builder.create();
        dialog.show();
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

                    intentStart = new Intent(getApplicationContext(), StartActivity.class);
                    //Reset de la liste des joueurs
                    listeJoueurs.clearAllPlayers();
                    startActivity(intentStart);
                    overridePendingTransition(R.animator.slide_in, R.animator.slide_out);
                    adRestartFromScratch.show();
                } else {
                    intentStart = new Intent(getApplicationContext(), StartActivity.class);
                    //Reset de la liste des joueurs
                    listeJoueurs.clearAllPlayers();
                    startActivity(intentStart);
                    overridePendingTransition(R.animator.slide_in, R.animator.slide_out);
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
                menuButton.performClick();
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
                menuButton.performClick();
                break;
            case R.id.changeNamesButton:
                if (adRestartFromScratch.isLoaded()) {
                    adRestartFromScratch.show();
                } else {
                    Intent intentStart = new Intent(getApplicationContext(), StartActivity.class);
                    //Reset de la liste des joueurs
                    listeJoueurs.clearAllPlayers();
                    startActivity(intentStart);
                    overridePendingTransition(R.animator.slide_out, R.animator.slide_in);
                }
                break;
            case R.id.newModeButton:
                Toast.makeText(getApplicationContext(), "Fonctionnalité en développement", Toast.LENGTH_SHORT).show();
                break;
            case R.id.regleButton:
                //Creation de l'html contenant les règles
                if(customRules.size() > 0){
                    if(arrayAdapter.getCount() > 0){
                        arrayAdapter.clear();
                    }
                    Iterator it = customRules.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        contenuRegles += Html.fromHtml("<b>" + pair.getKey() +" :</n>/n</b></br>" + pair.getValue() + "</br></br>");
                        arrayAdapter.add("" + pair.getKey().toString().toUpperCase() + " ---> " + pair.getValue().toString().toLowerCase());
                        //System.out.println(pair.getKey() + " = " + pair.getValue());
                    }
                }else{
                    arrayAdapter.add("Aucune règle, retourne boire.");
                }
                /*AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Règles")
                        .setMessage("")
                        .setPositiveButton("C'est bon j'ai retenu !", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(R.mipmap.ic_launcher)
                        .show();*/

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle("Règles");
                dialog.setNegativeButton("Ok j'ai compris !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                    }
                });
                dialog.show();

                /*TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setText(contenuRegles);
                textView.setMaxLines(5);
                textView.setScroller(new Scroller(this));
                textView.setVerticalScrollBarEnabled(true);
                textView.setMovementMethod(new ScrollingMovementMethod());
                dialog.show();*/
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

    public void updateCurrentCarte(Carte currentCarte){
        this.currentCarte = currentCarte;
        if(currentCarte.getValeur() == "Roi" ){
            nombreRoisTires++;
            Log.d("nb rois", String.valueOf(nombreRoisTires));
            if(nombreRoisTires == 4){
                displayAnimationFinalKing();
            }
        }
        if(currentCarte.getValeur() == "6"){
            displayPopupRegle();
        }
    }

    public void displayAnimationFinalKing(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("DERNIER ROI... CUL SEC MON POTE")
                .setPositiveButton("Merci j'ai bien vomi", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(R.mipmap.ic_launcher);

        LayoutInflater factory = LayoutInflater.from(this);
        final View viewRoi = factory.inflate(R.layout.verreduroi, null);
        dialog.setView(viewRoi);
        dialog.show();
    }

    public void displayPopupRegle(){
        final EditText input = new EditText(this);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("6 ! Crée une règle !")
                .setPositiveButton("C'est dans la boite !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(input.getText().toString().length() > 0){
                            customRules.put(listeJoueurs.getCurrentJoueur().getPrenom(), input.getText().toString());
                            dialog.dismiss();}
                    }
                })
                .setIcon(R.mipmap.ic_launcher);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //input.setLayoutParams(lp);
        input.setHint("Ecris ta règle !");
        dialog.setView(input);
        dialog.show();
    }

}
