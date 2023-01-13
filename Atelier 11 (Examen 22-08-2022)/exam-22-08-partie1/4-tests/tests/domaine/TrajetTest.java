package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrajetTest {

    Trajet trajet = null;

    @BeforeEach
    void setUp() {
        trajet = new Trajet("100", LocalDate.now().plusDays(10), "Marseille", "Lyon");

    }

    @Test
    void testA() {

        assertThrows(IllegalArgumentException.class, () -> trajet.peutAjouter(null));
    }

    @Test
    void testB() {
        assertFalse(trajet.ajouter(new Caisse("Caisse Test",LocalDate.now().plusDays(10),"Paris","Lyon",18000)));
    }
    @Test
    void testC() {
        assertTrue(trajet.ajouter(new Caisse("Caisse Test",LocalDate.now().plusDays(10),"Marseille","Lyon",18000)));
    }
}