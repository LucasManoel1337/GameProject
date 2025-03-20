package game.gameproject.front.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

import game.gameproject.dto.infoPlayerDto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static javax.swing.SwingConstants.CENTER;

public class Player extends JPanel implements KeyListener {

    // Variaveis globais
    Sounds trilhaSonoraMapa2 = new Sounds();
    Sounds trilhaSonoraMapa1 = new Sounds();
    Sounds trilhaSonoraMapa3 = new Sounds();
    Sounds trilhaSonoraMapa4 = new Sounds();
    Sounds trilhaSonoraMapa5 = new Sounds();
    Sounds trilhaSonoraMapa6 = new Sounds();
    Sounds trilhaSonoraMapa7 = new Sounds();
    Sounds abrirBau = new Sounds();
    Sounds somEspada = new Sounds();
    Sounds somDanoRecebe = new Sounds();
    Sounds trilhaSonoraMenu = new Sounds();
    private boolean tutorial = true;
    private int modeloSelecionado; // Modelo escolhido
    private String nomePersonagem; // Nome do Personagem
    private int nivel = 0; // Nivel do player
    private int dinheiro = 0; // Dinheiro do player
    public int vida = 50; // vida atual do player
    public int vidaMax = 50; // Vida maxima do player
    public int forca = 0; // força do player
    private int pontoDeAtribuicao = 0; // pontos de atribuição do player
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
    private BufferedImage imgBotaoSairDoJogo;
    private BufferedImage imgBotaoDevModeMap;
    private BufferedImage imgTutorial;
    private BufferedImage imgVitoria;
    private BufferedImage imgDerrota;
    private BufferedImage acordarSonho;
    private boolean mostrarEquipamento = false;
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
    private boolean turnoDoJogador = true;
    private JButton botaoFugir;
    private JButton botaoAtacar;
    Monstros monstro = new Monstros();
    private List<String> logDano = new CopyOnWriteArrayList<>(); // Use CopyOnWriteArrayList
    boolean playerUsouPot;
    private boolean pausa;
    private Rectangle menuPausa = new Rectangle(0,0,1280,768);
    private boolean jaResgatouBau;
    private BufferedImage bauPrataAberto;
    private boolean abrirInterfaceLoja;
    private BufferedImage lojaGui;
    private BufferedImage[] moedas = new BufferedImage[6]; // Vetor para imagens da moeda
    private int moedaIndex = 0;
    private int xMoeda = 10; // coordenadas X da moeda
    private int yMoeda = 75; // coordenadas Y da moeda
    // Timer para a animação da moeda
    private Timer timerMoeda;
    //Timer do log
    Timer timerLog;
    //Sinalizar se o player atacou
    private boolean playerAtacou = false;
    //Se o monstro morreu
    private boolean monstroMorreu;
    //Sinaliza derrota no boss
    private boolean derrota = false;
    //Sinaliza vitoria do boss
    private boolean vitoria = false;
    //Trava a tela de atributos para nao abrir em batalha
    private boolean modoBatalhaLockar = false;

    //Animação do Combate
    private animacaoAM animacaoAM;
    private animacaoAP animacaoAP;

    //Imagens Placas
    private BufferedImage imagemPlaca1; // Adicione a imagem da placa
    private BufferedImage imagemPlaca2;
    private BufferedImage imagemPlaca3;
    private BufferedImage imagemPlaca4;
    private BufferedImage imagemPlaca5;
    private BufferedImage imagemPlaca6;
    private BufferedImage cacamba;

    // Novos componentes para a tela de equipamentos
    private JButton botaoSairJogo;
    private JButton botaoAcordar;
    private JButton botaoDev;

    public Player(String nome, int modelo, Mapa mapaInicial, infoPlayerDto playerInfo) { // Construtor com parâmetros
    	this.playerInfo = playerInfo;
    	
    	setNivel(playerInfo.getNivel());
    	setDinheiro(playerInfo.getDinheiro());
    	setVida(playerInfo.getVida());

        //Trilha sonora dos mapas
        trilhaSonoraMapa2.carregarSom("Sons/Mapa2.wav");
        trilhaSonoraMapa1.carregarSom("Sons/emCasa.wav");
        trilhaSonoraMapa3.carregarSom("Sons/mapa3.wav");
        trilhaSonoraMapa4.carregarSom("Sons/mapaCombate.wav");
        trilhaSonoraMapa5.carregarSom("Sons/mapaBoss.wav");
        trilhaSonoraMapa6.carregarSom("Sons/musicaCombateBoss.wav");
        trilhaSonoraMapa7.carregarSom("Sons/Mapa7.wav");
        trilhaSonoraMenu.carregarSom("Sons/musicaInicial.wav");
        //Sons de efeitos
        abrirBau.carregarSom("Sons/chestOpen.wav");
        somEspada.carregarSom("Sons/espadaSom.wav");
        animacaoAM = new animacaoAM(); // Instanciando animação de ataque do monstro
        animacaoAP = new animacaoAP(); // Instanciando animação de ataque do player
        //Tira o layout padrao
        this.setLayout(null);
        
        // Criar o botão Atacar
        botaoAtacar = new JButton();// Adiciona um novo elemento de botão
        estiloBotoes(botaoAtacar);//Estilo do botao
        botaoAtacar.setIcon(new ImageIcon("imagens/interacao/botaoAtacar.png")); // Seta uma imagem no botão
        botaoAtacar.setContentAreaFilled(false);
        botaoAtacar.setBorder(new EmptyBorder(0, 0, 0, 0)); // Remover borda
        botaoAtacar.setFocusPainted(false);
        botaoAtacar.setBounds(700, 460, 120, 40); // Posições do botão
        botaoAtacar.setVisible(false); // Inicialmente invisível
        //Logica para o botao de ataque no Combate
        botaoAtacar.addActionListener(new ActionListener() { // Caso ele seja clicado
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turnoDoJogador && !playerAtacou && vida > 0) {
                    playerAtaque();
                    playerAtacou = true;
                    turnoDoJogador = false;
                    ataqueDoMonstro();
                    // Chama a lógica de combate após o ataque do jogador
                }
            }
        });
        add(botaoAtacar);

        botaoFugir = new JButton();// Cria o botão Fugir
        estiloBotoes(botaoFugir);// Estilo do botao
        botaoFugir.setBounds(450, 460, 120, 40); // Posições do botão
        botaoFugir.setVisible(false); // Inicialmente invisível
        botaoFugir.setIcon(new ImageIcon("imagens/interacao/botaoFugir.png")); //Set imagem do botão
        botaoFugir.setContentAreaFilled(false);
        botaoFugir.setBorder(new EmptyBorder(0, 0, 0, 0)); // Remover borda
        botaoFugir.setFocusPainted(false);
        //Lógica do botão fugir em batalha
        botaoFugir.addActionListener(new ActionListener() { // Caso o botão seja acionado
            @Override
            public void actionPerformed(ActionEvent e) {
                fugirDaBatalha(); // chamando o metodo da mesma classe
                monstro = new Monstros(); // Instanciando outra classe
                limparLog(); // chamando o metodo da mesma classe
            }
        });
        add(botaoFugir); // Adiciona o botão ao painel

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
            public void actionPerformed(ActionEvent e) { //Caso seja clicado
                trocarImagem();
                moverPersonagem();
                repaint();
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
        // Timer para a moeda ficar girando na tela
        timerMoeda = new Timer(100, new ActionListener() { // Timer da moeda (utilizado para ficar trocando a imagem da moeda)
            @Override
            public void actionPerformed(ActionEvent e) {
                animarMoeda(); // chama o metodo
                repaint(); // chama o metodo
            }
        });
        timerMoeda.start(); // Começa o tempo

        //Tela menu pausa
        botaoSairJogo = new JButton();// Novo elemento botão
        estiloBotoesComBorder(botaoSairJogo);//Estilo do botao
        ImageIcon iconeBotaoSairDoJogo = new ImageIcon(imgBotaoSairDoJogo); // Converte a imagem normal para o formato de icon
        botaoSairJogo.setIcon(iconeBotaoSairDoJogo); // Seta a imagens ao fundo dos botão
        botaoDev = new JButton();// Novo elemento botão
        estiloBotoesComBorder(botaoDev);//Estilo do botao
        ImageIcon iconeBotaoDevModeMap = new ImageIcon(imgBotaoDevModeMap); // Converte a imagem normal para o formato de icon
        botaoDev.setIcon(iconeBotaoDevModeMap); // Seta a imagens ao fundo dos botão

        botaoSairJogo.addActionListener(new ActionListener() {
            // Caso o botão seja clicado
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fechar Jogo

            }
        });

        botaoDev.addActionListener(new ActionListener() {
            // Caso o botão seja clicado
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mapaAtual.isDesenvolvedor()) { // Puxa o metodo da classe mapa e verifica seu boolean e altera
                    mapaAtual.setDesenvolvedor(true);
                } else {
                    mapaAtual.setDesenvolvedor(false);
                }
            }
        });
        
        setFocusable(true);
        requestFocusInWindow();
    }
    //Funcao para adicionar estilos para certos botoes
    public void estiloBotoesComBorder(JButton botao) {
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Muda o cursor para o estilo "hand"
                botao.setBorder(BorderFactory.createLineBorder(Color.BLUE)); // Adiciona borda azul ao entrar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setCursor(Cursor.getDefaultCursor()); // Retorna o cursor ao padrão
                botao.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda ao sair
            }
        });
    }
    //Funcao para adicionar estilos para certos botoes
    public void estiloBotoes(JButton botao) {
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Muda o cursor para o estilo "hand"
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setCursor(Cursor.getDefaultCursor()); // Retorna o cursor ao padrão
            }
        });
    }

    // Metodo para ficar trocando a imagem da moeda.
    private void animarMoeda() {
        moedaIndex = (moedaIndex + 1) % moedas.length; // Fica trocando a imagem da moeda
    }
    //Metodo para ganhar experiencia
    private void ganharExperiencia(int quantidadeXp) {
        this.xp += quantidadeXp; // Adiciona o xp do mosntro ao player
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
            moedas[0] = ImageIO.read(new File("imagens/status/spritesDasMoedas/moeda1.png"));
            moedas[1] = ImageIO.read(new File("imagens/status/spritesDasMoedas/moeda2.png"));
            moedas[2] = ImageIO.read(new File("imagens/status/spritesDasMoedas/moeda3.png"));
            moedas[3] = ImageIO.read(new File("imagens/status/spritesDasMoedas/moeda4.png"));
            moedas[4] = ImageIO.read(new File("imagens/status/spritesDasMoedas/moeda5.png"));
            moedas[5] = ImageIO.read(new File("imagens/status/spritesDasMoedas/moeda6.png"));
            imagemPlaca1 = ImageIO.read(new File("imagens/interacao/placaNebulaLake.png")); // Carregue a imagem da placa
            imagemPlaca2 = ImageIO.read(new File("imagens/interacao/placaShop.png")); // Carregue a imagem da placa
            imagemPlaca3 = ImageIO.read(new File("imagens/interacao/placaHorizonRiver.png")); // Carregue a imagem da placa
            imagemPlaca4 = ImageIO.read(new File("imagens/interacao/placaHorizonAreaBoss.png")); // Carregue a imagem da placa
            imagemPlaca5 = ImageIO.read(new File("imagens/interacao/placaAlternativeRoute.png")); // Carregue a imagem da placa
            imagemPlaca6 = ImageIO.read(new File("imagens/interacao/placaAreaBoss.png")); // Carregue a imagem da placa
            cacamba = ImageIO.read(new File("imagens/interacao/imgCacamba.png"));
            bauPrataAberto = ImageIO.read(new File("imagens/Baus/bauPrataAberto.png"));
            imgBotaoSairDoJogo = ImageIO.read(new File("imagens/MenuPrincipal/MenuPrincipal/imgBotaoSairDoJogo.jpg"));
            imgBotaoDevModeMap = ImageIO.read(new File("imagens/mapa/imgBotaoDevModeMap.png"));
            lojaGui = ImageIO.read(new File("imagens/interacao/lojagui.png"));
            imgTutorial = ImageIO.read(new File("imagens/tutorial.png"));
            imgVitoria = ImageIO.read(new File("imagens/bossFight/imgFundoBossVitoria.png"));
            imgDerrota = ImageIO.read(new File("imagens/bossFight/imgFundoBossDerrota.png"));
            acordarSonho = ImageIO.read(new File("imagens/interacao/imgBotaoFoiTudoApenasUmSonho.png"));

        } catch (IOException e) {
            System.err.println("Erro ao carregar imagens na classe Player!" + e.getMessage());
            System.exit(1);
        }
    }

    // Metodo para trocar a imagem do personagem enquanto anda.
    private void trocarImagem() {
        if (movendoCima) {
            personagemAtual = (personagemAtual == personagemCima1) ? personagemCima2 : personagemCima1;
        } else if (movendoBaixo) {
            personagemAtual = (personagemAtual == personagemBaixo1) ? personagemBaixo2 : personagemBaixo1;
        } else if (movendoEsquerda) {
            personagemAtual = (personagemAtual == personagemEsquerda1) ? personagemEsquerda2 : personagemEsquerda1;
        } else if (movendoDireita) {
            personagemAtual = (personagemAtual == personagemDireita1) ? personagemDireita2 : personagemDireita1;
        }
    }

    // Método para mover o personagem (chama o mapa para verificar colisões)
    private void moverPersonagem() {
        // Limites da tela
        int larguraTela = getWidth();
        int alturaTela = 760; // Necessario colocar 760 para quem utilizar a resolução do windows no 150%

        // Verificar se pode mover nas direções e ATUALIZAR as coordenadas
        if (movendoCima && yPersonagem > 0) {
            if (mapaAtual.podeMover(xPersonagem, yPersonagem - velocidade)) {
                yPersonagem -= velocidade; // Atualiza a coordenada yPersonagem
            }
        }
        if (movendoBaixo && yPersonagem < alturaTela - 70) {
            if (mapaAtual.podeMover(xPersonagem, yPersonagem + velocidade)) {
                yPersonagem += velocidade; // Atualiza a coordenada yPersonagem
            }
        }
        if (movendoEsquerda && xPersonagem > 0) {
            if (mapaAtual.podeMover(xPersonagem - velocidade, yPersonagem)) {
                xPersonagem -= velocidade; // Atualiza a coordenada xPersonagem
            }
        }
        if (movendoDireita && xPersonagem < larguraTela - 40) {
            if (mapaAtual.podeMover(xPersonagem + velocidade, yPersonagem)) {
                xPersonagem += velocidade; // Atualiza a coordenada xPersonagem
            }
        }


        // Sistema para o personagem spawnar no local certo após mudar de mapa.
        if (mapaAtual.getNumeroMapa() == 1 && mapaAtual.musicam1.contains(xPersonagem, yPersonagem)) {
            trilhaSonoraMapa1.tocarSomEmLoop("Sons/emCasa.wav");
        } else if (mapaAtual.getNumeroMapa() == 1 && mapaAtual.areaMudancaFundo.contains(xPersonagem, yPersonagem)) { // Mundo 1 para o Mundo 2
            trilhaSonoraMapa1.pararSom();
            trilhaSonoraMapa2.tocarSomEmLoop("Sons/Mapa2.wav");
            mudarMapa(new Mapa(2, this.personagemDireita1));
            xPersonagem = 1020; // Coordenada X para o Mundo 2
            yPersonagem = 260; // Coordenada Y para o Mundo 2
            tutorial = false;
            trilhaSonoraMapa1.pararSom();
        } else if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.areaMudancaFundo2.contains(xPersonagem, yPersonagem)) { // Mundo 2 para o Mundo 3
            mudarMapa(new Mapa(3, this.personagemDireita1));
            xPersonagem = 725; // Coordenada X para o Mundo 3
            yPersonagem = 600; // Coordenada Y para o Mundo 3
        } else if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.areaMudancaFundo4.contains(xPersonagem, yPersonagem)) { // Mundo 2 para o Mundo 1
            trilhaSonoraMapa2.pararSom();
            trilhaSonoraMapa1.tocarSomEmLoop("Sons/emCasa.wav");
            mudarMapa(new Mapa(1, this.personagemDireita1));
            xPersonagem = 575; // Coordenada X para o Mundo 1
            yPersonagem = 600; // Coordenada Y para o Mundo 1
        } else if (mapaAtual.getNumeroMapa() == 3 && mapaAtual.areaMudancaFundo3.contains(xPersonagem, yPersonagem)) { // Mundo 3 para o Mundo 2 aaaaaaaaaaaaaaaaaaaaaa
            mudarMapa(new Mapa(2, this.personagemDireita1));
            xPersonagem = 733; // Coordenada X para o Mundo 2
            yPersonagem = 10; // Coordenada Y para o Mundo 2
        } else if ((mapaAtual.getNumeroMapa() == 3)) {
            chanceBatalha();
        } else if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.areaMudancaFundo5.contains(xPersonagem, yPersonagem)) { // Mundo 2 para o Mundo 5
            trilhaSonoraMapa2.pararSom();
            trilhaSonoraMapa5.tocarSomEmLoop("Sons/mapaBoss.wav");
            mudarMapa(new Mapa(5, this.personagemDireita1));
            xPersonagem = 20; // Coordenada X para o Mundo 5
            yPersonagem = 330; // Coordenada Y para o Mundo 5
        } else if (mapaAtual.getNumeroMapa() == 5 && mapaAtual.areaMudancaFundo6.contains(xPersonagem, yPersonagem)) { // Mundo 5 para o Mundo 2
            trilhaSonoraMapa5.pararSom();
            trilhaSonoraMapa2.tocarSomEmLoop("Sons/Mapa2.wav");
            mudarMapa(new Mapa(2, this.personagemDireita1));
            xPersonagem = 1180; // Coordenada X para o Mundo 2
            yPersonagem = 250; // Coordenada Y para o Mundo 2 
        } else if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.areaMudancaFundo7.contains(xPersonagem, yPersonagem)) { // Mundo 2 para o Mundo 5
            trilhaSonoraMapa2.pararSom();
            trilhaSonoraMapa5.tocarSomEmLoop("Sons/mapaBoss.wav");
            mudarMapa(new Mapa(5, this.personagemDireita1));
            xPersonagem = 20; // Coordenada X para o Mundo 5
            yPersonagem = 570; // Coordenada Y para o Mundo 5
        } else if (mapaAtual.getNumeroMapa() == 5 && mapaAtual.areaMudancaFundo8.contains(xPersonagem, yPersonagem)) { // Mundo 5 para o Mundo 2
            trilhaSonoraMapa5.pararSom();
            trilhaSonoraMapa2.tocarSomEmLoop("Sons/Mapa2.wav");
            mudarMapa(new Mapa(2, this.personagemDireita1));
            xPersonagem = 1180; // Coordenada X para o Mundo 2
            yPersonagem = 493; // Coordenada Y para o Mundo 2 
        } else if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.areaMudancaFundo9.contains(xPersonagem, yPersonagem)) { // Mundo 5 para o Mundo 2
            trilhaSonoraMapa2.pararSom();
            trilhaSonoraMapa7.tocarSomEmLoop("Sons/MusicaCombateBoss.wav");
            mudarMapa(new Mapa(7, this.personagemDireita1));
            xPersonagem = 610; // Coordenada X para o Mundo 2
            yPersonagem = 670; // Coordenada Y para o Mundo 2 
        } else if (mapaAtual.getNumeroMapa() == 7 && mapaAtual.areaMudancaFundo10.contains(xPersonagem, yPersonagem)) { // Mundo 5 para o Mundo 2
            trilhaSonoraMapa7.pararSom();
            trilhaSonoraMapa2.tocarSomEmLoop("Sons/Mapa2.wav");
            mudarMapa(new Mapa(2, this.personagemDireita1));
            xPersonagem = 190; // Coordenada X para o Mundo 2
            yPersonagem = 180; // Coordenada Y para o Mundo 2 
        } else if (mapaAtual.getNumeroMapa() == 5 && mapaAtual.areaCombateBoss.contains(xPersonagem, yPersonagem) && !derrota && !vitoria) { // Mundo 5 para o Mundo 6
            batalhaBoss();
            trilhaSonoraMapa6.tocarSomEmLoop("Sons/MusicaCombateBoss.wav");
            mudarMapa(new Mapa(6, this.personagemDireita1));
            xPersonagem = 190; // Coordenada X para o Mundo 6
            yPersonagem = 180; // Coordenada Y para o Mundo 6
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
            case KeyEvent.VK_ESCAPE:
                if(mapaAtual.getNumeroMapa() == 6 || mapaAtual.getNumeroMapa() == 4) {
                } else {
                    pausa = !pausa;
                repaint();
                }
                break;
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
            case KeyEvent.VK_E:
                // Verifica se o mundo esta no mapa 7 e na area do shop
                if(mapaAtual.getNumeroMapa() == 7 && mapaAtual.areaShop.contains(xPersonagem, yPersonagem) && !mostrarEquipamento) {
                    abrirInterfaceLoja = !abrirInterfaceLoja;
                    repaint();
                    //Verifica se o player esta na area de abrir o bau, se sim ele abre o bau do mapa 3(por enquanto)e recebe 10 poções.
                }else if(mapaAtual.retanguloBauAbrir.contains(xPersonagem, yPersonagem) && !jaResgatouBau) {
                    abrirBau.tocarSom();
                    resetarMovimento();
                    if(mapaAtual.getNumeroMapa() == 3){
                        mapaAtual.setBauPrata(bauPrataAberto);
                        JOptionPane.showMessageDialog(null, "MIAR!", "Notificação", JOptionPane.INFORMATION_MESSAGE);
                        jaResgatouBau = true;
                    }
                }else if(mapaAtual.getNumeroMapa() == 3 && mapaAtual.retanguloBauAbrir.contains(xPersonagem, yPersonagem) && jaResgatouBau){
                    abrirBau.tocarSom();
                    resetarMovimento();
                    mapaAtual.setBauPrata(bauPrataAberto);
                    JOptionPane.showMessageDialog(null, "Não tem mais nada aqui!", "Notificação", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case KeyEvent.VK_C:
                repaint();
                if(!modoBatalhaLockar) {
                    mostrarEquipamento = !mostrarEquipamento; // Alterna o valor de mostrarEquipamento entre true e false
                }
                repaint(); // Redesenha a tela para refletir a mudança
                break;
        }
        if (!timerAnimacao.isRunning()) {
            timerAnimacao.start();
        }
        if((mapaAtual.getNumeroMapa() != 7 || !mapaAtual.areaShop.contains(xPersonagem, yPersonagem))){
            abrirInterfaceLoja = false;
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
        if (!movendoCima && !movendoBaixo && !movendoEsquerda && !movendoDireita) {
            timerAnimacao.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não usado neste caso.
    }

    // Função para imprimir as coisas na tela (Essencial para o jogo Funcionar corretamente)
    @Override
    public void paintComponent(Graphics g) {
        if (mapaAtual.musicam1.contains(xPersonagem, yPersonagem) && tutorial == true) {
            g.drawImage(imgTutorial, 0, 0, 1280, 768, null); 
        }
        
        super.paintComponent(g);
        mapaAtual.desenhar(g); // Chama o mapa para desenhar o fundo
        // Desenhar a imagem do persoangem em cima do fundo
        int larguraPersonagem = 30; // largura do personagem
        int alturaPersonagem = 50; // altura do persoangem
        if (mapaAtual.getNumeroMapa() == 4) { // caso estiver nesse mapa
            trilhaSonoraMapa2.pararSom();
            trilhaSonoraMapa4.tocarSomEmLoop("Sons/mapaCombate.wav");
        }else if(mapaAtual.getNumeroMapa() == 6 && !derrota && !vitoria) {
            trilhaSonoraMapa5.pararSom();
            trilhaSonoraMapa6.tocarSomEmLoop("Sons/mapaCombateBoss.wav");
        }else{    // Caso for mapa 4, no caso mapa de combate ele não irá desenhar o personagem.
            g.drawImage(personagemAtual, xPersonagem, yPersonagem, larguraPersonagem, alturaPersonagem, this);
        }

        mapaAtual.desenharIlusao(g); // Chama o mapa para desenhar a ilusão
        mapaAtual.carregarImagemBoss(monstro);
        // Desenhar a vida do monstro
        if (mapaAtual.getNumeroMapa() == 4 && monstro.getVida() > 0) {
            g.setColor(Color.RED); // Define a cor da barra de vida
            int xVidaMonstro = 673; // Posição inicial da barra de vida
            int yVidaMonstro = 337; // Posição inicial da barra de vida
            int larguraVida = (int) (monstro.getVida() * 2); // Calcula a largura da barra de vida proporcional à vida
            int alturaVida = 10; // Define a altura da barra de vida
            g.fillRect(xVidaMonstro, yVidaMonstro, larguraVida, alturaVida); // Desenhar a barra de vida
            mapaAtual.carregarImagemMonstro(monstro);
        }
        if (mapaAtual.getNumeroMapa() == 6 && monstro.getBossVida() > 0) {
            g.setColor(Color.RED); // Define a cor da barra de vida
            int xVidaMonstro = 670; // Posição inicial da barra de vida
            int yVidaMonstro = 247; // Posição inicial da barra de vida
            int larguraVida = (int) (monstro.getBossVida() +30); // Calcula a largura da barra de vida proporcional à vida
            int alturaVida = 10; // Define a altura da barra de vida
            g.fillRect(xVidaMonstro, yVidaMonstro, larguraVida, alturaVida); // Desenhar a barra de vida
            mapaAtual.carregarImagemMonstro(monstro);

        }
        //Desenhar Poção
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setColor(Color.WHITE);
        //Desenhar arma atual

        // Desenhar a moeda animada no canto da tela
        g.drawImage(moedas[moedaIndex], xMoeda, yMoeda, 40, 40, this);

        // Desenhar o nome do personagem abaixo da moeda
        g.setFont(new Font("Arial", Font.BOLD, 16)); // Define a fonte e tamanho do nome
        g.setColor(Color.WHITE); // Define a cor do nome
        g.drawString("Personagem: " + nomePersonagem, 10, 20); // Desenha o nome

        // Desenhar o dinheiro do lado da moeda
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.YELLOW);
        String dinheiroString = String.format("$%03d", dinheiro); // Formatar o dinheiro com zeros à esquerda
        g.drawString(dinheiroString, xMoeda + 45, yMoeda + 28);

        //Desenhar nivel do personagem
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);
        String Nivel = ("Nivel: " + nivel);
        g.drawString(Nivel, 300, 105);

        // Desenhar a barra de vida
        if (vida > 0) {
            g.setColor(Color.RED); // Define a cor da barra de vida
            int xVida = 10; // Posição inicial da barra de vida
            int yVida = 27; // Posição inicial da barra de vida
            int larguraVida = (int) (vida * 2); // Calcula a largura da barra de vida proporcional à vida
            int alturaVida = 15; // Define a altura da barra de vida
            g.fillRect(xVida, yVida, larguraVida, alturaVida); // Desenhar a barra de vida
        }

        // Desenhar a barra de XP
        g.setColor(xpBgColor); // Define a cor de fundo da barra de XP
        int xXp = 10; // Posição inicial da barra de XP
        int yXp = 45; // Posição inicial da barra de XP
        int larguraXp = 400; // Largura total da barra de XP
        int alturaXp = 10; // Altura da barra de XP
        g.fillRect(xXp, yXp, larguraXp, alturaXp); // Desenha o fundo da barra de XP

        // Calcula a largura da barra de XP proporcional ao XP atual
        int larguraXpPreenchida = (int) (((double) xp / xpMax) * larguraXp);

        g.setColor(xpColor); // Define a cor da barra de XP
        g.fillRect(xXp, yXp, larguraXpPreenchida, alturaXp); // Desenha a barra de XP preenchida

        // Verificar se o personagem está dentro do retângulo da placa
        if (mapaAtual.getNumeroMapa() == 1 && mapaAtual.cacamba.contains(xPersonagem, yPersonagem)) {
            // Desenhar a imagem da placa
            g.drawImage(cacamba, 1, 1, 1280, 720, this); // Ajuste as coordenadas da imagem da placa
        }
        if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.placa1.contains(xPersonagem, yPersonagem)) {
            // Desenhar a imagem da placa
            g.drawImage(imagemPlaca1, 1, 1, 1280, 720, this); // Ajuste as coordenadas da imagem da placa
        }
        if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.placa2.contains(xPersonagem, yPersonagem)) {
            // Desenhar a imagem da placa
            g.drawImage(imagemPlaca2, 1, 1, 1280, 720, this); // Ajuste as coordenadas da imagem da placa
        }
        if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.placa3.contains(xPersonagem, yPersonagem)) {
            // Desenhar a imagem da placa
            g.drawImage(imagemPlaca3, 1, 1, 1280, 720, this); // Ajuste as coordenadas da imagem da placa
        }
        if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.placa4.contains(xPersonagem, yPersonagem)) {
            // Desenhar a imagem da placa
            g.drawImage(imagemPlaca4, 1, 1, 1280, 720, this); // Ajuste as coordenadas da imagem da placa
        }
        if (mapaAtual.getNumeroMapa() == 2 && mapaAtual.placa5.contains(xPersonagem, yPersonagem)) {
            // Desenhar a imagem da placa
            g.drawImage(imagemPlaca5, 1, 1, 1280, 720, this); // Ajuste as coordenadas da imagem da placa
        }
        if (mapaAtual.getNumeroMapa() == 5 && mapaAtual.placa6.contains(xPersonagem, yPersonagem)) {
            // Desenhar a imagem da placa
            g.drawImage(imagemPlaca6, 1, 1, 1280, 720, this); // Ajuste as coordenadas da imagem da placa
        }

        if (mapaAtual.getNumeroMapa() == 4 && turnoDoJogador || mapaAtual.getNumeroMapa() == 6 && turnoDoJogador) { // Desenha animação do ataque do player
            animacaoAM.desenharAnimacao(g, 537, 338, 80, 80); // Ajuste as coordenadas e o tamanho da animação
        }

        if (mapaAtual.getNumeroMapa() == 4 && !turnoDoJogador || mapaAtual.getNumeroMapa() == 6 && !turnoDoJogador) { // Desenha animação do ataque do monstro
            animacaoAP.desenharAnimacao(g, 620, 270, 130, 130); // Ajuste as coordenadas e o tamanho da animação
        }
        
        if (mapaAtual.getNumeroMapa() == 1 && mapaAtual.musicam1.contains(xPersonagem, yPersonagem) && tutorial == true) {
            g.drawImage(imgTutorial, 1, 1, 1280, 720, this); // Ajuste as coordenadas da imagem da placa
        }
        //Estilo do log
        int y = 20; // Posição inicial dos logs na tela
        for (String log : logDano) { 
            Font font = new Font("Arial", Font.PLAIN, 20); // tipo de fonte, escrita e tamanho
            g.setFont(font);
            g.setColor(Color.WHITE); // cor da escrita
            g.drawString(log, 800, y); // Onde desenhar

            y += 30; // Incrementa a posição vertical para o próximo log
        }

        //Desenhar o menu pause
        if(pausa && !derrota){
            movendoBaixo = false;
            movendoCima = false;
            movendoEsquerda = false;
            movendoDireita = false;
            //Imagem do menu pause
            BufferedImage imageRectPausa;
            try {
                imageRectPausa = ImageIO.read((new File("imagens/TelaInicial3.png"))); //Carregando imagem
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Imagem ao inves do retangulo
            g.drawImage(imageRectPausa,menuPausa.x, menuPausa.y, menuPausa.width, menuPausa.height, null); // Desenhando a imagem de fundo do pause

            // Desenhe o título do menu de pausa
            g.setColor(Color.WHITE); //cor da escrita
            g.setFont(new Font("Arial", Font.BOLD, 54)); //Fonte, tipo de escrita e tamanho
            g.drawString("Pausa", 550, 100); //Coordenadas da escrita

            int posicaoX = getWidth() / 2 - 300 / 2; // Calculo para a centralização horizontal na tela

            //Posicionamento botões menu pausa
            add(botaoSairJogo);
            add(botaoDev);
            botaoSairJogo.setBounds(posicaoX, 130, 300, 80); //Coordenadas
            botaoDev.setBounds(posicaoX, 330, 300, 80); //Coordenadas

        }else{
            //Remove os botoes apos sair do menu pausa
            remove(botaoSairJogo);
            remove(botaoDev);
        }
        //Logica da aba Loja
        if(abrirInterfaceLoja && !pausa && !derrota){
            mostrarEquipamento = false;
            g.drawImage(lojaGui,100,200,350,250,null); //Desenhar imagem com as coordenadas
            g.setColor(Color.WHITE); //Cor
            g.setFont(new Font("Calibri", Font.BOLD, 16)); //Fonte, tipo de escrita e tamanho
            g.drawString("1x", 130, 246); //Escrita e coordenadas
            g.setColor(Color.yellow); //Cor
            g.setFont(new Font("Calibri", Font.BOLD, 16)); //Fonte, tipo de escrita e tamanho
            g.drawString("$10",125,320); // Escrita e coordenadas
            }
        if(derrota){
            botaoAcordar.setVisible(true);
            pausa = false;
            mostrarEquipamento = false;
            g.drawImage(imgDerrota,0,0,1280,768,this);
            botaoSairJogo.setBounds(660, 580, 300, 80);
            botaoAcordar.setBounds(340, 580, 300, 80);
            add(botaoSairJogo);
            add(botaoAcordar);

        }
        if(vitoria){
            pausa = false;
            mostrarEquipamento = false;
            g.drawImage(imgVitoria,0,0,1280,768,this);
            botaoSairJogo.setBounds(490, 580, 300, 80);
            add(botaoSairJogo);
        }
    }
    //Função para o ataque do jogador
    private void playerAtaque() {
        if (mapaAtual.getNumeroMapa() == 4 && monstro.getVida() > 0) { 
            monstro.perderVida(forca); //chamando metodo de outra classe
            adicionarLog(getForca() + " de dano ao " + monstro.getTipo() + ", vida restante: " + monstro.getVida());
            somEspada.carregarSom("Sons/espadaSom.wav"); //chamando metodo de outra classe
            somEspada.tocarSom(); //chamando metodo de outra classe
        }else if (mapaAtual.getNumeroMapa() == 6 && monstro.getBossVida() > 0) {
            monstro.perderBossVida(forca);
            adicionarLog(getForca() + " de dano ao " + monstro.getBoss() + ", vida restante: " + monstro.getBossVida());
            somEspada.carregarSom("Sons/espadaSom.wav"); //chamando metodo de outra classe
            somEspada.tocarSom(); //chamando metodo de outra classe
        }
        botaoAtacar.setVisible(false); // Torna o botão invisivel
        botaoFugir.setVisible(false); // Torna o botão invisivel
    }

    private void ataqueDoMonstro() {
        if(playerAtacou) {
            animacaoAP.iniciarAnimacaoAtaque(); //chamando metodo de outra classe
        }
        //Timer para o ataque do monstro
        Timer timerMonstro = new Timer(520, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ação do monstro
                if (mapaAtual.getNumeroMapa() == 4 && monstro.getVida() > 0) {
                    animacaoAM.iniciarAnimacaoAtaque(); //chamando metodo de outra classe
                    vida -= monstro.getForca();
                    adicionarLog(nomePersonagem + " perdeu " + monstro.getForca() + " de vida, agora está com: " + vida);
                    somDanoRecebe.carregarSom("Sons/monstroHit.wav");
                    somDanoRecebe.tocarSom(); //chamando metodo de outra classe
                } else if (mapaAtual.getNumeroMapa() == 6 && monstro.getBossVida() > 0) {
                    animacaoAM.iniciarAnimacaoAtaque(); //chamando metodo de outra classe
                    vida -= monstro.getBossForca(); //chamando metodo de outra classe
                    adicionarLog(nomePersonagem + " perdeu " + monstro.getBossForca() + " de vida, agora está com: " + vida);
                    somDanoRecebe.carregarSom("Sons/monstroHit.wav");
                    somDanoRecebe.tocarSom(); //chamando metodo de outra classe
                }else if (monstro.getVida() <= 0){
                    ganharExperiencia(monstro.getXp()); //chamando metodo de outra classe
                    setDinheiro(monstro.getOuro()); //chamando metodo de outra classe
                    JOptionPane.showMessageDialog(null, "Você ganhou " + monstro.getXp() + " xp" + " e " + monstro.getOuro() + " de ouro", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    reiniciarPosicaoMapa(); //chamando metodo de outra classe
                    monstroMorreu = true;
                }else{
                    //Deixado em comentario se futuramente o projeto tiver continuação após vitória do Boss
                    //ganharExperiencia(monstro.getBossExp()); //chamando metodo de outra classe
                    //setDinheiro(monstro.getBossOuro()); //chamando metodo de outra classe
                    //reiniciarPosicaoMapa(); //chamando metodo de outra classe
                }
                if(monstroMorreu) {
                    botaoAtacar.setVisible(false);
                    botaoFugir.setVisible(false);
                    modoBatalhaLockar = false;
                    monstroMorreu = false;
                }else if(vida <= 0 && mapaAtual.getNumeroMapa() == 4){
                    JOptionPane.showMessageDialog(null, "Você morreu!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
                    if (xp > 0) { // Verificando se o personagem está com + que 0 de xp
                        xp -= 15; // Penalidade caso o personagem morrer, ele ira perder 15 de xp.
                        if (xp < 0) { // Verificando se o xp ficou negativo
                            xp = 0; // Setando o xp para ficar 0
                        }
                    }
                    reiniciarJogo();
                }else if(vida <=0 && mapaAtual.getNumeroMapa() == 6){
                    JOptionPane.showMessageDialog(null, "Você Morreu!", "Seria uma ilusão?", JOptionPane.INFORMATION_MESSAGE);
                    derrota = true;
                    trilhaSonoraMapa6.pararSom();
                    botaoAtacar.setVisible(false);
                    botaoFugir.setVisible(false);
                    trilhaSonoraMenu.tocarSomEmLoop("Sons/musicaInicial.wav");
                }else if(monstro.getBossVida() <= 0 && mapaAtual.getNumeroMapa() == 6){
                    JOptionPane.showMessageDialog(null, "Você derrotou " + monstro.getBoss() + "!!!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                    vitoria = true;
                    trilhaSonoraMapa6.pararSom();
                    botaoAtacar.setVisible(false);
                    botaoFugir.setVisible(false);
                    trilhaSonoraMenu.tocarSomEmLoop("Sons/musicaInicial.wav");
                }else{
                    botaoAtacar.setVisible(true);
                    botaoFugir.setVisible(true);
                }
                //Muda o turno para o jogador
                turnoDoJogador = true;
                playerAtacou = false;
                playerUsouPot = false;
                
                if (vida <= 0) { // Verifica se o jogador está morto
                return; // Pare a execução do actionPerformed
    }

            }

        });
        timerMonstro.setRepeats(false); // Garante que o Timer execute apenas uma vez
        timerMonstro.start(); // Inicia o Timer do ataque do monstro

    }
    
    public void pararSons() {
    	trilhaSonoraMapa1.pararSom();
    	trilhaSonoraMapa2.pararSom();
    	trilhaSonoraMapa3.pararSom();
    	trilhaSonoraMapa4.pararSom();
    	trilhaSonoraMapa5.pararSom();
    	trilhaSonoraMapa6.pararSom();
    	trilhaSonoraMapa7.pararSom();
    	trilhaSonoraMenu.pararSom();
    }
    //Funcao para reiniciar o jogo se morrer
    private void reiniciarJogo() {
            trilhaSonoraMapa4.pararSom(); // parar de tocar a musica
            trilhaSonoraMapa6.pararSom(); // parar de tocar a musica
            trilhaSonoraMenu.pararSom(); // parar de tocar a musica
            botaoAcordar.setVisible(false); // Deixar o botao invisivel
            botaoAtacar.setVisible(false); // Deixar o botao invisivel
            botaoFugir.setVisible(false); // Deixar o botao invisivel
            trilhaSonoraMapa1.tocarSomEmLoop("emCasa.wav"); // Trocar musica
            mudarMapa(new Mapa(1, null)); // Setar o mapa 1
            xPersonagem = mapaAtual.getXSpawn(); // Spawnar o personagem no spawn do mapa
            yPersonagem = mapaAtual.getYSpawn(); // Spawnar o personagem no spawn do mapa
            vida = vidaMax; // Tornar a vida do personagem para a vida maxima que ele tem.
            limparLog(); // Limpar log de combate
            monstro = new Monstros(); // Criar outro monstro
            turnoDoJogador = true;
            modoBatalhaLockar = false;
    }
    //Funcao para reiniciar a posicao no mapa(usada ao vencer a batalha)
    private void reiniciarPosicaoMapa() {
            trilhaSonoraMapa4.pararSom(); // Parar a musica
            trilhaSonoraMapa6.pararSom(); // Parar a musica
            if (mapaAtual.getNumeroMapa() == 6) { //Caso o player estive no mapa 6
                mudarMapa(new Mapa(5, null));
                trilhaSonoraMapa5.tocarSomEmLoop("Sons/mapaBoss.wav"); // Tocar Musica
            } else {
                mudarMapa(new Mapa(3, null)); //Caso o mplayer estiver no mapa 3
                trilhaSonoraMapa2.tocarSomEmLoop("Sons/mapa2.wav"); // Tocar Musica
            }
            restaurarPosicao();
            limparLog();
            monstro = new Monstros(); // Reiniciar o monstro
            turnoDoJogador = true; // Voltar para o turno do jogador
            botaoAtacar.setVisible(false); // Deixar o botao invisivel
            botaoFugir.setVisible(false); // Deixar o botao invisivel
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
        //Joga o player no mapa combate
        if (novoMapa.getNumeroMapa() == 4 || novoMapa.getNumeroMapa() == 6) { // Mapa de Combate
            botaoFugir.setVisible(true);
            botaoAtacar.setVisible(true);
            resetarMovimento(); // Chama resetarMovimento() aqui, ao entrar no mapa de combate
        }else{
            //Esconde os botoes ataque e fugir
            botaoFugir.setVisible(false);
            botaoAtacar.setVisible(false);

        }
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
    //Chance para startar a batalha
    public void chanceBatalha() {
        if ((mapaAtual.getNumeroMapa() == 3 && mapaAtual.areaCombateGrama.contains(xPersonagem, yPersonagem)
                ||mapaAtual.getNumeroMapa() == 3 && mapaAtual.areaCombateGrama2.contains(xPersonagem, yPersonagem)
                ||mapaAtual.getNumeroMapa() == 3 && mapaAtual.areaCombateGrama3.contains(xPersonagem, yPersonagem))) {
            double chance = Math.random(); // Gera um número aleatório entre 0.0 e 1.0
            if (chance < 0.05) { // 5% de chance de encontrar um monstro no mapa 3
                pausa = false;
                mostrarEquipamento = false;
                modoBatalhaLockar = true;
                salvarPosicao();
                JOptionPane.showMessageDialog(null, "Você encontrou um inimigo!!", "Combate", JOptionPane.INFORMATION_MESSAGE);
                mudarMapa(new Mapa(4, this.personagemDireita1));
                resetarMovimento();
            }
        }

    }

    public void batalhaBoss(){
        if((mapaAtual.getNumeroMapa() == 5 && mapaAtual.areaCombateBoss.contains(xPersonagem, yPersonagem))){
            pausa = false;
            mostrarEquipamento = false;
            salvarPosicao();
            JOptionPane.showMessageDialog(null, "Você desafiou MoonDust!", "Combate", JOptionPane.INFORMATION_MESSAGE);
            mudarMapa(new Mapa(6,this.personagemDireita1));
            resetarMovimento();
            modoBatalhaLockar = true;
        }
    }

    //Logica do botao fugir na batalha
    private void fugirDaBatalha() {
        if (mapaAtual.getNumeroMapa() == 4) { // Verificar para se ele está no mundo 4
            trilhaSonoraMapa4.pararSom();
            mudarMapa(new Mapa(3, null));
            trilhaSonoraMapa2.tocarSomEmLoop("Sons/mapa2.wav");
            monstro = new Monstros();
            modoBatalhaLockar = false;
            restaurarPosicao();// Voltar para o mapa anterior

        }else{
            trilhaSonoraMapa6.pararSom();
            mudarMapa(new Mapa(5,null));
            trilhaSonoraMapa5.tocarSomEmLoop("Sons/mapaBoss.wav");
            monstro = new Monstros();
            restaurarPosicao();
            modoBatalhaLockar = false;
            restaurarPosicao();// Voltar para o mapa anterior
        }
    }
    //Funcao para limpar o log
    private void limparLog() {
        logDano.removeAll(logDano);
    }
    //Cria logs no jogo
    private void adicionarLog(String mensagem) {
        logDano.add(mensagem);
        if (!logDano.isEmpty()) {
            // Schedule a task to remove the oldest log after a delay
            timerLog = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    logDano.removeAll(logDano);
                    timerLog.stop();// Para o timer
                }
            });
            timerLog.setRepeats(false);
            timerLog.start(); // Starta o timer
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
    // Métodos para adicionar pontos de atribuição
    private void adicionarPontoDeAtribuicao(String atributo) {
        if (pontoDeAtribuicao > 0) {
            if (atributo.equals("Vida Maxima")) {
                vidaMax += 10; // Aumenta a vida máxima em 5 pontos

                // Ajuste a vida atual se ela for maior que a vida máxima:
                if (vida > vidaMax) {
                    vida = vidaMax;
                }

                pontoDeAtribuicao--; // Diminui o número de pontos de atribuição
            } else if (atributo.equals("Força")) {
                forca += 5; // Aumenta a força em 5 pontos
                pontoDeAtribuicao--; // Diminui o número de pontos de atribuição
            }
        }
    }
}