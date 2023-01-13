package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {

    StageStub stageStub;
    SportStub sportStub;
    MoniteurImpl moniteur;


    @BeforeEach
    void setUp() {
        sportStub = new SportStub(true);
        moniteur = new MoniteurImpl("Nom ");
        stageStub = new StageStub(7, sportStub, null);


    }

    @Test
    void TestMoniteurTC1() {

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageStub)),
                () -> assertEquals(1, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.contientStage(stageStub))

        );


    }

    @Test
    void TestMoniteurTC2() {

        ammenerALEtat(1,moniteur);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageStub)),
                () -> assertEquals(2, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.contientStage(stageStub))
        );


    }

    @Test
    void TestMoniteurTC3() {
        ammenerALEtat(2,moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageStub)),
                () -> assertEquals(3, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.contientStage(stageStub))
        );

    }

    @Test
    void TestMoniteurTC4() {
        ammenerALEtat(3,moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageStub)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.contientStage(stageStub))
        );

    }

    @Test
    void TestMoniteurTC5() {
        ammenerALEtat(3,moniteur);
        moniteur.ajouterStage(stageStub);
        assertAll(
                () -> assertTrue(moniteur.supprimerStage(stageStub)),
                () -> assertEquals(3, moniteur.nombreDeStages()),
                () -> assertFalse(moniteur.contientStage(stageStub))
        );

    }
    @Test
    void TestMoniteurTC6() {
        ammenerALEtat(2,moniteur);
        moniteur.ajouterStage(stageStub);
        assertAll(
                () -> assertTrue(moniteur.supprimerStage(stageStub)),
                () -> assertEquals(2, moniteur.nombreDeStages()),
                () -> assertFalse(moniteur.contientStage(stageStub))
        );

    }
    @Test
    void TestMoniteurTC7() {
        ammenerALEtat(2,moniteur);
        moniteur.ajouterStage(stageStub);
        assertAll(
                () -> assertTrue(moniteur.supprimerStage(stageStub)),
                () -> assertEquals(2, moniteur.nombreDeStages()),
                () -> assertFalse(moniteur.contientStage(stageStub))
        );

    }
    @Test
    void TestMoniteurTC8() {

        moniteur.ajouterStage(stageStub);
        assertAll(
                () -> assertTrue(moniteur.supprimerStage(stageStub)),
                () -> assertEquals(0, moniteur.nombreDeStages()),
                () -> assertFalse(moniteur.contientStage(stageStub))
        );

    }
    @Test
    void TestMoniteurTC9() {
        ammenerALEtat(3,moniteur);
        moniteur.ajouterStage(stageStub);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stageStub)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.contientStage(stageStub))
        );

    }
    @Test
    void TestMoniteurTC10() {
        ammenerALEtat(3,moniteur);
        moniteur.ajouterStage(stageStub);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(new StageStub(7,sportStub,null))),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.contientStage(stageStub))
        );

    }





    private void ammenerALEtat(int etat, Moniteur moniteurModif) {

        for (int i = 1; i <= etat; i++) {

            StageStub stageSupplementaire = new StageStub(i, sportStub, moniteurModif);
            moniteurModif.ajouterStage(stageSupplementaire);
        }

    }


    @Test
    void ajouterStage() {

    }

    @Test
    void supprimerStage() {

    }
}