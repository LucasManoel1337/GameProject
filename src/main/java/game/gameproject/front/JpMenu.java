package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpMenu extends JPanel {

    private GameFrame gameFrame;  // Alterado para GameFrame
    private infoPlayerDto playerInfo; // Referência ao DTO do jogador

    public JpMenu(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;  // Agora gameFrame não será mais null
        this.playerInfo = playerInfo;  // Atribui o playerInfo passado no construtor
        setLayout(null);
        setBackground(Color.WHITE);
        
        JLabel lBemVindo = new JLabel("Bem vindo jogador "+playerInfo.getNickPlayer());
        lBemVindo.setFont(new Font("Arial", Font.BOLD, 30));
        lBemVindo.setForeground(Color.BLACK);
        lBemVindo.setBounds(10, 100, 700, 30);
        lBemVindo.setVisible(true);
        add(lBemVindo);
        
        JLabel lPainelDeNovidades = new JLabel("Painel de novidades");
        lPainelDeNovidades.setFont(new Font("Arial", Font.BOLD, 16));
        lPainelDeNovidades.setForeground(Color.BLACK);
        lPainelDeNovidades.setBounds(70, 145, 700, 30);
        lPainelDeNovidades.setVisible(true);
        add(lPainelDeNovidades);

        JLabel lBtnStatus = new JLabel("Status");
        lBtnStatus.setFont(new Font("Arial", Font.BOLD, 14));
        lBtnStatus.setBounds(30, 10, 55, 30);
        lBtnStatus.setForeground(Color.GRAY);
        lBtnStatus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lBtnStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Abrir tela de Status...");
                //LF.switchToRegisterPanel(); // Chama a troca de tela
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lBtnStatus.setForeground(Color.BLUE); // Muda a cor ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lBtnStatus.setForeground(Color.GRAY); // Volta à cor original ao tirar o mouse
            }
        });
        add(lBtnStatus);

        JLabel lBtnInventario = new JLabel("Inventario");
        lBtnInventario.setFont(new Font("Arial", Font.BOLD, 14));
        lBtnInventario.setBounds(30 + 70, 10, 80, 30);
        lBtnInventario.setForeground(Color.GRAY);
        lBtnInventario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lBtnInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Abrir tela de Inventario...");
                //LF.switchToRegisterPanel(); // Chama a troca de tela
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lBtnInventario.setForeground(Color.BLUE); // Muda a cor ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lBtnInventario.setForeground(Color.GRAY); // Volta à cor original ao tirar o mouse
            }
        });
        add(lBtnInventario);

        JLabel lBtnMapa = new JLabel("Mapa");
        lBtnMapa.setFont(new Font("Arial", Font.BOLD, 14));
        lBtnMapa.setBounds(30 + 170, 10, 50, 30);
        lBtnMapa.setForeground(Color.GRAY);
        lBtnMapa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lBtnMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Abrir tela de Mapa...");
                //LF.switchToRegisterPanel(); // Chama a troca de tela
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lBtnMapa.setForeground(Color.BLUE); // Muda a cor ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lBtnMapa.setForeground(Color.GRAY); // Volta à cor original ao tirar o mouse
            }
        });
        add(lBtnMapa);

        JLabel lBtnMissões = new JLabel("Missões");
        lBtnMissões.setFont(new Font("Arial", Font.BOLD, 14));
        lBtnMissões.setBounds(30 + 240, 10, 60, 30);
        lBtnMissões.setForeground(Color.GRAY);
        lBtnMissões.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lBtnMissões.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Abrir tela de Missões...");
                //LF.switchToRegisterPanel(); // Chama a troca de tela
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lBtnMissões.setForeground(Color.BLUE); // Muda a cor ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lBtnMissões.setForeground(Color.GRAY); // Volta à cor original ao tirar o mouse
            }
        });
        add(lBtnMissões);

        JLabel lBtnAmigos = new JLabel("Amigos");
        lBtnAmigos.setFont(new Font("Arial", Font.BOLD, 14));
        lBtnAmigos.setBounds(30 + 330, 10, 60, 30);
        lBtnAmigos.setForeground(Color.GRAY);
        lBtnAmigos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lBtnAmigos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Abrir tela de Amigos...");
                //LF.switchToRegisterPanel(); // Chama a troca de tela
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lBtnAmigos.setForeground(Color.BLUE); // Muda a cor ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lBtnAmigos.setForeground(Color.GRAY); // Volta à cor original ao tirar o mouse
            }
        });
        add(lBtnAmigos);

        JLabel lBtnJogar = new JLabel("JOGAR");
        lBtnJogar.setFont(new Font("Arial", Font.BOLD, 18));
        lBtnJogar.setBounds(30 + 430, 15, 70, 30);
        lBtnJogar.setForeground(Color.RED);
        lBtnJogar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lBtnJogar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Abrir tela de Gameplay...");
                //LF.switchToRegisterPanel(); // Chama a troca de tela
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lBtnJogar.setForeground(Color.BLUE); // Muda a cor ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lBtnJogar.setForeground(Color.RED); // Volta à cor original ao tirar o mouse
            }
        });
        add(lBtnJogar);

        JLabel lBtnGuilda = new JLabel("Guilda");
        lBtnGuilda.setFont(new Font("Arial", Font.BOLD, 14));
        lBtnGuilda.setBounds(30 + 550, 10, 60, 30);
        lBtnGuilda.setForeground(Color.GRAY);
        lBtnGuilda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lBtnGuilda.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Abrir tela de Guilda...");
                //LF.switchToRegisterPanel(); // Chama a troca de tela
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lBtnGuilda.setForeground(Color.BLUE); // Muda a cor ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lBtnGuilda.setForeground(Color.GRAY); // Volta à cor original ao tirar o mouse
            }
        });
        add(lBtnGuilda);

        JLabel lBtnDescESair = new JLabel("Desconectar e Sair");
        lBtnDescESair.setFont(new Font("Arial", Font.BOLD, 14));
        lBtnDescESair.setBounds(30 + 770, 10, 150, 30);
        lBtnDescESair.setForeground(Color.GRAY);
        lBtnDescESair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lBtnDescESair.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Caminho do arquivo config.json
                File configFile = new File("config.json");

                if (configFile.exists()) {
                    if (configFile.delete()) {
                        System.out.println("Arquivo config.json deletado com sucesso.");
                    } else {
                        System.err.println("Falha ao deletar o arquivo config.json.");
                    }
                } else {
                    System.err.println("Arquivo config.json não encontrado.");
                }

                // Fecha o JFrame usando a referência
                gameFrame.dispose();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lBtnDescESair.setForeground(Color.BLUE); // Muda a cor ao passar o mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lBtnDescESair.setForeground(Color.GRAY); // Volta à cor original ao tirar o mouse
            }
        });
        add(lBtnDescESair);
    }
}
