package fr.android.tennistracker.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

public class AccesLocal {

    private String nomBase = "bdAndroid.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, nomBase, null, versionBase);
    }

    // Ajout d'un match dans la bd
    public void ajout(Match match) {
        bd = accesBD.getWritableDatabase();
        String req = "INSERT INTO match_tennis(nom_joueur1,"
                + "nom_joueur2, pts_gagnes_j1, pts_gagnes_j2, premieres_balles_j1,"
                + "premieres_balles_j2, ace_j1, ace_j2, double_faute_j1, double_faute_j2,"
                + "balles_de_break_j1, balles_de_break_j2, pts_gagnes_premiere_balle_j1,"
                + "pts_gagnes_premiere_balle_j2, balles_de_break_converties_j1, "
                + "balles_de_break_converties_j2, pts_gagnes_deuxieme_balle_j1,"
                + "pts_gagnes_deuxieme_balle_j2, pts_gagnants_j1, pts_gagnants_j2,"
                + "fautes_dir_j1, fautes_dir_j2, fautes_provoq_j1, fautes_provoq_j2,"
                + "nom_vainqueur, date_match) VALUES( "
                + "\"" + match.getNomJoueur1() + "\"" + ","
                + "\"" + match.getNomJoueur2() + "\"" + ","
                + match.getPtsGagnesJ1() + ","
                + match.getPtsGagnesJ2() + ","
                + match.getPremBallesJ1() + ","
                + match.getPremBallesJ2() + ","
                + match.getAce_j1() + ","
                + match.getAce_j2() + ","
                + match.getDouble_faute_j1() + ","
                + match.getDouble_faute_j2() + ","
                + "\"" + match.getBalles_de_break_j1() + "\"" + ","
                + "\"" + match.getBalles_de_break_j2() + "\"" + ","
                + match.getPts_gagnes_premiere_balle_j1() + ","
                + match.getPts_gagnes_premiere_balle_j2() + ","
                + "\"" + match.getBalles_de_break_converties_j1() + "\"" + ","
                + "\"" + match.getBalles_de_break_converties_j2() + "\"" + ","
                + match.getPts_gagnes_deuxieme_balle_j1() + ","
                + match.getPts_gagnes_deuxieme_balle_j2() + ","
                + match.getPts_gagnants_j1() + ","
                + match.getPts_gagnants_j2() + ","
                + match.getFautes_dir_j1() + ","
                + match.getFautes_dir_j2() + ","
                + match.getFautes_provoq_j1() + ","
                + match.getFautes_provoq_j2() + ","
                + "\"" + match.getNom_vainqueur() + "\"" + ","
                + "\"" + match.getDateMatch() + "\"" +  ")";
        bd.execSQL(req);
    }

    public Match recupDernier(){
        bd = accesBD.getReadableDatabase();
        Match match = null;
        String req = "select * from match_tennis";
        Cursor cursor = bd.rawQuery(req, null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            Date date = new Date();
            String nomJoueur1 = cursor.getString(1);
            String nomJoueur2 = cursor.getString(2);
            int pointGagneJ1 = cursor.getInt(3);
            int pointGagneJ2 = cursor.getInt(4);
            int premBalleJ1 = cursor.getInt(5);
            int premBalleJ2 = cursor.getInt(6);
            int aceJ1 = cursor.getInt(7);
            int aceJ2 = cursor.getInt(8);
            int doubleFauteJ1 = cursor.getInt(9);
            int doubleFauteJ2 = cursor.getInt(10);
            String balles_de_break_j1 = cursor.getString(11);
            String balles_de_break_j2 = cursor.getString(12);
            int ptsGagnesPremBalleJ1 = cursor.getInt(13);
            int ptsGagnesPremBalleJ2 = cursor.getInt(14);
            String balles_de_break_converties_j1 = cursor.getString(15);
            String balles_de_break_converties_j2 = cursor.getString(16);
            int ptsGagnesDeuxiemeBallJ1 = cursor.getInt(16);
            int ptsGagnesDeuxiemeBallJ2 = cursor.getInt(17);
            int ptGagnantJ1 = cursor.getInt(18);
            int ptGagnantJ2 = cursor.getInt(19);
            int faute_dirJ1 = cursor.getInt(20);
            int faute_dirJ2 = cursor.getInt(21);
            int fauteprovoqJ1 = cursor.getInt(22);
            int fauteprovoqJ2 = cursor.getInt(23);
            String nomVainqueur = cursor.getString(24);
            match = new Match(nomJoueur1,nomJoueur2,pointGagneJ1,pointGagneJ2,premBalleJ1,premBalleJ2
            ,aceJ1,aceJ2, doubleFauteJ1,doubleFauteJ2,balles_de_break_j1,balles_de_break_j2,ptsGagnesPremBalleJ1
                    ,ptsGagnesPremBalleJ2,balles_de_break_converties_j1,balles_de_break_converties_j2,ptsGagnesDeuxiemeBallJ1,
                    ptsGagnesDeuxiemeBallJ2,ptGagnantJ1,ptGagnantJ2,faute_dirJ1,faute_dirJ2,fauteprovoqJ1,fauteprovoqJ2,nomVainqueur);
        }
        cursor.close();
        return match;
    }

}
