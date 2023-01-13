package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {

    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);

        prixPub = new Prix(TypePromo.PUB, 10);
        prixPub.definirPrix(3, 15);

        prixSolde = new Prix(TypePromo.SOLDE, 10);
    }

    @DisplayName("Test constructeur avec promo null")
    @Test
    void constructeurTC1() {
        assertThrows(IllegalArgumentException.class, () -> new Prix(null, 10));
    }

    @DisplayName("Test constructeur avec valleur < 0")
    @Test
    void constructeurTC2() {
        assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.PUB, -15));
    }

    @DisplayName("test promo init a 0 instanciation de prix sans parametre  ")
    @Test
    void getterT1() {
        assertEquals(0, prixAucune.getValeurPromo());
    }

    @DisplayName("Valeur promo est bien celle passe en parametre du constructeur ")
    @Test
    void getterT2() {
        assertEquals(10, prixPub.getValeurPromo());
    }

    @DisplayName("Type de promo null avec constructeur sans parametre")
    @Test
    void getterT3() {
        assertNull(prixAucune.getTypePromo());
    }

    @DisplayName("Type de promo est la meme que constructeur ")
    @Test
    void getterT4() {
        assertEquals(TypePromo.SOLDE, prixSolde.getTypePromo());
    }

    @DisplayName("IllegalArgumentException si quantite est 0 ou negatif ")
    @Test
    void definirPrix1() {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(-10, 10));
    }

    @DisplayName("IllegalArgumentException si valeur est 0 ou negatif ")
    @Test
    void definirPrix2() {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(10, -10));
    }

    @DisplayName("IllegalArgumentException si valeur est 0 ou negatif ")
    @Test
    void definirPrix3() {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(10, -10));
    }

    @DisplayName("definir 6 euros pour 10 unite et verifier que l'ancien a ete remplacer")
    @Test
    void definirPrix4() {
        prixAucune.definirPrix(10, 6);
        assertEquals(6, prixAucune.getPrix(10));
    }

    @Test
    void getPrix1() {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.getPrix(-10));
    }

    //??
    @Test
    void getPrix2() {

        assertAll(
                () -> assertEquals(20, prixAucune.getPrix(1), "Ne renvoie pas le bon prix "),
                () -> assertEquals(20, prixAucune.getPrix(5), "Ne renvoie pas le bon prix "),
                () -> assertEquals(20, prixAucune.getPrix(9), "Ne renvoie pas le bon prix "),
                () -> assertEquals(10, prixAucune.getPrix(10), "Ne renvoie pas le bon prix "),
                () -> assertEquals(10, prixAucune.getPrix(15), "Ne renvoie pas le bon prix "),
                () -> assertEquals(10, prixAucune.getPrix(20), "Ne renvoie pas le bon prix "),
                () -> assertEquals(10, prixAucune.getPrix(25), "Ne renvoie pas le bon prix ")
        );

    }

    @Test
    void getPrix3() {
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixPub.getPrix(2));
    }

    @Test
    void getPrix4() {
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixSolde.getPrix(1));
    }


    @Test
    void getTypePromo() {
    }

    @Test
    void getValeurPromo() {
    }


}