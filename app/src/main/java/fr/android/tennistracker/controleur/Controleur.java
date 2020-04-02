package fr.android.tennistracker.controleur;

import android.content.Context;

import fr.android.tennistracker.modele.AccesDistant;
import fr.android.tennistracker.modele.AccesLocal;
import fr.android.tennistracker.modele.MatchDAO;

public final class Controleur {

    private static Controleur instance = null;
    public static MatchDAO matchDAO;
    private static AccesDistant accesDistant;
    private static AccesLocal accesLocal;


    private Controleur(){
        super();
    }

    public static final Controleur getInstance(Context context){
        if(Controleur.instance == null){
            Controleur.instance = new Controleur();
            accesLocal = new AccesLocal(context);
            accesDistant = new AccesDistant();

            matchDAO = accesLocal.recupDernier();
        }
        return Controleur.instance;
    }

    public void creerMatchDistant(String nomJoueur1, String nomJoueur2, int ptsGagnesJ1, int ptsGagnesJ2, int premBallesJ1, int premBallesJ2, int ace_j1, int ace_j2,
                                  int double_faute_j1, int double_faute_j2, String balles_de_break_j1, String balles_de_break_j2, int pts_gagnes_premiere_balle_j1,
                                  int pts_gagnes_premiere_balle_j2, String balles_de_break_converties_j1, String balles_de_break_converties_j2, int pts_gagnes_deuxieme_balle_j1,
                                  int pts_gagnes_deuxieme_balle_j2, int pts_gagnants_j1, int pts_gagnants_j2, int fautes_dir_j1, int fautes_dir_j2, int fautes_provoq_j1,
                                  int fautes_provoq_j2, String nom_vainqueur){

        matchDAO = new MatchDAO( nomJoueur1,  nomJoueur2,  ptsGagnesJ1,  ptsGagnesJ2,  premBallesJ1,  premBallesJ2,  ace_j1,
                ace_j2,  double_faute_j1,  double_faute_j2,  balles_de_break_j1,  balles_de_break_j2,  pts_gagnes_premiere_balle_j1,
                pts_gagnes_premiere_balle_j2,  balles_de_break_converties_j1,  balles_de_break_converties_j2,  pts_gagnes_deuxieme_balle_j1,
                pts_gagnes_deuxieme_balle_j2,  pts_gagnants_j1,  pts_gagnants_j2,  fautes_dir_j1,  fautes_dir_j2,  fautes_provoq_j1,  fautes_provoq_j2,
                nom_vainqueur);
        accesDistant.envoi("enreg", matchDAO.convertToJSONArray());
    }

    public void creerMatchLocal(String nomJoueur1, String nomJoueur2, int ptsGagnesJ1, int ptsGagnesJ2, int premBallesJ1, int premBallesJ2, int ace_j1, int ace_j2,
                                  int double_faute_j1, int double_faute_j2, String balles_de_break_j1, String balles_de_break_j2, int pts_gagnes_premiere_balle_j1,
                                  int pts_gagnes_premiere_balle_j2, String balles_de_break_converties_j1, String balles_de_break_converties_j2, int pts_gagnes_deuxieme_balle_j1,
                                  int pts_gagnes_deuxieme_balle_j2, int pts_gagnants_j1, int pts_gagnants_j2, int fautes_dir_j1, int fautes_dir_j2, int fautes_provoq_j1,
                                  int fautes_provoq_j2, String nom_vainqueur){

        matchDAO = new MatchDAO( nomJoueur1,  nomJoueur2,  ptsGagnesJ1,  ptsGagnesJ2,  premBallesJ1,  premBallesJ2,  ace_j1,
                ace_j2,  double_faute_j1,  double_faute_j2,  balles_de_break_j1,  balles_de_break_j2,  pts_gagnes_premiere_balle_j1,
                pts_gagnes_premiere_balle_j2,  balles_de_break_converties_j1,  balles_de_break_converties_j2,  pts_gagnes_deuxieme_balle_j1,
                pts_gagnes_deuxieme_balle_j2,  pts_gagnants_j1,  pts_gagnants_j2,  fautes_dir_j1,  fautes_dir_j2,  fautes_provoq_j1,  fautes_provoq_j2,
                nom_vainqueur);
        accesLocal.ajout(matchDAO);
    }
}
