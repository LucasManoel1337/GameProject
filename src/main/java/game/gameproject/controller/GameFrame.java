package game.gameproject.controller;

import game.gameproject.dto.infoPlayerDto;
import game.gameproject.front.Autenticacao.JpMenu;
import game.gameproject.front.Autenticacao.JpGame;

import javax.swing.*;

public class GameFrame extends JFrame {
    private JPanel currentPanel;
    private JpMenu telaMenu;
    private JpGame telaJogo;
    private infoPlayerDto playerInfo;  // Variável para armazenar as informações do player

    public GameFrame() {
        setTitle("Room 5 Studios - Game");
        setSize(1000, 750);  // Tamanho do jogo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("imagens/login/logoEmpresa.png").getImage());

        // Inicialize playerInfo com dados reais de login ou padrão
        playerInfo = login();  // Substitua com a lógica de obtenção das informações do jogador

        // Agora, o playerInfo está inicializado corretamente
        telaMenu = new JpMenu(this, playerInfo);
        currentPanel = telaMenu;
        add(currentPanel);

        setVisible(true);
    }

    // Método de login fictício para obter o infoPlayerDto
    private infoPlayerDto login() {
        // Aqui você deve implementar a lógica de login e retornar o infoPlayerDto adequado.
        // Caso o login falhe, você pode retornar null ou criar um objeto padrão.
        return new infoPlayerDto("playerNickname");  // Exemplo de retorno com dados fictícios
    }

    // Método para mudar para a tela de Jogo
    public void switchToGamePanel() {
        remove(currentPanel);
        if (telaJogo == null) {
            telaJogo = new JpGame(this); // Cria a tela de jogo
        }
        currentPanel = telaJogo;
        add(currentPanel);
        revalidate();
        repaint();
    }

    // Método para voltar para a tela de Menu
    public void switchToMenuPanel() {
        remove(currentPanel);
        if (telaMenu == null) {
            telaMenu = new JpMenu(this, playerInfo); // Cria a tela de menu
        }
        currentPanel = telaMenu;
        add(currentPanel);
        revalidate();
        repaint();
    }
}
