package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Lambda {

    /**
     * Retourne une liste contenant uniquement les Integer qui correspondent
     * au predicat match
     * @param list La liste d'Integer originale
     * @param match le predicat à respecter
     * @return une liste contenant les integer qui respectent match
     */
    public static <E> List allMatches(List<E> list, Predicate<E> match) {
        List<E> allMatches = new ArrayList<E>();

        for (E element : list) {
            if (match.test(element))
                allMatches.add(element);
        }
        return allMatches;
    }

    /**
     * Retourne une liste contenant tous les éléments de la liste originale, transformés
     * par la fonction transform
     * @param list La liste d'Integer originale
     * @param transform la fonction à appliquer aux éléments
     * @return une liste contenant les integer transformés par transform
     */
    public static  <E> List transformAll(List<E> list, Function<E, E> transform) {
        List<E> transformList = new ArrayList<E>();

        for (E element : list) {
            transformList.add(transform.apply(element));

        }
        return transformList;
    }

    public static <E> Stream filter(List<E> list, Predicate<E> match) {

        return list.stream().filter(match);
    }

    public static <E> Stream map (List<E> list, Function<E, E> transform){
        return list.stream().map(transform);
    }


}
