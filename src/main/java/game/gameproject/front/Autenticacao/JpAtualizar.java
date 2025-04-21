package game.gameproject.front.Autenticacao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.gameproject.controller.LauncherFrame;
import game.gameproject.dto.VersoesDto;
import game.gameproject.services.AtualizarGameService;

public class JpAtualizar extends JPanel {

    private Image imgFundo;
    private Image logo;
    private Color corLaranja = new Color(255, 140, 0);

    private LauncherFrame LF;

    public JpAtualizar(LauncherFrame launcherFrame) {
        this.LF = launcherFrame; // Agora LF não será mais null
        setLayout(null);
        setBackground(Color.WHITE);
        
        AtualizarGameService AGS = new AtualizarGameService();

        try {
            imgFundo = ImageIO.read(new File("imagens/login/fundolaucher.png"));
            logo = ImageIO.read(new File("imagens/login/logoEmpresa.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        VersoesDto versoesDto = new VersoesDto();
        
        JLabel lVersao = new JLabel(versoesDto.getVersaoLauncher());
        lVersao.setFont(new Font("Arial", Font.BOLD, 12));
        lVersao.setBounds(1, 703, 350, 40);
        lVersao.setForeground(Color.BLACK);
        add(lVersao);

        // Título LOGIN
        JLabel lLogin = new JLabel("JOGO DESATUALIZADO");
        lLogin.setFont(new Font("Arial", Font.BOLD, 18));
        lLogin.setBounds(86, 100, 350, 40);
        lLogin.setForeground(Color.BLACK);
        add(lLogin);

        // Label Usuário
        JLabel ltext1 = new JLabel("Foi dedectado que há uma nova versão para o");
        ltext1.setFont(new Font("Arial", Font.BOLD, 14));
        ltext1.setBounds(30, 140, 350, 40);
        ltext1.setForeground(Color.BLACK);
        add(ltext1);
        
        JLabel ltext2 = new JLabel("jogo, por conta disso não será possível entrar");
        ltext2.setFont(new Font("Arial", Font.BOLD, 14));
        ltext2.setBounds(30, 156, 350, 40);
        ltext2.setForeground(Color.BLACK);
        add(ltext2);
        
        JLabel ltext3 = new JLabel("no jogo com essa versão, clique abaixo para");
        ltext3.setFont(new Font("Arial", Font.BOLD, 14));
        ltext3.setBounds(34, 172, 350, 40);
        ltext3.setForeground(Color.BLACK);
        add(ltext3);
        
        JLabel ltext4 = new JLabel("atualizar e assim conseguir entrar no jogo!");
        ltext4.setFont(new Font("Arial", Font.BOLD, 14));
        ltext4.setBounds(39, 188, 350, 40);
        ltext4.setForeground(Color.BLACK);
        add(ltext4);
        
        JButton btnAtualizar = new BotaoArredondado("Atualizar", corLaranja);
        btnAtualizar.setBounds(130, 240, 120, 50);
        		btnAtualizar.addActionListener(e -> AGS.atualizarJogoViaGit());
        add(btnAtualizar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgFundo, 390, 0, 890, 768, this);
        g.drawImage(logo, 140, 0, 100, 100, this);
    }

    // Classe do botão com bordas arredondadas
    private static class BotaoArredondado extends JButton {

        private final Color corFundo;

        public BotaoArredondado(String texto, Color corFundo) {
            super(texto);
            this.corFundo = corFundo;
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 20));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(corFundo);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            super.paintComponent(g);
            g2.dispose();
        }
    }
}
