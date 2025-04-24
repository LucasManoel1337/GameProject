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

public class JpErroBanco extends JPanel {

    private Image imgFundo;
    private Image logo;

    private LauncherFrame LF;

    public JpErroBanco(LauncherFrame launcherFrame) {
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

        JLabel lLogin = new JLabel("ERRO INESPERADO");
        lLogin.setFont(new Font("Arial", Font.BOLD, 18));
        lLogin.setBounds(86, 100, 350, 40);
        lLogin.setForeground(Color.BLACK);
        add(lLogin);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgFundo, 390, 0, 890, 768, this);
        g.drawImage(logo, 140, 0, 100, 100, this);
    }
}
