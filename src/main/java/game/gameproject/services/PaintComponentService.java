package game.gameproject.services;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.gameproject.bdd.DatabaseConfig;

public class PaintComponentService {
	
	private BufferedImage simOnline;
    private BufferedImage simPing1;
    private BufferedImage simPing2;
    private BufferedImage simPing3;
    private BufferedImage simPing4;
    private BufferedImage simPing5;
    
    public PaintComponentService() {
    	carregarImagens();
    }
	
	private void carregarImagens() {
        try {
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

	public void desenharInformacoes(Graphics g) {
    	g.setColor(Color.BLACK);
        g.fillRect(1138, 706, 140, 24);

        g.setColor(Color.WHITE);
        g.fillRect(1140, 709, 140, 22);
        
        int ping = (int) DatabaseConfig.getDatabasePing();
        
        if (ping <= 15) {
            g.drawImage(simPing1, 1140, 710, 20, 20, null);
        } else if (ping <= 22) {
            g.drawImage(simPing2, 1140, 710, 20, 20, null);
        } else if (ping <= 28) {
            g.drawImage(simPing3, 1140, 710, 20, 20, null);
        } else if (ping <= 38) {
            g.drawImage(simPing4, 1140, 710, 20, 20, null);
        } else {
            g.drawImage(simPing5, 1140, 710, 20, 20, null);
        }
        
        g.setColor(Color.BLACK);
        g.drawString(DatabaseConfig.getDatabasePing()+"ms", 1160, 725);
        
        g.drawImage(simOnline, 1205, 715, 12, 12, null);
        g.setColor(Color.BLACK);
        g.drawString(PlayerService.getPlayersOnline()+"", 1220, 725);
    }

    public void desenharNomeJogador(Graphics g, String nome, int xPersonagem, int yPersonagem, int larguraPersonagem) {
        g.setColor(Color.BLACK);

        String nomeRestante = nome;

        FontMetrics fontMetrics = g.getFontMetrics();
        int larguraTexto = fontMetrics.stringWidth(nome);
        int alturaTexto = fontMetrics.getHeight();

        int xTexto = xPersonagem + (larguraPersonagem - larguraTexto) / 2;
        int yTexto = yPersonagem - alturaTexto;

        g.setColor(Color.BLACK);
        g.fillRect(xTexto - 5, yTexto - alturaTexto, larguraTexto + 10, alturaTexto + 5); // Borda preta

        g.setColor(Color.WHITE);
        g.fillRect(xTexto - 4, yTexto - alturaTexto + 1, larguraTexto + 8, alturaTexto + 3); // Retângulo branco

        g.setColor(Color.BLACK);
        g.drawString(nomeRestante, xTexto, yTexto);
    }
    
}
