package fr.android.tennistracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class NewMatch extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText et_joueur1;
    private EditText et_joueur2;
    private Spinner spinnerFormatMatch;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_demarrer:
                if(et_joueur1.getText().toString().isEmpty() || et_joueur2.getText().toString().isEmpty()){
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(NewMatch.this);
                    mBuilder.setTitle("Erreur de saisie");
                    mBuilder.setMessage("Le nom des joueurs ne peut être vide");
                    mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // textview.setText(listItem[i]);
                            dialog.dismiss();
                        }
                    });
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                }
                else {
                    Intent intent = new Intent(this, MatchActivity.class);
                    EditText et_joueur1 = (EditText) findViewById(R.id.et_joueur1);
                    EditText et_joueur2 = (EditText) findViewById(R.id.et_joueur2);
                    intent.putExtra("NOM_JOUEUR_1", et_joueur1.getText().toString());
                    intent.putExtra("NOM_JOUEUR_2", et_joueur2.getText().toString());
                    int nb_jeux = 6, tie_break =6;
                    boolean avantage = true;
                    switch (spinnerFormatMatch.getSelectedItemPosition()){
                        case 1:
                            nb_jeux = 5;
                            tie_break = 5;
                            break;
                        case 2:
                            nb_jeux = 4;
                            tie_break = 4;
                            break;
                        case 3:
                            nb_jeux = 5;
                            tie_break = 4;
                            avantage = false;
                            break;
                        case 4:
                            nb_jeux = 4;
                            tie_break = 3;
                            avantage = false;
                            break;
                        case 5:
                            nb_jeux = 3;
                            tie_break = 2;
                            avantage = false;
                            break;
                        default:
                            break;
                    }
                    intent.putExtra("NB_JEUX", nb_jeux);
                    intent.putExtra("AVANTAGE", avantage);
                    intent.putExtra("TIE_BREAK", tie_break);

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                break;
            case R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu_terminer);
        item.setVisible(false);
        item = menu.findItem(R.id.menu_annuler);
        item.setVisible(false);
        item = menu.findItem(R.id.menu_statistiques);
        item.setVisible(false);
        item = menu.findItem(R.id.menu_point_J1);
        item.setVisible(false);
        item = menu.findItem(R.id.menu_point_J2);
        item.setVisible(false);
        item = menu.findItem(R.id.menu_saisie_coup);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);

        invalidateOptionsMenu();

        spinnerFormatMatch = (Spinner) findViewById(R.id.spinner_format_match);
        ArrayAdapter<CharSequence> adapterFormatMatch = ArrayAdapter.createFromResource(this, R.array.formats_match, R.layout.support_simple_spinner_dropdown_item);
        adapterFormatMatch.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFormatMatch.setAdapter(adapterFormatMatch);
        spinnerFormatMatch.setOnItemSelectedListener(this);

        Spinner spinnerDernierSet = (Spinner) findViewById(R.id.spinner_dernier_set);
        ArrayAdapter<CharSequence> adapterDernierSet = ArrayAdapter.createFromResource(this, R.array.format_dernier_set, R.layout.support_simple_spinner_dropdown_item);
        adapterDernierSet.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerDernierSet.setAdapter(adapterDernierSet);
        spinnerDernierSet.setOnItemSelectedListener(this);

        Spinner spinnerTypeCoup = (Spinner) findViewById(R.id.spinner_type_coup);
        ArrayAdapter<CharSequence> adapterTypeCoup = ArrayAdapter.createFromResource(this, R.array.type_coup, R.layout.support_simple_spinner_dropdown_item);
        adapterTypeCoup.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTypeCoup.setAdapter(adapterTypeCoup);

        spinnerTypeCoup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Passez à la version PRO !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nouveau match");

        et_joueur1 = (EditText)findViewById(R.id.et_joueur1);
        et_joueur2 = (EditText)findViewById(R.id.et_joueur2);

        et_joueur1.setText("Joueur 1");
        et_joueur2.setText("Joueur 2");


        //

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
