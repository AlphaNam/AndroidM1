package fr.android.tennistracker.modele;

import android.util.Log;

import org.json.JSONArray;

import fr.android.tennistracker.outils.AccesHTTP;
import fr.android.tennistracker.outils.AsyncResponse;

public class AccesDistant implements AsyncResponse {

    private static final String SERVERADDR = "http://192.168.1.23/android_tennis_tracker/serveurandroid.php";

    public AccesDistant(){
        super();
    }

    /**
     * Retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur" , "********************" + output);

        // Découpage du message avec %
        String[] message = output.split("%");

        // Dans message[0]: "enreg", "dernier", "Erreur!"
        // Dans message[1]: reste


        // Si 2 cases
        if(message.length>1){
            if(message[0].equals("enreg")){
                Log.d("enreg", "*************************" + message[1]);
            } else{
                if(message[0].equals("dernier")){
                    // control.setMatch(match);
                    Log.d("enreg", "*************************" + message[1]);
                } else{
                    if(message[0].equals("Erreur !")){
                        Log.d("enreg", "*************************" + message[1]);
                    }
                }
            }
        }
    }

    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();

        // Lien de délégation
        accesDonnees.delegate = this;

        // Ajout parametres
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());

        // Appel au serveur
        accesDonnees.execute(SERVERADDR);
    }

}
