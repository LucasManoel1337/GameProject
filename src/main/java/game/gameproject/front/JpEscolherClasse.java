package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import javax.swing.*;
import java.awt.*;

public class JpEscolherClasse extends JPanel {

    private GameFrame gameFrame;
    private infoPlayerDto playerInfo;

    public JpEscolherClasse(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;
        this.playerInfo = playerInfo;
        setLayout(null);
        setBackground(Color.WHITE);
        
        addLabel("Classe", 55, 90, 30, Color.BLACK);
        addLabel("Escolha uma classe para jogar!", 420, 10, 30, Color.YELLOW);
        addLabel("Guerreiros", 566, 80, 26, Color.YELLOW);
        addLabel("Magos", 590, 400, 26, Color.YELLOW);
        
        // Guerreiros
        addLabel("Paladino", 175, 165, 14, Color.BLACK);
        addLabel("Berserker", 385, 165, 14, Color.BLACK);
        addLabel("Tank", 616, 165, 14, Color.BLACK);
        addLabel("Gladiador", 816, 165, 14, Color.BLACK);
        addLabel("Cavaleiro Sombrio", 1014, 165, 12, Color.BLACK);
        
        // Magos
        addLabel("Elementalista", 158, 484, 14, Color.BLACK);
        addLabel("Feiticeiro", 385, 484, 14, Color.BLACK);
        addLabel("Necromante", 592, 484, 14, Color.BLACK);
        addLabel("Batalhamago", 803, 484, 14, Color.BLACK);
        addLabel("Arcanista", 1034, 484, 14, Color.BLACK);
        
        //Paladino
        addLabel("Buff: +STR, +WIL", 150, 290, 12, Color.BLACK);
        addLabel("Debuff: -DEX, -SPI", 150, 300, 12, Color.BLACK);
        
        //Berserker
        addLabel("Buff: +STR, +CON", 365, 290, 12, Color.BLACK);
        addLabel("Debuff: -DEX, -SPI", 365, 300, 12, Color.BLACK);
        
        //Tank
        addLabel("Buff: +DEX, +CON", 580, 290, 12, Color.BLACK);
        addLabel("Debuff: -STR, -WIL", 580, 300, 12, Color.BLACK);
        
        //Gladiador
        addLabel("Buff: +STR, +DEX", 795, 290, 12, Color.BLACK);
        addLabel("Debuff: -WIL, -SPI", 795, 300, 12, Color.BLACK);
        
        //Cavaleiro sombrio
        addLabel("Buff: +STR, +WIL, +SPI", 1010, 290, 12, Color.BLACK);
        addLabel("Debuff: -DEX, -CON", 1010, 300, 12, Color.BLACK);
        
        
        
        //Elementalista
        addLabel("Buff: +WIL, +SPI", 150, 610, 12, Color.BLACK);
        addLabel("Debuff: -DEX, -CON", 150, 620, 12, Color.BLACK);
        
        //Feiticeiro
        addLabel("Buff: +WIL, +DEX", 365, 610, 12, Color.BLACK);
        addLabel("Debuff: -STR, -CON", 365, 620, 12, Color.BLACK);
        
        //Necromante
        addLabel("Buff: +WIL, +CON", 580, 610, 12, Color.BLACK);
        addLabel("Debuff: -DEX, -SPI", 580, 620, 12, Color.BLACK);
        
        //Batalhamago
        addLabel("Buff: +STR, +WIL", 795, 610, 12, Color.BLACK);
        addLabel("Debuff: -DEX, -SPI", 795, 620, 12, Color.BLACK);
        
        //Arcanista
        addLabel("Buff: +WIL, +SPI", 1010, 610, 12, Color.BLACK);
        addLabel("Debuff: -STR, -CON", 1010, 620, 12, Color.BLACK);
        
        
        
        JButton EscolherG1 = new JButton("Escolher");
        EscolherG1.setVisible(true);
        EscolherG1.setFocusable(false);
        EscolherG1.setBounds(155, 328, 100, 20);
        add(EscolherG1);
        EscolherG1.addActionListener(e -> {
            playerInfo.setClasse("Paladino");
            System.out.println(playerInfo.getClasse());
            //gameFrame.switchToMenuPanel();  
        });
        
        JButton EscolherG2 = new JButton("Escolher");
        EscolherG2.setVisible(true);
        EscolherG2.setFocusable(false);
        EscolherG2.setBounds(370, 328, 100, 20);
        add(EscolherG2);
        EscolherG2.addActionListener(e -> {
            playerInfo.setClasse("Beserker");
            System.out.println(playerInfo.getClasse());
            //gameFrame.switchToMenuPanel();  
        });
        
        JButton EscolherG3 = new JButton("Escolher");
        EscolherG3.setVisible(true);
        EscolherG3.setFocusable(false);
        EscolherG3.setBounds(585, 328, 100, 20);
        add(EscolherG3);
        EscolherG3.addActionListener(e -> {
            playerInfo.setClasse("Tank");
            System.out.println(playerInfo.getClasse());
            //gameFrame.switchToMenuPanel();  
        });
        
        JButton EscolherG4 = new JButton("Escolher");
        EscolherG4.setVisible(true);
        EscolherG4.setFocusable(false);
        EscolherG4.setBounds(800, 328, 100, 20);
        add(EscolherG4);
        EscolherG4.addActionListener(e -> {
            playerInfo.setClasse("Gladiador");
            System.out.println(playerInfo.getClasse());
            //gameFrame.switchToMenuPanel();  
        });
        
        JButton EscolherG5 = new JButton("Escolher");
        EscolherG5.setVisible(true);
        EscolherG5.setFocusable(false);
        EscolherG5.setBounds(1015, 328, 100, 20);
        add(EscolherG5);
        EscolherG5.addActionListener(e -> {
            playerInfo.setClasse("Cavaleiro Sombrio");
            System.out.println(playerInfo.getClasse());
            //gameFrame.switchToMenuPanel();  
        });
        
        
        
        JButton EscolherM1 = new JButton("Escolher");
        EscolherM1.setVisible(true);
        EscolherM1.setFocusable(false);
        EscolherM1.setBounds(155, 648, 100, 20);
        add(EscolherM1);
        EscolherM1.addActionListener(e -> {
            playerInfo.setClasse("Elementalista");
            System.out.println(playerInfo.getClasse());
            //gameFrame.switchToMenuPanel();  
        });
        
        JButton EscolherM2 = new JButton("Escolher");
        EscolherM2.setVisible(true);
        EscolherM2.setFocusable(false);
        EscolherM2.setBounds(370, 648, 100, 20);
        add(EscolherM2);
        EscolherM2.addActionListener(e -> {
            playerInfo.setClasse("Feiticeiro");
            System.out.println(playerInfo.getClasse());
            //gameFrame.switchToMenuPanel();  
        });
        
        JButton EscolherM3 = new JButton("Escolher");
        EscolherM3.setVisible(true);
        EscolherM3.setFocusable(false);
        EscolherM3.setBounds(585, 648, 100, 20);
        add(EscolherM3);
        EscolherM3.addActionListener(e -> {
            playerInfo.setClasse("Necromante");
            System.out.println(playerInfo.getClasse());
            //gameFrame.switchToMenuPanel();  
        });
        
        JButton EscolherM4 = new JButton("Escolher");
        EscolherM4.setVisible(true);
        EscolherM4.setFocusable(false);
        EscolherM4.setBounds(800, 648, 100, 20);
        add(EscolherM4);
        EscolherM4.addActionListener(e -> {
            playerInfo.setClasse("Batalhamago");
            System.out.println(playerInfo.getClasse());
            //gameFrame.switchToMenuPanel();  
        });
        
        JButton EscolherM5 = new JButton("Escolher");
        EscolherM5.setVisible(true);
        EscolherM5.setFocusable(false);
        EscolherM5.setBounds(1015, 648, 100, 20);
        add(EscolherM5);
        EscolherM5.addActionListener(e -> {
            playerInfo.setClasse("Arcanista");
            System.out.println(playerInfo.getClasse());
            //gameFrame.switchToMenuPanel();  
        });
        
        addImage("imagens/Menu/PlacaTelas.png", 0, 50, 200, 100);
        
        addClassImages("imagens/Menu/classes/fundoclasse.png", new int[][]{
            {535, 130}, {750, 130}, {965, 130}, {320, 130}, {105, 130}, // Guerreiros
            {535, 450}, {750, 450}, {965, 450}, {320, 450}, {105, 450}  // Magos
        }, 400 / 2, 540 / 2);
        
        addImage("imagens/Menu/backgroundmenu.png", 0, 0, 1280, 768);
    }

    private void addLabel(String text, int x, int y, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setForeground(color);
        label.setBounds(x, y, 700, 30);
        add(label);
    }

    private void addImage(String path, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(icon);
        label.setBounds(x, y, width, height);
        add(label);
    }
    
    private void addClassImages(String path, int[][] positions, int width, int height) {
        ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        for (int[] pos : positions) {
            JLabel label = new JLabel(icon);
            label.setBounds(pos[0], pos[1], width, height);
            add(label);
        }
    }
}