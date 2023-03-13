public class Consumidor implements Runnable{
    private Buffer buffer; // Consumidor tem acesso ao buffer de memoria compartilhado

    private volatile boolean running = true;
    // Consumidor recebe um buffer
    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    // metodo para fazer com que a thread durma dado os milissegundos passados
    private void dormir(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // metodo run sobrescrito, Consumidor remove o primeiro item do buffer de memoria
    public void run() {
        while(running) {
            System.out.println("Consumindo item: " + buffer.remover());
            ;
            dormir(1000);
            if (Thread.currentThread().isInterrupted()) {
                break; // Encerra o loop e sai do método run()
            }
        }
    }

    public void parar() {
        running = false;
        Thread.currentThread().interrupt(); // Interrompe a Thread se ela estiver bloqueada
    }
}