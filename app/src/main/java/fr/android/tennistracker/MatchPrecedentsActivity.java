package fr.android.tennistracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MatchPrecedentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_precedents);
        ListView matchPrecedentsListe = (ListView) findViewById(R.id.lv_match_precedents);

        ArrayList<String> matchPrecedents  = new ArrayList<>();
        matchPrecedents.add("Joueur 1 vs Joueur 2");
        matchPrecedents.add("Joueur 2 vs Joueur 3");
        matchPrecedents.add("Joueur 3 vs Joueur 4");
        matchPrecedents.add("Joueur 4 vs Joueur 5");
        matchPrecedents.add("Joueur 5 vs Joueur 6");

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_match_precedents,matchPrecedents);
        matchPrecedentsListe.setAdapter(adapter);
    }
}
