package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class JpGame extends JPanel {

    private GameFrame gameFrame;

    public JpGame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        setLayout(null);
        setBackground(Color.WHITE);

        // Carregar a imagem
        ImageIcon originalIcon = new ImageIcon("imagens/game/interface/hotbar.png"); // Substitua pelo caminho real
        Image scaledImage = originalIcon.getImage().getScaledInstance(500, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Criar JLabel com a imagem
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds((1000 - 500) / 2, (720 - 50) - 20, 500, 50); // Centralizado horizontalmente e na parte inferior

        // Adicionar ao painel
        add(imageLabel);
        
        bindEscapeKey();
    }
    
    private void bindEscapeKey() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "exitAction");
        getActionMap().put("exitAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ESC pressionado!"); // Aqui você define a ação desejada
                
                gameFrame.switchToMenuPanel();
            }
        });
    }
}
