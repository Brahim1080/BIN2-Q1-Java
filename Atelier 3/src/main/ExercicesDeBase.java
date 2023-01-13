package main;

import domaine.Trader;
import domaine.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExercicesDeBase {

    /**
     * La liste de base de toutes les transactions.
     */
    private List<Transaction> transactions;

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        System.out.println("Les transactions " + transactions);
        ExercicesDeBase main = new ExercicesDeBase(transactions);
        main.run();
    }



    /**
     * Crée un objet comprenant toutes les transactions afin de faciliter leur usage pour chaque point de l'énoncé
     *
     * @param transactions la liste des transactions
     */
    public ExercicesDeBase(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Exécute chaque point de l'énoncé
     */
    public void run() {
        this.predicats1();
        this.predicats2();
        this.predicats3();
        this.map1();
        this.map2();
        this.map3();
        this.sort1();
        this.sort2();
        this.reduce1();
        this.reduce2();
        this.test();
    }

    private void predicats1() {
        System.out.println("predicats1");
        Stream<Transaction> s = transactions
                .stream()
                .filter(e -> e.getYear() == 2011);
        System.out.println("sout du Stream brut" + s);
        s.forEach(System.out::println);
    }

    private void predicats2() {

        System.out.println("predicats2");
        var s = transactions
                .stream()
                .filter(e -> e.getValue() > 600);

        s.forEach(System.out::println);
    }


    private void predicats3() {

        System.out.println("predicats3");
        var s = transactions
                .stream()
                .filter(e -> e.getTrader().getName().equals("Raoul")).toList(); // TODO filtrer
        s.forEach(System.out::println);
    }

    private void map1() {
        System.out.println("map1");

        Stream<String> listeVille = transactions.stream()
                .map(Transaction :: getTrader)
                .map(Trader :: getCity)
                .distinct();

        listeVille.forEach(System.out :: println);

    }

    private void map2() {
        System.out.println("map2");

        Stream<Trader> listeCourtiersCambridge = transactions
                .stream()
                .map(Transaction :: getTrader)
                .filter(e -> e.getCity().equals("Cambridge"))
                .distinct();
        listeCourtiersCambridge.forEach(System.out :: println);
    }

    private void map3() {
        System.out.println("map3");

        String nomsTrader = transactions
                .stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .collect(Collectors.joining(","));
        System.out.println("Nom des traders :  " + nomsTrader);
    }

    private void sort1() {
        System.out.println("sort1");
       var transcTriees = transactions
                .stream()
                .sorted(Comparator.comparingInt(Transaction :: getValue).reversed())
               ;


        transcTriees.forEach(System.out::println);
    }

    private void sort2() {
        System.out.println("sort2");
        var nomsTries = transactions
                .stream()
                .map(Transaction :: getTrader)
                .sorted(Comparator.comparing(Trader :: getName))
                .distinct();


        nomsTries.forEach(System.out::println);
    }
    private void reduce1() {
        System.out.println("reduce1");
        Integer max = transactions
                .stream()
                .map(Transaction::getValue)
                .reduce((a,b) -> {
                            if (a > b)
                                return a;
                            else return b;
                            }).orElse(0);

        System.out.println("max = " + max);
    }

    /*
    Afficher la transaction dont la valeur est la plus petite. Attention : on demande bien
    d’afficher la transaction et non sa valeur ! Vous ne pouvez pas utiliser la méthode
    min. Au besoin, créer une transaction « neutre » avec une valeur de
    Integer.MAX_VALUE.*/
    private void reduce2() {
        System.out.println("reduce2");

        Transaction transactionMin = transactions.stream()
                .reduce(new Transaction(new Trader("s","s"),2022,Integer.MAX_VALUE), (a,b) -> {
                    if (a.getValue() < b.getValue()) {
                        return a;
                    }else return b;
                });

        System.out.println("transactionMin = " + transactionMin);

    }

    private void test(){
        System.out.println("Test max");
        Integer max2 = Stream.of(1, 2, 3, 4, 5)
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println(max2);
    }

}