import java.util.*;
import java.lang.Thread;

public class Produtor implements Runnable{
    private Random rand;   // atributo rand para gerar numeros aleatorios
    private Buffer buffer; // Produtor tem acesso ao buffer de memoria compartilhado

    private volatile boolean running = true;

    // Produtor cria uma instancia de random e recebe um buffer
    public Produtor(Buffer buffer) {
        rand = new Random(50);
        this.buffer = buffer;
    }

    // retorna um numero aleatorio entre 1 e 50
    private int geraItem() {
        return rand.nextInt(50)+1;
    }

    // metodo para fazer com que a thread durma dado os milissegundos passados
    private void dormir(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // metodo run sobrescrito, Produtor produz itens e tenta coloca-los no buffer compartilhado
    public void run() {
        int item;
        while(running) {
            item = geraItem();
            System.out.println("Produzindo item: " + item);
            buffer.inserir(item);
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