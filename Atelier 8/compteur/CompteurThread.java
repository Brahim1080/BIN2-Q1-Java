package compteur;

public class CompteurThread extends Thread {

    private final String nom;
    private final int max;

    //Cette variable de classe permet de retenir quel CompteurThread
    //a fini de compter le premier.
    private static CompteurThread gagnant;

    public CompteurThread(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {
        //TODO: 1. Compter jusqu'à max
        //         A chaque incrémentation, afficher le nom du compteur et son compte actuel.
        //      2. Quand le compte est terminé, afficher que le compteur a finit de compter
        for (int i = 1; i <= this.max; i++) {
            try {
                Thread.sleep(10);
                System.out.println("Nom : " + this.getNom() + " count" + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        synchronized (CompteurThread.class){
            try {
                Thread.sleep(10);
                if (gagnant == null)
                    gagnant = this;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }




    }

    public static CompteurThread getGagnant() {
        return gagnant;
    }
}
