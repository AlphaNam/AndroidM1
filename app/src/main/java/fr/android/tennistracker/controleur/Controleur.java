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

    public void creerMatch(String nomJoueur1, String nomJoueur2, int nbJeuxMax, boolean avecAvantage, int tieBreak){
        match = new Match(nomJoueur1, nomJoueur2, nbJeuxMax, avecAvantage, tieBreak);
        accesDistant.envoi("enreg", match.convertToJSONArray());
    }
}
