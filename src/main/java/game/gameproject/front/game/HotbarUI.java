package game.gameproject.front.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HotbarUI {

	private BufferedImage hotbar;
	
	
	public HotbarUI() {
        carregarImagens();
    }
	
	private void carregarImagens() {
        try {
            hotbar = ImageIO.read(new File("imagens/game/interface/hotbar.png"));
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagens na classe HotbarUI! " + e.getMessage());
            System.exit(1);
        }
    }
	
	public void render(Graphics g) {
		g.drawImage(hotbar, 385, 678, 500, 50, null);
    }
}


