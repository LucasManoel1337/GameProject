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

public class JpInventario extends JPanel {

    private GameFrame gameFrame;
    private infoPlayerDto playerInfo;

    public JpInventario(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;
        this.playerInfo = playerInfo;
        setLayout(null);
        setBackground(Color.WHITE);

        // Criar o título
        JLabel lTituloTela = new JLabel("Inventário");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(30, 90, 700, 30);
        lTituloTela.setVisible(true);
        add(lTituloTela);

        // Criar a imagem
        ImageIcon logoIcon = new ImageIcon("imagens/Menu/PlacaTelas.png");
        Image img = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 50, 200, 100);
        add(logoLabel);
        
        ImageIcon logoIconPIP1 = new ImageIcon("imagens/Menu/inventario/pergaminhoInventarioP1.png");
        Image imgPIP1 = logoIconPIP1.getImage().getScaledInstance(275+150, 410+150, Image.SCALE_SMOOTH);
        logoIconPIP1 = new ImageIcon(imgPIP1);
        
        ImageIcon logoIconPIP2 = new ImageIcon("imagens/Menu/inventario/pergaminhoInventarioP2.png");
        Image imgPIP2 = logoIconPIP2.getImage().getScaledInstance(275+150, 410+150, Image.SCALE_SMOOTH);
        logoIconPIP2 = new ImageIcon(imgPIP2);
        
        ImageIcon logoIconArmadura;
        if (playerInfo.getSex().equals("M")) {
            logoIconArmadura = new ImageIcon("imagens/Menu/inventario/pergaminhoArmaduraM.png");
        } else {
            logoIconArmadura = new ImageIcon("imagens/Menu/inventario/pergaminhoArmaduraF.png");
        }
        Image imgArmadura = logoIconArmadura.getImage().getScaledInstance(275+150, 410+150, Image.SCALE_SMOOTH);
        logoIconArmadura = new ImageIcon(imgArmadura);

        JLabel logoLabelS = new JLabel(logoIconPIP1);
        logoLabelS.setBounds(420, 70 + 50, 275+150, 410+150);
        add(logoLabelS);
        
        JLabel logoLabelE = new JLabel(logoIconPIP2);
        logoLabelE.setBounds(420+410, 70 + 50, 275+150, 410+150);
        add(logoLabelE);
        
        JLabel logoLabelD = new JLabel(logoIconArmadura);
        logoLabelD.setBounds(10, 70 + 50, 275+150, 410+150);
        add(logoLabelD);

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