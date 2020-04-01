package fr.android.tennistracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;


public class FragmentTableauScorePlusDetailles extends Fragment {
    private TextView tv_stat_general_j1, tv_stat_general_j2, tv_point_gagnes_j1, tv_point_gagnes_j2, tv_stat_service_j1, tv_stat_service_j2;
    private TextView tv_1ere_balles_j1, tv_1ere_balles_j2, tv_point_aces_j1, tv_point_aces_j2, tv_double_fautes_j1, tv_double_fautes_j2;
    private TextView tv_balles_de_break_sauvees_j1, tv_balles_de_break_sauvees_j2, tv_pt_gagnes_sur_1ere_balle_j1, tv_pt_gagnes_sur_1ere_balle_j2;
    private TextView tv_stat_retour_j1, tv_stat_retour_j2, tv_balles_de_break_converties_j1, tv_balles_de_break_converties_j2;
    private TextView tv_pt_gagnes_sur_2eme_balle_adversaire_j1, tv_pt_gagnes_sur_2eme_balle_adversaire_j2, tv_stat_points_j1, tv_stat_points_j2;
    private TextView tv_point_gagnants_j1, tv_point_gagnants_j2, tv_point_directes_j1, tv_point_directes_j2, tv_point_provoquees_j1, tv_point_provoquees_j2;


    public FragmentTableauScorePlusDetailles() {
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
        View view = inflater.inflate(R.layout.fragment_tableau_score_plus_detailles, container, false);
        tv_stat_general_j1 = view.findViewById(R.id.tv_stat_general_j1);
        tv_stat_general_j2 = view.findViewById(R.id.tv_stat_general_j2);
        tv_point_gagnes_j1 = view.findViewById(R.id.tv_point_gagnes_j1);
        tv_point_gagnes_j2 = view.findViewById(R.id.tv_point_gagnes_j2);
        tv_stat_service_j1 = view.findViewById(R.id.tv_stat_service_j1);
        tv_stat_service_j2 = view.findViewById(R.id.tv_stat_service_j2);
        tv_1ere_balles_j1 = view.findViewById(R.id.tv_1ere_balles_j1);
        tv_1ere_balles_j2 = view.findViewById(R.id.tv_1ere_balles_j2);
        tv_point_aces_j1 = view.findViewById(R.id.tv_point_aces_j1);
        tv_point_aces_j2 = view.findViewById(R.id.tv_point_aces_j2);
        tv_double_fautes_j1 = view.findViewById(R.id.tv_double_fautes_j1);
        tv_double_fautes_j2 = view.findViewById(R.id.tv_double_fautes_j2);
        tv_balles_de_break_sauvees_j1 = view.findViewById(R.id.tv_balles_de_break_sauvees_j1);
        tv_balles_de_break_sauvees_j2 = view.findViewById(R.id.tv_balles_de_break_sauvees_j2);
        tv_pt_gagnes_sur_1ere_balle_j1 = view.findViewById(R.id.tv_pt_gagnes_sur_1ere_balle_j1);
        tv_pt_gagnes_sur_1ere_balle_j2 = view.findViewById(R.id.tv_pt_gagnes_sur_1ere_balle_j2);
        tv_stat_retour_j1 = view.findViewById(R.id.tv_stat_retour_j1);
        tv_stat_retour_j2 = view.findViewById(R.id.tv_stat_retour_j2);
        tv_balles_de_break_converties_j1 = view.findViewById(R.id.tv_balles_de_break_converties_j1);
        tv_balles_de_break_converties_j2 = view.findViewById(R.id.tv_balles_de_break_converties_j2);
        tv_pt_gagnes_sur_2eme_balle_adversaire_j1 = view.findViewById(R.id.tv_pt_gagnes_sur_2eme_balle_adversaire_j1);
        tv_pt_gagnes_sur_2eme_balle_adversaire_j2 = view.findViewById(R.id.tv_pt_gagnes_sur_2eme_balle_adversaire_j2);
        tv_stat_points_j1 = view.findViewById(R.id.tv_stat_points_j1);
        tv_stat_points_j2 = view.findViewById(R.id.tv_stat_points_j2);
        tv_point_gagnants_j1 = view.findViewById(R.id.tv_point_gagnants_j1);
        tv_point_gagnants_j2 = view.findViewById(R.id.tv_point_gagnants_j2);
        tv_point_directes_j1 = view.findViewById(R.id.tv_point_directes_j1);
        tv_point_directes_j2 = view.findViewById(R.id.tv_point_directes_j2);
        tv_point_provoquees_j1 = view.findViewById(R.id.tv_point_provoquees_j1);
        tv_point_provoquees_j2 = view.findViewById(R.id.tv_point_provoquees_j2);

        HashMap<String, String> infosMatch = (HashMap<String , String>) getArguments().getSerializable("infos_match");

        String nomJoueur1, nomJoueur2, cpt_ace_j1, cpt_ace_j2, double_faute_j1, double_faute_j2, cpt_faute_directe_j1, cpt_faute_directe_j2;
        String cpt_faute_provoquee_j1, cpt_faute_provoquee_j2, cpt_point_gagnant_j1, cpt_point_gagnant_j2, cpt_point_gagne_j1, cpt_point_gagne_j2;


        nomJoueur1 = infosMatch.get("nom_joueur_1");
        nomJoueur2 = infosMatch.get("nom_joueur_2");

        cpt_ace_j1 = infosMatch.get("cpt_ace_j1");
        cpt_ace_j2 = infosMatch.get("cpt_ace_j2");

        double_faute_j1 = infosMatch.get("double_faute_j1");
        double_faute_j2 = infosMatch.get("double_faute_j2");

        cpt_faute_directe_j1 = infosMatch.get("cpt_faute_directe_j1");
        cpt_faute_directe_j2 = infosMatch.get("cpt_faute_directe_j2");

        cpt_faute_provoquee_j1 = infosMatch.get("cpt_faute_provoquee_j1");
        cpt_faute_provoquee_j2 = infosMatch.get("cpt_faute_provoquee_j2");

        cpt_point_gagnant_j1 = infosMatch.get("cpt_point_gagnant_j1");
        cpt_point_gagnant_j2 = infosMatch.get("cpt_point_gagnant_j2");

        cpt_point_gagne_j1 = infosMatch.get("cpt_point_gagne_j1");
        cpt_point_gagne_j2 = infosMatch.get("cpt_point_gagne_j2");

        tv_stat_general_j1.setText(nomJoueur1);
        tv_stat_service_j1.setText(nomJoueur1);
        tv_stat_retour_j1.setText(nomJoueur1);
        tv_stat_points_j1.setText(nomJoueur1);

        tv_stat_general_j2.setText(nomJoueur2);
        tv_stat_service_j2.setText(nomJoueur2);
        tv_stat_retour_j2.setText(nomJoueur2);
        tv_stat_points_j2.setText(nomJoueur2);

        tv_point_gagnes_j1.setText(cpt_point_gagne_j1);
        tv_point_gagnes_j2.setText(cpt_point_gagne_j2);

        tv_point_aces_j1.setText(cpt_ace_j1);
        tv_point_aces_j2.setText(cpt_ace_j2);

        tv_double_fautes_j1.setText(double_faute_j1);
        tv_double_fautes_j2.setText(double_faute_j2);

        tv_point_gagnants_j1.setText(cpt_point_gagnant_j1);
        tv_point_gagnants_j2.setText(cpt_point_gagnant_j2);

        tv_point_directes_j1.setText(cpt_faute_directe_j1);
        tv_point_directes_j2.setText(cpt_faute_directe_j2);

        tv_point_provoquees_j1.setText(cpt_faute_provoquee_j1);
        tv_point_provoquees_j2.setText(cpt_faute_provoquee_j2);

        String defaultString = Integer.toString(0);
        tv_1ere_balles_j1.setText(defaultString + "%");
        tv_1ere_balles_j2.setText(defaultString + "%");

        tv_balles_de_break_sauvees_j1.setText(defaultString + "/" + defaultString);
        tv_balles_de_break_sauvees_j2.setText(defaultString + "/" + defaultString);

        tv_pt_gagnes_sur_1ere_balle_j1.setText(defaultString + "%");
        tv_pt_gagnes_sur_1ere_balle_j2.setText(defaultString + "%");

        tv_balles_de_break_converties_j1.setText(defaultString + "/" + defaultString);
        tv_balles_de_break_converties_j2.setText(defaultString + "/" + defaultString);

        tv_pt_gagnes_sur_2eme_balle_adversaire_j1.setText(defaultString + "%");
        tv_pt_gagnes_sur_2eme_balle_adversaire_j2.setText(defaultString + "%");


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
