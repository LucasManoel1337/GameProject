package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.MenuBarService;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpAmigos extends JPanel {

    private GameFrame gameFrame;  // Alterado para GameFrame
    private infoPlayerDto playerInfo; // Referência ao DTO do jogador

    public JpAmigos(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;  // Agora gameFrame não será mais null
        this.playerInfo = playerInfo;  // Atribui o playerInfo passado no construtor
        setLayout(null);
        setBackground(Color.WHITE);
        
        JLabel lTituloTela = new JLabel("Tela Amigos");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(10, 100, 700, 30);
        lTituloTela.setVisible(true);
        add(lTituloTela);
        
        MenuBarService.addMenu(this, gameFrame);
    }
}