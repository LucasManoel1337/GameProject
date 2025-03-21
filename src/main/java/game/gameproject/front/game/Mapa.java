package game.gameproject.front.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Mapa{
    
    // Variaveis Globais
    public int numeroMapa;
    private int xSpawn;
    private int ySpawn;
    public Mapa(int numeroMapa, BufferedImage personagem) {
    this.numeroMapa = numeroMapa;
        
        carregarImagens();
        
        // Define as coordenadas de spawn para cada mapa
        switch (numeroMapa) {
            case 1:
                xSpawn = 580;
                ySpawn = 279;
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
}