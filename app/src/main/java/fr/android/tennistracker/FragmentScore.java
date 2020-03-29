package fr.android.tennistracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentScore extends Fragment {
    //private FragmentScoreListener listener;

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

    private static final String[] JEU_POSSIBLE = {"0","15","30","40"};
    private static final String AVANTAGE = "AD";

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

    public void init_jeu1(){
        tv_score_set1_j1.setText("0");
        tv_score_set1_j2.setText("0");
    }

    public void updateScore(boolean j1_point) {
        if(j1_point){
            // CAS 0 -> 15
            if(tv_score_jeu_actuel_j1.getText().toString().equals(JEU_POSSIBLE[0])) {
                tv_score_jeu_actuel_j1.setText(JEU_POSSIBLE[1]);
            }
            // CAS 15 -> 30
            else if(tv_score_jeu_actuel_j1.getText().toString().equals(JEU_POSSIBLE[1]))
                tv_score_jeu_actuel_j1.setText(JEU_POSSIBLE[2]);
            // CAS 30 -> 40
            else if(tv_score_jeu_actuel_j1.getText().toString().equals(JEU_POSSIBLE[2])) {
                tv_score_jeu_actuel_j1.setText(JEU_POSSIBLE[3]);
                if(!j1_sert)
                    joueur1_break();
                if(break_en_cours && tv_score_jeu_actuel_j2.getText().toString().equals(JEU_POSSIBLE[3]))
                    joueur1_debreak();
            }
            // CAS 40 -> AD
            else if(tv_score_jeu_actuel_j1.getText().toString().equals(JEU_POSSIBLE[3]) &&
                    (tv_score_jeu_actuel_j2.getText().toString().equals(JEU_POSSIBLE[3]))) {
                tv_score_jeu_actuel_j1.setText(AVANTAGE);
                if(!j1_sert)
                    joueur1_break();
            }
            // CAS AD AVERSAIRE -> 40-40
            else if(tv_score_jeu_actuel_j2.getText().toString().equals(AVANTAGE)){
                tv_score_jeu_actuel_j2.setText(JEU_POSSIBLE[3]);
                tv_score_jeu_actuel_j1.setText(JEU_POSSIBLE[3]);
            }
            // CAS JEU GAGNE
            else {
                int i = Integer.parseInt(tv_score_set1_j1.getText().toString());
                tv_score_set1_j1.setText(Integer.toString(++i));
                tv_score_jeu_actuel_j1.setText(JEU_POSSIBLE[0]);
                tv_score_jeu_actuel_j2.setText(JEU_POSSIBLE[0]);
                finJeu = true;
            }
        }
        else{
            // CAS 0 -> 15
            if(tv_score_jeu_actuel_j2.getText().toString().equals(JEU_POSSIBLE[0]))
                tv_score_jeu_actuel_j2.setText(JEU_POSSIBLE[1]);
            // CAS 15 -> 30
            else if(tv_score_jeu_actuel_j2.getText().toString().equals(JEU_POSSIBLE[1]))
                tv_score_jeu_actuel_j2.setText(JEU_POSSIBLE[2]);
            // CAS 30 -> 40
            else if(tv_score_jeu_actuel_j2.getText().toString().equals(JEU_POSSIBLE[2])) {
                tv_score_jeu_actuel_j2.setText(JEU_POSSIBLE[3]);
                if(j1_sert)
                    joueur2_break();
            }
            // CAS 40 -> AD
            else if(tv_score_jeu_actuel_j1.getText().toString().equals(JEU_POSSIBLE[3]) &&
                    (tv_score_jeu_actuel_j2.getText().toString().equals(JEU_POSSIBLE[3]))) {
                tv_score_jeu_actuel_j2.setText(AVANTAGE);
                if(j1_sert)
                    joueur2_break();
            }
            // CAS EGALITE
            else if(tv_score_jeu_actuel_j1.getText().toString().equals(AVANTAGE)){
                tv_score_jeu_actuel_j2.setText(JEU_POSSIBLE[3]);
                tv_score_jeu_actuel_j1.setText(JEU_POSSIBLE[3]);
            }
            // CAS JEU GAGNE
            else {
                int i = Integer.parseInt(tv_score_set1_j2.getText().toString());
                tv_score_set1_j2.setText(Integer.toString(++i));
                tv_score_jeu_actuel_j1.setText(JEU_POSSIBLE[0]);
                tv_score_jeu_actuel_j2.setText(JEU_POSSIBLE[0]);
                finJeu = true;
            }
        }
    }

    public boolean finJeu() {
        return finJeu;
    }

    public interface FragmentScoreListener{
        void onInputASent(boolean choice);
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

    public void joueur1_break(){
        tv_nom_joueur2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_red_24dp, 0);
        break_en_cours = true;
    }

    public void joueur2_break(){
        tv_nom_joueur1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_red_24dp, 0);
        break_en_cours = true;
    }

    private void joueur1_debreak(){
        tv_nom_joueur1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_yellow_24dp, 0);
        break_en_cours = false;
    }
}
