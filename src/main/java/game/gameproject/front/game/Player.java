package game.gameproject.front.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.Jogador;
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


public class Player extends JPanel implements KeyListener {

    private int modeloSelecionado = 1; 
    private BufferedImage personagemCima1;
    private BufferedImage personagemCima2;
    private BufferedImage personagemBaixo1;
    private BufferedImage personagemBaixo2;
    private BufferedImage personagemEsquerda1;
    private BufferedImage personagemEsquerda2;
    private BufferedImage personagemDireita1;
    private BufferedImage personagemDireita2;
    private BufferedImage personagemAtual;
    private BufferedImage hotbar;
    private BufferedImage fundoTest;
    private BufferedImage simOnline;
    private BufferedImage simPing1;
    private BufferedImage simPing2;
    private BufferedImage simPing3;
    private BufferedImage simPing4;
    private BufferedImage simPing5;
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
    
    private List<Jogador> jogadores = new ArrayList<>();
    
    PlayerService PS = new PlayerService();
    DatabaseConfig bdd = new DatabaseConfig();

    public Player(String nome, Mapa mapaInicial, infoPlayerDto playerInfo) { // Construtor com parâmetros
    	this.playerInfo = playerInfo;
        this.setLayout(null);

        this.mapaAtual = mapaInicial; // Atribui o mapa inicial
        carregarImagens(); // Carregar imagens (agora as imagens dependem do modelo)
        personagemAtual = personagemBaixo1; // Modelo/Posição Inicial do boneco
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
                
                //System.out.println("Enviando coordenadas: ID: "+playerInfo.getIdPlayer()+" X:"+xPersonagem+" Y: "+yPersonagem 
                        //+" Sprite: "+sprite);
                PS.salvarCoordenadas(playerInfo.getIdPlayer(), xPersonagem, yPersonagem, sprite);

                // Primeiro, buscar as informações do jogador 2
                buscarJogadores();
                // Aqui você pode então fazer a impressão ou o que for necessário
            }
        });
        
        timerAnimacao.start();

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
            hotbar = ImageIO.read(new File(String.format("imagens/game/interface/hotbar.png")));
            fundoTest = ImageIO.read(new File(String.format("imagens/game/fundos/fundo.png")));
            simOnline = ImageIO.read(new File(String.format("imagens/game/interface/online.png")));
            simPing1 = ImageIO.read(new File(String.format("imagens/game/interface/ping1.png")));
            simPing2 = ImageIO.read(new File(String.format("imagens/game/interface/ping2.png")));
            simPing3 = ImageIO.read(new File(String.format("imagens/game/interface/ping3.png")));
            simPing4 = ImageIO.read(new File(String.format("imagens/game/interface/ping4.png")));
            simPing5 = ImageIO.read(new File(String.format("imagens/game/interface/ping5.png")));
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

        g.drawImage(fundoTest, 0, 0, 1280, 768, this);

        for (Jogador jogador : jogadores) {
            // Desenha o sprite do jogador
            g.drawImage(jogador.getSpritePlayer(), jogador.getxPlayer(), jogador.getyPlayer(), 30, 50, this);
            
            // Verifica se o jogador é o jogador controlado pelo usuário (playerInfo)
            if (jogador.equals(playerInfo)) {
                // Desenha o nome do jogador controlado pelo usuário com a tag "[ADM]" se necessário
                desenharNomeJogador(g, jogador.getNomePlayer(), jogador.getxPlayer(), jogador.getyPlayer(), 30, true);
            } else {
                // Desenha o nome dos outros jogadores sem a tag "[ADM]"
                desenharNomeJogador(g, jogador.getNomePlayer(), jogador.getxPlayer(), jogador.getyPlayer(), 30, false);
            }
        }

        // Desenha o personagem do jogador 2 (se necessário)
        g.drawImage(personagemAtual, xPersonagem, yPersonagem, larguraPersonagem, alturaPersonagem, this);
        
        // Desenha o nome do jogador 2 sobre o personagem
        desenharNomeJogador(g, playerInfo.getNickPlayer(), xPersonagem, yPersonagem, larguraPersonagem, true);
        
        g.drawImage(hotbar, 385, 678, 500, 50, this);
        
        int ping = (int) DatabaseConfig.getDatabasePing();
        
        if (ping <= 15) {
            g.drawImage(simPing1, 1140, 709, 20, 20, this);
        } else if (ping <= 22) {
            g.drawImage(simPing2, 1140, 709, 20, 20, this);
        } else if (ping <= 28) {
            g.drawImage(simPing3, 1140, 709, 20, 20, this);
        } else if (ping <= 38) {
            g.drawImage(simPing4, 1140, 709, 20, 20, this);
        } else {
            g.drawImage(simPing5, 1140, 709, 20, 20, this);
        }
        
        g.setColor(Color.WHITE);
        g.drawString(DatabaseConfig.getDatabasePing()+"ms", 1160, 722);
        
        g.drawImage(simOnline, 1200, 710, 15, 15, this);
        g.setColor(Color.WHITE);
        g.drawString(PlayerService.getPlayersOnline()+"/20", 1220, 722);
    }

    private void desenharNomeJogador(Graphics g, String nome, int xPersonagem, int yPersonagem, int larguraPersonagem, boolean adicionarAdm) {
        g.setColor(Color.BLACK);

        String prefixoAdm = "[ADM] ";
        String nomeRestante = nome;

        // Adiciona "[ADM]" apenas se for o jogador controlado pelo usuário e if playerInfo.getOp() == 1
        if (adicionarAdm && playerInfo.getOp() == 1) {
            nome = prefixoAdm + nome; // Adiciona "[ADM]" ao nome
        }

        // Se o nome começa com [ADM], separamos
        if (nome.startsWith(prefixoAdm)) {
            nomeRestante = nome.substring(prefixoAdm.length());
        }

        // Obtém as métricas da fonte para calcular a largura e altura do texto
        FontMetrics fontMetrics = g.getFontMetrics();
        int larguraTexto = fontMetrics.stringWidth(nome);
        int alturaTexto = fontMetrics.getHeight();

        // Calcula a posição X para centralizar o texto sobre o personagem
        int xTexto = xPersonagem + (larguraPersonagem - larguraTexto) / 2;
        int yTexto = yPersonagem - alturaTexto; // Ajuste para colocar o texto logo acima do personagem

        // Desenha o retângulo de fundo branco com borda preta
        // Borda preta
        g.setColor(Color.BLACK);
        g.fillRect(xTexto - 5, yTexto - alturaTexto, larguraTexto + 10, alturaTexto + 5); // Borda preta

        // Retângulo branco
        g.setColor(Color.WHITE);
        g.fillRect(xTexto - 4, yTexto - alturaTexto + 1, larguraTexto + 8, alturaTexto + 3); // Retângulo branco

        // Desenha o prefixo "[ADM]" em vermelho, se existir
        if (nome.startsWith(prefixoAdm)) {
            g.setColor(Color.RED);
            g.drawString(prefixoAdm, xTexto, yTexto);
            // Atualiza a posição X para o restante do nome
            xTexto += fontMetrics.stringWidth(prefixoAdm);
        }

        // Desenha o restante do nome em preto
        g.setColor(Color.BLACK);
        g.drawString(nomeRestante, xTexto, yTexto);
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
        
    public void buscarJogadores() {
    	String sql = "SELECT p.id_player, p.x, p.y, p.sprite, l.usuario FROM tb_player_coordenadas p "
    	           + "JOIN tb_login l ON p.id_player = l.id "
    	           + "WHERE p.online = 1 ORDER BY p.id_player"; // Filtra jogadores com status online

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery(); // Executa a consulta

            // Limpa a lista de jogadores (caso já tenha jogadores cadastrados)
            jogadores.clear();

            while (rs.next()) { // Para cada jogador encontrado
                int idPlayer = rs.getInt("id_player");
                String nomePlayer = rs.getString("usuario");
                int xPlayer = rs.getInt("x");
                int yPlayer = rs.getInt("y");
                String spritePath = rs.getString("sprite"); // Caminho completo do sprite

                // Carregar o sprite com base no caminho completo armazenado no banco
                Image sprite = carregarSprite(spritePath);

                // Cria um objeto Jogador e adiciona à lista
                Jogador jogador = new Jogador(idPlayer, nomePlayer, xPlayer, yPlayer, sprite);
                jogadores.add(jogador);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceção caso ocorra
        }
    }

    public Image carregarSprite(String spritePath) {
        try {
            // Substitui "modelo1d" por "modelo1" no caminho
            String caminhoCorrigido = spritePath.replace("modelo1d", "modelo1");

            // Tenta carregar a imagem usando o caminho corrigido
            File file = new File(caminhoCorrigido);
            if (file.exists()) {
                return ImageIO.read(file);
            } else {
                //System.out.println("Arquivo não encontrado: " + caminhoCorrigido);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Retorna null caso ocorra algum erro
        }
    }
    }