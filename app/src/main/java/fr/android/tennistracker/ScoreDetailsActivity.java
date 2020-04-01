package fr.android.tennistracker;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import fr.android.tennistracker.ui.main.SectionsPagerAdapter;

public class ScoreDetailsActivity extends AppCompatActivity {

   // private FragmentTableauScorePlusDetailles fragmentTableauScorePlusDetailles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_details);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        HashMap<String, String> infosMatch = null;
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            infosMatch = (HashMap<String,String>)bundle.getSerializable("infos_match");
        }


       /* fragmentTableauScorePlusDetailles = new FragmentTableauScorePlusDetailles();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.lt_tableau_detailles,fragmentTableauScorePlusDetailles)
                .commit();*/
    }

}