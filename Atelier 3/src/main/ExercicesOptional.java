package main;

import domaine.Trader;
import domaine.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ExercicesOptional {

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

        ExercicesOptional main = new ExercicesOptional(transactions);
        main.run();
    }

    /**
     * La liste de base de toutes les transactions.
     */
    private List<Transaction> transactions;

    /**
     * Crée un objet comprenant toutes les transactions afin de faciliter leur usage pour chaque point de l'énoncé
     *
     * @param transactions la liste des transactions
     */
    public ExercicesOptional(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Exécute chaque point de l'énoncé
     */
    public void run() {
        this.optional1();
        this.optional2();

    }


    private void optional1() {
        System.out.println("optional1");
        Integer maxEnOption = transactions.stream()
                .map(Transaction::getValue)
                .reduce((a, b) -> {
                    if (a > b)
                        return a;
                    else return b;
                }).orElse(-1);

        System.out.println("maxEnOption = " + maxEnOption);
        /*
        Integer max = transactions
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer.MIN_VALUE, (a, b) -> {
                    if (a > b)
                        return a;
                    else return b;
                });
                 System.out.println("max = " + max);
        */

    }

    private void optional2() {
        System.out.println("optional2");

        Optional<Transaction> transactionMinEnOption = transactions.stream()
                .reduce((a, b) -> {
                    if (a.getValue() < b.getValue()) {
                        return a;
                    } else return b;
                });
        if (transactionMinEnOption.isPresent()){
            System.out.println("transactionMinEnOption = " + transactionMinEnOption);
        }else System.out.println("Erreur stream vide");


    }

}