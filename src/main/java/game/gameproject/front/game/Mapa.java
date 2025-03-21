package game.gameproject.front.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Mapa{
    
    // Variaveis Globais
    public int numeroMapa;
    // Array especifico para cada mapa
    public Rectangle musicam1;
    public Rectangle areaMudancaFundo; // Área retangular para mudar o fundo
    public Rectangle areaMudancaFundo2;
    public Rectangle areaMudancaFundo3;
    public Rectangle areaMudancaFundo4;
    private int xSpawn;
    private int ySpawn;
    public Mapa(int numeroMapa, BufferedImage personagem) {
    this.numeroMapa = numeroMapa;
        
        carregarImagens();

        // Define a área retangular para mudar o fundo
        
        areaMudancaFundo = new Rectangle(545, 625, 95, 50); // Largura e altura de 100 pixels
        areaMudancaFundo2 = new Rectangle(733, -20, 30, 30);
        areaMudancaFundo3 = new Rectangle(725, 655, 50, 50); // Area de transição
        areaMudancaFundo4 = new Rectangle(1020, 200, 20, 30); // Area de transição
        musicam1 = new Rectangle(390,440,20,20); // Area de interação para aparecer a imagem de controles e começar a musica da casa.
        
        // Define as coordenadas de spawn para cada mapa
        switch (numeroMapa) {
            case 1:
                xSpawn = 580;
                ySpawn = 279;
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

    

    // Método para carregar as imagens necessarias para o jogo
    private void carregarImagens() {
        System.out.println();
    }

    // Método para desenhar o fundo do mapa
    public void desenhar(Graphics g) {
        // Switch para desenhar os mapas aos fundos.
        switch (numeroMapa) {
            case 1:
                break;
        }
    }

    // Método para desenhar a ilusão (Mesma coisa que o desenhar em cima, porem isso aqui é so desenhado depois de desenhar o personagem)
    public void desenharIlusao(Graphics g) {
        switch (numeroMapa) {
            case 1:
                g.setColor(Color.RED); // Definindo a cor
                g.fillRect(areaMudancaFundo.x, areaMudancaFundo.y, areaMudancaFundo.width, areaMudancaFundo.height); // Desenhar area de innteração
                g.fillRect(musicam1.x, musicam1.y, musicam1.width, musicam1.height); // Desenhar area de innteração
                break;
            case 2:
                g.setColor(Color.RED); // Definindo a cor
                g.fillRect(areaMudancaFundo4.x, areaMudancaFundo4.y, areaMudancaFundo4.width, areaMudancaFundo4.height); // Desenhar area de innteração
                g.fillRect(areaMudancaFundo2.x, areaMudancaFundo2.y, areaMudancaFundo2.width, areaMudancaFundo2.height); // Desenhar area de innteração
                break;
        }
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