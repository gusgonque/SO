public class Main {
    // main com caso de teste envolvendo um Produtor e um Consumidor
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Produtor produtor = new Produtor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        Thread p1 = new Thread(produtor);   // thread produtor1
        Thread c1 = new Thread(consumidor); // thread consumidor1

        p1.start();
        c1.start();
    }
}