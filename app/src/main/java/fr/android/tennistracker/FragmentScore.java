package fr.android.tennistracker;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentScore extends Fragment {
    private boolean finJeu;
    private TextView tv_nom_joueur1;
    private TextView tv_nom_joueur2;
    private TextView tv_score_jeu_actuel_j1;
    private TextView tv_score_jeu_actuel_j2;

    private TextView tv_score_set1_j1;
    private TextView tv_score_set1_j2;

    private TextView tv_score_set2_j1;
    private TextView tv_score_set2_j2;

    private TextView tv_score_set3_j1;
    private TextView tv_score_set3_j2;

    private boolean j1_sert;
    private boolean break_en_cours;

    private static final String CHAINE_JEU_VALEUR_0 = "0";
    private static final String CHAINE_JEU_VALEUR_15 = "15";
    private static final String CHAINE_JEU_VALEUR_30 = "30";
    private static final String CHAINE_JEU_VALEUR_40 = "40";
    private static final String AVANTAGE = "AD";

    private static final int JEU_VALEUR_0 = 0;
    private static final int JEU_VALEUR_15 = 15;
    private static final int JEU_VALEUR_30 = 30;
    private static final int JEU_VALEUR_40 = 40;
    private static final int JEU_VALEUR_AD = 50;

    private int nb_jeux_max;
    private boolean avantage;
    private int tie_break;

    private int score_jeu_actuel_j1;
    private int score_jeu_actuel_j2;
    private int score_set_actuel_j1;
    private int score_set_actuel_j2;
    private int set_en_cours;

    private int set_gagne_j1;
    private int set_gagne_j2;

    private boolean match_gagne;

    public void setServer(boolean joueur1_sert) {
        if(joueur1_sert){
            tv_nom_joueur1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_yellow_24dp, 0);
            tv_nom_joueur2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_black_24dp, 0);
        }
        else{
            tv_nom_joueur1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_black_24dp, 0);
            tv_nom_joueur2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_yellow_24dp, 0);
        }
        j1_sert = joueur1_sert;
    }

    public void init_set1(){
        tv_score_set1_j1.setText(CHAINE_JEU_VALEUR_0);
        tv_score_set1_j2.setText(CHAINE_JEU_VALEUR_0);
        set_en_cours = 1;
        resetJeuActuel();
        resetSetActuel();
        match_gagne = false;
    }

    private void init_set2(){
        tv_score_set2_j1.setText(CHAINE_JEU_VALEUR_0);
        tv_score_set2_j2.setText(CHAINE_JEU_VALEUR_0);
        resetSetActuel();
    }

    private void init_set3(){
        tv_score_set3_j1.setText(CHAINE_JEU_VALEUR_0);
        tv_score_set3_j2.setText(CHAINE_JEU_VALEUR_0);
        resetSetActuel();
    }


    public void updateScore(boolean j1_point) {
        if(!match_gagne)
            updateScoreSet1(j1_point);
    }

    public boolean finJeu() {
        return finJeu;
    }

    public interface FragmentScoreListener{
        void onInputASent(boolean joueur1_sert);
    }

    public FragmentScore() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_score, container, false);

        tv_nom_joueur1 = (TextView)view.findViewById(R.id.tv_score_nomj1);
        tv_nom_joueur2 = (TextView)view.findViewById(R.id.tv_score_nomj2);

        tv_score_jeu_actuel_j1 = (TextView)view.findViewById(R.id.tv_score_jeu_actuel_j1);
        tv_score_jeu_actuel_j2 = (TextView)view.findViewById(R.id.tv_score_jeu_actuel_j2);

        tv_score_set1_j1 = (TextView)view.findViewById(R.id.tv_score_jeu1_j1);
        tv_score_set1_j2 = (TextView)view.findViewById(R.id.tv_score_jeu1_j2);

        tv_score_set2_j1 = (TextView)view.findViewById(R.id.tv_score_jeu2_j1);
        tv_score_set2_j2 = (TextView)view.findViewById(R.id.tv_score_jeu2_j2);

        tv_score_set3_j1 = (TextView)view.findViewById(R.id.tv_score_jeu3_j1);
        tv_score_set3_j2 = (TextView)view.findViewById(R.id.tv_score_jeu3_j2);

        tv_nom_joueur1.setText(getArguments().getString("NOM_JOUEUR_1"));
        tv_nom_joueur2.setText(getArguments().getString("NOM_JOUEUR_2"));

        nb_jeux_max = getArguments().getInt("NB_JEUX");
        tie_break = getArguments().getInt("TIE_BREAK");
        avantage = getArguments().getBoolean("AVANTAGE");

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof  FragmentScoreListener){
            listener = (FragmentScoreListener)context;
        } else {
            throw new RuntimeException((context.toString())
                    + "must implement FragmentScoreListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //listener = null;
    }

    public void resetJeu(){
        finJeu = false;
    }

    private void joueur1_break(){
        tv_nom_joueur2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_red_24dp, 0);
        break_en_cours = true;
    }

    private void joueur2_break(){
        tv_nom_joueur1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_red_24dp, 0);
        break_en_cours = true;
    }

    private void joueur1_debreak(){
        tv_nom_joueur1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_yellow_24dp, 0);
        break_en_cours = false;
    }
    private void joueur2_debreak(){
        tv_nom_joueur2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_yellow_24dp, 0);
        break_en_cours = false;
    }

    private void j1RemporteJeu(){
        score_set_actuel_j1++;
        if(set_en_cours == 1)
            tv_score_set1_j1.setText(Integer.toString(score_set_actuel_j1));
        if(set_en_cours == 2)
            tv_score_set2_j1.setText(Integer.toString(score_set_actuel_j1));
        if(set_en_cours == 3)
            tv_score_set3_j1.setText(Integer.toString(score_set_actuel_j1));

        // CAS J1 REMPORTE LE SET SANS TB
        if(score_set_actuel_j1 == nb_jeux_max && score_set_actuel_j1 != score_set_actuel_j2) {
            set_en_cours++;
            set_gagne_j1++;
            if(set_en_cours == 2)
                init_set2();
            if(set_en_cours == 3 && set_gagne_j1 == set_gagne_j2)
                init_set3();
            if(set_gagne_j1 == 2){
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setMessage(tv_nom_joueur1.getText().toString() + " a remporté le match !");
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
                match_gagne = true;
            }
        }

        resetJeuActuel();
        finJeu = true;
    }

    private void j2RemporteJeu(){
        score_set_actuel_j2++;
        if(set_en_cours == 1)
            tv_score_set1_j2.setText(Integer.toString(score_set_actuel_j2));
        if(set_en_cours == 2)
            tv_score_set2_j2.setText(Integer.toString(score_set_actuel_j2));
        if(set_en_cours == 3)
            tv_score_set3_j2.setText(Integer.toString(score_set_actuel_j2));

        // CAS J2 REMPORTE LE SET
        if(score_set_actuel_j2 == nb_jeux_max && score_set_actuel_j1 != score_set_actuel_j2) {
            set_en_cours++;
            set_gagne_j2++;
            if(set_en_cours == 2)
                init_set2();
            if(set_en_cours == 3 && set_gagne_j1 == set_gagne_j2)
                init_set3();
            if(set_gagne_j2 == 2){
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setMessage(tv_nom_joueur2.getText().toString() + " a remporté le match !");
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
                match_gagne = true;
            }
        }
        resetJeuActuel();
        finJeu = true;


    }

    private void resetJeuActuel(){
        tv_score_jeu_actuel_j1.setText(CHAINE_JEU_VALEUR_0);
        tv_score_jeu_actuel_j2.setText(CHAINE_JEU_VALEUR_0);

        score_jeu_actuel_j1 = JEU_VALEUR_0;
        score_jeu_actuel_j2 = JEU_VALEUR_0;
    }

    private void resetSetActuel(){
        score_set_actuel_j1 = 0;
        score_set_actuel_j2 = 0;
    }

    private void updateScoreSet1(boolean j1_point){
        if(j1_point)
            updateJeuActuel_J1();
        else
            updateJeuActuel_J2();
    }

    private void updateJeuActuel_J2() {
        // CAS 0 -> 15
        if(score_jeu_actuel_j2 == JEU_VALEUR_0) {
            score_jeu_actuel_j2 = JEU_VALEUR_15;
            tv_score_jeu_actuel_j2.setText(CHAINE_JEU_VALEUR_15);
        }
        // CAS 15 -> 30
        else if(score_jeu_actuel_j2 == JEU_VALEUR_15){
            score_jeu_actuel_j2 = JEU_VALEUR_30;
            tv_score_jeu_actuel_j2.setText(CHAINE_JEU_VALEUR_30);
        }
        // CAS 30 -> 40
        else if(score_jeu_actuel_j2 == JEU_VALEUR_30) {
            score_jeu_actuel_j2 = JEU_VALEUR_40;
            tv_score_jeu_actuel_j2.setText(CHAINE_JEU_VALEUR_40);
            if(j1_sert && !(score_jeu_actuel_j1 == JEU_VALEUR_40 ||
                    score_jeu_actuel_j1 == JEU_VALEUR_AD))
                joueur2_break();
            if(break_en_cours && score_jeu_actuel_j1 == JEU_VALEUR_40 && !j1_sert)
                joueur2_debreak();
        }
        // CAS 40 -> AD
        else if(score_jeu_actuel_j1 == JEU_VALEUR_40 &&
                (score_jeu_actuel_j2 == JEU_VALEUR_40)) {
            score_jeu_actuel_j2 = JEU_VALEUR_AD;
            tv_score_jeu_actuel_j2.setText(AVANTAGE);
            if(j1_sert)
                joueur2_break();
        }
        // CAS EGALITE
        else if(score_jeu_actuel_j1 == JEU_VALEUR_AD){
            egaliteJeuActuel();
            if(break_en_cours)
                joueur2_debreak();
        }
        // CAS JEU GAGNE
        else {
            j2RemporteJeu();
        }
    }

    private void updateJeuActuel_J1() {
        // CAS 0 -> 15
        if(score_jeu_actuel_j1 == JEU_VALEUR_0) {
            score_jeu_actuel_j1 = JEU_VALEUR_15;
            tv_score_jeu_actuel_j1.setText(CHAINE_JEU_VALEUR_15);
        }

        // CAS 15 -> 30
        else if(score_jeu_actuel_j1 == JEU_VALEUR_15) {
            score_jeu_actuel_j1 = JEU_VALEUR_30;
            tv_score_jeu_actuel_j1.setText(CHAINE_JEU_VALEUR_30);
        }
        // CAS 30 -> 40
        else if(score_jeu_actuel_j1 == JEU_VALEUR_30) {
            score_jeu_actuel_j1= JEU_VALEUR_40;
            tv_score_jeu_actuel_j1.setText(CHAINE_JEU_VALEUR_40);

            if(!j1_sert  && !(score_jeu_actuel_j2 == (JEU_VALEUR_40) ||
                    score_jeu_actuel_j2 == JEU_VALEUR_AD))
                joueur1_break();
            if(break_en_cours && score_jeu_actuel_j2 == JEU_VALEUR_40 && j1_sert)
                joueur1_debreak();
        }
        // CAS 40 -> AD
        else if(score_jeu_actuel_j1 == JEU_VALEUR_40 && (score_jeu_actuel_j2 == JEU_VALEUR_40)) {
            score_jeu_actuel_j1 = JEU_VALEUR_AD;
            tv_score_jeu_actuel_j1.setText(AVANTAGE);
            if(!j1_sert)
                joueur1_break();
        }
        // CAS AD AVERSAIRE -> 40-40
        else if(score_jeu_actuel_j2 == JEU_VALEUR_AD){
            egaliteJeuActuel();
            if(break_en_cours)
                joueur1_debreak();
        }
        // CAS JEU GAGNE
        else {
            j1RemporteJeu();
        }
    }

    private void egaliteJeuActuel(){
        tv_score_jeu_actuel_j2.setText(CHAINE_JEU_VALEUR_40);
        tv_score_jeu_actuel_j1.setText(CHAINE_JEU_VALEUR_40);
        score_jeu_actuel_j2 = JEU_VALEUR_40;
        score_jeu_actuel_j1 = JEU_VALEUR_40;
    }
}
