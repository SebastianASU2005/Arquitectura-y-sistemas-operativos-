import java.util.concurrent.Semaphore;
public class SincronizacionProcesos {
    // Semáforo para controlar el acceso a la piscina
    private static Semaphore semaforoPiscina;

    // Capacidad máxima de la piscina
    private static final int CAPACIDAD_MAXIMA_PISCINA = 3;

    public static void main(String[] args) {
        // Creamos un semáforo con la capacidad máxima de la piscina
        semaforoPiscina = new Semaphore(CAPACIDAD_MAXIMA_PISCINA);

        // Creamos varios hilos (clientes) que intentarán acceder a la piscina
        Thread[] hilosClientes = new Thread[5]; // 5 clientes

        for (int i = 0; i < hilosClientes.length; i++) {
            hilosClientes[i] = new Thread(new Cliente(i + 1));
            hilosClientes[i].start();
        }
    }

    // Clase que representa un cliente (hilo)
    static class Cliente implements Runnable {
        private int idCliente;

        public Cliente(int idCliente) {
            this.idCliente = idCliente;
        }

        @Override
        public void run() {
            try {
                System.out.println("Cliente " + idCliente + " está esperando para entrar a la piscina.");

                // Intenta adquirir un permiso del semáforo (entra a la piscina)
                semaforoPiscina.acquire();

                System.out.println("Cliente " + idCliente + " está nadando en la piscina.");

                // Simulamos que el cliente está nadando (espera aleatoria)
                Thread.sleep(2000);

                System.out.println("Cliente " + idCliente + " ha salido de la piscina.");

                // Libera el permiso del semáforo (sale de la piscina)
                semaforoPiscina.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
