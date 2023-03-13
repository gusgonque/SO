import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProdutorConsumidor {
    public static void main(String[] args) {
        // Cria uma fila bloqueante com capacidade para 10 elementos
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        // Cria os produtores e consumidores
        Thread produtor1 = new Produtor(queue, "Produtor 1");
        Thread produtor2 = new Produtor(queue, "Produtor 2");
        Thread consumidor1 = new Consumidor(queue, "Consumidor 1");
        Thread consumidor2 = new Consumidor(queue, "Consumidor 2");

        // Inicia os produtores e consumidores
        produtor1.start();
        produtor2.start();
        consumidor1.start();
        consumidor2.start();
    }
}

public class Produtor extends Thread {

    private BlockingQueue<Integer> buffer;
    private int quantidadeProduzida;

    public Produtor(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
        this.quantidadeProduzida = 0;
    }

    public int getQuantidadeProduzida() {
        return quantidadeProduzida;
    }

    @Override
public void run() {
        while (true) {
            try {
                // Produz um elemento
                int elemento = produzirElemento();

                // Adiciona o elemento no buffer compartilhado
                buffer.put(elemento);

                // Incrementa a quantidade de elementos produzidos
                quantidadeProduzida++;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int produzirElemento() {
        // Implementação para produzir um elemento
        ...
    }
}