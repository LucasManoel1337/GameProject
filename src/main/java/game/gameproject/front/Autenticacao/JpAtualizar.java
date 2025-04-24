package game.gameproject.front.Autenticacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.gameproject.controller.LauncherFrame;
import game.gameproject.dto.VersoesDto;
import game.gameproject.services.AtualizarGameService;
import game.gameproject.support.ImagemDiretorios;

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

        ImagemDiretorios ImgD = new ImagemDiretorios();

        try {
            imgFundo = ImageIO.read(new File(ImgD.getFundoLaucher()));
            logo = ImageIO.read(new File(ImgD.getEmpresaIcon()));
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
        
        JLabel ltext5 = new JLabel("Obs: Ao clicar no botão ele irá atualizar");
        ltext5.setFont(new Font("Arial", Font.BOLD, 14));
        ltext5.setBounds(50, 300, 350, 40);
        ltext5.setForeground(Color.RED);
        add(ltext5);
        
        JLabel ltext6 = new JLabel("via github, por conta disso irá abrir o cmd,");
        ltext6.setFont(new Font("Arial", Font.BOLD, 14));
        ltext6.setBounds(40, 316, 350, 40);
        ltext6.setForeground(Color.RED);
        add(ltext6);
        
        JLabel ltext7= new JLabel("e irá aparecer algumas coisas baixando,");
        ltext7.setFont(new Font("Arial", Font.BOLD, 14));
        ltext7.setBounds(45, 332, 350, 40);
        ltext7.setForeground(Color.RED);
        add(ltext7);
        
        JLabel ltext8= new JLabel("que no caso é atualização, as vezes pode");
        ltext8.setFont(new Font("Arial", Font.BOLD, 14));
        ltext8.setBounds(41, 348, 350, 40);
        ltext8.setForeground(Color.RED);
        add(ltext8);
        
        JLabel ltext9= new JLabel("dar algum problema. Caso ocorrer um");
        ltext9.setFont(new Font("Arial", Font.BOLD, 14));
        ltext9.setBounds(50, 364, 350, 40);
        ltext9.setForeground(Color.RED);
        add(ltext9);
        
        JLabel ltext10= new JLabel("problema, recomendo que delete a pasta");
        ltext10.setFont(new Font("Arial", Font.BOLD, 14));
        ltext10.setBounds(40, 380, 350, 40);
        ltext10.setForeground(Color.RED);
        add(ltext10);
        
        JLabel ltext11= new JLabel("do jogo e clone o jogo novamente,");
        ltext11.setFont(new Font("Arial", Font.BOLD, 14));
        ltext11.setBounds(58, 396, 350, 40);
        ltext11.setForeground(Color.RED);
        add(ltext11);
        
        JLabel ltext12= new JLabel("assim estará atualizado e pronto");
        ltext12.setFont(new Font("Arial", Font.BOLD, 14));
        ltext12.setBounds(66, 412, 350, 40);
        ltext12.setForeground(Color.RED);
        add(ltext12);
        
        JLabel ltext13= new JLabel("para jogar!");
        ltext13.setFont(new Font("Arial", Font.BOLD, 14));
        ltext13.setBounds(148, 428, 350, 40);
        ltext13.setForeground(Color.RED);
        add(ltext13);
        
        
        
        JLabel ltext14= new JLabel("Obs: Esse sistema foi testado somente");
        ltext14.setFont(new Font("Arial", Font.BOLD, 14));
        ltext14.setBounds(50, 460, 350, 40);
        ltext14.setForeground(Color.RED);
        add(ltext14);
        
        JLabel ltext15= new JLabel("em Windows, mas foi feito para Linux e");
        ltext15.setFont(new Font("Arial", Font.BOLD, 14));
        ltext15.setBounds(50, 476, 350, 40);
        ltext15.setForeground(Color.RED);
        add(ltext15);
        
        JLabel ltext16= new JLabel("Mac também, porém não foi testado!");
        ltext16.setFont(new Font("Arial", Font.BOLD, 14));
        ltext16.setBounds(58, 492, 350, 40);
        ltext16.setForeground(Color.RED);
        add(ltext16);
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
