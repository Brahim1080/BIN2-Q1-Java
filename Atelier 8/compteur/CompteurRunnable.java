package compteur;


import java.util.concurrent.atomic.AtomicInteger;

public class CompteurRunnable implements Runnable {

    private String nom;
    private int max;

    private int ordre;
    private static final AtomicInteger ordreArrive = new AtomicInteger();

    //TODO: ajouter un attribut de classe qui retient l'ordre d'arrivée.


    @Override
    public void run() {
        //TODO: 1. Compter jusqu'à max
        //         A chaque incrémentation, afficher le nom du compteur et son compte actuel.
        //      2. Quand le compte est terminé, afficher que le compteur a finit de compter
        //         et indiquer son ordre d'arrivée.
        for (int i = 1; i <= this.max; i++) {

            try {
                Thread.sleep(100);
                if (i != max) System.out.println("Nom : " + this.nom + " count" + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        try {
            Thread.sleep(100);
            System.out.println("Nom : " + this.nom + " count" + max);
            ordreArrive.getAndIncrement();

            System.out.println(this.nom + " a fini " + ordreArrive + " ieme");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);


        }
    }

    public CompteurRunnable(String nom, int max){
            this.nom = nom;
            this.max = max;
        }

        public String getNom () {
            return nom;
        }

    }