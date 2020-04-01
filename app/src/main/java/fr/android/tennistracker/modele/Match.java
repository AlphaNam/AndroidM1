package fr.android.tennistracker.modele;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private int id_match;
    private String nomJoueur1, nomJoueur2;
    private int ptsGagnesJ1, ptsGagnesJ2;
    private int premBallesJ1, premBallesJ2;
    private int ace_j1, ace_j2;
    private int double_faute_j1, double_faute_j2;
    private String balles_de_break_j1 , balles_de_break_j2;
    private int pts_gagnes_premiere_balle_j1, pts_gagnes_premiere_balle_j2;
    private String balles_de_break_converties_j1, balles_de_break_converties_j2;
    private int pts_gagnes_deuxieme_balle_j1, pts_gagnes_deuxieme_balle_j2;
    private int pts_gagnants_j1, pts_gagnants_j2;
    private int fautes_dir_j1, fautes_dir_j2;
    private int fautes_provoq_j1, fautes_provoq_j2;
    private String nom_vainqueur;
    private String dateMatch;

    public Match(String nomJoueur1, String nomJoueur2, int ptsGagnesJ1, int ptsGagnesJ2, int premBallesJ1, int premBallesJ2, int ace_j1, int ace_j2, int double_faute_j1, int double_faute_j2, String balles_de_break_j1, String balles_de_break_j2, int pts_gagnes_premiere_balle_j1, int pts_gagnes_premiere_balle_j2, String balles_de_break_converties_j1, String balles_de_break_converties_j2, int pts_gagnes_deuxieme_balle_j1, int pts_gagnes_deuxieme_balle_j2, int pts_gagnants_j1, int pts_gagnants_j2, int fautes_dir_j1, int fautes_dir_j2, int fautes_provoq_j1, int fautes_provoq_j2, String nom_vainqueur) {
        this.nomJoueur1 = nomJoueur1;
        this.nomJoueur2 = nomJoueur2;
        this.ptsGagnesJ1 = ptsGagnesJ1;
        this.ptsGagnesJ2 = ptsGagnesJ2;
        this.premBallesJ1 = premBallesJ1;
        this.premBallesJ2 = premBallesJ2;
        this.ace_j1 = ace_j1;
        this.ace_j2 = ace_j2;
        this.double_faute_j1 = double_faute_j1;
        this.double_faute_j2 = double_faute_j2;
        this.balles_de_break_j1 = balles_de_break_j1;
        this.balles_de_break_j2 = balles_de_break_j2;
        this.pts_gagnes_premiere_balle_j1 = pts_gagnes_premiere_balle_j1;
        this.pts_gagnes_premiere_balle_j2 = pts_gagnes_premiere_balle_j2;
        this.balles_de_break_converties_j1 = balles_de_break_converties_j1;
        this.balles_de_break_converties_j2 = balles_de_break_converties_j2;
        this.pts_gagnes_deuxieme_balle_j1 = pts_gagnes_deuxieme_balle_j1;
        this.pts_gagnes_deuxieme_balle_j2 = pts_gagnes_deuxieme_balle_j2;
        this.pts_gagnants_j1 = pts_gagnants_j1;
        this.pts_gagnants_j2 = pts_gagnants_j2;
        this.fautes_dir_j1 = fautes_dir_j1;
        this.fautes_dir_j2 = fautes_dir_j2;
        this.fautes_provoq_j1 = fautes_provoq_j1;
        this.fautes_provoq_j2 = fautes_provoq_j2;
        this.nom_vainqueur = nom_vainqueur;
    }

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
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

    public int getPtsGagnesJ1() {
        return ptsGagnesJ1;
    }

    public void setPtsGagnesJ1(int ptsGagnesJ1) {
        this.ptsGagnesJ1 = ptsGagnesJ1;
    }

    public int getPtsGagnesJ2() {
        return ptsGagnesJ2;
    }

    public void setPtsGagnesJ2(int ptsGagnesJ2) {
        this.ptsGagnesJ2 = ptsGagnesJ2;
    }

    public int getPremBallesJ1() {
        return premBallesJ1;
    }

    public void setPremBallesJ1(int premBallesJ1) {
        this.premBallesJ1 = premBallesJ1;
    }

    public int getPremBallesJ2() {
        return premBallesJ2;
    }

    public void setPremBallesJ2(int premBallesJ2) {
        this.premBallesJ2 = premBallesJ2;
    }

    public int getAce_j1() {
        return ace_j1;
    }

    public void setAce_j1(int ace_j1) {
        this.ace_j1 = ace_j1;
    }

    public int getAce_j2() {
        return ace_j2;
    }

    public void setAce_j2(int ace_j2) {
        this.ace_j2 = ace_j2;
    }

    public int getDouble_faute_j1() {
        return double_faute_j1;
    }

    public void setDouble_faute_j1(int double_faute_j1) {
        this.double_faute_j1 = double_faute_j1;
    }

    public int getDouble_faute_j2() {
        return double_faute_j2;
    }

    public void setDouble_faute_j2(int double_faute_j2) {
        this.double_faute_j2 = double_faute_j2;
    }

    public String getBalles_de_break_j1() {
        return balles_de_break_j1;
    }

    public void setBalles_de_break_j1(String balles_de_break_j1) {
        this.balles_de_break_j1 = balles_de_break_j1;
    }

    public String getBalles_de_break_j2() {
        return balles_de_break_j2;
    }

    public void setBalles_de_break_j2(String balles_de_break_j2) {
        this.balles_de_break_j2 = balles_de_break_j2;
    }

    public int getPts_gagnes_premiere_balle_j1() {
        return pts_gagnes_premiere_balle_j1;
    }

    public void setPts_gagnes_premiere_balle_j1(int pts_gagnes_premiere_balle_j1) {
        this.pts_gagnes_premiere_balle_j1 = pts_gagnes_premiere_balle_j1;
    }

    public int getPts_gagnes_premiere_balle_j2() {
        return pts_gagnes_premiere_balle_j2;
    }

    public void setPts_gagnes_premiere_balle_j2(int pts_gagnes_premiere_balle_j2) {
        this.pts_gagnes_premiere_balle_j2 = pts_gagnes_premiere_balle_j2;
    }

    public String getBalles_de_break_converties_j1() {
        return balles_de_break_converties_j1;
    }

    public void setBalles_de_break_converties_j1(String balles_de_break_converties_j1) {
        this.balles_de_break_converties_j1 = balles_de_break_converties_j1;
    }

    public String getBalles_de_break_converties_j2() {
        return balles_de_break_converties_j2;
    }

    public void setBalles_de_break_converties_j2(String balles_de_break_converties_j2) {
        this.balles_de_break_converties_j2 = balles_de_break_converties_j2;
    }

    public int getPts_gagnes_deuxieme_balle_j1() {
        return pts_gagnes_deuxieme_balle_j1;
    }

    public void setPts_gagnes_deuxieme_balle_j1(int pts_gagnes_deuxieme_balle_j1) {
        this.pts_gagnes_deuxieme_balle_j1 = pts_gagnes_deuxieme_balle_j1;
    }

    public int getPts_gagnes_deuxieme_balle_j2() {
        return pts_gagnes_deuxieme_balle_j2;
    }

    public void setPts_gagnes_deuxieme_balle_j2(int pts_gagnes_deuxieme_balle_j2) {
        this.pts_gagnes_deuxieme_balle_j2 = pts_gagnes_deuxieme_balle_j2;
    }

    public int getPts_gagnants_j1() {
        return pts_gagnants_j1;
    }

    public void setPts_gagnants_j1(int pts_gagnants_j1) {
        this.pts_gagnants_j1 = pts_gagnants_j1;
    }

    public int getPts_gagnants_j2() {
        return pts_gagnants_j2;
    }

    public void setPts_gagnants_j2(int pts_gagnants_j2) {
        this.pts_gagnants_j2 = pts_gagnants_j2;
    }

    public int getFautes_dir_j1() {
        return fautes_dir_j1;
    }

    public void setFautes_dir_j1(int fautes_dir_j1) {
        this.fautes_dir_j1 = fautes_dir_j1;
    }

    public int getFautes_dir_j2() {
        return fautes_dir_j2;
    }

    public void setFautes_dir_j2(int fautes_dir_j2) {
        this.fautes_dir_j2 = fautes_dir_j2;
    }

    public int getFautes_provoq_j1() {
        return fautes_provoq_j1;
    }

    public void setFautes_provoq_j1(int fautes_provoq_j1) {
        this.fautes_provoq_j1 = fautes_provoq_j1;
    }

    public int getFautes_provoq_j2() {
        return fautes_provoq_j2;
    }

    public void setFautes_provoq_j2(int fautes_provoq_j2) {
        this.fautes_provoq_j2 = fautes_provoq_j2;
    }

    public String getNom_vainqueur() {
        return nom_vainqueur;
    }

    public void setNom_vainqueur(String nom_vainqueur) {
        this.nom_vainqueur = nom_vainqueur;
    }

    public String getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(String dateMatch) {
        this.dateMatch = dateMatch;
    }

    public JSONArray convertToJSONArray(){
        List laList = new ArrayList<>();
        laList.add(nomJoueur1);
        laList.add(nomJoueur2);
        laList.add(ptsGagnesJ1);
        laList.add(ptsGagnesJ2);
        laList.add(premBallesJ1);
        laList.add(premBallesJ2);
        laList.add(ace_j1);
        laList.add(ace_j2);
        laList.add(double_faute_j1);
        laList.add(double_faute_j2);
        laList.add(balles_de_break_j1);
        laList.add(balles_de_break_j2);
        laList.add(pts_gagnes_premiere_balle_j1);
        laList.add(pts_gagnes_premiere_balle_j2);
        laList.add(balles_de_break_converties_j1);
        laList.add(balles_de_break_converties_j2);
        laList.add(pts_gagnes_deuxieme_balle_j1);
        laList.add(pts_gagnes_deuxieme_balle_j2);
        laList.add(pts_gagnants_j1);
        laList.add(pts_gagnants_j2);
        laList.add(fautes_dir_j1);
        laList.add(fautes_dir_j2);
        laList.add(fautes_provoq_j1);
        laList.add(fautes_provoq_j2);
        laList.add(nom_vainqueur);
        return new JSONArray(laList);
    }

}
