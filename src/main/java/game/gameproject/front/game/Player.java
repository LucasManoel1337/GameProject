package game.gameproject.front.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import game.gameproject.dto.ConfiguracoesDto;
import game.gameproject.dto.Jogador;
import game.gameproject.dto.chatDto;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.PlayerService;
import game.gameproject.services.StatusService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player extends JPanel implements KeyListener {

    private BufferedImage fundoTest;
    private Mapa mapaAtual;
    public String sprite = "";
    
    public int mapa;
    
    public List<Jogador> jogadores = new ArrayList<>();
    
    // Instanciamento de outras classes
    private final StatusService SS = new StatusService();
    private final infoPlayerDto playerInfo;
    private final ConfiguracoesDto Config = new ConfiguracoesDto();
    private final interfaceHub IH;
    private final PlayerService PS = new PlayerService();
    private final ChatUI ChatUI;
    
    // Imagens de animação do personagem
    private static BufferedImage personagemCima1, personagemCima2;
    private static BufferedImage personagemBaixo1, personagemBaixo2;
    private static BufferedImage personagemEsquerda1, personagemEsquerda2;
    private static BufferedImage personagemDireita1, personagemDireita2;
    private static BufferedImage personagemAtual;
    
    // Coordenadas e movimentação
    public static final int larguraPersonagem = 30;
    public static final int alturaPersonagem = 50;
    public int xPersonagem;
    public int yPersonagem;
    private int velocidade = 5;
    private boolean movendoCima = false;
    private boolean movendoBaixo = false;
    private boolean movendoEsquerda = false;
    private boolean movendoDireita = false;
    private boolean travaMorte = true;
    
    public Player(String nome, Mapa mapaInicial, infoPlayerDto playerInfo) {
    	this.playerInfo = playerInfo;
        this.setLayout(null);
        this.mapaAtual = mapaInicial;
        this.IH = new interfaceHub(this.playerInfo, this);
        this.ChatUI = new ChatUI(this.playerInfo);
        ChatUI.addToParent(this);
        
        setFocusable(true);
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocusInWindow();
        
        carregarImagens();
        personagemAtual = personagemBaixo1;
        xPersonagem = PS.buscarCoordenadaX(playerInfo.getIdPlayer());
        yPersonagem = PS.buscarCoordenadaY(playerInfo.getIdPlayer());
        sprite = PS.buscarSprite(playerInfo.getIdPlayer());
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
            fundoTest = ImageIO.read(new File(String.format("imagens/game/fundos/fundo.png"))); // Provisorio até o sistema de mapas
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
        if(travaMorte) {
        	switch (codigo) {
            case KeyEvent.VK_W:
                movendoCima = true;
                velocidade = 5;
                break;
            case KeyEvent.VK_S:
                movendoBaixo = true;
                velocidade = 5;
                break;
            case KeyEvent.VK_A:
                movendoEsquerda = true;
                velocidade = 5;
                break;
            case KeyEvent.VK_D:
                movendoDireita = true;
                velocidade = 5;
                break;
        }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int codigo = e.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_W:
                movendoCima = false;
                if(movendoCima == false && movendoBaixo == false && movendoDireita == false && movendoEsquerda == false ) {
                	velocidade = 0;
                }
                break;
            case KeyEvent.VK_S:
                movendoBaixo = false;
                if(movendoCima == false && movendoBaixo == false && movendoDireita == false && movendoEsquerda == false ) {
                	velocidade = 0;
                }
                break;
            case KeyEvent.VK_A:
                movendoEsquerda = false;
                if(movendoCima == false && movendoBaixo == false && movendoDireita == false && movendoEsquerda == false ) {
                	velocidade = 0;
                }
                break;
            case KeyEvent.VK_D:
                movendoDireita = false;
                if(movendoCima == false && movendoBaixo == false && movendoDireita == false && movendoEsquerda == false ) {
                	velocidade = 0;
                }
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

        g.drawImage(fundoTest, 0, 0, 1280, 768, this); // Provisorio até o sistema de mapas

        if (Config.isVisualizarOutrosJogadores()) {
            for (Jogador jogador : jogadores) {
                if (Config.isModoDev()) {
                    g.setColor(Color.black);
                    g.drawRect(jogador.getxPlayer(), jogador.getyPlayer(), 30, 50);
                }

                g.drawImage(jogador.getSpritePlayer(), jogador.getxPlayer(), jogador.getyPlayer(), 30, 50, this);

                String nomeExibicao = jogador.isOp() ? "[ADMIN] " + jogador.getNomePlayer() : jogador.getNomePlayer();

                IH.desenharNomeJogador(g, nomeExibicao, jogador.getxPlayer(), jogador.getyPlayer(), 30);

                if (jogador.getDigitando()) {
                    g.drawImage(ChatUI.getTypingImage(), jogador.getxPlayer(), jogador.getyPlayer() - 60, 30, 30, this);
                }
            }
        }
        
        if(travaMorte) {
        	if(Config.isModoDev()) {
        		g.setColor(Color.RED);
        		g.drawRect(xPersonagem, yPersonagem, larguraPersonagem, alturaPersonagem);
        	}
        	g.drawImage(personagemAtual, xPersonagem, yPersonagem, larguraPersonagem, alturaPersonagem, this);
        
        
        	if (!playerInfo.isOp()) {
        		IH.desenharNomeJogador(g, playerInfo.getNickPlayer(), xPersonagem, yPersonagem, larguraPersonagem);
        	} else {
        		IH.desenharNomeJogador(g, "[ADMIN] " + playerInfo.getNickPlayer(), xPersonagem, yPersonagem, larguraPersonagem);
        	}
        } else {
        	// tela de morte e renascimento!
        	System.out.println("VOCE MORREU!");
        }
        
        ChatUI.render(g, xPersonagem, yPersonagem);
        
        IH.desenharHubStats(g, this);
        
        verificarStatus();
    }
    
    public void verificarStatus() {
    	if(playerInfo.getXpAtual() > playerInfo.getXpMaxima()) {
    		playerInfo.setXpAtual(playerInfo.getXpAtual() - playerInfo.getXpMaxima());
    		playerInfo.setXpMaxima(playerInfo.getXpMaxima()*2);
    		playerInfo.setNivel(playerInfo.getNivel()+1);
    		playerInfo.setPontos(playerInfo.getPontos()+1);
    		
    		SS.atualizarStatusBanco(playerInfo.getIdPlayer(), playerInfo.getNivel(), playerInfo.getPontos(), playerInfo.getVidaMaxima(), playerInfo.getStaminaMaxima(), playerInfo.getForca(), playerInfo.getManaMaxima(), playerInfo.getForcaMana(), playerInfo.getDinheiro(), playerInfo.getClasse(), playerInfo.getXpAtual(), playerInfo.getXpMaxima());
    	}
    	
    	if(playerInfo.getVidaAtual() < 0) {
    		playerInfo.setVidaAtual(0);
    		
    		travaMorte = false;
    		
    		resetarMovimento();
    	}
    }
    
    public void resetarMovimento() {
        movendoCima = false;
        movendoBaixo = false;
        movendoEsquerda = false;
        movendoDireita = false;
        velocidade = 5;
    }
    
    public int getVelocidade() {
    	return velocidade;
    }
}