package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.MenuBarService;
import game.gameproject.services.StatusService;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class JpStatus extends JPanel {

    private GameFrame gameFrame;  // Alterado para GameFrame
    private infoPlayerDto playerInfo; // Referência ao DTO do jogador

    public JLabel lJogador = new JLabel("Nick: ");
    public JLabel lNivel = new JLabel("");
    public JLabel lClasse = new JLabel("");
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
    
    public int xVida = 0;
    public int xStamina = 0;
    public int xForca = 0;
    public int xMana = 0;
    public int xForcaMana = 0;
    
    public int xDinheiro = 0;

    public StatusService playerService = new StatusService();

    public JpStatus(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;  // Agora gameFrame não será mais null
        this.playerInfo = playerInfo;  // Atribui o playerInfo passado no construtor
        setLayout(null);
        setBackground(Color.WHITE);
        
        JLabel lTituloTela = new JLabel("Status");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(53, 90, 700, 30);  // Coloquei a posição mais alta para que o título fique acima
        lTituloTela.setVisible(true);
        add(lTituloTela);

        // Criar a imagem
        ImageIcon logoIcon = new ImageIcon("imagens/Menu/PlacaTelas.png");
        Image img = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);  // Ajuste de tamanho da imagem
        logoIcon = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 50, 200, 100);  // Coloquei a imagem abaixo do título (a partir de y = 50)
        add(logoLabel);

        lJogador = new JLabel(playerInfo.getNickPlayer(), SwingConstants.CENTER);
        lJogador.setFont(new Font("Arial", Font.BOLD, 16));
        lJogador.setForeground(Color.BLACK);

        FontMetrics fm = lJogador.getFontMetrics(lJogador.getFont());
        int textWidth = fm.stringWidth(playerInfo.getNickPlayer());
        lJogador.setPreferredSize(new Dimension(textWidth, 30));
        int areaLargura = 10+getWidth();
        int x = 626 + (areaLargura - textWidth) / 2;
        lJogador.setBounds(x-50, 202 + 50, textWidth+100, 30);
        lJogador.setVisible(true);
        add(lJogador);
        
        lNivel = new JLabel(""+playerInfo.getNivel());
        lNivel.setFont(new Font("Arial", Font.BOLD, 16));
        lNivel.setForeground(Color.BLACK);
        
        FontMetrics fmNivel = lNivel.getFontMetrics(lNivel.getFont());
        int textWidthNivel = fmNivel.stringWidth(""+playerInfo.getNivel());
        lNivel.setPreferredSize(new Dimension(textWidthNivel, 30));
        int areaLarguraNivel = 10+getWidth();
        int xNivel = 626 + (areaLarguraNivel - textWidthNivel) / 2;
        lNivel.setBounds(xNivel, 220 + 50, textWidthNivel+100, 30);
        lNivel.setVisible(true);
        add(lNivel);
        
        lClasse = new JLabel("Classe: " + playerInfo.getNickPlayer());
        lClasse.setFont(new Font("Arial", Font.BOLD, 16));
        lClasse.setForeground(Color.BLACK);
        FontMetrics fmClasse = lClasse.getFontMetrics(lClasse.getFont());
        int textWidthClasse = fmClasse.stringWidth("Classe: " + playerInfo.getPontos());
        lClasse.setPreferredSize(new Dimension(textWidthClasse, 30));
        int areaLarguraClasse = 10+getWidth();
        int xClasse = 626 + (areaLarguraClasse - textWidthClasse) / 2;
        lClasse.setBounds(xClasse-50, 260 + 50, textWidthClasse+100, 30);
        lClasse.setVisible(true);
        add(lClasse);

        lPontos = new JLabel("Pontos disponiveis: " + playerInfo.getPontos());
        lPontos.setFont(new Font("Arial", Font.BOLD, 16));
        lPontos.setForeground(Color.BLACK);
        FontMetrics fmPontos = lPontos.getFontMetrics(lPontos.getFont());
        int textWidthPontos = fmPontos.stringWidth("Pontos disponiveis: " + playerInfo.getPontos());
        lPontos.setPreferredSize(new Dimension(textWidthPontos, 30));
        int areaLarguraPontos = 10+getWidth();
        int xPontos = 626 + (areaLarguraPontos - textWidthPontos) / 2;
        lPontos.setBounds(xPontos, 280 + 50, textWidthPontos+100, 30);
        lPontos.setVisible(true);
        add(lPontos);

        lVida = new JLabel("CON: " + playerInfo.getVida());
        lVida.setFont(new Font("Arial", Font.BOLD, 16));
        lVida.setForeground(Color.BLACK);
        FontMetrics fmVida = lVida.getFontMetrics(lVida.getFont());
        int textWidthVida = fmVida.stringWidth("CON: " + playerInfo.getPontos());
        lVida.setPreferredSize(new Dimension(textWidthVida, 30));
        int areaLarguraVida = 10+getWidth();
        xVida = 626 + (areaLarguraVida - textWidthVida) / 2;
        lVida.setBounds(xVida-50, 315 + 50, textWidthVida+100, 30);
        lVida.setVisible(true);
        add(lVida);

        lStamina = new JLabel("DEX: " + playerInfo.getStamina());
        lStamina.setFont(new Font("Arial", Font.BOLD, 16));
        lStamina.setForeground(Color.BLACK);
        FontMetrics fmStamina = lStamina.getFontMetrics(lStamina.getFont());
        int textWidthStamina = fmStamina.stringWidth("DEX: " + playerInfo.getStamina());
        lStamina.setPreferredSize(new Dimension(textWidthStamina, 30));
        int areaLarguraStamina = 10+getWidth();
        xStamina = 626 + (areaLarguraStamina - textWidthStamina) / 2;
        lStamina.setBounds(xStamina-50, 330 + 50, textWidthStamina+100, 30);
        lStamina.setVisible(true);
        add(lStamina);

        lForca = new JLabel("STR: " + playerInfo.getForca());
        lForca.setFont(new Font("Arial", Font.BOLD, 16));
        lForca.setForeground(Color.BLACK);
        FontMetrics fmForca = lForca.getFontMetrics(lForca.getFont());
        int textWidthForca = fmForca.stringWidth("STR: " + playerInfo.getForca());
        lForca.setPreferredSize(new Dimension(textWidthForca, 30));
        int areaLarguraForca = 10+getWidth();
        xForca = 626 + (areaLarguraForca - textWidthForca) / 2;
        lForca.setBounds(xForca-50, 325 + 20 + 50, textWidthForca+100, 30);
        lForca.setVisible(true);
        add(lForca);
        
        lMana = new JLabel("SPI: " + playerInfo.getMana());
        lMana.setFont(new Font("Arial", Font.BOLD, 16));
        lMana.setForeground(Color.BLACK);
        FontMetrics fmMana = lMana.getFontMetrics(lMana.getFont());
        int textWidthMana = fmMana.stringWidth("SPI: " + playerInfo.getMana());
        lMana.setPreferredSize(new Dimension(textWidthMana, 30));
        int areaLarguraMana = 10+getWidth();
        xMana = 626 + (areaLarguraMana - textWidthMana) / 2;
        lMana.setBounds(xMana-50, 240 + 20 + 100 + 50, textWidthMana+100, 30);
        lMana.setVisible(true);
        add(lMana);
        
        lForcaMana = new JLabel("WIL: " + playerInfo.getForcaMana());
        lForcaMana.setFont(new Font("Arial", Font.BOLD, 16));
        lForcaMana.setForeground(Color.BLACK);
        FontMetrics fmForcaMana = lForcaMana.getFontMetrics(lForcaMana.getFont());
        int textWidthForcaMana = fmForcaMana.stringWidth("WIL: " + playerInfo.getForcaMana());
        lForcaMana.setPreferredSize(new Dimension(textWidthForcaMana, 30));
        int areaLarguraForcaMana = 10+getWidth();
        xForcaMana = 626 + (areaLarguraForcaMana - textWidthForcaMana) / 2;
        lForcaMana.setBounds(xMana-50, 250 + 25 + 100 + 50, textWidthForcaMana+100, 30);
        lForcaMana.setVisible(true);
        add(lForcaMana);

        lDinheiro = new JLabel("Dinheiro: " + playerInfo.getDinheiro());
        lDinheiro.setFont(new Font("Arial", Font.BOLD, 16));
        lDinheiro.setForeground(Color.BLACK);
        FontMetrics fmDinheiro = lDinheiro.getFontMetrics(lDinheiro.getFont());
        int textWidthDinheiro = fmDinheiro.stringWidth("Dinheiro: " + playerInfo.getDinheiro());
        lDinheiro.setPreferredSize(new Dimension(textWidthDinheiro, 30));
        int areaLarguraDinheiro = 10+getWidth();
        xDinheiro = 626 + (areaLarguraDinheiro - textWidthDinheiro) / 2;
        lDinheiro.setBounds(xMana-50, 250 + 25 + 130 + 50, textWidthDinheiro+100, 30);
        lDinheiro.setVisible(true);
        add(lDinheiro);

        btnAddVida = new JButton("");
        btnAddVida.setBackground(Color.YELLOW);
        btnAddVida.setBounds(xVida + 50, 210 + 15 + 100 + 50, 10, 10);
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
        btnAddStamina.setBounds(xVida + 50, 210 + 30 + 100 + 50, 10, 10);
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
        btnAddForca.setBounds(xVida + 50, 210 + 45 + 100 + 50, 10, 10);
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
        btnAddMana.setBounds(xVida + 50, 210 + 60 + 100 + 50, 10, 10);
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
        btnAddForcaMana.setBounds(xVida + 50, 210 + 75 + 100 + 50, 10, 10);
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
        
        ImageIcon logoIconS = new ImageIcon("imagens/Menu/status/status.png");
        Image imgS = logoIconS.getImage().getScaledInstance(275+150, 410+150, Image.SCALE_SMOOTH);  // Ajuste de tamanho da imagem
        logoIconS = new ImageIcon(imgS);

        JLabel logoLabelS = new JLabel(logoIconS);
        logoLabelS.setBounds(420, 70 + 50, 275+150, 410+150);  // Coloquei a imagem abaixo do título (a partir de y = 50)
        add(logoLabelS);
        
        JLabel logoLabelE = new JLabel(logoIconS);
        logoLabelE.setBounds(420+410, 70 + 50, 275+150, 410+150);  // Coloquei a imagem abaixo do título (a partir de y = 50)
        add(logoLabelE);
        
        JLabel logoLabelD = new JLabel(logoIconS);
        logoLabelD.setBounds(10, 70 + 50, 275+150, 410+150);  // Coloquei a imagem abaixo do título (a partir de y = 50)
        add(logoLabelD);
        
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
        lNivel.setText(""+playerInfo.getNivel());
        lClasse.setText("Classe: "+playerInfo.getNickPlayer());
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
