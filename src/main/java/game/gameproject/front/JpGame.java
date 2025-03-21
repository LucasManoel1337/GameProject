package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.front.game.*;
import game.gameproject.services.PlayerService;
import game.gameproject.dto.infoPlayerDto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class JpGame extends JPanel {

    private GameFrame gameFrame;
    private infoPlayerDto playerInfo;
    private Player player; // Instância do Player

    public JpGame(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;
        this.playerInfo = playerInfo;
        setLayout(null);
        
        // Criando o Player
        player = new Player(playerInfo.getNickPlayer(), new Mapa(1, null), playerInfo);
        player.setBounds(0, 0, 1280, 768); // Posição inicial e tamanho
        add(player);
        
        // Garante que o Player receba foco corretamente
        SwingUtilities.invokeLater(() -> player.requestFocusInWindow());
        
        bindEscapeKey();
        
        setupKeyBindings(); // Configura os KeyBindings

        player.requestFocusInWindow();
        
        PlayerService PS = new PlayerService();
        PS.setOnline(playerInfo.getIdPlayer());
    }

    private void bindEscapeKey() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "exitAction");
        getActionMap().put("exitAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ESC pressionado!");
                
                gameFrame.switchToMenuPanel();
            }
        });
    }
    
    private void setupKeyBindings() {
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        // Mapeamento das teclas para ações
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "wPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "wReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "sPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "sReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "aPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "aReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "dPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "dReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, 0, false), "shiftPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, 0, true), "shiftReleased");

        // Ações para teclas pressionadas
        actionMap.put("wPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.keyPressed(new KeyEvent(player, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'w'));
            }
        });
        actionMap.put("sPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.keyPressed(new KeyEvent(player, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 's'));
            }
        });
        actionMap.put("aPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.keyPressed(new KeyEvent(player, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a'));
            }
        });
        actionMap.put("dPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.keyPressed(new KeyEvent(player, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'd'));
            }
        });
        actionMap.put("shiftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.keyPressed(new KeyEvent(player, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_SHIFT, ' '));
            }
        });

        // Ações para teclas liberadas
        actionMap.put("wReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.keyReleased(new KeyEvent(player, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'w'));
            }
        });
        actionMap.put("sReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.keyReleased(new KeyEvent(player, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 's'));
            }
        });
        actionMap.put("aReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.keyReleased(new KeyEvent(player, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a'));
            }
        });
        actionMap.put("dReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.keyReleased(new KeyEvent(player, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'd'));
            }
        });
        actionMap.put("shiftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.keyReleased(new KeyEvent(player, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_SHIFT, ' '));
            }
        });
    }
}