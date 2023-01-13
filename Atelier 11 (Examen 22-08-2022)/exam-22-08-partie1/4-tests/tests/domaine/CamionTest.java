package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CamionTest {

    private Camion camion;
    private Trajet trajetNonValide;
    @BeforeEach
    void setUp() {
        camion = new Camion("1-TTT-789",40,1800);
        trajetNonValide = Mockito.mock(Trajet.class);
        Mockito.when(trajetNonValide.nbCaisses()).thenReturn(50);
        Mockito.when(trajetNonValide.getDate()).thenReturn(LocalDate.now().plusDays(4));

    }

    @Test
    void ajouterTrajet() {
        assertAll(
                ()-> assertFalse(camion.ajouterTrajet(trajetNonValide)),
                ()-> assertNull(camion.trouverTrajet(trajetNonValide.getDate()))

        );
    }
}