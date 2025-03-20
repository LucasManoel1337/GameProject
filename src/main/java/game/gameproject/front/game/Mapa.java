package game.gameproject.front.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Mapa{
    
    // Variaveis Globais
    private boolean desenvolvedor = false; // Modo desenvolvedor, Caso estiver false varios itens que faz o jogo funcionar ira ficar invisiveis, caso estiver true ira ficar visivel.
    public int numeroMapa;
    private BufferedImage interiorCasaInicial;
    private BufferedImage paredesInteriorCasa;
    private BufferedImage novoFundo; // Novo fundo da tela
    private BufferedImage fundoMundoAFora;
    private BufferedImage novoFundo3;
    private BufferedImage ilusaoMapa3;
    private BufferedImage mapaCombateGrama;
    private BufferedImage mapaCombateBoss;
    private BufferedImage mapaBoss;
    private BufferedImage isulaoMapa5;
    private BufferedImage boss;
    private BufferedImage mapaLoja;
    private BufferedImage ilusaoMapaLoja;
    // Array especifico para cada mapa
    private Rectangle[] obstaculosMapa1 = new Rectangle[100]; // Ajuste o tamanho do array
    private Rectangle[] obstaculosMapa2 = new Rectangle[100]; // Ajuste o tamanho do array para o mapa 2
    private Rectangle[] obstaculosMapa3 = new Rectangle[100]; // Ajuste o tamanho do array para o mapa 3
    private Rectangle[] obstaculosMapa5 = new Rectangle[100]; // Ajuste o tamanho do array para o mapa 5
    private Rectangle[] obstaculosMapa7 = new Rectangle[100]; // Ajuste o tamanho do array para o mapa 7
    public Rectangle musicam1;
    public Rectangle cacamba;
    public Rectangle areaMudancaFundo; // Área retangular para mudar o fundo
    public Rectangle areaMudancaFundo2;
    public Rectangle areaMudancaFundo3;
    public Rectangle areaMudancaFundo4;
    public Rectangle areaMudancaFundo5;
    public Rectangle areaMudancaFundo6;
    public Rectangle areaMudancaFundo7;
    public Rectangle areaMudancaFundo8;
    public Rectangle areaMudancaFundo9;
    public Rectangle areaMudancaFundo10;
    public Rectangle areaCombateGrama;
    public Rectangle areaCombateGrama1;
    public Rectangle areaCombateGrama2;
    public Rectangle areaCombateGrama3;
    public Rectangle areaCombateBoss;
    public Rectangle placa1;
    public Rectangle placa2;
    public Rectangle placa3;
    public Rectangle placa4;
    public Rectangle placa5;
    public Rectangle placa6;
    public Rectangle areaShop;
    private int xSpawn;
    private int ySpawn;
    private BufferedImage personagemMapaCombate; 
    private BufferedImage monstroImagem;
    private BufferedImage imagemBoss;// Variável para armazenar a imagem do monstro
    private BufferedImage bauPrata;
    private BufferedImage bauDourado;
    public Rectangle retanguloBau;
    public Rectangle retanguloBauAbrir;
    public Mapa(int numeroMapa, BufferedImage personagem) {
    this.numeroMapa = numeroMapa;
        
        carregarImagens();
        inicializarAreasObstaculos();

        // Define a área retangular para mudar o fundo
        
        areaMudancaFundo = new Rectangle(545, 625, 95, 50); // Largura e altura de 100 pixels
        areaMudancaFundo2 = new Rectangle(733, -20, 30, 30);
        areaMudancaFundo3 = new Rectangle(725, 655, 50, 50); // Area de transição
        areaMudancaFundo4 = new Rectangle(1020, 200, 20, 30); // Area de transição
        areaMudancaFundo5 = new Rectangle(1220, 250, 50, 67); // Area de transição
        areaMudancaFundo6 = new Rectangle(-2, 320, 14, 50); // Area de transição
        areaMudancaFundo7 = new Rectangle(1220, 470, 50, 67); // Area de transição
        areaMudancaFundo8 = new Rectangle(-1, 540, 14, 60); // Area de transição
        areaMudancaFundo9 = new Rectangle(190, 140, 20, 20); // Area de transição
        areaMudancaFundo10 = new Rectangle(570, 690, 100, 20); // Area de transição
        areaCombateGrama = new Rectangle(555,520,130,120); // Area 1 de transição para mapa combate
        areaCombateGrama2 = new Rectangle(140,240,100,120);  // Area 2 de transição para mapa combate
        areaCombateGrama3 = new Rectangle(1026,215,40,170); // Area 3 de transição para mapa combate
        areaShop = new Rectangle(565,200,80,70); // Area de interação do shop
        musicam1 = new Rectangle(390,440,20,20); // Area de interação para aparecer a imagem de controles e começar a musica da casa.
        cacamba = new Rectangle(330,250,80,40);
        areaCombateBoss = new Rectangle(660,10,300,180); // Area de transição para o combate com o boss
        
        // Areas de interação
        placa1 = new Rectangle(763, 7, 33, 32); // Area de interação da placa 1
        placa2 = new Rectangle(284, 167, 33, 32); // Area de interação da placa 2
        placa3 = new Rectangle(505, 327, 33, 32); // Area de interação da placa 3
        placa4 = new Rectangle(1148, 247, 33, 32); // Area de interação da placa 4
        placa5 = new Rectangle(1055, 464, 33, 32); // Area de interação da placa 5
        placa6 = new Rectangle(735, 293, 33, 32); // Area de interação da placa 6
        //Retangulos Baus
        retanguloBau = new Rectangle(868,10,4,1); // Area de interação da do bau
        retanguloBauAbrir = new Rectangle(835, 0,40,35);
        // Define as coordenadas de spawn para cada mapa
        switch (numeroMapa) {
            case 1:
                xSpawn = 400;
                ySpawn = 450;
                break;
            case 2:
                xSpawn = 1020;
                ySpawn = 260;
                break;
            case 3:
                xSpawn = 725;
                ySpawn = 600;
                break;
        }
        
        if (numeroMapa == 4 || numeroMapa == 6) { // Caso estiver no mapa 4 ou 6
            this.personagemMapaCombate = personagem ;
        }
    }

    //Funcao inicializar array obstaculos
    private void inicializarAreasObstaculos() {
        // Inicializar todos os elementos do array com Rectangles vazios
        for (int i = 0; i < obstaculosMapa1.length; i++) {
            obstaculosMapa1[i] = new Rectangle(); // Inicializa o array do mapa 1
            obstaculosMapa2[i] = new Rectangle(); // Inicializa o array do mapa 2
            obstaculosMapa3[i] = new Rectangle(); // Inicializa o array do mapa 3
            obstaculosMapa5[i] = new Rectangle(); // Inicializa o array do mapa 5
            obstaculosMapa7[i] = new Rectangle(); // Inicializa o array do mapa 7
        }
        //Array obstaculo do mapa 1
        obstaculosMapa1[0] = new Rectangle(372, 375, 85, 67);
        obstaculosMapa1[1] = new Rectangle(295, 375, 80, 30);
        obstaculosMapa1[2] = new Rectangle(456, 375, 40, 30);
        obstaculosMapa1[3] = new Rectangle(496, 375, 52, 3);
        obstaculosMapa1[4] = new Rectangle(298, 150, 640, 3);
        obstaculosMapa1[5] = new Rectangle(500, 150, 36, 73);
        obstaculosMapa1[6] = new Rectangle(622, 150, 36, 73);
        obstaculosMapa1[7] = new Rectangle(559, 150, 40, 28);
        obstaculosMapa1[8] = new Rectangle(708, 150, 36, 64);
        obstaculosMapa1[9] = new Rectangle(860, 150, 36, 64);
        obstaculosMapa1[10] = new Rectangle(744, 150, 116, 71);
        obstaculosMapa1[11] = new Rectangle(745, 354, 256, 3);
        obstaculosMapa1[12] = new Rectangle(665, 560, 334, 3);
        obstaculosMapa1[13] = new Rectangle(938, 150, 3, 200); 
        obstaculosMapa1[14] = new Rectangle(985, 350, 3, 190); 
        obstaculosMapa1[15] = new Rectangle(945, 560, 3, 155); 
        obstaculosMapa1[16] = new Rectangle(461, 150, 3, 90); 
        obstaculosMapa1[17] = new Rectangle(289, 150, 6, 490);
        obstaculosMapa1[18] = new Rectangle(289, 640, 256, 3);
        obstaculosMapa1[19] = new Rectangle(541, 710, 407, 3);
        obstaculosMapa1[20] = new Rectangle(542, 641, 3, 55);
        obstaculosMapa1[21] = new Rectangle(880, 560, 50, 10);
        //Array obstaculo do mapa 2
        obstaculosMapa2[0] = new Rectangle(999, 180, 180, 30);
        obstaculosMapa2[1] = new Rectangle(511, 465, 33, 3); 
        obstaculosMapa2[2] = new Rectangle(511+45, 465-33, 85, 3); 
        obstaculosMapa2[3] = new Rectangle(511+145, 465-66, 112, 3); 
        obstaculosMapa2[4] = new Rectangle(511+338, 465-66, 415, 3);
        obstaculosMapa2[5] = new Rectangle(511+338, 465-66, 3, 145);
        obstaculosMapa2[6] = new Rectangle(511+255, 465-66, 3, 145);
        obstaculosMapa2[7] = new Rectangle(511+239, 465+197, 147, 3); 
        obstaculosMapa2[8] = new Rectangle(511+400, 465+163, 147, 3);
        obstaculosMapa2[9] = new Rectangle(511+560, 465+129, 200, 3);
        obstaculosMapa2[10] = new Rectangle(511+594, 465+5, 160, 3);
        obstaculosMapa2[11] = new Rectangle(511+338, 465-33, 259, 3);
        obstaculosMapa2[12] = new Rectangle(511+145, 465-33, 113, 3);
        obstaculosMapa2[13] = new Rectangle(511+46, 465, 79, 3);
        obstaculosMapa2[14] = new Rectangle(511+29, 465, 3, 79+33);
        obstaculosMapa2[15] = new Rectangle(511, 465+132, 90, 3);
        obstaculosMapa2[16] = new Rectangle(511+145, 465+132, 46, 3);
        obstaculosMapa2[17] = new Rectangle(511+145+33, 465+132+33, 46, 3);
        obstaculosMapa2[18] = new Rectangle(511+145, 465+132, 3, 112);
        obstaculosMapa2[19] = new Rectangle(511, 465+227, 90, 3);
        obstaculosMapa2[20] = new Rectangle(505, 465+152, 3, 90);
        obstaculosMapa2[21] = new Rectangle(511+99, 465+245, 30, 3);
        obstaculosMapa2[22] = new Rectangle(511-5, 374, 3, 108);
        obstaculosMapa2[23] = new Rectangle(511-5, 250, 3, 30);
        obstaculosMapa2[24] = new Rectangle(506+33, 0, 3, 221);
        obstaculosMapa2[25] = new Rectangle(506-105, 0, 3, 182);
        obstaculosMapa2[26] = new Rectangle(506-72, 231, 3, 1);
        obstaculosMapa2[27] = new Rectangle(506-39, 250, 3, 30);
        obstaculosMapa2[28] = new Rectangle(506-39, 374, 3, 30);
        obstaculosMapa2[29] = new Rectangle(272, 465-27, 85, 3);
        obstaculosMapa2[30] = new Rectangle(0, 465-27, 85, 3);
        obstaculosMapa2[31] = new Rectangle(115, 407, 74, 3);
        obstaculosMapa2[32] = new Rectangle(189, 407, 3, 120);
        obstaculosMapa2[33] = new Rectangle(272, 438, 3, 84);
        obstaculosMapa2[34] = new Rectangle(371, 406, 66, 3);
        obstaculosMapa2[35] = new Rectangle(431, 373, 3, 3);
        obstaculosMapa2[36] = new Rectangle(272, 480, 60, 3);
        obstaculosMapa2[37] = new Rectangle(272+60, 510, 3, 61);
        obstaculosMapa2[38] = new Rectangle(272+90, 510+99, 3, 69);
        obstaculosMapa2[39] = new Rectangle(272-99, 718, 157, 3);
        obstaculosMapa2[40] = new Rectangle(66, 480, 125, 3);
        obstaculosMapa2[41] = new Rectangle(66, 510, 3, 60);
        obstaculosMapa2[42] = new Rectangle(33, 576, 3, 120);
        obstaculosMapa2[43] = new Rectangle(33, 718, 58, 3);
        obstaculosMapa2[44] = new Rectangle(50, 313, 35, 3);
        obstaculosMapa2[46] = new Rectangle(1200, 140, 52, 3);
        obstaculosMapa2[47] = new Rectangle(140, 140, 150, 10);
        obstaculosMapa2[48] = new Rectangle(560, 20, 3, 3);
        
        //Array obstaculo do mapa 3
        obstaculosMapa3[0] = new Rectangle(367, 440, 28, 3);
        obstaculosMapa3[1] = new Rectangle(149, 220, 36, 1);
        obstaculosMapa3[2] = new Rectangle(22, 155, 36, 1);
        obstaculosMapa3[3] = new Rectangle(85, 68, 37, 1);
        obstaculosMapa3[4] = new Rectangle(470, 44, 35, 1);
        obstaculosMapa3[5] = new Rectangle(820, 163, 32, 1);
        obstaculosMapa3[6] = new Rectangle(1106, 190, 39, 1);
        obstaculosMapa3[7] = new Rectangle(1040, 528, 39, 1);
        obstaculosMapa3[8] = new Rectangle(945, 432, 39, 1);
        obstaculosMapa3[9] = new Rectangle(782, 643, 39, 1);
        obstaculosMapa3[10] = new Rectangle(690, 415, 1, 1); 
        obstaculosMapa3[11] = new Rectangle(433, 210, 3, 3); 
        obstaculosMapa3[12] = new Rectangle(947, 135, 1, 1);
        obstaculosMapa3[13] = new Rectangle(1203, 436, 1, 1); 
        obstaculosMapa3[14] = new Rectangle(1042, 650, 1, 1);
        obstaculosMapa3[15] = new Rectangle(473, 537, 1, 300);
        obstaculosMapa3[16] = new Rectangle(436, 537, 1, 300);
        obstaculosMapa3[17] = new Rectangle(473, 400, 1, 42);
        obstaculosMapa3[18] = new Rectangle(434, 400, 2, 42);
        obstaculosMapa3[19] = new Rectangle(434, 258, 2, 170);
        obstaculosMapa3[20] = new Rectangle(434, 258, 140, 1);
        obstaculosMapa3[21] = new Rectangle(574, 258, 1, 90);
        obstaculosMapa3[22] = new Rectangle(574, 348, 290, 1);
        obstaculosMapa3[23] = new Rectangle(864, 348, 1, 10);
        obstaculosMapa3[24] = new Rectangle(474, 358, 390, 1);
        
        //Array obstaculo do mapa 5
        obstaculosMapa5[0] = new Rectangle(0, 468, 1280, 3);
        obstaculosMapa5[1] = new Rectangle(12, 0, 3, 315);
        obstaculosMapa5[2] = new Rectangle(12, 428, 3, 42);
        obstaculosMapa5[3] = new Rectangle(608, 0, 1, 245);
        obstaculosMapa5[4] = new Rectangle(1000, 0, 1, 245);
        obstaculosMapa5[5] = new Rectangle(608, 245, 152, 1);
        obstaculosMapa5[6] = new Rectangle(852, 245, 147, 1);
        obstaculosMapa5[7] = new Rectangle(760, 245, 1, 30);
        obstaculosMapa5[8] = new Rectangle(852, 245, 1, 30);
        obstaculosMapa5[9] = new Rectangle(1275, 0, 1, 515);
        obstaculosMapa5[10] = new Rectangle(0, -7, 1267, 1);
        obstaculosMapa5[11] = new Rectangle(240, 110, 33, 1);
        obstaculosMapa5[12] = new Rectangle(1200, 174, 45, 1);
        obstaculosMapa5[13] = new Rectangle(-1, 540, 160, 01);
        obstaculosMapa5[14] = new Rectangle(-1, 660, 160, 01);
        obstaculosMapa5[15] = new Rectangle(144, 540, 01, 110);
        obstaculosMapa5[16] = new Rectangle(790, 3, 27, 91); // Colisão do Boss
        
        //Array obstaculo do mapa 7
        obstaculosMapa7[0] = new Rectangle(219, 146, 01, 270);
        obstaculosMapa7[1] = new Rectangle(219, 146, 820, 01);
        obstaculosMapa7[2] = new Rectangle(1039, 146, 01, 827);
        obstaculosMapa7[3] = new Rectangle(690, 720, 700, 01);
        obstaculosMapa7[4] = new Rectangle(477, 720, 90, 01);
        obstaculosMapa7[5] = new Rectangle(477, 307, 01, 900);
        obstaculosMapa7[6] = new Rectangle(396, 307, 80, 01);
        obstaculosMapa7[7] = new Rectangle(396, 307, 01, 110);
        obstaculosMapa7[8] = new Rectangle(219, 407, 180, 01);
        obstaculosMapa7[9] = new Rectangle(530, 0, 01, 233);
        obstaculosMapa7[10] = new Rectangle(730, 0, 01, 233);
        obstaculosMapa7[11] = new Rectangle(530, 233, 197, 01);
        obstaculosMapa7[12] = new Rectangle(740, 0, 300, 180);
        obstaculosMapa7[13] = new Rectangle(219, 0, 320, 180);
        obstaculosMapa7[14] = new Rectangle(1010, 300, 10, 77);
        obstaculosMapa7[15] = new Rectangle(792, 340, 56, 34);
        obstaculosMapa7[16] = new Rectangle(856, 502, 58, 34);
        obstaculosMapa7[17] = new Rectangle(856, 502, 58, 34);
        obstaculosMapa7[18] = new Rectangle(219, 340, 95, 01);
    }

    // Método para carregar as imagens necessarias para o jogo
    private void carregarImagens() {
        // No caso ela pega a variavel global e ela armazena a imagem que foi carregada e distribuida para ela.
        try { // Tentar carregar
            interiorCasaInicial = ImageIO.read(new File("imagens/mapa/fundoCasaInicial.png"));
            paredesInteriorCasa = ImageIO.read(new File("imagens/mapa/paredesInteriorCasa.png"));
            novoFundo = ImageIO.read(new File("imagens/mapa/mundoAFora.png"));
            fundoMundoAFora = ImageIO.read(new File("imagens/mapa/fundoMundoAFora.png"));
            novoFundo3 = ImageIO.read(new File("imagens/mapa/Mapa2-pokemon.png"));
            ilusaoMapa3 = ImageIO.read(new File("imagens/mapa/ilusaoMapa3.png"));
            mapaCombateGrama = ImageIO.read(new File("imagens/mapa/MapaCombateGrama.png"));
            mapaCombateBoss = ImageIO.read(new File("imagens/mapa/batalhaBoss.png"));
            mapaBoss = ImageIO.read(new File("imagens/mapa/mapaBoss.png"));
            isulaoMapa5 = ImageIO.read(new File("imagens/mapa/isulaoMapa5.png"));
            mapaLoja = ImageIO.read(new File("imagens/mapa/mapaLoja.png"));
            ilusaoMapaLoja = ImageIO.read(new File("imagens/mapa/ilusaoMapaLoja.png"));
            bauPrata = ImageIO.read(new File("imagens/Baus/bauPrata.png"));
            bauDourado = ImageIO.read(new File("imagens/Baus/bauDourado.png"));
        } catch (IOException e) { // Caso der erro em alguma das imagens.
            System.err.println("Erro ao carregar imagens na classe Mapa!" + e.getMessage()); // Ira aparecer esse codigo de erro
            System.exit(1); // Fechar o Jogo
        }
    }

    // Método para desenhar o fundo do mapa
    public void desenhar(Graphics g) {
        Monstros monstro = new Monstros();
        // Switch para desenhar os mapas aos fundos.
        switch (numeroMapa) {
            case 1:
                g.drawImage(interiorCasaInicial, 0, 0, 1280, 768, null); // Desenhar o mapa
                if (desenvolvedor) { // Caso estiver modo desenvolvedor desenhar os codigos dentro do if
                    g.fillRect(cacamba.x, cacamba.y, cacamba.width, cacamba.height);
                }
                break;
            case 2:
                g.drawImage(novoFundo, 0, 0, 1280, 768, null); // Desenhar o mapa
                break;
            case 3:
                g.drawImage(novoFundo3, 0, 0, 1280, 730, null); // Desenhar o mapa
                g.drawImage(bauPrata, 850, 20, 31, 30, null); // Desenhar o bau no mapa
                if(desenvolvedor) { // Caso estiver modo desenvolvedor desenhar os codigos dentro do if
                    g.fillRect(retanguloBau.x, retanguloBau.y, retanguloBau.width, retanguloBau.height); // Desenhar area de interação
                    g.setColor(Color.RED);
                    g.fillRect(areaCombateGrama.x, areaCombateGrama.y, areaCombateGrama.width, areaCombateGrama.height); // Desenhar area de interação
                    g.fillRect(areaCombateGrama2.x, areaCombateGrama2.y, areaCombateGrama2.width, areaCombateGrama2.height); // Desenhar area de interação
                    g.fillRect(areaCombateGrama3.x, areaCombateGrama3.y, areaCombateGrama3.width, areaCombateGrama3.height); // Desenhar area de interação
                    g.fillRect(retanguloBauAbrir.x, retanguloBauAbrir.y, retanguloBauAbrir.width, retanguloBauAbrir.height); // Desenhar area de interação
                }
                break;
            case 4:
                g.drawImage(mapaCombateGrama, 0, 0, 1280, 768, null); // Desenhar o mapa
                if(monstroImagem != null) { // Verifica se a imagem do monstro foi carregada
                    if(monstro.getTipo().equals("Slime")) { // Caso for Slime
                        g.drawImage(monstroImagem, 680, 350, 70, 70, null); // Desenha o modelo do personagem parado na tela
                    }else if(monstro.getTipo().equals("Goblin")) { // Caso for Goblin
                        g.drawImage(monstroImagem, 680, 350, 70, 70, null); // Desenha o modelo do personagem parado na tela
                    }else{ // Caso for FadaMacabra
                        g.drawImage(monstroImagem, 680, 350, 70, 70, null); // Desenha o modelo do personagem parado na tela
                    }
                }
                g.drawImage(personagemMapaCombate, 540, 350, 50, 70, null); // Desenha o player na tela de combate
                break;
            case 5:
                g.drawImage(mapaBoss, 0, 0, 1280, 768, null); // Desenhar o mapa
                    if (desenvolvedor) { // Caso estiver modo desenvolvedor desenhar os codigos dentro do if
                        g.fillRect(areaCombateBoss.x, areaCombateBoss.y, areaCombateBoss.width, areaCombateBoss.height); // Desenhar area de interação
                    }
                g.drawImage(imagemBoss, 740, 10, 120, 140, null);
                break;
            case 6:
                g.drawImage(mapaCombateBoss,0,0,1280,768,null); // Desenhar o mapa
                if(imagemBoss != null){ // Verificação se a imagem esta vazia ou não
                    g.drawImage(imagemBoss, 665, 270, 120, 140, null); // Desenhar o boss na tela
                }
                g.drawImage(personagemMapaCombate, 540, 350, 30, 50, null); // Desenhar o personagem na tela de combate do boss
                break;
            case 7:
                g.drawImage(mapaLoja, 0, 0, 1280, 768, null); // Desenhar o mapa
                if (desenvolvedor) { // Caso estiver modo desenvolvedor desenhar os codigos dentro do if
                g.fillRect(areaShop.x, areaShop.y, areaShop.width, areaShop.height); // Desenhar area de interação
                }
                break;
        }
    }

    // Método para desenhar a ilusão (Mesma coisa que o desenhar em cima, porem isso aqui é so desenhado depois de desenhar o personagem)
    public void desenharIlusao(Graphics g) {
        switch (numeroMapa) {
            case 1:
                g.drawImage(paredesInteriorCasa, 0, 0, 1280, 768, null);
                g.setColor(Color.RED); // Definindo a cor
                if (desenvolvedor) { // Caso estiver modo desenvolvedor desenhar os codigos dentro do if
                for (Rectangle obstaculo : obstaculosMapa1) { // Utilizando for para imprimir as colisões
                    g.fillRect(obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height); // Desenhando as colisões
                }
                g.fillRect(areaMudancaFundo.x, areaMudancaFundo.y, areaMudancaFundo.width, areaMudancaFundo.height); // Desenhar area de innteração
                g.fillRect(musicam1.x, musicam1.y, musicam1.width, musicam1.height); // Desenhar area de innteração
                }
                break;
            case 2:
                g.drawImage(fundoMundoAFora, 0, -2, 1280, 768, null); 
                g.setColor(Color.RED); // Definindo a cor
                if (desenvolvedor) { // Caso estiver modo desenvolvedor desenhar os codigos dentro do if
                    for (Rectangle obstaculo : obstaculosMapa2) { // Utilizando for para imprimir as colisões
                        g.fillRect(obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height); // Desenhando as colisões
                }
                g.fillRect(areaMudancaFundo4.x, areaMudancaFundo4.y, areaMudancaFundo4.width, areaMudancaFundo4.height); // Desenhar area de innteração
                g.fillRect(areaMudancaFundo2.x, areaMudancaFundo2.y, areaMudancaFundo2.width, areaMudancaFundo2.height); // Desenhar area de innteração
                g.setColor(Color.orange); // Definindo a cor
                g.fillRect(placa1.x, placa1.y, placa1.width, placa1.height); // Desenhar area de innteração
                g.fillRect(placa2.x, placa2.y, placa2.width, placa2.height); // Desenhar area de innteração
                g.fillRect(placa3.x, placa3.y, placa3.width, placa3.height); // Desenhar area de innteração
                g.fillRect(placa4.x, placa4.y, placa4.width, placa4.height); // Desenhar area de innteração
                g.fillRect(placa5.x, placa5.y, placa5.width, placa5.height); // Desenhar area de innteração
                g.setColor(Color.gray); // Definindo a cor
                g.fillRect(areaMudancaFundo5.x, areaMudancaFundo5.y, areaMudancaFundo5.width, areaMudancaFundo5.height); // Desenhar area de innteração
                g.fillRect(areaMudancaFundo7.x, areaMudancaFundo7.y, areaMudancaFundo7.width, areaMudancaFundo7.height); // Desenhar area de innteração
                g.fillRect(areaMudancaFundo9.x, areaMudancaFundo9.y, areaMudancaFundo9.width, areaMudancaFundo9.height); // Desenhar area de innteração
                }
                break;
            case 3:
                g.drawImage(ilusaoMapa3, 0, 0, 1280, 768, null);
                g.setColor(Color.RED); // Cor para os obstáculos
                if (desenvolvedor) { // Caso estiver modo desenvolvedor desenhar os codigos dentro do if
                for (Rectangle obstaculo : obstaculosMapa3) { // Utilizando for para imprimir as colisões
                g.fillRect(obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height); // Desenhando as colisões
            }
                g.fillRect(areaMudancaFundo3.x, areaMudancaFundo3.y, areaMudancaFundo3.width, areaMudancaFundo3.height); // Desenhar area de innteração
                }
                break;
            case 4:
                break;
            case 5:
                g.drawImage(isulaoMapa5, 0, 0, 1280, 768, null);
                if (desenvolvedor) { // Caso estiver modo desenvolvedor desenhar os codigos dentro do if
                    g.setColor(Color.RED); // Cor para os obstáculos
                    for (Rectangle obstaculo : obstaculosMapa5) { // Utilizando for para imprimir as colisões
                        g.fillRect(obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height); // Desenhando as colisões
                    }
                    g.setColor(Color.gray); // Definindo cor
                    g.fillRect(areaMudancaFundo6.x, areaMudancaFundo6.y, areaMudancaFundo6.width, areaMudancaFundo6.height); // Desenhar area de innteração
                    g.fillRect(areaMudancaFundo8.x, areaMudancaFundo8.y, areaMudancaFundo8.width, areaMudancaFundo8.height); // Desenhar area de innteração
                    g.setColor(Color.orange); // Definindo cor
                    g.fillRect(placa6.x, placa6.y, placa6.width, placa6.height); // Desenhar area de innteração
                }
                break;
            case 6:
                break;
            case 7:
                g.drawImage(ilusaoMapaLoja, 0, 0, 1280, 768, null);
                if (desenvolvedor) { // Caso estiver modo desenvolvedor desenhar os codigos dentro do if
                    g.setColor(Color.RED); // Cor para os obstáculos1
                    for (Rectangle obstaculo : obstaculosMapa7) { // Utilizando for para imprimir as colisões
                        g.fillRect(obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height); // Desenhando as colisões
                    }
                    g.setColor(Color.gray); // Definindo cor
                    g.fillRect(areaMudancaFundo10.x, areaMudancaFundo10.y, areaMudancaFundo10.width, areaMudancaFundo10.height); // Desenhar area de innteração
                }
                break;
        }
    }

    // Método para verificar se o player pode mover para a posição especificada
    public boolean podeMover(int x, int y) {
        Rectangle areaPlayer = new Rectangle(x, y, 40, 70); // Área do player

        switch (numeroMapa) {
            case 1:
                for (Rectangle obstaculo : obstaculosMapa1) { // Ele meio que Carrega todos as colisoes desse mapa
                    if (obstaculo != null && areaPlayer.intersects(obstaculo)) { // Caso o player tentando entrar dentro ca colisão
                        return false; // Colisão detectada, não pode mover
                    }
                }
                break;
            case 2:
                for (Rectangle obstaculo : obstaculosMapa2) { // Ele meio que Carrega todos as colisoes desse mapa
                    if (obstaculo != null && areaPlayer.intersects(obstaculo)) { // Caso o player tentando entrar dentro ca colisão
                        return false; // Colisão detectada, não pode mover
                    }
                }
                break;
            case 3:
                for (Rectangle obstaculo : obstaculosMapa3) { // Ele meio que Carrega todos as colisoes desse mapa
                    if (obstaculo != null && areaPlayer.intersects(obstaculo)) { // Caso o player tentando entrar dentro ca colisão
                        return false; // Colisão detectada, não pode mover
                    }
                }
                if(retanguloBau != null && areaPlayer.intersects(retanguloBau)){ // Caso o player tentando entrar dentro ca colisão
                    return false;
                }
                break;
            case 5:
                for (Rectangle obstaculo : obstaculosMapa5) { // Ele meio que Carrega todos as colisoes desse mapa
                    if (obstaculo != null && areaPlayer.intersects(obstaculo)) { // Caso o player tentando entrar dentro ca colisão
                        return false; // Colisão detectada, não pode mover
                    }
                }
                break;
            case 7:
                for (Rectangle obstaculo : obstaculosMapa7) { // Ele meio que Carrega todos as colisoes desse mapa
                    if (obstaculo != null && areaPlayer.intersects(obstaculo)) { // Caso o player tentando entrar dentro ca colisão
                        return false; // Colisão detectada, não pode mover
                    }
                }
                break;

        }
        return true; // Sem colisão, pode mover
    }

    // Metodo para caso o bau foi aberto ou não
    public void setBauPrata(BufferedImage bauPrata) {
        this.bauPrata = bauPrata;
    }

    // Método para obter as coordenadas de spawn do mapa
    public int getXSpawn() {
        return xSpawn;
    }
    // Método para obter as coordenadas de spawn do mapa
    public int getYSpawn() {
        return ySpawn;
    }
    // Métodos para obter as coordenadas de spawn do mapa
    public void setYSpawn(int y){
         ySpawn = y;
    }// Métodos para obter as coordenadas de spawn do mapa
    public void setXSpawn(int x){
        xSpawn = x;
    }
    // Método para obter o número do mapa
    public int getNumeroMapa() {
        return numeroMapa;
    }

    // Métodos para obter as áreas de transição
    public Rectangle getAreaMudancaFundo() {
        return areaMudancaFundo;
    }
    // Métodos para obter as áreas de transição
    public Rectangle getAreaMudancaFundo2() {
        return areaMudancaFundo2;
    }
    // Métodos para obter as áreas de transição
    public Rectangle getAreaMudancaFundo3() {
        return areaMudancaFundo3;
    }
    // Métodos para obter as áreas de transição
    public Rectangle getAreaMudancaFundo4() {
        return areaMudancaFundo4;
    }

    // Método para carregar a imagem do monstro (chamado apenas na tela de combate)
    public void carregarImagemMonstro(Monstros monstro) {
        try {
            this.monstroImagem = ImageIO.read(new File("imagens/monstros/" + monstro.getTipo() + ".png"));
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagem do monstro " + monstro.getTipo() + " na classe Mapa!" + e.getMessage());
        }
    }
    public void carregarImagemBoss(Monstros monstro){
        try {
            this.imagemBoss = ImageIO.read(new File("imagens/monstros/MoonDust.png"));
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagem do monstro " + monstro.getTipo() + " na classe Mapa!" + e.getMessage());
        }
    }

    // Metodo get do Desenvolvedor
    public boolean isDesenvolvedor() {
        return desenvolvedor;
    }
    // Metodo set do Desenvolvedor
    public void setDesenvolvedor(boolean desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    
}