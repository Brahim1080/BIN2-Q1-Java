package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {
    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;

    private Produit produitAvecPrix;
    private Produit produitSansPrix;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);

        prixPub = new Prix(TypePromo.PUB, 10);
        prixPub.definirPrix(3, 15);

        prixSolde = new Prix(TypePromo.SOLDE, 10);

        produitAvecPrix = new Produit("PS5", "SONY", "GAMING");
        produitAvecPrix.ajouterPrix(LocalDate.now(), prixAucune);
        produitAvecPrix.ajouterPrix(LocalDate.now().plusWeeks(2), prixPub);
        produitAvecPrix.ajouterPrix(LocalDate.now().plusWeeks(1), prixSolde);

        produitSansPrix = new Produit("XBOX", "MICROSOFT", "GAMING");
    }

    //Test constructeur
    @Test
    void construsctrorT1() {
        assertThrows(IllegalArgumentException.class, () -> new Produit(null, " ", "   "));
    }

    @Test
    void constructorT2() {
        assertAll(
                () -> assertEquals("PS5", produitAvecPrix.getNom()),
                () -> assertEquals("SONY", produitAvecPrix.getMarque()),
                () -> assertEquals("GAMING", produitAvecPrix.getRayon()),
                () -> assertEquals("XBOX", produitSansPrix.getNom()),
                () -> assertEquals("MICROSOFT", produitSansPrix.getMarque()),
                () -> assertEquals("GAMING", produitSansPrix.getRayon())
        );
    }

    //Test des prix Ajouter et getPrix
    @Test
    void TestPrix1() {
        assertThrows(IllegalArgumentException.class , ()->produitSansPrix.ajouterPrix(LocalDate.now(),null));
    }
    @Test
    void TestPrix2() {
        assertThrows(DateDejaPresenteException.class , ()-> produitAvecPrix.ajouterPrix(LocalDate.now(),prixSolde));
    }

    @Test
    void TestPrix3() {
        produitSansPrix.ajouterPrix(LocalDate.now(),prixPub);
        assertEquals(prixPub , produitSansPrix.getPrix(LocalDate.now()));

    }
    @Test
    void TestPrix4() {
        assertThrows(PrixNonDisponibleException.class ,() -> produitAvecPrix.getPrix(LocalDate.now().minusDays(4)));

    }
    @Test
    void TestPrix5() {
        assertThrows(PrixNonDisponibleException.class ,() -> produitSansPrix.getPrix(LocalDate.now()));

    }
    @Test
    void TestPrix6() {
        assertEquals(prixSolde, produitAvecPrix.getPrix(LocalDate.now().plusDays(9)));
    }

    //Test des méthodes equals et hashcode
    @Test
    void TestEquals1() {
        Produit p1 = new Produit("1","1","1");
        Produit p2 = new Produit("1","1","1");
        assertEquals(p1,p2);
    }
    @Test
    void TestEquals2() {
        Produit p1 = new Produit("1","1","1");
        Produit p2 = new Produit("2","1","1");
        assertNotEquals(p1,p2);
    }

    @Test
    void TestEquals3() {
        Produit p1 = new Produit("1","1","1");
        Produit p2 = new Produit("1","2","1");
        assertNotEquals(p1,p2);
    }
    @Test
    void TestEquals4() {
        Produit p1 = new Produit("1","1","1");
        Produit p2 = new Produit("1","1","2");
        assertNotEquals(p1,p2);
    }

    @Test
    void TestHashCode5() {
        Produit p1 = new Produit("1","1","1");
        Produit p2 = new Produit("1","1","1");

        System.out.println("p1.hashCode() = " + p1.hashCode());
        System.out.println("p2.hashCode() = " + p2.hashCode());
        assertEquals(p1.hashCode(),p2.hashCode());
    }


}