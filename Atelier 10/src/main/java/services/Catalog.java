package services;

import domaine.CompactDisc;
import domaine.CompactDiscImpl;

import java.util.List;

public interface Catalog {
    List<CompactDisc> findCds(String title, String artist);

    void initCatalog(CompactDisc... defaultItems);
}
