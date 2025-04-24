package game.gameproject.front.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.Jogador;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.PaintComponentService;
import game.gameproject.services.PlayerService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player extends JPanel implements KeyListener {

    private BufferedImage personagemCima1, personagemCima2;
    private BufferedImage personagemBaixo1, personagemBaixo2;
    private BufferedImage personagemEsquerda1, personagemEsquerda2;
    private BufferedImage personagemDireita1, personagemDireita2;
    private BufferedImage personagemAtual;
    private BufferedImage hotbar;
    private BufferedImage fundoTest;
    public int xPersonagem;
    public int yPersonagem;
    private int velocidade = 5;
    private boolean movendoCima = false;
    private boolean movendoBaixo = false;
    private boolean movendoEsquerda = false;
    private boolean movendoDireita = false;
    private Mapa mapaAtual;
    private infoPlayerDto playerInfo;
    public String sprite = "";
    
    public List<Jogador> jogadores = new ArrayList<>();
    
    PlayerService PS = new PlayerService();
    DatabaseConfig bdd = new DatabaseConfig();
    PaintComponentService PCS = new PaintComponentService();

    public Player(String nome, Mapa mapaInicial, infoPlayerDto playerInfo) {
    	this.playerInfo = playerInfo;
        this.setLayout(null);
        this.mapaAtual = mapaInicial;
        
        setDoubleBuffered(true); // Garante o double buffering
        carregarImagens();
        personagemAtual = personagemBaixo1;
        setFocusable(true);
        xPersonagem = mapaAtual.getXSpawn();
        yPersonagem = mapaAtual.getYSpawn();
        
        setFocusable(true);
        requestFocusInWindow();
        
    }

    private void carregarImagens() {
        try {
            personagemCima1 = ImageIO.read(new File(String.format("imagens/player/"+playerInfo.getClasse()+"/modeloCosta1.png")));
            personagemCima2 = ImageIO.read(new File(String.format("imagens/player/"+playerInfo.getClasse()+"/modeloCosta2.png")));
            personagemBaixo1 = ImageIO.read(new File(String.format("imagens/player/"+playerInfo.getClasse()+"/modeloFrente1.png")));
            personagemBaixo2 = ImageIO.read(new File(String.format("imagens/player/"+playerInfo.getClasse()+"/modeloFrente2.png")));
            personagemEsquerda1 = ImageIO.read(new File(String.format("imagens/player/"+playerInfo.getClasse()+"/modeloDireito1.png")));
            personagemEsquerda2 = ImageIO.read(new File(String.format("imagens/player/"+playerInfo.getClasse()+"/modeloDireito2.png")));
            personagemDireita1 = ImageIO.read(new File(String.format("imagens/player/"+playerInfo.getClasse()+"/modeloEsquerdo1.png")));
            personagemDireita2 = ImageIO.read(new File(String.format("imagens/player/"+playerInfo.getClasse()+"/modeloEsquerdo2.png")));
            hotbar = ImageIO.read(new File(String.format("imagens/game/interface/hotbar.png")));
            fundoTest = ImageIO.read(new File(String.format("imagens/game/fundos/fundo.png")));
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagens na classe Player!" + e.getMessage());
            System.exit(1);
        }
    }

    public void trocarImagem() {
        int numeroImagem = 1;

        if (movendoCima) {
            personagemAtual = (personagemAtual == personagemCima1) ? personagemCima2 : personagemCima1;
            numeroImagem = (personagemAtual == personagemCima1) ? 1 : 2;
            sprite = "imagens/player/"+playerInfo.getClasse()+"/modeloCosta" + numeroImagem + ".png";
        } else if (movendoBaixo) {
            personagemAtual = (personagemAtual == personagemBaixo1) ? personagemBaixo2 : personagemBaixo1;
            numeroImagem = (personagemAtual == personagemBaixo1) ? 1 : 2;
            sprite = "imagens/player/"+playerInfo.getClasse()+"/modeloFrente" + numeroImagem + ".png";
        } else if (movendoEsquerda) {
            personagemAtual = (personagemAtual == personagemEsquerda1) ? personagemEsquerda2 : personagemEsquerda1;
            numeroImagem = (personagemAtual == personagemEsquerda1) ? 1 : 2;
            sprite = "imagens/player/"+playerInfo.getClasse()+"/modeloDireito" + numeroImagem + ".png";
        } else if (movendoDireita) {
            personagemAtual = (personagemAtual == personagemDireita1) ? personagemDireita2 : personagemDireita1;
            numeroImagem = (personagemAtual == personagemDireita1) ? 1 : 2;
            sprite = "imagens/player/"+playerInfo.getClasse()+"/modeloEsquerdo" + numeroImagem + ".png";
        }
    }

    public void moverPersonagem() {
        int larguraTela = getWidth();
        int alturaTela = 760;

        if (movendoCima && yPersonagem > 0) {
                yPersonagem -= velocidade;
        }
        if (movendoBaixo && yPersonagem < alturaTela - 70) {
                yPersonagem += velocidade;
        }
        if (movendoEsquerda && xPersonagem > 0) {
                xPersonagem -= velocidade;
        }
        if (movendoDireita && xPersonagem < larguraTela - 40) {
                xPersonagem += velocidade;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int codigo = e.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_W:
                movendoCima = true;
                break;
            case KeyEvent.VK_S:
                movendoBaixo = true;
                break;
            case KeyEvent.VK_A:
                movendoEsquerda = true;
                break;
            case KeyEvent.VK_D:
                movendoDireita = true;
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int codigo = e.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_W:
                movendoCima = false;
                break;
            case KeyEvent.VK_S:
                movendoBaixo = false;
                break;
            case KeyEvent.VK_A:
                movendoEsquerda = false;
                break;
            case KeyEvent.VK_D:
                movendoDireita = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não usado neste caso.
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        mapaAtual.desenhar(g);

        int larguraPersonagem = 30;
        int alturaPersonagem = 50;

        g.drawImage(fundoTest, 0, 0, 1280, 768, this);

        for (Jogador jogador : jogadores) {
            g.drawImage(jogador.getSpritePlayer(), jogador.getxPlayer(), jogador.getyPlayer(), 30, 50, this);
            
            if (jogador.equals(playerInfo)) {
                PCS.desenharNomeJogador(g, jogador.getNomePlayer(), jogador.getxPlayer(), jogador.getyPlayer(), 30);
            } else {
            	PCS.desenharNomeJogador(g, jogador.getNomePlayer(), jogador.getxPlayer(), jogador.getyPlayer(), 30);
            }
        }

        g.drawImage(personagemAtual, xPersonagem, yPersonagem, larguraPersonagem, alturaPersonagem, this);
        
        PCS.desenharNomeJogador(g, playerInfo.getNickPlayer(), xPersonagem, yPersonagem, larguraPersonagem);
        
        g.drawImage(hotbar, 385, 678, 500, 50, this);
        
        PCS.desenharInformacoes(g);
        
        JTextField JTFChat = new JTextField();
        JTFChat.setBounds(5, 695, 300, 30);
        JTFChat.setBackground(Color.LIGHT_GRAY);
        JTFChat.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.add(JTFChat);
        
        JButton bChatEnviar = new JButton("Enviar");
        bChatEnviar.setBounds(310, 695, 70, 30);
        bChatEnviar.setBackground(new Color(32, 3, 3));
        bChatEnviar.setForeground(Color.WHITE);
        bChatEnviar.setFocusPainted(false);
        bChatEnviar.setOpaque(true);
        bChatEnviar.setContentAreaFilled(true);
        bChatEnviar.setBorderPainted(false);
        bChatEnviar.setVisible(true);
        this.add(bChatEnviar);
    }

    public void mudarMapa(Mapa novoMapa) {
        this.mapaAtual = novoMapa;
        xPersonagem = mapaAtual.getXSpawn();
        yPersonagem = mapaAtual.getYSpawn();
    }

    public void resetarMovimento() {
        movendoCima = false;
        movendoBaixo = false;
        movendoEsquerda = false;
        movendoDireita = false;
        velocidade = 5;
    }
}