import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Semaphore[] palillos = new Semaphore[numFilosofos];
        Filosofo[] filosofos = new Filosofo[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            palillos[i] = new Semaphore(1); // Cada palillo está disponible inicialmente
        }

        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(i, palillos);
            filosofos[i].start();
        }


        }
}