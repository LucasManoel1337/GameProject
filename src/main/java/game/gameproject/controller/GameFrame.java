package game.gameproject.controller;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.front.JpAmigos;
import game.gameproject.front.JpConfiguracoes;
import game.gameproject.front.JpCreditos;
import game.gameproject.front.JpEscolherClasse;
import game.gameproject.front.JpEstatisticas;
import game.gameproject.front.JpMenu;
import game.gameproject.front.JpGame;
import game.gameproject.front.JpGuilda;
import game.gameproject.front.JpInventario;
import game.gameproject.front.JpMapa;
import game.gameproject.front.JpMissoes;
import game.gameproject.front.JpStatus;
import game.gameproject.services.PlayerService;
import game.gameproject.support.ImagemDiretorios;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class GameFrame extends JFrame {

    private static JPanel currentPanel;
    private JpMenu telaMenu;
    private JpGame telaJogo;
    infoPlayerDto playerInfo = new infoPlayerDto("a");

    private JpStatus telaStatus;
    private JpInventario telaInventario;
    private JpMapa telaMapa;
    private JpMissoes telaMissoes;
    private JpAmigos telaAmigos;
    private JpGuilda telaGuilda;
    private JpConfiguracoes telaConfiguracoes;
    private JpCreditos telaCreditos;
    private JpEstatisticas telaEstatisticas;
    private JpEscolherClasse telaEscolherClasse;
    
    private final ScheduledExecutorService scheduler;

    private PlayerService PS = new PlayerService();

    public GameFrame(infoPlayerDto playerInfo) {
        this.playerInfo = playerInfo;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();

        ImagemDiretorios ImgD = new ImagemDiretorios();

        setTitle("Room 5 Studios - Game");
        setSize(1280, 768);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(ImgD.getGameIcon()).getImage());

        // Inicializa o painel de menu
        telaMenu = new JpMenu(this, playerInfo);
        currentPanel = telaMenu;
        add(currentPanel);

        // Substitui EXIT_ON_CLOSE por DO_NOTHING, para controlar o shutdown
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 1) Marca o jogador como offline
                int idPlayer = playerInfo.getIdPlayer();
                if (PS.setOffline(idPlayer) == 1) {
                    System.out.println("Jogador " + idPlayer + " desconectado ao fechar o jogo.");
                } else {
                    System.err.println("Falha ao atualizar status do jogador.");
                }

                // 2) Se houver scheduler, encerre-o primeiro
                scheduler.shutdown();
                try {
                    if (!scheduler.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                        scheduler.shutdownNow();
                    }
                } catch (InterruptedException ex) {
                    scheduler.shutdownNow();
                    Thread.currentThread().interrupt();
                }

                // 3) Fecha o pool de conexões
                DatabaseConfig.close();

                // 4) Fecha a aplicação
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    /** Opcional: método para trocar de painel em tempo de execução */
    public void setCurrentPanel(JPanel painel) {
        getContentPane().removeAll();
        currentPanel = painel;
        add(currentPanel);
        revalidate();
        repaint();
    }

    // Método para mudar para a tela de Jogo
    public void switchToGamePanel() {
        remove(currentPanel);
        if (telaJogo == null) {
            telaJogo = new JpGame(this, playerInfo); // Cria a tela de jogo
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
        telaStatus.atualizarLabelsEAtualizar();
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
    
    public void switchToConfiguracoesPanel() {
        remove(currentPanel);
        if (telaConfiguracoes == null) {
            telaConfiguracoes = new JpConfiguracoes(this, playerInfo);
        }
        currentPanel = telaConfiguracoes;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToCreditosPanel() {
        remove(currentPanel);
        if (telaCreditos == null) {
            telaCreditos = new JpCreditos(this, playerInfo);
        }
        currentPanel = telaCreditos;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToEstatisticasPanel() {
        remove(currentPanel);
        if (telaEstatisticas == null) {
            telaEstatisticas = new JpEstatisticas(this, playerInfo);
        }
        currentPanel = telaEstatisticas;
        telaEstatisticas.atualizarEstatisticas();
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToEscolherClassePanel() {
        remove(currentPanel);
        if (telaEscolherClasse == null) {
            telaEscolherClasse = new JpEscolherClasse(this, playerInfo);
        }
        currentPanel = telaEscolherClasse;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public JPanel getCurrentPanel() {
        return currentPanel;
    }
}
