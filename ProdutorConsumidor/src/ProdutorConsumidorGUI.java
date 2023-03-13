import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProdutorConsumidorGUI {
    private Buffer buffer;
    private Produtor produtor;
    private Consumidor consumidor;
    private JLabel bufferLabel;
    private JLabel produtorLabel;
    private JLabel consumidorLabel;
    private JButton startButton;
    private JButton stopButton;

    public ProdutorConsumidorGUI() {
        // Inicializa as classes do problema
        buffer = new Buffer(10);
        produtor = new Produtor(buffer);
        consumidor = new Consumidor(buffer);

        // Cria os componentes da interface gráfica
        bufferLabel = new JLabel("Buffer vazio");
        produtorLabel = new JLabel("Produtor parado");
        consumidorLabel = new JLabel("Consumidor parado");
        startButton = new JButton("Iniciar");
        stopButton = new JButton("Parar");
        stopButton.setEnabled(false);

        // Adiciona os listeners dos botões
        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            produtor.run();
            consumidor.run();
        });
        stopButton.addActionListener(e -> {
            stopButton.setEnabled(false);
            produtor.parar();
            consumidor.parar();
            buffer.esvaziar();
            bufferLabel.setText("Buffer vazio");
            produtorLabel.setText("Produtor parado");
            consumidorLabel.setText("Consumidor parado");
            startButton.setEnabled(true);
        });

        // Cria o painel da interface gráfica
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(bufferLabel);
        panel.add(produtorLabel);
        panel.add(consumidorLabel);
        panel.add(startButton);
        panel.add(stopButton);

        // Cria a janela da interface gráfica
        JFrame frame = new JFrame("Produtor-Consumidor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ProdutorConsumidorGUI();
    }
}