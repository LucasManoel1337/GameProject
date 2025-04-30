package game.gameproject.controller;

import game.gameproject.front.Autenticacao.JpManutencao;
import game.gameproject.front.Autenticacao.JpAtualizar;
import game.gameproject.front.Autenticacao.JpLogin;
import game.gameproject.front.Autenticacao.JpRegistrar;
import game.gameproject.front.Autenticacao.JpSemInternet;
import game.gameproject.front.Autenticacao.JpErroBanco;
import game.gameproject.support.ImagemDiretorios;

import java.awt.GraphicsEnvironment;

import javax.swing.*;

public class LauncherFrame extends JFrame {
    private JPanel currentPanel;
    private JpLogin telaLogin;
    private JpRegistrar telaRegistrar;
    private JpAtualizar telaAtualizar;
    private JpManutencao telaManutencao;
    private JpSemInternet telaSemInternet;
    private JpErroBanco telaErroBanco;

    public LauncherFrame() {
    	 ImagemDiretorios ImgD = new ImagemDiretorios();

         setTitle("Room 5 Studios - Launcher");
         setSize(1280, 768);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setMaximizedBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
         setExtendedState(JFrame.MAXIMIZED_BOTH);
         setLocationRelativeTo(null);
         setIconImage(new ImageIcon(ImgD.getGameIcon()).getImage());

         telaLogin = new JpLogin(this);
         currentPanel = telaLogin;
         add(currentPanel);
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
    
    public void switchToAtualizarPanel() {
        remove(currentPanel);
        if (telaAtualizar == null) {
            telaAtualizar = new JpAtualizar(this);
        }
        currentPanel = telaAtualizar;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToManutencaoPanel() {
        remove(currentPanel);
        if (telaManutencao == null) {
            telaManutencao = new JpManutencao(this);
        }
        currentPanel = telaManutencao;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToSemInternetPanel() {
        remove(currentPanel);
        if (telaSemInternet == null) {
            telaSemInternet = new JpSemInternet(this);
        }
        currentPanel = telaSemInternet;
        add(currentPanel);
        revalidate();
        repaint();
    }
    
    public void switchToErroBancoPanel() {
        remove(currentPanel);
        if (telaErroBanco == null) {
            telaErroBanco = new JpErroBanco(this);
        }
        currentPanel = telaErroBanco;
        add(currentPanel);
        revalidate();
        repaint();
    }
}
