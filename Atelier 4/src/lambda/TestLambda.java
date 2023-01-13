package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestLambda {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(153, 22, 4567, 50, 209, 34, 1040);

        List<Integer> result;

        Stream stream;

        //Trouve tous les entiers de la liste qui sont plus grands que 200
        result = Lambda.allMatches(list, a -> a > 200);
        System.out.println(result);

        System.out.println("Resultat avec l API Stream");
        stream = Lambda.filter(list, a -> a > 200);//list.stream().filter(a -> a > 200);//
        stream.forEach(System.out::println);


        //Trouve tous les entiers pairs de la liste
        result = Lambda.allMatches(list, a -> a % 2 == 0);
        System.out.println(result);

        //Trouve tous les entiers de la liste dont le premier chiffre est 1
        result = Lambda.allMatches(list, a -> a.toString().charAt(0) == '1');
        System.out.println(result);

        //Retourne une liste contenant les entiers de la liste originale multipliés par 2
        result = Lambda.transformAll(list, a -> a * 2);
        System.out.println(result);


        //Retourne une liste contenant les entiers de la liste originale auxquels on a soustrait 25
        result = Lambda.transformAll(list, a -> a - 25);
        System.out.println(result);


        //TODO: une fois arrivé au point 1.3, enlevez les commentaires et compélétez
        //      en remplaçant les ??? par des expression lambda appropriées

        List<String> list2 = Arrays.asList("hello", "bonjour", "goeiedag", "hallo", "hej");
//
//        //Trouve toutes les String de la liste qui commencent par "h"
        List<String> result2 = Lambda.allMatches(list2, s -> s.charAt(0) == 'h');
        System.out.println(result2);
        System.out.println("Resultat avec l API Stream");
        stream = Lambda.filter(list2, s -> s.charAt(0) == 'h');
        stream.forEach(System.out::println);

//
//        //Retournerune liste qui contient la taille de chacune des String de la liste originale
        List<Integer> result3 = Lambda.transformAll(list2, s -> String.valueOf(s.length()));
        System.out.println(result3);

        System.out.println("Resultat avec l API Stream");
        Stream stream3  = Lambda.map(list2, s -> String.valueOf(s.length()));
        stream3.forEach(System.out::println);


    }
}
