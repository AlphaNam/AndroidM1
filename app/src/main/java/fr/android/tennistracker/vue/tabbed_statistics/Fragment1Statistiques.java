package fr.android.tennistracker.vue.tabbed_statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;

import fr.android.tennistracker.R;

public class Fragment1Statistiques extends Fragment {
    private TextView tv_joueur1;
    private TextView tv_joueur2;
    private TextView tv_score_jeu1_j1;
    private TextView tv_score_jeu2_j1;
    private TextView tv_score_jeu3_j1;
    private TextView tv_score_jeu1_j2;
    private TextView tv_score_jeu2_j2;
    private TextView tv_score_jeu3_j2;

    private FragmentTableauScorePlusDetailles fragmentTableauScorePlusDetailles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1_statistiques, container, false);
        tv_joueur1 = (TextView)view.findViewById(R.id.tv_score_nomj1);
        tv_joueur2 = (TextView)view.findViewById(R.id.tv_score_nomj2);

        tv_score_jeu1_j1 = (TextView)view.findViewById(R.id.tv_score_jeu1_j1);
        tv_score_jeu2_j1 = (TextView)view.findViewById(R.id.tv_score_jeu2_j1);
        tv_score_jeu3_j1 = (TextView)view.findViewById(R.id.tv_score_jeu3_j1);
        tv_score_jeu1_j2 = (TextView)view.findViewById(R.id.tv_score_jeu1_j2);
        tv_score_jeu2_j2 = (TextView)view.findViewById(R.id.tv_score_jeu2_j2);
        tv_score_jeu3_j2 = (TextView)view.findViewById(R.id.tv_score_jeu3_j2);


        HashMap<String, String> infosMatch = (HashMap<String , String>) getArguments().getSerializable("infos_match");

        String nom_joueur1 = infosMatch.get("nom_joueur_1");
        String nom_joueur2 = infosMatch.get("nom_joueur_2");
        String set1_j1 = infosMatch.get("score_set1_j1");
        String set2_j1 = infosMatch.get("score_set2_j1");
        String set3_j1 = infosMatch.get("score_set3_j1");
        String set1_j2 = infosMatch.get("score_set1_j2");
        String set2_j2 = infosMatch.get("score_set2_j2");
        String set3_j2 = infosMatch.get("score_set3_j2");


        tv_joueur1.setText(nom_joueur1);
        tv_joueur2.setText(nom_joueur2);

        tv_score_jeu1_j1.setText(set1_j1);
        tv_score_jeu2_j1.setText(set2_j1);
        tv_score_jeu3_j1.setText(set3_j1);
        tv_score_jeu1_j2.setText(set1_j2);
        tv_score_jeu2_j2.setText(set2_j2);
        tv_score_jeu3_j2.setText(set3_j2);

        fragmentTableauScorePlusDetailles = new FragmentTableauScorePlusDetailles();

        Bundle bundle = getArguments();
        fragmentTableauScorePlusDetailles.setArguments(bundle);

        ScoreDetailsActivity mContext = (ScoreDetailsActivity)getContext();
        FragmentManager fragmentManager = mContext.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frame_layout_score_details, fragmentTableauScorePlusDetailles)
                .commit();

        return view;
    }
}
