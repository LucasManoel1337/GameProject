package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.controller.KeyController;
import game.gameproject.front.game.*;
import game.gameproject.services.PlayerService;
import game.gameproject.dto.ConfiguracoesDto;
import game.gameproject.dto.chatDto;
import game.gameproject.dto.infoPlayerDto;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JpGame extends JPanel {

    private GameFrame gameFrame;
    private infoPlayerDto playerInfo;
    private Player player;
    
    public PlayerService PS = new PlayerService();
    public chatDto CD = new chatDto();
    public ConfiguracoesDto Config = new ConfiguracoesDto();
    public KeyController keyController;

    public JpGame(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;
        this.playerInfo = playerInfo;
        this.setName("telaJogo"); // No construtor do JpGame
        setLayout(null);
        keyController = new KeyController(player, playerInfo);

        // Criando o Player
        player = new Player(playerInfo.getNickPlayer(), new Mapa(1, null), playerInfo);
        player.setBounds(0, 0, 1280, 768); // Posição inicial e tamanho
        add(player);    
        
     // Cria o ScheduledExecutorService
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        // Executa o método atualizar a cada 16,67 milissegundos (60 FPS)
        scheduler.scheduleAtFixedRate(() -> {
        	
        	if ("telaJogo".equals(gameFrame.getCurrentPanel().getName())) {
        		player.trocarImagem();
                player.moverPersonagem();
                
                if(player.getVelocidade() > 0) {
                	PS.salvarCoordenadas(playerInfo.getIdPlayer(), player.xPersonagem, player.yPersonagem, player.sprite);
                }
                
                if(Config.isVisualizarOutrosJogadores()) {
                	player.jogadores = PS.buscarJogadores(playerInfo.getIdPlayer());
                }
                repaint();
        	}
            
        }, 0, 1000 / 60, TimeUnit.MILLISECONDS);

        // Garante que o Player receba foco corretamente
        SwingUtilities.invokeLater(() -> player.requestFocusInWindow());

        // Inicializando o KeyController com o player
        keyController = new KeyController(player, playerInfo);
        
        // Configura o mapeamento das teclas
        setupKeyBindings();

        // Configura o mapeamento da tecla ESC para sair
        keyController.bindEscapeKey(this, gameFrame);
        
        keyController.bindEKey(this, gameFrame);
        keyController.bindQKey(this, gameFrame);
        keyController.bindFKey(this, gameFrame);
        keyController.bindGKey(this, gameFrame);
        keyController.bindMKey(this, gameFrame);
        keyController.bindVKey(this, gameFrame);
        keyController.bindTKey(this, gameFrame);

        // Marca o jogador como online
        PS.setOnline(playerInfo.getIdPlayer());
    }

    private void setupKeyBindings() {
        // Teclas de movimentação e ações
        String[] keys = {"W", "S", "A", "D"};

        for (String key : keys) {
            mapKey(key);
        }
    }

    private void mapKey(String key) {
        // Mapeando teclas pressionadas e liberadas para ações correspondentes
        String keyPressed = key + "Pressed";
        String keyReleased = key + "Released";

        // Adicionando Key Bindings
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(getKeyCode(key), 0, false), keyPressed);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(getKeyCode(key), 0, true), keyReleased);

        getActionMap().put(keyPressed, keyController.getKeyAction(key, true));
        getActionMap().put(keyReleased, keyController.getKeyAction(key, false));
    }

    private void bindTelaAtalho(String tecla, Runnable acao) {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(tecla), "abrir" + tecla);
        getActionMap().put("abrir" + tecla, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao.run();
            }
        });
    }

    private int getKeyCode(String key) {
        switch (key) {
            case "W": return KeyEvent.VK_W;
            case "S": return KeyEvent.VK_S;
            case "A": return KeyEvent.VK_A;
            case "D": return KeyEvent.VK_D;
            default: return -1;
        }
    }
}
