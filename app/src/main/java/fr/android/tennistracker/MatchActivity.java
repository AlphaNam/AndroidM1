package fr.android.tennistracker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

public class MatchActivity extends AppCompatActivity implements FragmentScore.FragmentScoreListener {
    private FragmentScore fragmentScore;
    private FragmentService fragmentService;
    private FragmentEchange fragmentEchange;

    private TextView tv_serveur;

    private static final String ACTIVITY_TITLE = "Enregistrement";
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
        tv_serveur = new TextView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomJoueur1 = extras.getString("NOM_JOUEUR_1");
            nomJoueur2 = extras.getString("NOM_JOUEUR_2");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(ACTIVITY_TITLE);
        invalidateOptionsMenu();

        final String[] listItems = new String[]{
                (nomJoueur1.isEmpty()) ? JOUEUR_1 : nomJoueur1,
                (nomJoueur2.isEmpty()) ? JOUEUR_2 : nomJoueur2
        };

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MatchActivity.this);
        mBuilder.setTitle("Premier serveur");
        mBuilder.setItems(listItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0)
                    onInputASent(true);
                else
                    onInputASent(false);
            }
        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);

        fragmentScore = new FragmentScore();
        fragmentService = new FragmentService();
        fragmentEchange = new FragmentEchange();

        Bundle bundle = new Bundle();
        bundle.putString("NOM_JOUEUR_1", nomJoueur1);
        bundle.putString("NOM_JOUEUR_2", nomJoueur2);

        fragmentScore.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.score_layout, fragmentScore)
                .add(R.id.service_layout, fragmentService)
                .add(R.id.echange_layout, fragmentEchange)
                .commit();
    }

    @Override
    public void onInputASent(boolean choice) {
        fragmentScore.setServer(choice);
    }
}
