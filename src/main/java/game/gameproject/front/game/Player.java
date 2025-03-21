package game.gameproject.front.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.PlayerService;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static javax.swing.SwingConstants.CENTER;

public class Player extends JPanel implements KeyListener {

    private boolean tutorial = true;
    private int modeloSelecionado; // Modelo escolhido
    private String nomePersonagem; // Nome do Personagem
    private int nivel = 0; // Nivel do player
    private int dinheiro = 0; // Dinheiro do player
    public int vida = 50; // vida atual do player
    public int vidaMax = 50; // Vida maxima do player
    public int forca = 0; // força do player
    private int xp = 15; // XP atual
    private int xpMax = 100; // XP máximo
    private Color xpColor = Color.GREEN; // Cor da barra de XP
    private Color xpBgColor = Color.GRAY; // Cor de fundo da barra de XP
    private BufferedImage personagemCima1;
    private BufferedImage personagemCima2;
    private BufferedImage personagemBaixo1;
    private BufferedImage personagemBaixo2;
    private BufferedImage personagemEsquerda1;
    private BufferedImage personagemEsquerda2;
    private BufferedImage personagemDireita1;
    private BufferedImage personagemDireita2;
    private BufferedImage personagemAtual;
    private int xPersonagem; // Posição X inicial do boneco.
    private int yPersonagem; // Posição Y inicial do boneco.
    private int velocidade = 9; // Velocidade de movimentação do boneco
    private Timer timerAnimacao;
    private boolean movendoCima = false; // Ativação/Desativação da animação do boneco.
    private boolean movendoBaixo = false; // Ativação/Desativação da animação do boneco.
    private boolean movendoEsquerda = false; // Ativação/Desativação da animação do boneco.
    private boolean movendoDireita = false; // Ativação/Desativação da animação do boneco.
    private Mapa mapaAtual; // Referência para o mapa atual
    private infoPlayerDto playerInfo;
    private int lastX;
    private int lastY;
    private String sprite = "";
    
    private String personagemOn1 = "";
    private BufferedImage imgPersonagemOn1;
    private int xPersonagemOn1;
    private int yPersonagemOn1; 
    
    private boolean liberadoCarregar = false;

    public Player(String nome, int modelo, Mapa mapaInicial, infoPlayerDto playerInfo) { // Construtor com parâmetros
    	this.playerInfo = playerInfo;
    	
    	setNivel(playerInfo.getNivel());
    	setDinheiro(playerInfo.getDinheiro());
    	setVida(playerInfo.getVida());
        this.setLayout(null);
        
        PlayerService PS = new PlayerService();

        //Variaveis da classe Player
        this.nomePersonagem = nome; // Atribuir o nome do personagem
        this.modeloSelecionado = modelo; // Atribuir o modelo do personagem
        this.mapaAtual = mapaInicial; // Atribui o mapa inicial
        carregarImagens(); // Carregar imagens (agora as imagens dependem do modelo)
        personagemAtual = personagemDireita1; // Modelo/Posição Inicial do boneco
        setFocusable(true);
        xPersonagem = mapaAtual.getXSpawn(); // Define a posição inicial do player de acordo com o mapa
        yPersonagem = mapaAtual.getYSpawn(); // Define a posição inicial do player de acordo com o mapa
        // Timer para animação do personagem (apenas quando pressionar teclas)
        timerAnimacao = new Timer(60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                trocarImagem();
                moverPersonagem();
                repaint();
                
                System.out.println("Enviando coordenadas: ID: "+playerInfo.getIdPlayer()+" X:"+xPersonagem+" Y: "+yPersonagem 
                        +" Sprite: "+sprite);
                PS.salvarCoordenadas(playerInfo.getIdPlayer(), xPersonagem, yPersonagem, sprite);

                // Primeiro, buscar as informações do jogador 2
                buscarJogadorId2();

                // Aqui você pode então fazer a impressão ou o que for necessário
            }
        });

        // Adicione um listener para a tecla Tab
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    atualizar(); // Chama o método atualizar() quando Tab é pressionado
                }
            }
        });
        
        setFocusable(true);
        requestFocusInWindow();
    }

    // Metodo para carregar imagens do jogo
    private void carregarImagens() {
        try {
            personagemCima1 = ImageIO.read(new File(String.format("imagens/player/modelo%d/modeloCosta1.png", modeloSelecionado)));
            personagemCima2 = ImageIO.read(new File(String.format("imagens/player/modelo%d/modeloCosta2.png", modeloSelecionado)));
            personagemBaixo1 = ImageIO.read(new File(String.format("imagens/player/modelo%d/modeloFrente1.png", modeloSelecionado)));
            personagemBaixo2 = ImageIO.read(new File(String.format("imagens/player/modelo%d/modeloFrente2.png", modeloSelecionado)));
            personagemEsquerda1 = ImageIO.read(new File(String.format("imagens/player/modelo%d/modeloDireito1.png", modeloSelecionado)));
            personagemEsquerda2 = ImageIO.read(new File(String.format("imagens/player/modelo%d/modeloDireito2.png", modeloSelecionado)));
            personagemDireita1 = ImageIO.read(new File(String.format("imagens/player/modelo%d/modeloEsquerdo1.png", modeloSelecionado)));
            personagemDireita2 = ImageIO.read(new File(String.format("imagens/player/modelo%d/modeloEsquerdo2.png", modeloSelecionado)));
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagens na classe Player!" + e.getMessage());
            System.exit(1);
        }
    }

    // Metodo para trocar a imagem do personagem enquanto anda.
    private void trocarImagem() {
        int numeroImagem = 1; // Inicializa com o número 1.

        if (movendoCima) {
            personagemAtual = (personagemAtual == personagemCima1) ? personagemCima2 : personagemCima1;
            numeroImagem = (personagemAtual == personagemCima1) ? 1 : 2; // Alterna entre 1 e 2
            sprite = "imagens/player/modelo1d/modeloCosta" + numeroImagem + ".png"; // Usa o número dinamicamente
        } else if (movendoBaixo) {
            personagemAtual = (personagemAtual == personagemBaixo1) ? personagemBaixo2 : personagemBaixo1;
            numeroImagem = (personagemAtual == personagemBaixo1) ? 1 : 2; // Alterna entre 1 e 2
            sprite = "imagens/player/modelo1d/modeloFrente" + numeroImagem + ".png"; // Usa o número dinamicamente
        } else if (movendoEsquerda) {
            personagemAtual = (personagemAtual == personagemEsquerda1) ? personagemEsquerda2 : personagemEsquerda1;
            numeroImagem = (personagemAtual == personagemEsquerda1) ? 1 : 2; // Alterna entre 1 e 2
            sprite = "imagens/player/modelo1d/modeloDireito" + numeroImagem + ".png"; // Usa o número dinamicamente
        } else if (movendoDireita) {
            personagemAtual = (personagemAtual == personagemDireita1) ? personagemDireita2 : personagemDireita1;
            numeroImagem = (personagemAtual == personagemDireita1) ? 1 : 2; // Alterna entre 1 e 2
            sprite = "imagens/player/modelo1d/modeloEsquerdo" + numeroImagem + ".png"; // Usa o número dinamicamente
        }
    }

    // Método para mover o personagem (chama o mapa para verificar colisões)
    private void moverPersonagem() {
        // Limites da tela
        int larguraTela = getWidth();
        int alturaTela = 760; // Necessario colocar 760 para quem utilizar a resolução do windows no 150%

        // Verificar se pode mover nas direções e ATUALIZAR as coordenadas
        if (movendoCima && yPersonagem > 0) {
                yPersonagem -= velocidade; // Atualiza a coordenada yPersonagem
        }
        if (movendoBaixo && yPersonagem < alturaTela - 70) {
                yPersonagem += velocidade; // Atualiza a coordenada yPersonagem
        }
        if (movendoEsquerda && xPersonagem > 0) {
                xPersonagem -= velocidade; // Atualiza a coordenada xPersonagem
        }
        if (movendoDireita && xPersonagem < larguraTela - 40) {
                xPersonagem += velocidade; // Atualiza a coordenada xPersonagem
        }

        // Sistema para o personagem spawnar no local certo após mudar de mapa.
        if (mapaAtual.getNumeroMapa() == 1 && mapaAtual.musicam1.contains(xPersonagem, yPersonagem)) {

        } else if (mapaAtual.getNumeroMapa() == 1 && mapaAtual.areaMudancaFundo.contains(xPersonagem, yPersonagem)) { // Mundo 1 para o Mundo 2
            mudarMapa(new Mapa(2, this.personagemDireita1));
            xPersonagem = 1020; // Coordenada X para o Mundo 2
            yPersonagem = 260; // Coordenada Y para o Mundo 2
            tutorial = false;
        } else if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.areaMudancaFundo2.contains(xPersonagem, yPersonagem)) { // Mundo 2 para o Mundo 3
            mudarMapa(new Mapa(3, this.personagemDireita1));
            xPersonagem = 725; // Coordenada X para o Mundo 3
            yPersonagem = 600; // Coordenada Y para o Mundo 3
        } else if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.areaMudancaFundo4.contains(xPersonagem, yPersonagem)) { // Mundo 2 para o Mundo 1
            mudarMapa(new Mapa(1, this.personagemDireita1));
            xPersonagem = 575; // Coordenada X para o Mundo 1
            yPersonagem = 600; // Coordenada Y para o Mundo 1
        } else if (mapaAtual.getNumeroMapa() == 3 && mapaAtual.areaMudancaFundo3.contains(xPersonagem, yPersonagem)) { // Mundo 3 para o Mundo 2 
            mudarMapa(new Mapa(2, this.personagemDireita1));
            xPersonagem = 733; // Coordenada X para o Mundo 2
            yPersonagem = 10; // Coordenada Y para o Mundo 2
        }
    }
    // Método para atualizar o estado do personagem
    public void atualizar() {
        repaint();
        trocarImagem();
    }
    //Mapeamento de teclas para movimento do personagem
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
            case KeyEvent.VK_SHIFT:
                velocidade = 13;
                break;
        }
        if (!timerAnimacao.isRunning()) {
            timerAnimacao.start();
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
            case KeyEvent.VK_SHIFT:
                velocidade = 9;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não usado neste caso.
    }

    // Função para imprimir as coisas na tela (Essencial para o jogo Funcionar corretamente)
    @Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    mapaAtual.desenhar(g); // Desenha o fundo do mapa

    int larguraPersonagem = 30;
    int alturaPersonagem = 50;

    //Desenhando boneco
    g.drawImage(personagemAtual, xPersonagem, yPersonagem, larguraPersonagem, alturaPersonagem, this);
    
    g.drawImage(personagemAtual, xPersonagemOn1, yPersonagemOn1, larguraPersonagem, alturaPersonagem, this);
  

    // Nome do jogador
    g.setFont(new Font("Arial", Font.BOLD, 16));
    g.setColor(Color.WHITE);
    g.drawString("Personagem: " + nomePersonagem, 10, 20);

    // Nível do jogador
    g.setFont(new Font("Arial", Font.BOLD, 20));
    g.setColor(Color.WHITE);
    g.drawString("Nivel: " + nivel, 300, 105);

    // Barra de vida
    if (vida > 0) {
        g.setColor(Color.RED);
        g.fillRect(10, 27, (int) (vida * 2), 15);
    }

    // Barra de XP
    g.setColor(xpBgColor);
    g.fillRect(10, 45, 400, 10);
    g.setColor(xpColor);
    g.fillRect(10, 45, (int) (((double) xp / xpMax) * 400), 10);
}

    //Função que adiciona vida
    public void setVida(int vida) {
        this.vida = vida;
    }

    // Método para trocar o mapa atual
    public void mudarMapa(Mapa novoMapa) {
        this.mapaAtual = novoMapa;
        xPersonagem = mapaAtual.getXSpawn();
        yPersonagem = mapaAtual.getYSpawn();
    }
    //Usado para parar o personagem em interacoes
    public void resetarMovimento() {
        movendoCima = false;
        movendoBaixo = false;
        movendoEsquerda = false;
        movendoDireita = false;
        velocidade = 9;
        if (timerAnimacao.isRunning()) {
            timerAnimacao.stop();
        }
    }
    //Checa a vida maxima se passou de vida maxima
    public void verificarVidaMaxima() {
        if (vida > vidaMax) {
            vida = vidaMax;
        }
    }

    
    public int setNivel(int valor) {
        return this.nivel += valor;
    }

    //Adiciona dinheiro ao player
    public int setDinheiro(int valor) {
        return this.dinheiro += valor;
    }
    //Retorna o valor de dinheiro
    public int getDinheiro() {
        return dinheiro;
    }
    //Retonar o valor de forca do personagem
    public int getForca() {
        return forca;
    }
    //Adiciona forca ao personagem
    public void setForca(int forca) {
        this.forca = forca;
    }
    //Retorna o valor de vida do personagem
    public int getVida() {
        return vida;
    }
    //Pega a posicao X do personagem
    public int getPersonagemX() {
        return xPersonagem;
    }
    //Pega a posicao Y do personagem
    public int getPersonagemY() {
        return yPersonagem;
    }
    //Salva a posicao X e Y do personagem
    public void salvarPosicao() {
        this.lastX = this.xPersonagem;
        this.lastY = this.yPersonagem;
    }
    //Restaura a posicao do personagem baseado no salvarposicao();
    public void restaurarPosicao() {
        this.xPersonagem = this.lastX;
        this.yPersonagem = this.lastY + 20 ;
    }

        public void buscarJogadorId2() {
            String sql = "SELECT x, y, sprite FROM tb_player_coordenadas WHERE id_player = 2"; // Consulta SQL

            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                ResultSet rs = stmt.executeQuery(); // Executa a consulta

                if (rs.next()) { // Se encontrar o jogador com ID 2
                    // Atribui os valores das colunas às variáveis
                    xPersonagemOn1 = rs.getInt("x");
                    yPersonagemOn1 = rs.getInt("y");
                    personagemOn1 = rs.getString("sprite");
                    
                    liberadoCarregar = true;
                }

            } catch (SQLException e) {
                e.printStackTrace(); // Trata exceção caso ocorra
            }
        }

        // Getters para acessar as variáveis
        public int getxPersonagemOn1() {
            return xPersonagemOn1;
        }

        public int getyPersonagemOn1() {
            return yPersonagemOn1;
        }

        public String getPersonagemOn1() {
            return personagemOn1;
        }
    }