package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.MenuBarService;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
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
        
        JLabel lTituloTela = new JLabel("Amigos");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(45, 90, 700, 30);  // Coloquei a posição mais alta para que o título fique acima
        lTituloTela.setVisible(true);
        add(lTituloTela);

        // Criar a imagem
        ImageIcon logoIcon = new ImageIcon("imagens/Menu/PlacaTelas.png");
        Image img = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);  // Ajuste de tamanho da imagem
        logoIcon = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 50, 200, 100);  // Coloquei a imagem abaixo do título (a partir de y = 50)
        add(logoLabel);
        
        MenuBarService.addMenu(this, gameFrame);
    }
}