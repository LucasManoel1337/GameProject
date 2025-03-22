package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.MenuBarService;
import game.gameproject.services.StatusService;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class JpStatus extends JPanel {

    private GameFrame gameFrame;  // Alterado para GameFrame
    private infoPlayerDto playerInfo; // Referência ao DTO do jogador

    public JLabel lJogador = new JLabel("Nick: ");
    public JLabel lNivel = new JLabel("");
    public JLabel lPontos = new JLabel("");
    public JLabel lVida = new JLabel("");
    public JLabel lStamina = new JLabel("");
    public JLabel lForca = new JLabel("");
    public JLabel lMana = new JLabel("");
    public JLabel lForcaMana = new JLabel("");
    public JLabel lDinheiro = new JLabel("");

    public JButton btnAddVida;
    public JButton btnAddStamina;
    public JButton btnAddForca;
    public JButton btnAddMana;
    public JButton btnAddForcaMana;

    public StatusService playerService = new StatusService();

    public JpStatus(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;  // Agora gameFrame não será mais null
        this.playerInfo = playerInfo;  // Atribui o playerInfo passado no construtor
        setLayout(null);
        setBackground(Color.WHITE);

        // Criar o título
        JLabel lTituloTela = new JLabel("Status");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(55, 90, 700, 30);  // Coloquei a posição mais alta para que o título fique acima
        lTituloTela.setVisible(true);
        add(lTituloTela);

        // Criar a imagem
        ImageIcon logoIcon = new ImageIcon("imagens/Menu/PlacaTelas.png");
        Image img = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);  // Ajuste de tamanho da imagem
        logoIcon = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 50, 200, 100);  // Coloquei a imagem abaixo do título (a partir de y = 50)
        add(logoLabel);

        lJogador = new JLabel("Nick: " + playerInfo.getNickPlayer());
        lJogador.setFont(new Font("Arial", Font.BOLD, 16));
        lJogador.setForeground(Color.BLACK);
        lJogador.setBounds(10, 150, 700, 30);
        lJogador.setVisible(true);
        add(lJogador);

        lNivel = new JLabel("Nivel: " + playerInfo.getNivel());
        lNivel.setFont(new Font("Arial", Font.BOLD, 14));
        lNivel.setForeground(Color.BLACK);
        lNivel.setBounds(10, 170, 700, 30);
        lNivel.setVisible(true);
        add(lNivel);

        lPontos = new JLabel("Pontos disponiveis: " + playerInfo.getPontos());
        lPontos.setFont(new Font("Arial", Font.BOLD, 14));
        lPontos.setForeground(Color.BLACK);
        lPontos.setBounds(10, 190, 700, 30);
        lPontos.setVisible(true);
        add(lPontos);

        lVida = new JLabel("CON: " + playerInfo.getVida());
        lVida.setFont(new Font("Arial", Font.BOLD, 14));
        lVida.setForeground(Color.BLACK);
        lVida.setBounds(10, 210 + 5, 700, 30);
        lVida.setVisible(true);
        add(lVida);

        lStamina = new JLabel("DEX: " + playerInfo.getStamina());
        lStamina.setFont(new Font("Arial", Font.BOLD, 14));
        lStamina.setForeground(Color.BLACK);
        lStamina.setBounds(10, 220 + 10, 700, 30);
        lStamina.setVisible(true);
        add(lStamina);

        lForca = new JLabel("STR: " + playerInfo.getForca());
        lForca.setFont(new Font("Arial", Font.BOLD, 14));
        lForca.setForeground(Color.BLACK);
        lForca.setBounds(10, 230 + 15, 700, 30);
        lForca.setVisible(true);
        add(lForca);
        
        lMana = new JLabel("SPI: " + playerInfo.getMana());
        lMana.setFont(new Font("Arial", Font.BOLD, 14));
        lMana.setForeground(Color.BLACK);
        lMana.setBounds(10, 240 + 20, 700, 30);
        lMana.setVisible(true);
        add(lMana);
        
        lForcaMana = new JLabel("WIL: " + playerInfo.getForcaMana());
        lForcaMana.setFont(new Font("Arial", Font.BOLD, 14));
        lForcaMana.setForeground(Color.BLACK);
        lForcaMana.setBounds(10, 250 + 25, 700, 30);
        lForcaMana.setVisible(true);
        add(lForcaMana);

        lDinheiro = new JLabel("Dinheiro: " + playerInfo.getDinheiro());
        lDinheiro.setFont(new Font("Arial", Font.BOLD, 14));
        lDinheiro.setForeground(Color.BLACK);
        lDinheiro.setBounds(10, 260 + 30, 700, 30);
        lDinheiro.setVisible(true);
        add(lDinheiro);

        btnAddVida = new JButton("");
        btnAddVida.setBackground(Color.YELLOW);
        btnAddVida.setBounds(100, 210 + 15, 10, 10);
        btnAddVida.setBorderPainted(false);
        btnAddVida.setVisible(playerInfo.getPontos() > 0);
        btnAddVida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarVida();
                atualizarLabelsEAtualizar();
            }
        });
        add(btnAddVida);

        btnAddStamina = new JButton("");
        btnAddStamina.setBackground(Color.YELLOW);
        btnAddStamina.setBounds(100, 210 + 30, 10, 10);
        btnAddStamina.setBorderPainted(false);
        btnAddStamina.setVisible(playerInfo.getPontos() > 0);
        btnAddStamina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarStamina();
                atualizarLabelsEAtualizar();
            }
        });
        add(btnAddStamina);

        btnAddForca = new JButton("");
        btnAddForca.setBackground(Color.YELLOW);
        btnAddForca.setBounds(100, 210 + 45, 10, 10);
        btnAddForca.setBorderPainted(false);
        btnAddForca.setVisible(playerInfo.getPontos() > 0);
        btnAddForca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarForca();
                atualizarLabelsEAtualizar();
            }
        });
        add(btnAddForca);
        
        btnAddMana = new JButton("");
        btnAddMana.setBackground(Color.YELLOW);
        btnAddMana.setBounds(100, 210 + 60, 10, 10);
        btnAddMana.setBorderPainted(false);
        btnAddMana.setVisible(playerInfo.getPontos() > 0);
        btnAddMana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarMana();
                atualizarLabelsEAtualizar();
            }
        });
        add(btnAddMana);
        
        btnAddForcaMana = new JButton("");
        btnAddForcaMana.setBackground(Color.YELLOW);
        btnAddForcaMana.setBounds(100, 210 + 75, 10, 10);
        btnAddForcaMana.setBorderPainted(false);
        btnAddForcaMana.setVisible(playerInfo.getPontos() > 0);
        btnAddForcaMana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarForcaMana();
                atualizarLabelsEAtualizar();
            }
        });
        add(btnAddForcaMana);
        
        MenuBarService.addMenu(this, gameFrame, playerInfo);
        bindEscapeKey();
    }
    
    private void bindEscapeKey() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "exitAction");
        getActionMap().put("exitAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ESC pressionado!");
                
                gameFrame.switchToGamePanel();
            }
        });
}

    public void atualizarLabelsEAtualizar() {
        lNivel.setText("Nivel: " + playerInfo.getNivel());
        lVida.setText("CON: " + playerInfo.getVida());
        lPontos.setText("Pontos disponíveis: " + playerInfo.getPontos());
        lStamina.setText("DEX: " + playerInfo.getStamina());
        lForca.setText("STR: " + playerInfo.getForca());
        lMana.setText("SPI: " + playerInfo.getMana());
        lForcaMana.setText("WIL: " + playerInfo.getForcaMana());
        lDinheiro.setText("Dinheiro: " + playerInfo.getDinheiro());

        // Verifica se o valor de pontos chegou a zero, e desabilita o botão ou o torna invisível
        if (playerInfo.getPontos() <= 0) {
            btnAddVida.setVisible(false); // Desabilita o botão
            btnAddStamina.setVisible(false); // Desabilita o botão
            btnAddForca.setVisible(false); // Desabilita o botão
            btnAddMana.setVisible(false); // Desabilita o botão
            btnAddForcaMana.setVisible(false); // Desabilita o botão
        }
        revalidate();
        repaint();
    }

    public void aumentarVida() {
        playerInfo.setPontos(playerInfo.getPontos() - 1);
        playerInfo.setVida(playerInfo.getVida() + 1);
        playerInfo.setNivel(playerInfo.getNivel() + 1);

        playerService.atualizarStatusBanco(playerInfo.getIdPlayer(), playerInfo.getNivel(), playerInfo.getPontos(), playerInfo.getVida(), playerInfo.getStamina(), playerInfo.getForca(), playerInfo.getMana(), playerInfo.getForcaMana(), playerInfo.getDinheiro());
    }

    public void aumentarStamina() {
        playerInfo.setPontos(playerInfo.getPontos() - 1);
        playerInfo.setStamina(playerInfo.getStamina() + 1);
        playerInfo.setNivel(playerInfo.getNivel() + 1);

        playerService.atualizarStatusBanco(playerInfo.getIdPlayer(), playerInfo.getNivel(), playerInfo.getPontos(), playerInfo.getVida(), playerInfo.getStamina(), playerInfo.getForca(), playerInfo.getMana(), playerInfo.getForcaMana(), playerInfo.getDinheiro());
    }

    public void aumentarForca() {
        playerInfo.setPontos(playerInfo.getPontos() - 1);
        playerInfo.setForca(playerInfo.getForca() + 1);
        playerInfo.setNivel(playerInfo.getNivel() + 1);

        playerService.atualizarStatusBanco(playerInfo.getIdPlayer(), playerInfo.getNivel(), playerInfo.getPontos(), playerInfo.getVida(), playerInfo.getStamina(), playerInfo.getForca(), playerInfo.getMana(), playerInfo.getForcaMana(), playerInfo.getDinheiro());
    }
    
    public void aumentarMana() {
        playerInfo.setPontos(playerInfo.getPontos() - 1);
        playerInfo.setMana(playerInfo.getMana() + 1);
        playerInfo.setNivel(playerInfo.getNivel() + 1);

        playerService.atualizarStatusBanco(playerInfo.getIdPlayer(), playerInfo.getNivel(), playerInfo.getPontos(), playerInfo.getVida(), playerInfo.getStamina(), playerInfo.getForca(), playerInfo.getMana(), playerInfo.getForcaMana(), playerInfo.getDinheiro());
    }
    
    public void aumentarForcaMana() {
        playerInfo.setPontos(playerInfo.getPontos() - 1);
        playerInfo.setForcaMana(playerInfo.getForcaMana() + 1);
        playerInfo.setNivel(playerInfo.getNivel() + 1);

        playerService.atualizarStatusBanco(playerInfo.getIdPlayer(), playerInfo.getNivel(), playerInfo.getPontos(), playerInfo.getVida(), playerInfo.getStamina(), playerInfo.getForca(), playerInfo.getMana(), playerInfo.getForcaMana(), playerInfo.getDinheiro());
    }
}
