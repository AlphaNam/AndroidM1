package fr.android.tennistracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class NewMatch extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_demarrer:
                Intent intent = new Intent(this, MatchActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

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

        /*MenuItem itemTerminer = (MenuItem)findViewById(R.id.menu_terminer);
        itemTerminer.setVisible(false);*/

        //getSupportActionBar().
        //invalidateOptionsMenu();

        invalidateOptionsMenu();


        Spinner spinnerFormatMatch = (Spinner) findViewById(R.id.spinner_format_match);
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
        /*spinnerTypeCoup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Passez à la version PRO !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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


                /*new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Passez à la version PRO !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nouveau match");


        //

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
