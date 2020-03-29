package fr.android.tennistracker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

public class MatchActivity extends AppCompatActivity {
    private FragmentScore fragmentScore;
    private FragmentService fragmentService;
    private FragmentEchange fragmentEchange;

    private static final String JOUEUR_1 = "Joueur 1";
    private static final String JOUEUR_2 = "Joueur 2";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu_demarrer);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        String nomJoueur1 = null;
        String nomJoueur2 = null;
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            nomJoueur1 = extras.getString("NOM_JOUEUR_1");
            nomJoueur2 = extras.getString("NOM_JOUEUR_2");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Enregistrement");
        // ?
        invalidateOptionsMenu();
        // ?


        //final Integer choice;
        //if((nomJoueur1 == null)
        final String[] listItems = new String[]{
                (nomJoueur1.isEmpty())? JOUEUR_1 :nomJoueur1,
                (nomJoueur2.isEmpty())? JOUEUR_2 :nomJoueur2
        };
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MatchActivity.this);
        mBuilder.setTitle("Premier serveur");
        mBuilder.setItems(listItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(listItems[which].equals(listItems[0])){
                    // Attacher le point jaune au fragment score
                    //dialog.dismiss();
                }
            }
        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // textview.setText(listItem[i]);
                //dialog.dismiss();
            }
        });


        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

        fragmentScore = new FragmentScore();
        fragmentService = new FragmentService();
        fragmentEchange = new FragmentEchange();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.score_layout, fragmentScore)
                .add(R.id.service_layout, fragmentService)
                .add(R.id.echange_layout, fragmentEchange)
                .commit();
/*
        TextView tv_score_name_joueur1 = (TextView)findViewById(R.id.tv_score_nomj1);
        TextView tv_score_name_joueur2 = (TextView)findViewById(R.id.tv_score_nomj2);*/
        /*if(nomJoueur1 != null)
            tv_score_name_joueur1.setText(nomJoueur1);
        if(nomJoueur2 != null)
            tv_score_name_joueur2.setText(nomJoueur2);*/

        if(!nomJoueur1.isEmpty())
            fragmentScore.updateTextViewJoueur1(nomJoueur1);

        if(!nomJoueur2.isEmpty())
            fragmentScore.updateTextViewJoueur2(nomJoueur2);

    }

}
