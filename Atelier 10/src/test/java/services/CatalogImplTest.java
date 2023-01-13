package services;

import domaine.CompactDisc;
import domaine.CompactDiscImpl;
import domaine.DomainFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogImplTest {

    private DomainFactory factory = new DomainFactory();
    @BeforeEach
    void setUp() {
    }

    @Test
    void cdIsFound() {
        Catalog catalog = new CatalogImpl();
        CompactDisc cd1 = factory.createCompactDisc("Young Mystic", "Bob Marley", 3, 12, "666-777");
        CompactDisc cd2 = factory.createCompactDisc("Time Out", "Dave Brubeck Quartet", 10, 111.1, "123-456");
        catalog.initCatalog(cd1, cd2);
        assertAll(
                () -> assertEquals(1, catalog.findCds("young mystic", "").size()),
                () -> assertEquals(cd1, catalog.findCds("young mystic", "").get(0))
        );
    }

}