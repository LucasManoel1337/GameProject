package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.MenuBarService;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpMenu extends JPanel {

    private GameFrame gameFrame;  // Alterado para GameFrame
    private infoPlayerDto playerInfo; // Referência ao DTO do jogador

    public JpMenu(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;  // Agora gameFrame não será mais null
        this.playerInfo = playerInfo;  // Atribui o playerInfo passado no construtor
        setLayout(null);
        setBackground(Color.WHITE);
        
        JLabel lBemVindo = new JLabel("Bem vindo jogador "+playerInfo.getNickPlayer());
        lBemVindo.setFont(new Font("Arial", Font.BOLD, 30));
        lBemVindo.setForeground(Color.BLACK);
        lBemVindo.setBounds(10, 100, 700, 30);
        lBemVindo.setVisible(true);
        add(lBemVindo);
        
        JLabel lPainelDeNovidades = new JLabel("Painel de novidades");
        lPainelDeNovidades.setFont(new Font("Arial", Font.BOLD, 16));
        lPainelDeNovidades.setForeground(Color.BLACK);
        lPainelDeNovidades.setBounds(70, 145, 700, 30);
        lPainelDeNovidades.setVisible(true);
        add(lPainelDeNovidades);
        
        MenuBarService.addMenu(this, gameFrame);

    }
}
