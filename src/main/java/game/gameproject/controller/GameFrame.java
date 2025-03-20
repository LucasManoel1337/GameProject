package game.gameproject.controller;

import game.gameproject.dto.infoPlayerDto;
import game.gameproject.front.JpAmigos;
import game.gameproject.front.JpMenu;
import game.gameproject.front.JpGame;
import game.gameproject.front.JpGuilda;
import game.gameproject.front.JpInventario;
import game.gameproject.front.JpMapa;
import game.gameproject.front.JpMissoes;
import game.gameproject.front.JpStatus;
import game.gameproject.services.StatusService;
import javax.swing.*;

public class GameFrame extends JFrame {

    private JPanel currentPanel;
    private JpMenu telaMenu;
    private JpGame telaJogo;
    infoPlayerDto playerInfo = new infoPlayerDto("a");

    private JpStatus telaStatus;
    private JpInventario telaInventario;
    private JpMapa telaMapa;
    private JpMissoes telaMissoes;
    private JpAmigos telaAmigos;
    private JpGuilda telaGuilda;

    private final StatusService playerService = new StatusService();

    public GameFrame() {
        setTitle("Room 5 Studios - Game");
        setSize(1000, 750);  // Tamanho do jogo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("imagens/login/logoEmpresa.png").getImage());
        setResizable(false);

        // Agora, o playerInfo está inicializado corretamente
        telaMenu = new JpMenu(this, playerInfo);
        currentPanel = telaMenu;
        add(currentPanel);
        
        setVisible(true);
    }

    // Método para mudar para a tela de Jogo
    public void switchToGamePanel() {
        remove(currentPanel);
        if (telaJogo == null) {
            telaJogo = new JpGame(this); // Cria a tela de jogo
        }
        currentPanel = telaJogo;
        add(currentPanel);
        revalidate();
        repaint();
    }

    // Método para voltar para a tela de Menu
    public void switchToMenuPanel() {
        remove(currentPanel);
        if (telaMenu == null) {
            telaMenu = new JpMenu(this, playerInfo); // Cria a tela de menu
        }
        currentPanel = telaMenu;
        add(currentPanel);
        revalidate();
        repaint();
    }

    public void switchToStatusPanel() {
        remove(currentPanel);
        if (telaStatus == null) {
            telaStatus = new JpStatus(this, playerInfo); // Cria a tela de jogo
        }
        currentPanel = telaStatus;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToInventarioPanel() {
        remove(currentPanel);
        if (telaInventario == null) {
            telaInventario = new JpInventario(this, playerInfo); // Cria a tela de jogo
        }
        currentPanel = telaInventario;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToMapaPanel() {
        remove(currentPanel);
        if (telaMapa == null) {
            telaMapa = new JpMapa(this, playerInfo); // Cria a tela de jogo
        }
        currentPanel = telaMapa;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToMissoesPanel() {
        remove(currentPanel);
        if (telaMissoes == null) {
            telaMissoes = new JpMissoes(this, playerInfo); // Cria a tela de jogo
        }
        currentPanel = telaMissoes;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToAmigosPanel() {
        remove(currentPanel);
        if (telaAmigos == null) {
            telaAmigos = new JpAmigos(this, playerInfo); // Cria a tela de jogo
        }
        currentPanel = telaAmigos;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToGuildaPanel() {
        remove(currentPanel);
        if (telaGuilda == null) {
            telaGuilda = new JpGuilda(this, playerInfo); // Cria a tela de jogo
        }
        currentPanel = telaGuilda;
        add(currentPanel);
        revalidate();
        repaint();
    }
}
