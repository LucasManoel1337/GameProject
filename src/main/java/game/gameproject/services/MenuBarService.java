package game.gameproject.services;

import game.gameproject.controller.GameFrame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuBarService {

    public static void addMenu(JPanel panel, GameFrame gameFrame) {
        // Criando os botões de navegação (JLabel)
        JLabel lBtnStatus = createMenuButton("Status", 30, panel, gameFrame);
        JLabel lBtnInventario = createMenuButton("Inventario", 100, panel, gameFrame);
        JLabel lBtnMapa = createMenuButton("Mapa", 200, panel, gameFrame);
        JLabel lBtnMissões = createMenuButton("Missões", 270, panel, gameFrame);
        JLabel lBtnAmigos = createMenuButton("Amigos", 360, panel, gameFrame);
        JLabel lBtnJogar = createMenuButton("JOGAR", 460, panel, Color.RED, gameFrame);
        JLabel lBtnGuilda = createMenuButton("Guilda", 580, panel, gameFrame);
        JLabel lBtnSair = createMenuButton("Sair", 630, panel, gameFrame);
        JLabel lBtnDescESair = createMenuButton("Desconectar e Sair", 800, panel, gameFrame);
        

        // Adicionando os itens de menu ao painel
        panel.add(lBtnStatus);
        panel.add(lBtnInventario);
        panel.add(lBtnMapa);
        panel.add(lBtnMissões);
        panel.add(lBtnAmigos);
        panel.add(lBtnJogar);
        panel.add(lBtnGuilda);
        panel.add(lBtnSair);
        panel.add(lBtnDescESair);
    }

    private static JLabel createMenuButton(String text, int xPosition, JPanel panel, GameFrame gameFrame) {
        return createMenuButton(text, xPosition, panel, Color.GRAY, gameFrame);
    }

    private static JLabel createMenuButton(String text, int xPosition, JPanel panel, Color textColor, GameFrame gameFrame) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        if (text == "Desconectar e Sair") {
            label.setBounds(xPosition, 10, 180, 30);
        } else if (text == "Status") {
            label.setBounds(30, 10, 55, 30);
        } else if (text == "Inventario") {
            label.setBounds(100, 10, 80, 30);
        } else if (text == "Mapa") {
            label.setBounds(200, 10, 50, 30);
        } else if (text == "Missões") {
            label.setBounds(270, 10, 60, 30);
        } else if (text == "Amigos") {
            label.setBounds(360, 10, 60, 30);
        } else if (text == "JOGAR") {
            label.setBounds(470, 15, 70, 30);
        } else if (text == "Guilda") {
            label.setBounds(580, 10, 60, 30);
        } else if (text == "Sair") {
            label.setBounds(660, 10, 50, 30);
        }
        label.setForeground(textColor);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (text.equals("Status")) {
                    gameFrame.switchToStatusPanel(); // Chama a troca de tela
                } else if (text.equals("Inventario")) {
                    gameFrame.switchToInventarioPanel(); // Chama a troca de tela
                } else if (text.equals("Mapa")) {
                    gameFrame.switchToMapaPanel(); // Chama a troca de tela
                } else if (text.equals("Missões")) {
                    gameFrame.switchToMissoesPanel(); // Chama a troca de tela
                } else if (text.equals("Amigos")) {
                    gameFrame.switchToAmigosPanel(); // Chama a troca de tela
                } else if (text.equals("JOGAR")) {
                    gameFrame.switchToGamePanel(); // Chama a troca de tela
                } else if (text.equals("Guilda")) {
                    gameFrame.switchToGuildaPanel(); // Chama a troca de tela
                } else if (text.equals("Sair")) {
                    gameFrame.dispose();
                }else if (text.equals("Desconectar e Sair")) {
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
            }
        });
        return label;
    }
}
