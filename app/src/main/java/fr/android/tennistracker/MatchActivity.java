package fr.android.tennistracker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

public class MatchActivity extends AppCompatActivity {
    private FragmentScore fragmentScore;
    private FragmentService fragmentService;
    private FragmentEchange fragmentEchange;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Enregistrement");
        // ?
        invalidateOptionsMenu();
        // ?
        //final Integer choice;
        String[] listItems = new String[]{"Joueur 1", "Joueur 2"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MatchActivity.this);
        mBuilder.setTitle("Premier serveur");
        mBuilder.setItems(listItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // textview.setText(listItem[i]);
                dialog.dismiss();
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

    }

}
