package com.example.apprenti.galaxian;

/**
 * Created by apprenti on 18/10/17.
 */

public class GalaxianModel {
    private String nom;
    private String mdp;
    private int score;

    public GalaxianModel(){

    }

    public GalaxianModel(String nom, String mdp, Integer score) {
        this.nom = nom;
        this.mdp = mdp;
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public String getMdp() {
        return mdp;
    }

    public int getScore() {
        return score;
    }
}
