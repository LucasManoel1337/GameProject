package game.gameproject.front.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.Jogador;
import game.gameproject.dto.VersoesDto;
import game.gameproject.dto.chatDto;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.ChatGlobalService;
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
    
    public int mapa;
    
    chatDto CD = new chatDto();
    VersoesDto VD = new VersoesDto();
    
    public boolean chatAtivo = CD.isChatAtivo();
    
    public List<Jogador> jogadores = new ArrayList<>();
    
    private JTextField JTFChat = new JTextField();
    private JButton bChatEnviar = new JButton("Enviar");
    
    private JTextArea chatArea = new JTextArea();
    private JScrollPane chatScrollPane = new JScrollPane(chatArea);
    
    private JProgressBar jBarXp = new JProgressBar();
    private JProgressBar jBarVida = new JProgressBar();
    private JProgressBar jBarMana = new JProgressBar();
    private JProgressBar jBarStamina = new JProgressBar();
    
    
    PlayerService PS = new PlayerService();
    DatabaseConfig bdd = new DatabaseConfig();
    PaintComponentService PCS = new PaintComponentService();
    ChatGlobalService CGS = new ChatGlobalService();
    
    private int digitarIndex = 0;
    private Timer timerDigitar;
    private BufferedImage[] digitar = new BufferedImage[6];

    public Player(String nome, Mapa mapaInicial, infoPlayerDto playerInfo) {
    	this.playerInfo = playerInfo;
        this.setLayout(null);
        this.mapaAtual = mapaInicial;
        
        setDoubleBuffered(true);
        carregarImagens();
        personagemAtual = personagemBaixo1;
        setFocusable(true);
        xPersonagem = PS.buscarCoordenadaX(playerInfo.getIdPlayer());
        yPersonagem = PS.buscarCoordenadaY(playerInfo.getIdPlayer());
        sprite = PS.buscarSprite(playerInfo.getIdPlayer());
        //mapa = PS.buscarMapa(playerInfo.getIdPlayer());
        
        setFocusable(true);
        requestFocusInWindow();
        
        JTFChat.setBounds(5, 695, 300, 30);
        JTFChat.setBackground(new Color(211, 211, 211, 150));
        JTFChat.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JTFChat.setVisible(false);
        JTFChat.addActionListener(e -> bChatEnviar.doClick());
        this.add(JTFChat);

        // Botão de envio
        bChatEnviar.setBounds(310, 695, 70, 30);
        bChatEnviar.setBackground(new Color(32, 3, 3));
        bChatEnviar.setForeground(Color.WHITE);
        bChatEnviar.setFocusPainted(false);
        bChatEnviar.setOpaque(true);
        bChatEnviar.setContentAreaFilled(true);
        bChatEnviar.setBorderPainted(false);
        bChatEnviar.setVisible(false);
        bChatEnviar.addActionListener(e -> {
            CGS.enviarMensagem(playerInfo.getIdPlayer(), playerInfo.getNickPlayer(), JTFChat.getText());
            JTFChat.setText("");
            carregarMensagens();
        });
        this.add(bChatEnviar);

        chatArea.setEditable(false);
        chatArea.setBackground(new Color(255, 255, 255, 150));
        chatArea.setForeground(Color.BLACK);
        chatArea.setRows(20);
        chatArea.setColumns(30);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFocusable(false);
        chatArea.setBorder(null);
        chatArea.setVisible(false);

        chatScrollPane.setBounds(5, 410, 375, 280);
        chatScrollPane.setVisible(false);
        chatScrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        chatScrollPane.getVerticalScrollBar().setOpaque(false);
        chatScrollPane.getHorizontalScrollBar().setOpaque(false);

        chatScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        chatScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        chatScrollPane.setBorder(null);

        this.add(chatScrollPane);
        carregarMensagens();
        
        jBarXp.setForeground(new Color(0, 255, 0)); 
        jBarXp.setMaximum(playerInfo.getXpMaxima());
        jBarXp.setMinimum(0);
        jBarXp.setValue(playerInfo.getXpAtual());
        jBarXp.setBorder(null);
        jBarXp.setBorderPainted(false);
        jBarXp.setBounds(5, 5, 300, 12);
        jBarXp.setStringPainted(true);
        jBarXp.setString(playerInfo.getXpAtual()+"/"+playerInfo.getXpMaxima());
        jBarXp.setIndeterminate(false);
        this.add(jBarXp);

        jBarVida.setForeground(Color.RED);
        jBarVida.setMaximum(playerInfo.getVidaMaxima());
        jBarVida.setMinimum(0);
        jBarVida.setValue(playerInfo.getVidaAtual());
        jBarVida.setBorder(null);
        jBarVida.setBorderPainted(false);
        jBarVida.setBounds(5, 18, 300, 12);
        jBarVida.setStringPainted(true);
        jBarVida.setString(playerInfo.getVidaAtual()+"/"+playerInfo.getVidaMaxima());
        jBarVida.setIndeterminate(false);
        this.add(jBarVida);

        jBarMana.setForeground(new Color(0, 255, 255));
        jBarMana.setMaximum(playerInfo.getManaMaxima());
        jBarMana.setMinimum(0);
        jBarMana.setValue(playerInfo.getManaAtual());
        jBarMana.setBorder(null);
        jBarMana.setBorderPainted(false);
        jBarMana.setBounds(5, 31, 300, 12);
        jBarMana.setStringPainted(true);
        jBarMana.setString(playerInfo.getManaAtual()+"/"+playerInfo.getManaMaxima());
        jBarMana.setIndeterminate(false);
        this.add(jBarMana);

        jBarStamina.setForeground(new Color(0, 51, 25));
        jBarStamina.setMaximum(playerInfo.getStaminaMaxima());
        jBarStamina.setMinimum(0);
        jBarStamina.setValue(playerInfo.getStaminaAtual());
        jBarStamina.setBorder(null);
        jBarStamina.setBorderPainted(false);
        jBarStamina.setBounds(5, 44, 300, 12);
        jBarStamina.setStringPainted(true);
        jBarStamina.setString(playerInfo.getStaminaAtual()+"/"+playerInfo.getStaminaMaxima());
        jBarStamina.setIndeterminate(false);
        this.add(jBarStamina);
        
        timerDigitar = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animarDigitar();
            }
        });
        timerDigitar.start();

    }
    
    private void animarDigitar() {
        digitarIndex = (digitarIndex + 1) % digitar.length;
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
            digitar[0] = ImageIO.read(new File("imagens/game/animacaoDigitando/digitando1.png"));
            digitar[1] = ImageIO.read(new File("imagens/game/animacaoDigitando/digitando2.png"));
            digitar[2] = ImageIO.read(new File("imagens/game/animacaoDigitando/digitando3.png"));
            digitar[3] = ImageIO.read(new File("imagens/game/animacaoDigitando/digitando4.png"));
            digitar[4] = ImageIO.read(new File("imagens/game/animacaoDigitando/digitando5.png"));
            digitar[5] = ImageIO.read(new File("imagens/game/animacaoDigitando/digitando6.png"));
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
        	if(VD.isModoDev()) {
        		g.setColor(Color.black);
            	g.drawRect(jogador.getxPlayer(), jogador.getyPlayer(), 30, 50);
        	}
            g.drawImage(jogador.getSpritePlayer(), jogador.getxPlayer(), jogador.getyPlayer(), 30, 50, this);
            
            if (jogador.equals(playerInfo)) {
                PCS.desenharNomeJogador(g, jogador.getNomePlayer(), jogador.getxPlayer(), jogador.getyPlayer(), 30);
            } else {
            	PCS.desenharNomeJogador(g, jogador.getNomePlayer(), jogador.getxPlayer(), jogador.getyPlayer(), 30);
            }
            
            if (jogador.getDigitando()) {
                g.drawImage(digitar[digitarIndex], jogador.getxPlayer(), jogador.getyPlayer() - 60, 30, 30, this);
            }
        }
        
        if(VD.isModoDev()) {
        	g.setColor(Color.RED);
            g.drawRect(xPersonagem, yPersonagem, larguraPersonagem, alturaPersonagem);
        }
        g.drawImage(personagemAtual, xPersonagem, yPersonagem, larguraPersonagem, alturaPersonagem, this);
        
        PCS.desenharNomeJogador(g, playerInfo.getNickPlayer(), xPersonagem, yPersonagem, larguraPersonagem);
        
        g.drawImage(hotbar, 385, 678, 500, 50, this);
        
        PCS.desenharInformacoes(g);
        
        if(CD.isChatAtivo()) {
        	JTFChat.setVisible(true);
        	bChatEnviar.setVisible(true);
        	chatScrollPane.setVisible(true);
        	chatArea.setVisible(true);
        	g.drawImage(digitar[digitarIndex], xPersonagem, yPersonagem-60, 30, 30, this);
        	carregarMensagens();
        } else {
        	JTFChat.setText("");
        	JTFChat.setVisible(false);
        	bChatEnviar.setVisible(false);
        	chatScrollPane.setVisible(false);
        	chatArea.setVisible(false);
        }
        
        recarregarStatusHub();
    }

    public void mudarMapa(Mapa novoMapa) {
        this.mapaAtual = novoMapa;
        xPersonagem = mapaAtual.getXSpawn();
        yPersonagem = mapaAtual.getYSpawn();
    }

    private void carregarMensagens() {
        String mensagens = CGS.obterUltimasMensagens();
        chatArea.setText(mensagens);
    }
    
    private void recarregarStatusHub() {
    	jBarXp.setValue(playerInfo.getXpAtual());
    	jBarVida.setValue(playerInfo.getVidaAtual());
    	jBarMana.setValue(playerInfo.getManaAtual());
    	jBarStamina.setValue(playerInfo.getStaminaAtual());
    }
    
    public void resetarMovimento() {
        movendoCima = false;
        movendoBaixo = false;
        movendoEsquerda = false;
        movendoDireita = false;
        velocidade = 5;
    }
}