package domaine;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {

    private Sport sportCompetent;
    private Moniteur moniteur;
    private Stage stageValide;


    @BeforeEach
    void setUp() {
        moniteur = new MoniteurImpl("Brahim");
        sportCompetent = Mockito.mock(Sport.class);
        Mockito.when(sportCompetent.contientMoniteur(moniteur)).thenReturn(true);


        stageValide = Mockito.mock(Stage.class);
        Mockito.when(stageValide.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageValide.getMoniteur()).thenReturn(null); // Pour pouvoir l'ajouter ?
        Mockito.when(stageValide.getNumeroDeSemaine()).thenReturn(8);

    }

    @Test
    void TC1() {
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(1, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
                // Verifie si la Methode enregistrer Moniteur a bien ete appeler dans ajouter
        );

    }

    @Test
    void TC2() {
        ammenerALEtat(1, moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(2, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
                // Verifie si la Methode enregistrer Moniteur a bien ete appeler dans ajouter
        );

    }


    @Test
    void TC3() {
        ammenerALEtat(2, moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(3, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
                // Verifie si la Methode enregistrer Moniteur a bien ete appeler dans ajouter
        );

    }

    @Test
    void TC4() {
        ammenerALEtat(3, moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
                // Verifie si la Methode enregistrer Moniteur a bien ete appeler dans ajouter
        );


    }

    @Test
    void TC5() {
        ammenerALEtat(3, moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stageValide)), //Stage deja present
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur) //Pour le premier ajout
                // Verifie si la Methode enregistrer Moniteur a bien ete appeler dans ajouter
        );


    }
    @Test
    void TC6() {
        ammenerALEtat(4, moniteur);
        //Creation d'un stage a la meme semaine que stageValide
        Stage stageMemeSemaine = Mockito.mock(Stage.class);
        Mockito.when(stageMemeSemaine.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageMemeSemaine.getMoniteur()).thenReturn(null);
        Mockito.when(stageMemeSemaine.getNumeroDeSemaine()).thenReturn(1);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stageMemeSemaine)), //Lajout du stage dans la meme semaine
                () -> assertFalse(moniteur.contientStage(stageMemeSemaine)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageMemeSemaine, Mockito.never()).enregistrerMoniteur(moniteur) //N'a jamais ete appeler
                // Verifie si la Methode enregistrer Moniteur a bien ete appeler dans ajouter
        );


    }
    @Test
    void TC7() {
        ammenerALEtat(4, moniteur);
        //Stage possedant deja un autre moniteur
        Stage stageAutreMoniteur = Mockito.mock(Stage.class);
        Mockito.when(stageAutreMoniteur.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageAutreMoniteur.getMoniteur()).thenReturn(new MoniteurImpl("AutreMoniteur"));
        Mockito.when(stageAutreMoniteur.getNumeroDeSemaine()).thenReturn(8);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stageAutreMoniteur)), //Lajout du stage qui a deja un moniteur
                () -> assertFalse(moniteur.contientStage(stageAutreMoniteur)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageAutreMoniteur, Mockito.never()).enregistrerMoniteur(moniteur)
                //N'a jamais ete appeler
                // Verifie si la Methode enregistrer Moniteur a bien ete appeler dans ajouter
        );


    }
    @Test
    void TC8() {
        ammenerALEtat(4, moniteur);
        //Stage possedant deja un autre moniteur
        Stage stage = Mockito.mock(Stage.class);
        Mockito.when(stage.getSport()).thenReturn(sportCompetent);
        Mockito.when(stage.getMoniteur()).thenReturn(moniteur);
        Mockito.when(stage.getNumeroDeSemaine()).thenReturn(8);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stage)), //Lajout du stage ou le moniteur est celui quon veut ajt
                () -> assertTrue(moniteur.contientStage(stage)),
                () -> assertEquals(5, moniteur.nombreDeStages()),
                () -> Mockito.verify(stage, Mockito.never()).enregistrerMoniteur(moniteur)
                //N'a jamais ete appeler
                // Verifie si la Methode enregistrer Moniteur a bien ete appeler dans ajouter
        );
    }
    @Test
    void TC9() {

        //Stage possedant deja un autre moniteur
        Stage stageNonCompetent = Mockito.mock(Stage.class);
        Mockito.when(stageNonCompetent.getSport()).thenReturn(new SportImpl("SportNonCompetent"));
        Mockito.when(stageNonCompetent.getMoniteur()).thenReturn(null);
        Mockito.when(stageNonCompetent.getNumeroDeSemaine()).thenReturn(8);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stageNonCompetent)), //Lajout du stage ou le moniteur n'est pas competent
                () -> assertFalse(moniteur.contientStage(stageNonCompetent)),
                () -> assertEquals(0, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageNonCompetent, Mockito.never()).enregistrerMoniteur(moniteur)
                //N'a jamais ete appeler
                // Verifie si la Methode enregistrer Moniteur a bien ete appeler dans ajouter
        );
    }




    private void ammenerALEtat(int etat, Moniteur moniteurModif) {

        for (int i = 1; i <= etat; i++) {
            Stage stage = Mockito.mock(Stage.class);
            Mockito.when(stage.getSport()).thenReturn(sportCompetent);
            Mockito.when(stage.getMoniteur()).thenReturn(null);
            Mockito.when(stage.getNumeroDeSemaine()).thenReturn(i);
            moniteurModif.ajouterStage(stage);
        }

    }
}