package game.gameproject.front.Autenticacao;

import game.gameproject.controller.GameFrame;
import game.gameproject.controller.LauncherFrame;
import game.gameproject.dto.VersoesDto;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.AutenticacaoService;
import game.gameproject.services.LoginService;
import game.gameproject.support.ImagemDiretorios;
import game.gameproject.support.TimerAvisosLabelsSupport;
import game.gameproject.support.TelaUtilsSupport;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class JpLogin extends JPanel {

    private Image imgFundo;
    private Image logo;
    private JPasswordField cSenha;
    private boolean senhaVisivel = false;
    private Color corLaranja = new Color(255, 140, 0);

    private LauncherFrame LF;

    public JLabel lNaoEncontrado = new JLabel("Usuário ou Senha estão errados!");
    public JLabel lCamposVazios = new JLabel("Não é possível se logar com algum campo vazio!");

    TimerAvisosLabelsSupport LabelSupportT = new TimerAvisosLabelsSupport();
    TelaUtilsSupport TelaUtils = new TelaUtilsSupport();

    public JpLogin(LauncherFrame launcherFrame) {
        this.LF = launcherFrame;
        setLayout(null);
        setBackground(Color.WHITE);

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
        lVersao.setBounds(TelaUtils.scaleBounds(1, 700, 350, 40));
        lVersao.setForeground(Color.BLACK);
        add(lVersao);

        JLabel lLogin = new JLabel("LOGIN");
        lLogin.setFont(new Font("Arial", Font.BOLD, 18));
        lLogin.setBounds(TelaUtils.scaleBounds(168, 100, 350, 40));
        lLogin.setForeground(Color.BLACK);
        add(lLogin);

        JLabel lUsuario = new JLabel("Usuário");
        lUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        lUsuario.setBounds(TelaUtils.scaleBounds(30, 140, 350, 40));
        lUsuario.setForeground(Color.BLACK);
        add(lUsuario);

        JTextField cUsuario = new JTextField();
        cUsuario.setBounds(TelaUtils.scaleBounds(30, 170, 320, 40));
        cUsuario.setBackground(Color.LIGHT_GRAY);
        cUsuario.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        cUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        add(cUsuario);

        JLabel lSenha = new JLabel("Senha");
        lSenha.setFont(new Font("Arial", Font.BOLD, 14));
        lSenha.setBounds(TelaUtils.scaleBounds(30, 220, 350, 40));
        lSenha.setForeground(Color.BLACK);
        add(lSenha);

        lNaoEncontrado.setFont(new Font("Arial", Font.BOLD, 14));
        lNaoEncontrado.setBounds(TelaUtils.scaleBounds(115, 420, 350, 40));
        lNaoEncontrado.setForeground(Color.RED);
        lNaoEncontrado.setVisible(false);
        add(lNaoEncontrado);

        lCamposVazios.setFont(new Font("Arial", Font.BOLD, 14));
        lCamposVazios.setBounds(TelaUtils.scaleBounds(75, 420, 350, 40));
        lCamposVazios.setForeground(Color.RED);
        lCamposVazios.setVisible(false);
        add(lCamposVazios);

        cSenha = new JPasswordField();
        cSenha.setBounds(TelaUtils.scaleBounds(30, 250, 280, 40));
        cSenha.setBackground(Color.LIGHT_GRAY);
        cSenha.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        cSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        add(cSenha);

        JButton btnVerSenha = new JButton("👁");
        btnVerSenha.setBounds(TelaUtils.scaleBounds(315, 250, 35, 40));
        btnVerSenha.setBorder(null);
        btnVerSenha.setFocusPainted(false);
        btnVerSenha.setBackground(Color.LIGHT_GRAY);
        btnVerSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSenhaVisivel();
            }
        });
        add(btnVerSenha);

        JButton btnEntrar = new BotaoArredondado("Entrar", corLaranja);
        btnEntrar.setBounds(TelaUtils.scaleBounds(140, 330, 100, 50));
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lNaoEncontrado.setVisible(false);
                lCamposVazios.setVisible(false);

                if (cUsuario.getText().isEmpty() || cSenha.getText().isEmpty()) {
                    LabelSupportT.exibirAvisoTemporario(lCamposVazios);
                } else {
                    String usuario = cUsuario.getText();
                    String senha = new String(cSenha.getPassword());

                    LoginService loginService = new LoginService();
                    int resultado = loginService.login(usuario, senha);

                    if (resultado == 0) {
                        AutenticacaoService autenticacaoService = new AutenticacaoService();
                        infoPlayerDto playerInfo = autenticacaoService.autenticarUsuario();

                        GameFrame GF = new GameFrame();
                        if (playerInfo.getClasse() == null) {
                            GF.switchToEscolherClassePanel();
                        }
                        launcherFrame.dispose();
                    } else {
                        LabelSupportT.exibirAvisoTemporario(lNaoEncontrado);
                        cSenha.setText("");
                    }
                }
            }
        });
        add(btnEntrar);

        JLabel lblNaoTenhoCadastro = new JLabel("Não tenho cadastro");
        lblNaoTenhoCadastro.setFont(new Font("Arial", Font.BOLD, 14));
        lblNaoTenhoCadastro.setBounds(TelaUtils.scaleBounds(144, 390, 150, 30));
        lblNaoTenhoCadastro.setForeground(Color.GRAY);
        lblNaoTenhoCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblNaoTenhoCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cUsuario.setText("");
                cSenha.setText("");
                lNaoEncontrado.setVisible(false);
                lCamposVazios.setVisible(false);
                LF.switchToRegisterPanel();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNaoTenhoCadastro.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblNaoTenhoCadastro.setForeground(Color.GRAY);
            }
        });
        add(lblNaoTenhoCadastro);
    }

    private void toggleSenhaVisivel() {
        senhaVisivel = !senhaVisivel;
        cSenha.setEchoChar(senhaVisivel ? (char) 0 : '•');
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgFundo, TelaUtils.scaleX(390), 0, TelaUtils.scaleX(890), TelaUtils.scaleY(768), this);
        g.drawImage(logo, TelaUtils.scaleX(140), 0, TelaUtils.scaleX(100), TelaUtils.scaleY(100), this);
    }

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
