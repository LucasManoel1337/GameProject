package game.gameproject.controller;

import game.gameproject.front.game.Player;
import game.gameproject.dto.infoPlayerDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class KeyController {

    private Player player;
    private infoPlayerDto playerInfo;

    public KeyController(Player player, infoPlayerDto playerInfo) {
        this.player = player;
        this.playerInfo = playerInfo;
    }

    // Método para mapear ações das teclas pressionadas e liberadas
    public AbstractAction getKeyAction(String key, boolean pressed) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int keyCode = getKeyCode(key);
                char keyChar = key.charAt(0);

                if (pressed) {
                    player.keyPressed(new KeyEvent(player, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, keyChar));
                } else {
                    player.keyReleased(new KeyEvent(player, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, keyCode, keyChar));
                }

                // Verifica se a tecla pressionada é a aspa simples
                if (key.equals("'") && pressed) {
                    toggleOpValue();
                }
            }
        };
    }

    // Método para alternar o valor de 'op' entre 0 e 1
    private void toggleOpValue() {
        if (playerInfo.getOp() == 1) {
            playerInfo.setOp(0);  // Se 'op' for 1, muda para 0
        } else {
            playerInfo.setOp(1);  // Se 'op' for 0, muda para 1
        }
        System.out.println("Valor de op alterado: " + playerInfo.getOp());
    }

    // Método para mapear a tecla de escape para ação de sair
    public void bindEscapeKey(JPanel panel, GameFrame gameFrame) {
        panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exitAction");
        panel.getActionMap().put("exitAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ESC pressionado!");
                gameFrame.switchToMenuPanel();
            }
        });
    }

    // Converter a String da tecla para o código de tecla correspondente
    private int getKeyCode(String key) {
        switch (key) {
            case "W": return KeyEvent.VK_W;
            case "S": return KeyEvent.VK_S;
            case "A": return KeyEvent.VK_A;
            case "D": return KeyEvent.VK_D;
            case "SHIFT": return KeyEvent.VK_SHIFT;
            case "'": return KeyEvent.VK_QUOTE;  // Código para a tecla de aspa simples
            default: return -1;
        }
    }
}
