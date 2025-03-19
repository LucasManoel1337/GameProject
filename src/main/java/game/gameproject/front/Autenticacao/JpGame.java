package game.gameproject.front.Autenticacao;

import game.gameproject.controller.GameFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JpGame extends JPanel {

    private GameFrame gameFrame;  // Alterado para GameFrame

    public JpGame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;  // Agora gameFrame não será mais null
        setLayout(null);
        setBackground(Color.WHITE);

        // Exemplo de botão para iniciar o jogo
        JButton startButton = new JButton("Ir para menu");
        startButton.setBounds(400, 300, 200, 50);
        startButton.addActionListener(e -> gameFrame.switchToMenuPanel()); // Muda para o painel de jogo

        // Exemplo de botão para sair
        JButton exitButton = new JButton("Sair");
        exitButton.setBounds(400, 400, 200, 50);
        exitButton.addActionListener(e -> System.exit(0)); // Sai do jogo

        // Adiciona os botões ao painel
        add(startButton);
        add(exitButton);
    }
}
