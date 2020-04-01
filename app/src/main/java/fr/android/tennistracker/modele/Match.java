package fr.android.tennistracker.modele;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private String nomJoueur1;
    private String nomJoueur2;

    private int nbJeuxMax;
    private boolean avecAvantage;
    private int tieBreak;

    private FormatDernierSet formatDernierSet;

    public enum FormatDernierSet {
        SET_NORMAL,
        TIE_BREAK_EN_7_POINTS,
        SUPER_TIE_BREAK_EN_10_POINTS
    }



    public String getNomJoueur1() {
        return nomJoueur1;
    }

    public void setNomJoueur1(String nomJoueur1) {
        this.nomJoueur1 = nomJoueur1;
    }

    public String getNomJoueur2() {
        return nomJoueur2;
    }

    public void setNomJoueur2(String nomJoueur2) {
        this.nomJoueur2 = nomJoueur2;
    }

    public int getNbJeuxMax() {
        return nbJeuxMax;
    }

    public void setNbJeuxMax(int nbJeuxMax) {
        this.nbJeuxMax = nbJeuxMax;
    }

    public boolean isAvecAvantage() {
        return avecAvantage;
    }

    public void setAvecAvantage(boolean avecAvantage) {
        this.avecAvantage = avecAvantage;
    }

    public int getTieBreak() {
        return tieBreak;
    }

    public void setTieBreak(int tieBreak) {
        this.tieBreak = tieBreak;
    }

    public FormatDernierSet getFormatDernierSet() {
        return formatDernierSet;
    }

    public void setFormatDernierSet(FormatDernierSet formatDernierSet) {
        this.formatDernierSet = formatDernierSet;
    }

    public Match(String nomJoueur1, String nomJoueur2, int nbJeuxMax, boolean avecAvantage, int tieBreak) {
        this.nomJoueur1 = nomJoueur1;
        this.nomJoueur2 = nomJoueur2;
        this.nbJeuxMax = nbJeuxMax;
        this.avecAvantage = avecAvantage;
        this.tieBreak = tieBreak;
        formatDernierSet = FormatDernierSet.SET_NORMAL;
    }

    public JSONArray convertToJSONArray(){
        List laList = new ArrayList<>();
        laList.add(nomJoueur1);
        laList.add(nomJoueur2);
        laList.add(nbJeuxMax);
        laList.add(avecAvantage);
        laList.add(tieBreak);
        return new JSONArray(laList);
    }

}
