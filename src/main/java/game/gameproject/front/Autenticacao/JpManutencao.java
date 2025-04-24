package game.gameproject.front.Autenticacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.gameproject.controller.LauncherFrame;
import game.gameproject.dto.VersoesDto;
import game.gameproject.services.AtualizarGameService;
import game.gameproject.support.ImagemDiretorios;

public class JpManutencao extends JPanel {

    private Image imgFundo;
    private Image logo;

    private LauncherFrame LF;

    public JpManutencao(LauncherFrame launcherFrame) {
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

        JLabel lLogin = new JLabel("JOGO EM MANUTENÇÃO");
        lLogin.setFont(new Font("Arial", Font.BOLD, 18));
        lLogin.setBounds(86, 100, 350, 40);
        lLogin.setForeground(Color.BLACK);
        add(lLogin);

        JLabel ltext1 = new JLabel("Atualmente o jogo está passando por uma");
        ltext1.setFont(new Font("Arial", Font.BOLD, 14));
        ltext1.setBounds(40, 140, 350, 40);
        ltext1.setForeground(Color.BLACK);
        add(ltext1);
        
        JLabel ltext2 = new JLabel("manutenção, fique atento nos canais de");
        ltext2.setFont(new Font("Arial", Font.BOLD, 14));
        ltext2.setBounds(46, 156, 350, 40);
        ltext2.setForeground(Color.BLACK);
        add(ltext2);
        
        JLabel ltext3 = new JLabel("comunicação para saber o que está ocorrendo");
        ltext3.setFont(new Font("Arial", Font.BOLD, 14));
        ltext3.setBounds(28, 172, 350, 40);
        ltext3.setForeground(Color.BLACK);
        add(ltext3);
        
        JLabel ltext4 = new JLabel("e também ver a previsão de quando irá");
        ltext4.setFont(new Font("Arial", Font.BOLD, 14));
        ltext4.setBounds(48, 188, 350, 40);
        ltext4.setForeground(Color.BLACK);
        add(ltext4);
        
        JLabel ltext5 = new JLabel("sair de manutenção para voltar a jogar.");
        ltext5.setFont(new Font("Arial", Font.BOLD, 14));
        ltext5.setBounds(48, 204, 350, 40);
        ltext5.setForeground(Color.BLACK);
        add(ltext5);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgFundo, 390, 0, 890, 768, this);
        g.drawImage(logo, 140, 0, 100, 100, this);
    }
}
