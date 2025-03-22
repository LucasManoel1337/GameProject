package game.gameproject.support;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerAvisosLabelsSupport {

    public void exibirAvisoTemporario(JLabel label) {
        label.setVisible(true); // Exibe a label

        // Timer para ocultar a label após 5 segundos
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
            }
        });
        timer.setRepeats(false); // Executa apenas uma vez
        timer.start();
    }
}