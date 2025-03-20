package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class JpGame extends JPanel {

    private GameFrame gameFrame;
    private infoPlayerDto playerInfo;

    public JpGame(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;  // Agora gameFrame não será mais null
        this.playerInfo = playerInfo;  // Atribui o playerInfo passado no construtor
        setLayout(null);
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
