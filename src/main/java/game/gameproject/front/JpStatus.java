package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.MenuBarService;
import game.gameproject.services.StatusService;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpStatus extends JPanel {

    private GameFrame gameFrame;  // Alterado para GameFrame
    private infoPlayerDto playerInfo; // Referência ao DTO do jogador

    public JLabel lJogador = new JLabel("Nick: ");
    public JLabel lNivel = new JLabel("");
    public JLabel lPontos = new JLabel("");
    public JLabel lVida = new JLabel("");
    public JLabel lStamina = new JLabel("");
    public JLabel lForca = new JLabel("");
    public JLabel lDinheiro = new JLabel("");
    
    public JButton btnAddVida;
    public JButton btnAddStamina;
    public JButton btnAddForca;
    
    public StatusService playerService = new StatusService();

    public JpStatus(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;  // Agora gameFrame não será mais null
        this.playerInfo = playerInfo;  // Atribui o playerInfo passado no construtor
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lTituloTela = new JLabel("Tela Status");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(10, 100, 700, 30);
        lTituloTela.setVisible(true);
        add(lTituloTela);

        MenuBarService.addMenu(this, gameFrame);

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

        lDinheiro = new JLabel("Dinheiro: " + playerInfo.getDinheiro());
        lDinheiro.setFont(new Font("Arial", Font.BOLD, 14));
        lDinheiro.setForeground(Color.BLACK);
        lDinheiro.setBounds(10, 240 + 20, 700, 30);
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
    }

    public void atualizarLabelsEAtualizar() {
        lNivel.setText("Nivel: " + playerInfo.getNivel());
        lVida.setText("CON: " + playerInfo.getVida());
        lPontos.setText("Pontos disponíveis: " + playerInfo.getPontos());
        lStamina.setText("DEX: " + playerInfo.getStamina());
        lForca.setText("STR: " + playerInfo.getForca());
        lDinheiro.setText("Dinheiro: " + playerInfo.getDinheiro());

        // Verifica se o valor de pontos chegou a zero, e desabilita o botão ou o torna invisível
        if (playerInfo.getPontos() <= 0) {
            btnAddVida.setVisible(false); // Desabilita o botão
            btnAddStamina.setVisible(false); // Desabilita o botão
            btnAddForca.setVisible(false); // Desabilita o botão
        }
        revalidate();
        repaint();
    }
    
    public void aumentarVida() {
        playerInfo.setPontos(playerInfo.getPontos() - 1);
        playerInfo.setVida(playerInfo.getVida() + 1);
        playerInfo.setNivel(playerInfo.getNivel() + 1);

        playerService.atualizarStatusBanco(playerInfo.getIdPlayer(), playerInfo.getNivel(), playerInfo.getPontos(), playerInfo.getVida(), playerInfo.getStamina(), playerInfo.getForca(), playerInfo.getDinheiro());
    }

    public void aumentarStamina() {
        playerInfo.setPontos(playerInfo.getPontos() - 1);
        playerInfo.setStamina(playerInfo.getStamina() + 1);
        playerInfo.setNivel(playerInfo.getNivel() + 1);

        playerService.atualizarStatusBanco(playerInfo.getIdPlayer(), playerInfo.getNivel(), playerInfo.getPontos(), playerInfo.getVida(), playerInfo.getStamina(), playerInfo.getForca(), playerInfo.getDinheiro());
    }

    public void aumentarForca() {
        playerInfo.setPontos(playerInfo.getPontos() - 1);
        playerInfo.setForca(playerInfo.getForca() + 1);
        playerInfo.setNivel(playerInfo.getNivel() + 1);

        playerService.atualizarStatusBanco(playerInfo.getIdPlayer(), playerInfo.getNivel(), playerInfo.getPontos(), playerInfo.getVida(), playerInfo.getStamina(), playerInfo.getForca(), playerInfo.getDinheiro());
    }
}