package game.gameproject.services;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.infoPlayerDto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuBarService {

    public String[] menuOptions = {"Status", "Inventário", "Mapa", "Missões", "Amigos", "Guilda"};
    
    public static void addMenu(JPanel panel, GameFrame gameFrame, infoPlayerDto playerInfo) {
    	
    // Criando os botões de navegação (JLabel)
    JLabel lBtnStatus = createMenuButton("Status", 30, panel, gameFrame, playerInfo);
    JLabel lBtnInventario = createMenuButton("Inventario", 100, panel, gameFrame, playerInfo);
    JLabel lBtnMapa = createMenuButton("Mapa", 200, panel, gameFrame, playerInfo);
    JLabel lBtnMissões = createMenuButton("Missões", 270, panel, gameFrame, playerInfo);
    JLabel lBtnAmigos = createMenuButton("Amigos", 360, panel, gameFrame, playerInfo);
    JLabel lBtnJogar = createMenuButton("JOGAR", 460, panel, Color.RED, gameFrame, playerInfo);
    JLabel lBtnGuilda = createMenuButton("Guilda", 580, panel, gameFrame, playerInfo);
    JLabel lBtnSair = createMenuButton("Sair", 630, panel, gameFrame, playerInfo);
    JLabel lBtnDescESair = createMenuButton("Desconectar e Sair", 800, panel, gameFrame, playerInfo);

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

    // Configurando o LayoutManager para null
    panel.setLayout(null);

    // Criando a imagem e redimensionando
    String imagePath = "imagens/Menu/fundos.jpg";
    ImageIcon imageIcon = new ImageIcon(imagePath);

    // Verificando se a imagem foi carregada corretamente
    if (imageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
        System.out.println("Erro: A imagem não foi carregada corretamente. Verifique o caminho da imagem.");
    } else {
        System.out.println("Imagem carregada com sucesso.");
    }

    // Tentar redimensionar a imagem
    try {
        Image img = imageIcon.getImage().getScaledInstance(1000, 750, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(img);
    } catch (Exception e) {
        System.out.println("Erro ao redimensionar a imagem: " + e.getMessage());
    }

    // Criando o JLabel da imagem e definindo a posição para cobrir o painel
    JLabel imageLabel = new JLabel(imageIcon);
    imageLabel.setBounds(0, 0, 1000, 750); // A imagem vai preencher o painel

    // Adicionando a imagem ao painel
    panel.add(imageLabel);

    // Verificando se o painel foi atualizado corretamente
    if (panel.getComponentCount() > 0) {
        System.out.println("Componentes do painel: " + panel.getComponentCount());
    } else {
        System.out.println("Erro: Nenhum componente foi adicionado ao painel.");
    }

    // Revalidando e repintando o painel para refletir a mudança
    panel.revalidate();
    panel.repaint();
}

    private static JLabel createMenuButton(String text, int xPosition, JPanel panel, GameFrame gameFrame, infoPlayerDto playerInfo) {
        return createMenuButton(text, xPosition, panel, Color.GRAY, gameFrame, playerInfo);
    }

    private static JLabel createMenuButton(String text, int xPosition, JPanel panel, Color textColor, GameFrame gameFrame, infoPlayerDto playerInfo) {
    	PlayerService PS = new PlayerService();

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        if (text.equals("Desconectar e Sair")) {
            label.setBounds(xPosition, 10, 180, 30);
        } else if (text.equals("Status")) {
            label.setBounds(30, 10, 55, 30);
        } else if (text.equals("Inventario")) {
            label.setBounds(100, 10, 80, 30);
        } else if (text.equals("Mapa")) {
            label.setBounds(200, 10, 50, 30);
        } else if (text.equals("Missões")) {
            label.setBounds(270, 10, 60, 30);
        } else if (text.equals("Amigos")) {
            label.setBounds(360, 10, 60, 30);
        } else if (text.equals("JOGAR")) {
            label.setBounds(470, 15, 70, 30);
        } else if (text.equals("Guilda")) {
            label.setBounds(580, 10, 60, 30);
        } else if (text.equals("Sair")) {
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
                	if(PS.setOffline(playerInfo.getIdPlayer()) == 1) {
                		System.out.println("Player desconectado com sucesso!");
                		System.exit(1);
                	} else {
                		System.out.println("Ocorreu um problema para deixar o player offline!");
                	}
                } else if (text.equals("Desconectar e Sair")) {
                	if (PS.setOffline(playerInfo.getIdPlayer()) == 1) {
                		System.out.println("Player desconectado com sucesso!");
                		
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

                        System.exit(1);
                	} else {
                		System.out.println("Ocorreu um problema para deixar o player offline!");
                	}
                }
            }
        });
        return label;
    }
}
