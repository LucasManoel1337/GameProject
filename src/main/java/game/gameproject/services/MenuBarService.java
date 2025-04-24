package game.gameproject.services;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.support.FontLoaderSupport;

public class MenuBarService {

    public String[] menuOptions = {"Status", "Inventário", "Mapa", "Missões", "Amigos", "Guilda", "Configurações"};
    
    public static void addMenu(JPanel panel, GameFrame gameFrame, infoPlayerDto playerInfo) {
    	
    	Font customFont = null;
        try {
            // Carregando a fonte personalizada
            customFont = FontLoaderSupport.loadCustomFont("fonts/MedievalSharp-Regular.ttf", 14);  // Substitua o caminho para sua fonte
        } catch (IOException | FontFormatException e) {
            e.printStackTrace(); // Caso tenha algum erro, imprime no console
        }
    	
    // Criando os botões de navegação (JLabel)
    JLabel lBtnStatus = createMenuButton("Status", 30, panel, gameFrame, playerInfo, customFont);
    JLabel lBtnInventario = createMenuButton("Inventário", 100, panel, gameFrame, playerInfo, customFont);
    JLabel lBtnMapa = createMenuButton("Mapa", 200, panel, gameFrame, playerInfo, customFont);
    JLabel lBtnMissões = createMenuButton("Missões", 270, panel, gameFrame, playerInfo, customFont);
    JLabel lBtnAmigos = createMenuButton("Amigos", 360, panel, gameFrame, playerInfo, customFont);
    JLabel lBtnGuilda = createMenuButton("Guilda", 580, panel, gameFrame, playerInfo, customFont);
    JLabel lBtnConfiguracoes = createMenuButton("Configurações", 460, panel, gameFrame, playerInfo, customFont);
    JLabel lBtnJogar = createMenuButton("JOGAR", 640, panel, Color.WHITE, gameFrame, playerInfo, customFont);
    JLabel lBtnSair = createMenuButton("Sair", 630, panel, gameFrame, playerInfo, customFont);
    JLabel lBtnDescESair = createMenuButton("Desconectar e Sair", 800, panel, gameFrame, playerInfo, customFont);

    // Adicionando os itens de menu ao painel
    panel.add(lBtnStatus);
    panel.add(lBtnInventario);
    panel.add(lBtnMapa);
    panel.add(lBtnMissões);
    panel.add(lBtnAmigos);
    panel.add(lBtnJogar);
    panel.add(lBtnGuilda);
    panel.add(lBtnConfiguracoes);
    panel.add(lBtnSair);
    panel.add(lBtnDescESair);

    // Configurando o LayoutManager para null
    panel.setLayout(null);

    // Verificando se o painel foi atualizado corretamente
    if (panel.getComponentCount() > 0) {
        //System.out.println("Componentes do painel: " + panel.getComponentCount());
    } else {
        System.out.println("Erro: Nenhum componente foi adicionado ao painel.");
    }
    
    ImageIcon logoIcon = new ImageIcon("imagens/Menu/backgroundmenu.png");
    Image img = logoIcon.getImage().getScaledInstance(1280, 768, Image.SCALE_SMOOTH);  // Ajuste de tamanho da imagem
    logoIcon = new ImageIcon(img);

    JLabel logoLabel = new JLabel(logoIcon);
    logoLabel.setBounds(0, 0, 1280, 768);  // Coloquei a imagem abaixo do título (a partir de y = 50)
    panel.add(logoLabel);
    
    panel.revalidate();
    panel.repaint();
}

    private static JLabel createMenuButton(String text, int xPosition, JPanel panel, GameFrame gameFrame, infoPlayerDto playerInfo, Font customFont) {
        return createMenuButton(text, xPosition, panel, Color.YELLOW, gameFrame, playerInfo, customFont);
    }

    private static JLabel createMenuButton(String text, int xPosition, JPanel panel, Color textColor, GameFrame gameFrame, infoPlayerDto playerInfo, Font customFont) {
    	PlayerService PS = new PlayerService();

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        if (text.equals("Desconectar e Sair")) {
            label.setBounds(1090, 10, 180, 30);
        } else if (text.equals("Status")) {
            label.setBounds(30, 10, 55, 30);
        } else if (text.equals("Inventário")) {
            label.setBounds(100, 10, 80, 30);
        } else if (text.equals("Mapa")) {
            label.setBounds(200, 10, 50, 30);
        } else if (text.equals("Missões")) {
            label.setBounds(270, 10, 60, 30);
        } else if (text.equals("Amigos")) {
            label.setBounds(360, 10, 60, 30);
        } else if (text.equals("Guilda")) {
            label.setBounds(440, 10, 60, 30);
        } else if (text.equals("Configurações")) {
        	label.setBounds(510, 10, 110, 30);
        } else if (text.equals("JOGAR")) {
            label.setBounds(720, 10, 70, 30);
        } else if (text.equals("Sair")) {
            label.setBounds(1010, 10, 50, 30);
        }
        label.setFont(customFont);
        label.setForeground(textColor);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (text.equals("Status")) {
                    gameFrame.switchToStatusPanel(); // Chama a troca de tela
                } else if (text.equals("Inventário")) {
                    gameFrame.switchToInventarioPanel(); // Chama a troca de tela
                } else if (text.equals("Mapa")) {
                    gameFrame.switchToMapaPanel(); // Chama a troca de tela
                } else if (text.equals("Missões")) {
                    gameFrame.switchToMissoesPanel(); // Chama a troca de tela
                } else if (text.equals("Amigos")) {
                    gameFrame.switchToAmigosPanel(); // Chama a troca de tela
                } else if (text.equals("Guilda")) {
                    gameFrame.switchToGuildaPanel(); // Chama a troca de tela
                } else if (text.equals("Configurações")) {
                	gameFrame.switchToConfiguracoesPanel(); // Chama a troca de tela
                } else if (text.equals("JOGAR")) {
                    gameFrame.switchToGamePanel(); // Chama a troca de tela
                } else if (text.equals("Sair")) {
                	PS.setOffline(playerInfo.getIdPlayer());
                		DatabaseConfig.close();
                		System.exit(0);
                } else if (text.equals("Desconectar e Sair")) {
                    boolean desconectadoComSucesso = (PS.setOffline(playerInfo.getIdPlayer()) == 1);

                    if (desconectadoComSucesso) {
                        System.out.println("✔️ Player desconectado com sucesso!");

                        File configFile = new File("config.json");

                        if (configFile.exists()) {
                            if (configFile.delete()) {
                                System.out.println("✔️ Arquivo config.json deletado com sucesso.");
                            } else {
                                System.err.println("❌ Falha ao deletar o arquivo config.json.");
                            }
                        } else {
                            System.out.println("ℹ️ Arquivo config.json não encontrado.");
                        }

                        PS.setOffline(playerInfo.getIdPlayer());
                        
                        DatabaseConfig.close();
                        System.exit(1);
                    } else {
                        System.err.println("❌ Ocorreu um problema ao deixar o player offline.");
                        JOptionPane.showMessageDialog(
                            null,
                            "Erro ao desconectar. Tente novamente.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                }

            }
        });
        return label;
    }
}
