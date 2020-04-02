package fr.android.tennistracker.controleur;

import org.json.JSONArray;

import fr.android.tennistracker.modele.AccesDistant;
import fr.android.tennistracker.modele.Match;

public final class Controleur {

    private static Controleur instance = null;
    public static Match match;
    private static AccesDistant accesDistant;


    private Controleur(){
        super();
    }

    // setMatch(match)
    /**
     * this.match = match
     * ((MatchActivity) context).recupMatch()
     */

    public static final Controleur getInstance(){
        if(Controleur.instance == null){
            Controleur.instance = new Controleur();
            accesDistant = new AccesDistant();
            accesDistant.envoi("dernier", new JSONArray());
        }
        return Controleur.instance;
    }

    public void creerMatch(String nomJoueur1, String nomJoueur2, int ptsGagnesJ1, int ptsGagnesJ2, int premBallesJ1, int premBallesJ2, int ace_j1, int ace_j2,
                           int double_faute_j1, int double_faute_j2, String balles_de_break_j1, String balles_de_break_j2, int pts_gagnes_premiere_balle_j1,
                           int pts_gagnes_premiere_balle_j2, String balles_de_break_converties_j1, String balles_de_break_converties_j2, int pts_gagnes_deuxieme_balle_j1,
                           int pts_gagnes_deuxieme_balle_j2, int pts_gagnants_j1, int pts_gagnants_j2, int fautes_dir_j1, int fautes_dir_j2, int fautes_provoq_j1,
                           int fautes_provoq_j2, String nom_vainqueur){

        match = new Match( nomJoueur1,  nomJoueur2,  ptsGagnesJ1,  ptsGagnesJ2,  premBallesJ1,  premBallesJ2,  ace_j1,
                ace_j2,  double_faute_j1,  double_faute_j2,  balles_de_break_j1,  balles_de_break_j2,  pts_gagnes_premiere_balle_j1,
                pts_gagnes_premiere_balle_j2,  balles_de_break_converties_j1,  balles_de_break_converties_j2,  pts_gagnes_deuxieme_balle_j1,
                pts_gagnes_deuxieme_balle_j2,  pts_gagnants_j1,  pts_gagnants_j2,  fautes_dir_j1,  fautes_dir_j2,  fautes_provoq_j1,  fautes_provoq_j2,
                nom_vainqueur);
        accesDistant.envoi("enreg", match.convertToJSONArray());
    }
}