import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread{
    private int id;
    private Semaphore[] palillos;
    private Random random = new Random();

    public Filosofo(int id, Semaphore[] palillos) {
        this.id = id;
        this.palillos = palillos;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                tomarPalillos();
                comer();
                soltarPalillos();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("El filósofo " + id + " está pensando");
        Thread.sleep(random.nextInt(5000 - 1000) + 1000);
    }

    private void tomarPalillos() throws InterruptedException {
        int palilloIzquierdo = id;
        int palilloDerecho = (id + 1) % palillos.length;

        palillos[palilloIzquierdo].acquire();
        palillos[palilloDerecho].acquire();

        System.out.println("Filósofo " + id + " comiendo");
    }

    private void comer() throws InterruptedException {
        Thread.sleep(random.nextInt(5000 - 1000) + 1000);
    }

    private void soltarPalillos() {
        int palilloIzquierdo = id;
        int palilloDerecho = (id + 1) % palillos.length;

        palillos[palilloIzquierdo].release();
        palillos[palilloDerecho].release();

        System.out.println("El filósofo " + id + " está pensando");
    }
}
