package domaine;

import java.util.Set;

public class StageStub implements Stage {

    private int getNumeroSemaine;
    private Sport getSport;
    private Moniteur getMoniteur;

    public StageStub(int getNumeroSemaine, Sport getSport, Moniteur getMoniteur) {
        this.getNumeroSemaine = getNumeroSemaine;
        this.getSport = getSport;
        this.getMoniteur = getMoniteur;
    }

    @Override
    public Moniteur getMoniteur() {
        return this.getMoniteur;
    }

    @Override
    public int getNumeroDeSemaine() {
        return this.getNumeroSemaine;
    }

    @Override
    public Sport getSport() {
        return this.getSport;
    }

    @Override
    public String getIntitule() {
        return null;
    }


    @Override
    public String getLieu() {
        return null;
    }


    @Override
    public boolean enregistrerMoniteur(Moniteur moniteur) {
        return false;
    }


    @Override
    public boolean supprimerMoniteur() {
        return false;
    }


    @Override
    public boolean ajouterEnfant(Enfant enfant) {
        return false;
    }


    @Override
    public boolean supprimerEnfant(Enfant enfant) {
        return false;
    }


    @Override
    public boolean contientEnfant(Enfant enfant) {
        return false;
    }


    @Override
    public int nombreDEnfants() {
        return 0;
    }


    @Override
    public Set<Enfant> enfants() {
        return null;
    }
}
