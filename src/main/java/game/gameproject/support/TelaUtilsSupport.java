package game.gameproject.support;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class TelaUtilsSupport {
	private static final int RESOLUCAO_PADRAO_LARGURA = 1280;
    private static final int RESOLUCAO_PADRAO_ALTURA = 768;

    private static final Dimension RESOLUCAO_ATUAL = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double SCALE_X = RESOLUCAO_ATUAL.getWidth() / RESOLUCAO_PADRAO_LARGURA;
    private static final double SCALE_Y = RESOLUCAO_ATUAL.getHeight() / RESOLUCAO_PADRAO_ALTURA;

    public static int scaleX(int valor) {
        return (int) (valor * SCALE_X);
    }

    public static int scaleY(int valor) {
        return (int) (valor * SCALE_Y);
    }

    public static Rectangle scaleBounds(int x, int y, int width, int height) {
        return new Rectangle(scaleX(x), scaleY(y), scaleX(width), scaleY(height));
    }
}
