package game.gameproject.front.Autenticacao;

import game.gameproject.controller.GameFrame;
import game.gameproject.controller.LauncherFrame;
import game.gameproject.dto.VersoesDto;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.AutenticacaoService;
import game.gameproject.services.LoginService;
import game.gameproject.services.RegistrarService;
import game.gameproject.support.TimerAvisosLabelsSupport;

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
    
    public JLabel lNaoEncontrado = new JLabel("Usuário ou Senha estão errado!");
    public JLabel lCamposVazios = new JLabel("Não é possivel se logar com algum campo vazio!");
    
    TimerAvisosLabelsSupport LabelSupportT = new TimerAvisosLabelsSupport();

    public JpLogin(LauncherFrame launcherFrame) {
        this.LF = launcherFrame; // Agora LF não será mais null
        setLayout(null);
        setBackground(Color.WHITE);

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
        JLabel lLogin = new JLabel("LOGIN");
        lLogin.setFont(new Font("Arial", Font.BOLD, 18));
        lLogin.setBounds(160, 100, 350, 40);
        lLogin.setForeground(Color.BLACK);
        add(lLogin);

        // Label Usuário
        JLabel lUsuario = new JLabel("Usuário");
        lUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        lUsuario.setBounds(30, 140, 350, 40);
        lUsuario.setForeground(Color.BLACK);
        add(lUsuario);

        // Campo Usuário
        JTextField cUsuario = new JTextField();
        cUsuario.setBounds(30, 170, 320, 40);
        cUsuario.setBackground(Color.LIGHT_GRAY);
        cUsuario.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        cUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        add(cUsuario);

        // Label Senha
        JLabel lSenha = new JLabel("Senha");
        lSenha.setFont(new Font("Arial", Font.BOLD, 14));
        lSenha.setBounds(30, 220, 350, 40);
        lSenha.setForeground(Color.BLACK);
        add(lSenha);
        
        lNaoEncontrado.setFont(new Font("Arial", Font.BOLD, 14));
        lNaoEncontrado.setBounds(80, 420, 350, 40);
        lNaoEncontrado.setForeground(Color.RED);
        lNaoEncontrado.setVisible(false);
        add(lNaoEncontrado);
        
        lCamposVazios.setFont(new Font("Arial", Font.BOLD, 14));
        lCamposVazios.setBounds(20, 420, 350, 40);
        lCamposVazios.setForeground(Color.RED);
        lCamposVazios.setVisible(false);
        add(lCamposVazios);

        // Campo Senha
        cSenha = new JPasswordField();
        cSenha.setBounds(30, 250, 280, 40);
        cSenha.setBackground(Color.LIGHT_GRAY);
        cSenha.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        cSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        add(cSenha);

        // Botão para mostrar/esconder senha
        JButton btnVerSenha = new JButton("👁");
        btnVerSenha.setBounds(315, 250, 35, 40);
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

        // Botão Entrar com bordas arredondadas
        JButton btnEntrar = new BotaoArredondado("Entrar", corLaranja);
        btnEntrar.setBounds(140, 330, 100, 50);
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                lNaoEncontrado.setVisible(false);
                lCamposVazios.setVisible(false);
                
                if(cUsuario.getText().isEmpty() || cSenha.getText().isEmpty()) {
            		LabelSupportT.exibirAvisoTemporario(lCamposVazios);
            	} else {
            		// Pegando os dados de login (usuário e senha) do formulário
                    String usuario = cUsuario.getText(); // Supondo que txtUsuario seja o campo do usuário
                    String senha = new String(cSenha.getPassword()); // Supondo que txtSenha seja o campo da senha

                    // Criando uma instância do LoginService para verificar o login
                    LoginService loginService = new LoginService();
                    int resultado = loginService.login(usuario, senha); // Tentando logar com o usuário e senha

                    // Verificando o resultado do login
                    if (resultado == 0) {
                        // Login bem-sucedido, inicia o jogo
                        AutenticacaoService autenticacaoService = new AutenticacaoService();
                        infoPlayerDto playerInfo = autenticacaoService.autenticarUsuario();
                        
                        GameFrame gameFrame = new GameFrame();
                        launcherFrame.dispose();
                    } else {      			
                        LabelSupportT.exibirAvisoTemporario(lNaoEncontrado);
                        cSenha.setText("");
                    }
            	}    
            }
        });

        add(btnEntrar);

        // Label "Não tenho cadastro"
        JLabel lblNaoTenhoCadastro = new JLabel("Não tenho cadastro");
        lblNaoTenhoCadastro.setFont(new Font("Arial", Font.BOLD, 14));
        lblNaoTenhoCadastro.setBounds(120, 390, 150, 30);
        lblNaoTenhoCadastro.setForeground(Color.GRAY);
        lblNaoTenhoCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));

// Adicionando evento de clique
        lblNaoTenhoCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cUsuario.setText("");
                cSenha.setText("");
                
                lNaoEncontrado.setVisible(false);
                lCamposVazios.setVisible(false);
                
                LF.switchToRegisterPanel(); // Chama a troca de tela
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNaoTenhoCadastro.setForeground(Color.BLUE); // Muda a cor ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblNaoTenhoCadastro.setForeground(Color.GRAY); // Volta à cor original ao tirar o mouse
            }
        });

// Adiciona o JLabel ao painel
        add(lblNaoTenhoCadastro);

    }

    // Alterna a visibilidade da senha
    private void toggleSenhaVisivel() {
        senhaVisivel = !senhaVisivel;
        cSenha.setEchoChar(senhaVisivel ? (char) 0 : '•');
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
