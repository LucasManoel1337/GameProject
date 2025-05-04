package game.gameproject.front;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.MenuBarService;

public class JpEstatisticas extends JPanel {

    private GameFrame gameFrame;
    private infoPlayerDto playerInfo;

    public JpEstatisticas(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;
        this.playerInfo = playerInfo;
        setLayout(null);
        setBackground(Color.WHITE);
        
        JLabel lTituloTela = new JLabel("Estatisticas");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(16, 90, 700, 30);
        lTituloTela.setVisible(true);
        add(lTituloTela);

        ImageIcon logoIcon = new ImageIcon("imagens/Menu/PlacaTelas.png");
        Image img = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 50, 200, 100);
        add(logoLabel);
        
        ImageIcon logoIconS = new ImageIcon("imagens/Menu/status/status.png");
        Image imgS = logoIconS.getImage().getScaledInstance(275+150, 410+150, Image.SCALE_SMOOTH);  // Ajuste de tamanho da imagem
        logoIconS = new ImageIcon(imgS);

        JLabel logoLabelS = new JLabel(logoIconS);
        logoLabelS.setBounds(420, 70 + 50, 275+150, 410+150);  // Coloquei a imagem abaixo do título (a partir de y = 50)
        add(logoLabelS);
        
        JLabel logoLabelE = new JLabel(logoIconS);
        logoLabelE.setBounds(420+410, 70 + 50, 275+150, 410+150);  // Coloquei a imagem abaixo do título (a partir de y = 50)
        add(logoLabelE);
        
        JLabel logoLabelD = new JLabel(logoIconS);
        logoLabelD.setBounds(10, 70 + 50, 275+150, 410+150);  // Coloquei a imagem abaixo do título (a partir de y = 50)
        add(logoLabelD);
        
        MenuBarService.addMenu(this, gameFrame, playerInfo);
        bindEscapeKey();
    }
    
    public void atualizarEstatisticas() {
    	
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