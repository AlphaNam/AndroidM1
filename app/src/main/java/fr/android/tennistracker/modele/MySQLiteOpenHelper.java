package fr.android.tennistracker.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private String creation = "CREATE TABLE IF NOT EXISTS match_tennis (" +
            "  match_id INT PRIMARY KEY," +
            "  nom_joueur1 varchar(20) DEFAULT NULL," +
            "  nom_joueur2 varchar(20) DEFAULT NULL," +
            "  pts_gagnes_j1 int(4) DEFAULT NULL, " +
            "  pts_gagnes_j2 int(4) DEFAULT NULL, " +
            "  premieres_balles_j1 int(4) DEFAULT NULL, " +
            "  premieres_balles_j2 int(4) DEFAULT NULL, " +
            "  ace_j1 int(4) DEFAULT NULL, " +
            "  ace_j2 int(4) DEFAULT NULL, " +
            "  double_faute_j1 int(4) DEFAULT NULL, " +
            "  double_faute_j2 int(4) DEFAULT NULL, " +
            "  balles_de_break_j1 varchar(10) DEFAULT NULL, " +
            "  balles_de_break_j2 varchar(10) DEFAULT NULL, " +
            "  pts_gagnes_premiere_balle_j1 int(4) DEFAULT NULL, " +
            "  pts_gagnes_premiere_balle_j2 int(4) DEFAULT NULL, " +
            "  balles_de_break_converties_j1 varchar(10) DEFAULT NULL, " +
            "  balles_de_break_converties_j2 varchar(10) DEFAULT NULL, " +
            "  pts_gagnes_deuxieme_balle_j1 int(4) DEFAULT NULL, " +
            "  pts_gagnes_deuxieme_balle_j2 int(4) DEFAULT NULL, " +
            "  pts_gagnants_j1 int(4) DEFAULT NULL, " +
            "  pts_gagnants_j2 int(4) DEFAULT NULL, " +
            "  fautes_dir_j1 int(4) DEFAULT NULL, " +
            "  fautes_dir_j2 int(4) DEFAULT NULL, " +
            "  fautes_provoq_j1 int(4) DEFAULT NULL, " +
            "  fautes_provoq_j2 int(4) DEFAULT NULL, " +
            "  nom_vainqueur varchar(20) DEFAULT NULL, " +
            "  date_match date DEFAULT NULL " +
            ");"
            ;



    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
