package deadlock_magic;

public class Grimoire {
    public enum Ingredient {
        BAVE_DE_CRAPEAU, PIERRE_PHILOSOPHALE, CUISSE_DE_GRENOUILLE, POIL_DE_TROLL, SILICIUM, LARME_DE_FEE
    }

    public void mediter(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void calmeInfini(String nomMagicien){
        synchronized (Ingredient.LARME_DE_FEE){
            System.out.println(Ingredient.LARME_DE_FEE + " pris par " + nomMagicien +". Je vais m�diter");
            mediter();

            synchronized (Ingredient.POIL_DE_TROLL){
                System.out.println(Ingredient.POIL_DE_TROLL + " pris par " + nomMagicien +". Je vais m�diter");
                mediter();
                synchronized (Ingredient.CUISSE_DE_GRENOUILLE){
                    System.out.println(Ingredient.CUISSE_DE_GRENOUILLE + " pris par " + nomMagicien +". Je vais m�diter");
                    mediter();
                    System.out.println("Calme infini lanc� !");
                }
            }
        }
    }

    public void demonDeMinuit(String nomMagicien){
        synchronized (Ingredient.POIL_DE_TROLL){
            System.out.println(Ingredient.POIL_DE_TROLL + " pris par " + nomMagicien +". Je vais m�diter");
            mediter();
            synchronized (Ingredient.LARME_DE_FEE){
                System.out.println(Ingredient.LARME_DE_FEE + " pris par " + nomMagicien +". Je vais m�diter");
                mediter();
                synchronized (Ingredient.PIERRE_PHILOSOPHALE){
                    System.out.println(Ingredient.PIERRE_PHILOSOPHALE + " pris par " + nomMagicien +". Je vais m�diter");
                    mediter();
                    System.out.println("D�mons de minuit lanc� !");
                }
            }
        }
    }


    public void pluieBienfaisante(String nomMagicien){
        synchronized (Ingredient.PIERRE_PHILOSOPHALE){
            System.out.println(Ingredient.PIERRE_PHILOSOPHALE + " pris par " + nomMagicien +". Je vais m�diter");
            mediter();
            synchronized (Ingredient.CUISSE_DE_GRENOUILLE){
                System.out.println(Ingredient.CUISSE_DE_GRENOUILLE + " pris par " + nomMagicien +". Je vais m�diter");
                mediter();
                synchronized (Ingredient.SILICIUM){
                    System.out.println(Ingredient.SILICIUM + " pris par " + nomMagicien +". Je vais m�diter");
                    mediter();
                    System.out.println("Pluie bienfaisante lanc� !");
                }
            }
        }
    }
}
