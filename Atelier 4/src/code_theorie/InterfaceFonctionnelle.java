package code_theorie;

import domaine.Employe;


import java.util.function.Function;


public class InterfaceFonctionnelle implements Function <Employe , String>{

    @Override
    public String apply(Employe employe) {
        return employe.getNom();
    }
}
