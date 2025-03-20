package game.gameproject.front.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Mapa{
    
    // Variaveis Globais
    public int numeroMapa;
    private BufferedImage interiorCasaInicial;
    private BufferedImage paredesInteriorCasa;
    private BufferedImage novoFundo; // Novo fundo da tela
    private BufferedImage fundoMundoAFora;
    // Array especifico para cada mapa
    private Rectangle[] obstaculosMapa1 = new Rectangle[100]; // Ajuste o tamanho do array
    private Rectangle[] obstaculosMapa2 = new Rectangle[100]; // Ajuste o tamanho do array para o mapa 2
    public Rectangle musicam1;
    public Rectangle areaMudancaFundo; // Área retangular para mudar o fundo
    public Rectangle areaMudancaFundo2;
    public Rectangle areaMudancaFundo3;
    public Rectangle areaMudancaFundo4;
    public Rectangle areaMudancaFundo5;
    private int xSpawn;
    private int ySpawn;
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
        musicam1 = new Rectangle(390,440,20,20); // Area de interação para aparecer a imagem de controles e começar a musica da casa.
        
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
    }

    //Funcao inicializar array obstaculos
    private void inicializarAreasObstaculos() {
        // Inicializar todos os elementos do array com Rectangles vazios
        for (int i = 0; i < obstaculosMapa1.length; i++) {
            obstaculosMapa1[i] = new Rectangle(); // Inicializa o array do mapa 1
            obstaculosMapa2[i] = new Rectangle(); // Inicializa o array do mapa 2
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
    }

    // Método para carregar as imagens necessarias para o jogo
    private void carregarImagens() {
        // No caso ela pega a variavel global e ela armazena a imagem que foi carregada e distribuida para ela.
        try { // Tentar carregar
            interiorCasaInicial = ImageIO.read(new File("imagens/mapa/fundoCasaInicial.png"));
            paredesInteriorCasa = ImageIO.read(new File("imagens/mapa/paredesInteriorCasa.png"));
            novoFundo = ImageIO.read(new File("imagens/mapa/mundoAFora.png"));
            fundoMundoAFora = ImageIO.read(new File("imagens/mapa/fundoMundoAFora.png"));
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
                break;
            case 2:
                g.drawImage(novoFundo, 0, 0, 1280, 768, null); // Desenhar o mapa
                break;
        }
    }

    // Método para desenhar a ilusão (Mesma coisa que o desenhar em cima, porem isso aqui é so desenhado depois de desenhar o personagem)
    public void desenharIlusao(Graphics g) {
        switch (numeroMapa) {
            case 1:
                g.drawImage(paredesInteriorCasa, 0, 0, 1280, 768, null);
                g.setColor(Color.RED); // Definindo a cor
                for (Rectangle obstaculo : obstaculosMapa1) { // Utilizando for para imprimir as colisões
                    g.fillRect(obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height); // Desenhando as colisões
                g.fillRect(areaMudancaFundo.x, areaMudancaFundo.y, areaMudancaFundo.width, areaMudancaFundo.height); // Desenhar area de innteração
                g.fillRect(musicam1.x, musicam1.y, musicam1.width, musicam1.height); // Desenhar area de innteração
                }
                break;
            case 2:
                g.drawImage(fundoMundoAFora, 0, -2, 1280, 768, null); 
                g.setColor(Color.RED); // Definindo a cor
                    for (Rectangle obstaculo : obstaculosMapa2) { // Utilizando for para imprimir as colisões
                        g.fillRect(obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height); // Desenhando as colisões
                g.fillRect(areaMudancaFundo4.x, areaMudancaFundo4.y, areaMudancaFundo4.width, areaMudancaFundo4.height); // Desenhar area de innteração
                g.fillRect(areaMudancaFundo2.x, areaMudancaFundo2.y, areaMudancaFundo2.width, areaMudancaFundo2.height); // Desenhar area de innteração
                g.setColor(Color.orange); // Definindo a cor
                g.setColor(Color.gray); // Definindo a cor
                g.fillRect(areaMudancaFundo5.x, areaMudancaFundo5.y, areaMudancaFundo5.width, areaMudancaFundo5.height); // Desenhar area de innteração
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

        }
        return true; // Sem colisão, pode mover
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

    //Métodos para obter as áreas de transição
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
    
}