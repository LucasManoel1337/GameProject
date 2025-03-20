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

public class JpInventario extends JPanel {

    private GameFrame gameFrame;  // Alterado para GameFrame
    private infoPlayerDto playerInfo; // Referência ao DTO do jogador

    public JpInventario(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;  // Agora gameFrame não será mais null
        this.playerInfo = playerInfo;  // Atribui o playerInfo passado no construtor
        setLayout(null);
        setBackground(Color.WHITE);

        // Criar o título
        JLabel lTituloTela = new JLabel("Inventário");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(30, 90, 700, 30);  // Coloquei a posição mais alta para que o título fique acima
        lTituloTela.setVisible(true);
        add(lTituloTela);

        ImageIcon originalIcon = new ImageIcon("imagens/Menu/inventario/inventario.png");

// Obter dimensões originais
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();

// Calcular novas dimensões mantendo a proporção
        int newWidth = 500; // Largura desejada
        int newHeight = (originalHeight * newWidth) / originalWidth; // Altura proporcional

        Image scaledImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

// Criar JLabel com a imagem redimensionada corretamente
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds((520 - newWidth) / 2, 200, newWidth, newHeight);

        add(imageLabel);

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
