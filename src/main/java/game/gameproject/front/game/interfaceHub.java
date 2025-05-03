package game.gameproject.front.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import game.gameproject.bdd.DatabaseConfig;
import game.gameproject.dto.ConfiguracoesDto;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.PlayerService;

public class interfaceHub {

    private final ConfiguracoesDto config = new ConfiguracoesDto();

    private final BufferedImage[] moedas = new BufferedImage[6];
    private BufferedImage fundoHub, simOnline, simPing1, simPing2, simPing3, simPing4, simPing5, hotbar;

    private int moedaIndex = 0;
    private final int xMoeda = 22, yMoeda = 120;
    private final Color colorLabel = new Color(199, 179, 136);

    private final JProgressBar jBarXp = createProgressBar(new Color(199, 136, 35), 15, 50);
    private final JProgressBar jBarVida = createProgressBar(new Color(159, 55, 36), 15, 68);
    private final JProgressBar jBarMana = createProgressBar(new Color(40, 68, 91), 15, 86);
    private final JProgressBar jBarStamina = createProgressBar(new Color(64, 96, 35), 15, 104);

    private long lastTime = System.nanoTime();
    private int frames = 0;
    private int fps = 0;
    
    private infoPlayerDto playerInfo;

    public interfaceHub(infoPlayerDto playerInfo) {
    	this.playerInfo = playerInfo;
        carregarImagens();
        Timer timerMoeda = new Timer(100, e -> animarMoeda());
        timerMoeda.start();
    }

    public void desenharHubStats(Graphics g, String nick, int nivel, int dinheiro, JPanel painel) {
        g.drawImage(fundoHub, 5, 5, 300, 150, null);
        g.drawImage(moedas[moedaIndex], xMoeda, yMoeda, 26, 26, null);

        painel.add(createLabel(nick, 20, 0, 150, 50));
        painel.add(createLabel("Lv. " + nivel, 20, 0, 150, 80));
        painel.add(createLabel("$ " + dinheiro, 49, 94, 150, 80));

        atualizarBarra(jBarXp, playerInfo.getXpAtual(), playerInfo.getXpMaxima(), playerInfo.getXpAtual()+" / "+playerInfo.getXpMaxima());
        painel.add(jBarXp);

        atualizarBarra(jBarVida, playerInfo.getVidaAtual(), playerInfo.getVidaMaxima(), playerInfo.getVidaAtual()+" / "+playerInfo.getVidaMaxima());
        painel.add(jBarVida);

        atualizarBarra(jBarMana, playerInfo.getManaAtual(), playerInfo.getManaMaxima(), playerInfo.getManaAtual()+" / "+playerInfo.getManaMaxima());
        painel.add(jBarMana);

        atualizarBarra(jBarStamina, playerInfo.getStaminaAtual(), playerInfo.getStaminaMaxima(), playerInfo.getStaminaAtual()+" / "+playerInfo.getStaminaMaxima());
        painel.add(jBarStamina);

        desenharPingEOnline(g);
        g.drawImage(hotbar, 385, 678, 500, 50, null);

        if (config.isVisualizarFps()) {
            calcularFps();
            g.setColor(Color.WHITE);
            g.drawString("FPS: " + fps, 1210, 10);
        }
    }

    public void desenharNomeJogador(Graphics g, String nome, int xPersonagem, int yPersonagem, int larguraPersonagem) {
        FontMetrics fontMetrics = g.getFontMetrics();
        int larguraTexto = fontMetrics.stringWidth(nome);
        int alturaTexto = fontMetrics.getHeight();
        int xTexto = xPersonagem + (larguraPersonagem - larguraTexto) / 2;
        int yTexto = yPersonagem - alturaTexto;

        // Fundo preto externo
        g.setColor(Color.BLACK);
        g.fillRect(xTexto - 5, yTexto - alturaTexto, larguraTexto + 10, alturaTexto + 5);

        // Fundo branco interno
        g.setColor(Color.WHITE);
        g.fillRect(xTexto - 4, yTexto - alturaTexto + 1, larguraTexto + 8, alturaTexto + 3);

        // Verifica se nome tem prefixo [ADMIN]
        if (nome.startsWith("[ADMIN] ")) {
            String adminPrefix = "[ADMIN] ";
            String nomeReal = nome.substring(adminPrefix.length());

            int larguraAdmin = fontMetrics.stringWidth(adminPrefix);

            // Desenha [ADMIN] em vermelho
            g.setColor(Color.RED);
            g.drawString(adminPrefix, xTexto, yTexto);

            // Desenha o nome real em preto
            g.setColor(Color.BLACK);
            g.drawString(nomeReal, xTexto + larguraAdmin, yTexto);
        } else {
            g.setColor(Color.BLACK);
            g.drawString(nome, xTexto, yTexto);
        }
    }

    private void carregarImagens() {
        try {
            for (int i = 0; i < moedas.length; i++) {
                moedas[i] = ImageIO.read(new File("imagens/game/interface/statusHUB/animacaoMoeda/moeda" + (i + 1) + ".png"));
            }

            fundoHub = ImageIO.read(new File("imagens/game/interface/statusHUB/fundoHubStats.png"));
            simOnline = ImageIO.read(new File("imagens/game/interface/online.png"));
            simPing1 = ImageIO.read(new File("imagens/game/interface/ping1.png"));
            simPing2 = ImageIO.read(new File("imagens/game/interface/ping2.png"));
            simPing3 = ImageIO.read(new File("imagens/game/interface/ping3.png"));
            simPing4 = ImageIO.read(new File("imagens/game/interface/ping4.png"));
            simPing5 = ImageIO.read(new File("imagens/game/interface/ping5.png"));
            hotbar = ImageIO.read(new File("imagens/game/interface/hotbar.png"));

        } catch (IOException e) {
            System.err.println("Erro ao carregar imagens na classe InterfaceHub! " + e.getMessage());
            System.exit(1);
        }
    }

    private void animarMoeda() {
        moedaIndex = (moedaIndex + 1) % moedas.length;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setForeground(colorLabel);
        label.setBounds(x, y, width, height);
        return label;
    }

    private static JProgressBar createProgressBar(Color color, int x, int y) {
        JProgressBar bar = new JProgressBar();
        bar.setForeground(color);
        bar.setBounds(x, y, 280, 12);
        bar.setBorder(null);
        bar.setBorderPainted(false);
        bar.setIndeterminate(false);
        return bar;
    }

    private void atualizarBarra(JProgressBar barra, int valor, int maximo, String texto) {
        barra.setMaximum(maximo);
        barra.setMinimum(0);
        barra.setValue(valor);
        barra.setString(texto);
        barra.setStringPainted(config.isVisualizarDadosHub());
    }

    private void desenharPingEOnline(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(1138, 706, 140, 24);

        g.setColor(Color.WHITE);
        g.fillRect(1140, 709, 140, 22);

        int ping = (int) DatabaseConfig.getDatabasePing();
        BufferedImage pingIcon = simPing5;

        if (ping <= 15) pingIcon = simPing1;
        else if (ping <= 22) pingIcon = simPing2;
        else if (ping <= 28) pingIcon = simPing3;
        else if (ping <= 38) pingIcon = simPing4;

        g.drawImage(pingIcon, 1140, 710, 20, 20, null);
        g.setColor(Color.BLACK);
        g.drawString(ping + "ms", 1160, 725);

        g.drawImage(simOnline, 1205, 715, 12, 12, null);
        g.drawString(String.valueOf(PlayerService.getPlayersOnline()), 1220, 725);
    }

    private void calcularFps() {
        frames++;
        long now = System.nanoTime();
        if (now - lastTime >= 1_000_000_000) {
            fps = frames;
            frames = 0;
            lastTime = now;
        }
    }
}
