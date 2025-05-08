package game.gameproject.support;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class SliderCustomUI extends BasicSliderUI {

    private final Color corPreenchimento;

    public SliderCustomUI(JSlider slider, Color corPreenchimento) {
        super(slider);
        this.corPreenchimento = corPreenchimento;
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Rectangle trackBounds = trackRect;

        // Fundo da trilha
        g2.setColor(Color.DARK_GRAY);
        g2.fillRoundRect(trackBounds.x, trackBounds.y + trackBounds.height / 2 - 2, trackBounds.width, 4, 4, 4);

        // Parte preenchida
        int valorAtual = xPositionForValue(slider.getValue());
        g2.setColor(corPreenchimento);
        g2.fillRoundRect(trackBounds.x, trackBounds.y + trackBounds.height / 2 - 2, valorAtual - trackBounds.x, 4, 4, 4);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
    }
}
