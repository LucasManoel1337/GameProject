package game.gameproject.controller;

import game.gameproject.front.Autenticacao.JpLogin;
import game.gameproject.front.Autenticacao.JpRegistrar;

import javax.swing.*;

public class LauncherFrame extends JFrame {
    private JPanel currentPanel;
    private JpLogin telaLogin;
    private JpRegistrar telaRegistrar;

    public LauncherFrame() {
        setTitle("Room 5 Studios - Launcher");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("imagens/login/logoEmpresa.png").getImage());

        // Passa a referência correta do LauncherFrame para JpLogin
        telaLogin = new JpLogin(this);
        currentPanel = telaLogin;
        add(currentPanel);

        setVisible(true);
    }

    public void switchToRegisterPanel() {
        remove(currentPanel);
        if (telaRegistrar == null) {
            telaRegistrar = new JpRegistrar(this); // Passa a referência correta
        }
        currentPanel = telaRegistrar;
        add(currentPanel);
        revalidate();
        repaint();
    }

    public void switchToLoginPanel() {
        remove(currentPanel);
        if (telaLogin == null) {
            telaLogin = new JpLogin(this);
        }
        currentPanel = telaLogin;
        add(currentPanel);
        revalidate();
        repaint();
    }
}
