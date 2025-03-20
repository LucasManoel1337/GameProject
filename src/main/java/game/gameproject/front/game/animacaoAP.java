package game.gameproject.front.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class animacaoAP {

    private BufferedImage[] ataqueImagens = new BufferedImage[16]; // Vetor da quantidade de imagens para animação
    private int ataqueIndex = 0;
    private Timer timerAtaque; // Timer para a animação de ataque

    public animacaoAP() {
        // Carregar as imagens da animação
        try {
            for (int i = 1; i <= 16; i++) { // Ajuste o loop para o número de imagens
                ataqueImagens[i - 1] = ImageIO.read(new File(String.format("imagens/animacaoBatalha/player/ataquetrue%d.png", i)));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagens!" + e.getMessage());
            System.exit(1);
        }
    }

    // Método para iniciar a animação de ataque
    public void iniciarAnimacaoAtaque() {
        ataqueIndex = 0; // Reiniciar o índice da imagem
        timerAtaque = new Timer(30, new ActionListener() { // Ajustar o timer para animação ficar uma delicia 68
            @Override
            public void actionPerformed(ActionEvent e) {
                ataqueIndex = (ataqueIndex + 1) % ataqueImagens.length; // Avançar para a próxima imagem
                if (ataqueIndex == 0) { // Se a animação terminou
                    pararAnimacaoAtaque();
                }
            }
        });
        timerAtaque.start();
    }

    // Método para parar a animação de ataque
    public void pararAnimacaoAtaque() {
        if (timerAtaque != null) {
            timerAtaque.stop();
        }
    }

    // Método para desenhar a animação em um componente gráfico (JPanel, por exemplo)
    public void desenharAnimacao(Graphics g, int x, int y, int largura, int altura) {
        if (timerAtaque != null && timerAtaque.isRunning()) {
            g.drawImage(ataqueImagens[ataqueIndex], x, y, largura, altura, null);
        }
    }
}