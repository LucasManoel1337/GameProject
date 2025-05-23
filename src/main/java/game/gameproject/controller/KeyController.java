package game.gameproject.controller;

import game.gameproject.front.game.Player;
import game.gameproject.services.PlayerService;
import game.gameproject.dto.chatDto;
import game.gameproject.dto.infoPlayerDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class KeyController {
	
	chatDto CD = new chatDto();

    private Player player;
    private infoPlayerDto playerInfo;
    PlayerService PS = new PlayerService();

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
            }
        };
    }

    // Método para mapear a tecla de escape para ação de sair
    public void bindEscapeKey(JPanel panel, GameFrame gameFrame) {
        panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "menuOrCloseChat");
        panel.getActionMap().put("menuOrCloseChat", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CD.isChatAtivo()) {
                    System.out.println("ESC pressionado - Fechar chat");
                    CD.setChatAtivo(false);
                    try {
    					PS.inverterStatusDigitando(playerInfo.getIdPlayer());
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                    panel.requestFocusInWindow(); // Devolve foco ao jogo
                } else {
                    System.out.println("ESC pressionado - Voltar ao menu");
                    player.resetarMovimento();
                    gameFrame.switchToMenuPanel();
                }
            }
        });
    }
    
    public void bindQKey(JPanel panel, GameFrame gameFrame) {
        panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "missaoAction");
        panel.getActionMap().put("missaoAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (CD.isChatAtivo()) return;
                System.out.println("Q pressionado!");
                player.resetarMovimento();
                gameFrame.switchToMissoesPanel();
            }
        });
    }
    
    public void bindEKey(JPanel panel, GameFrame gameFrame) {
        panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "inventarioAction");
        panel.getActionMap().put("inventarioAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (CD.isChatAtivo()) return;
                System.out.println("E pressionado!");
                player.resetarMovimento();
                gameFrame.switchToInventarioPanel();
            }
        });
    }
    
    public void bindFKey(JPanel panel, GameFrame gameFrame) {
        panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), "amigosAction");
        panel.getActionMap().put("amigosAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (CD.isChatAtivo()) return;
                System.out.println("F pressionado!");
                player.resetarMovimento();
                gameFrame.switchToAmigosPanel();
            }
        });
    }
    
    public void bindGKey(JPanel panel, GameFrame gameFrame) {
        panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "guildaAction");
        panel.getActionMap().put("guildaAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (CD.isChatAtivo()) return;
                System.out.println("G pressionado!");
                player.resetarMovimento();
                gameFrame.switchToGuildaPanel();
            }
        });
    }
    
    public void bindMKey(JPanel panel, GameFrame gameFrame) {
        panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), "mapaAction");
        panel.getActionMap().put("mapaAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (CD.isChatAtivo()) return;
                System.out.println("M pressionado!");
                player.resetarMovimento();
                gameFrame.switchToMapaPanel();
            }
        });
    }
    
    public void bindVKey(JPanel panel, GameFrame gameFrame) {
        panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0), "statusAction");
        panel.getActionMap().put("statusAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (CD.isChatAtivo()) return;
                System.out.println("V pressionado!");
                player.resetarMovimento();
                gameFrame.switchToStatusPanel();
            }
        });
    }
    
    public void bindTKey(JPanel panel, GameFrame gameFrame) {
        panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), "abrirChat");
        panel.getActionMap().put("abrirChat", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (CD.isChatAtivo()) return;
                System.out.println("T pressionado - Chat ativado");
                CD.setChatAtivo(true);
                try {
					PS.inverterStatusDigitando(playerInfo.getIdPlayer());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
            }
        });
    }

    // Converter a String da tecla para o código de tecla correspondente
    private int getKeyCode(String key) {
        if (CD.isChatAtivo()) {
        	player.resetarMovimento();
        	return -1;
        }

        switch (key) {
            case "W": return KeyEvent.VK_W;
            case "S": return KeyEvent.VK_S;
            case "A": return KeyEvent.VK_A;
            case "D": return KeyEvent.VK_D;
            default: return -1;
        }
    }

}
