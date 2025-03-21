package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.MenuBarService;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class JpMenu extends JPanel {

    private GameFrame gameFrame;  // Alterado para GameFrame
    private infoPlayerDto playerInfo; // Referência ao DTO do jogador

    public JpMenu(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;  // Agora gameFrame não será mais null
        this.playerInfo = playerInfo;  // Atribui o playerInfo passado no construtor
        setLayout(null);
        setBackground(Color.WHITE);
        
        // Criar o título
        JLabel lTituloTela = new JLabel("Menu");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(65, 90, 700, 30);  // Coloquei a posição mais alta para que o título fique acima
        lTituloTela.setVisible(true);
        add(lTituloTela);

        // Criar a imagem
        ImageIcon logoIcon = new ImageIcon("imagens/Menu/PlacaTelas.png");
        Image img = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);  // Ajuste de tamanho da imagem
        logoIcon = new ImageIcon(img);
        
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 50, 200, 100);  // Coloquei a imagem abaixo do título (a partir de y = 50)
        add(logoLabel);
        
        JLabel lBemVindo = new JLabel("Bem vindo jogador "+playerInfo.getNickPlayer());
        lBemVindo.setFont(new Font("Arial", Font.BOLD, 30));
        lBemVindo.setForeground(Color.BLACK);
        lBemVindo.setBounds(10, 300, 700, 30);
        lBemVindo.setVisible(true);
        add(lBemVindo);
        
        JLabel lPainelDeNovidades = new JLabel("Painel de novidades");
        lPainelDeNovidades.setFont(new Font("Arial", Font.BOLD, 16));
        lPainelDeNovidades.setForeground(Color.BLACK);
        lPainelDeNovidades.setBounds(70, 145, 700, 30);
        lPainelDeNovidades.setVisible(true);
        add(lPainelDeNovidades);
        
        MenuBarService.addMenu(this, gameFrame, playerInfo);
        bindEscapeKey();
    }
    
    private void bindEscapeKey() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "exitAction");
        getActionMap().put("exitAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ESC pressionado!");
                
                gameFrame.switchToGamePanel();
            }
        });
}
}