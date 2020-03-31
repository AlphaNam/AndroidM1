package fr.android.tennistracker.outils;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AccesHTTP extends AsyncTask<String, Integer, Long> {

    private ArrayList<NameValuePair> parameter;
    private String ret = null;
    public AsyncResponse delegate = null;

    /**
     * Constructor
     */
    public AccesHTTP() {
        parameter = new ArrayList<NameValuePair>();
    }

    /**
     * Ajout d'un parametre post
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur){
        parameter.add(new BasicNameValuePair(nom,valeur));

    }

    // Connexion bdd en tâche de fond
    @Override
    protected Long doInBackground(String... strings) {
        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]);


        try {
            // encodage parametres
            paramCnx.setEntity(new UrlEncodedFormEntity(parameter));

            // connexion et envoi de parametre, attente de reponse
            HttpResponse response = cnxHttp.execute(paramCnx);

            // transformation de la réponse
            ret = EntityUtils.toString(response.getEntity());

        } catch (UnsupportedEncodingException e) {
            Log.d("Erreur encodage","*******************************" + e.toString());
        } catch (ClientProtocolException e) {
            Log.d("Erreur procole","*******************************" + e.toString());
        } catch (IOException e) {
            Log.d("Erreur I/O","*******************************" + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result){
        delegate.processFinish(ret.toString());
    }
}
