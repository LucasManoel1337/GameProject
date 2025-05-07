package game.gameproject.front.game;

import game.gameproject.dto.chatDto;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.dto.ConfiguracoesDto;
import game.gameproject.services.ChatGlobalService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChatUI {
    private final infoPlayerDto playerInfo;
    private final chatDto CD = new chatDto();
    private final ConfiguracoesDto Config = new ConfiguracoesDto();
    private final ChatGlobalService CGS;

    private JTextField chatField;
    private JButton sendButton;
    private JTextArea chatArea;
    private JScrollPane chatScrollPane;

    private BufferedImage[] digitar = new BufferedImage[6];
    private int digitarIndex = 0;
    private Timer timerDigitar;
    private boolean chatVisivel = false;

    public ChatUI(infoPlayerDto playerInfo) {
        this.playerInfo = playerInfo;
        this.CGS = new ChatGlobalService(playerInfo);
        carregarImagens();
        initComponents();
        startTypingAnimation();
    }

    private void carregarImagens() {
        try {
            for (int i = 0; i < digitar.length; i++) {
                digitar[i] = ImageIO.read(new File(
                    String.format("imagens/game/animacaoDigitando/digitando%d.png", i + 1)
                ));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar animação de digitar: " + e.getMessage());
        }
    }

    private void initComponents() {
        chatField = new JTextField();
        chatField.setBounds(5, 695, 300, 30);
        chatField.setBackground(new Color(211, 211, 211, 150));
        chatField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        chatField.setVisible(false);
        chatField.addActionListener(e -> sendButton.doClick());

        sendButton = new JButton("Enviar");
        sendButton.setBounds(310, 695, 70, 30);
        sendButton.setBackground(new Color(32, 3, 3));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setOpaque(true);
        sendButton.setContentAreaFilled(true);
        sendButton.setBorderPainted(false);
        sendButton.setVisible(false);
        sendButton.addActionListener(e -> {
            CGS.enviarMensagem(
                playerInfo.getIdPlayer(),
                playerInfo.getNickPlayer(),
                chatField.getText(),
                playerInfo
            );
            chatField.setText("");
            carregarMensagens();
        });

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(new Color(255, 255, 255, 150));
        chatArea.setForeground(Color.BLACK);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setVisible(false);

        chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBounds(5, 410, 375, 280);
        chatScrollPane.setVisible(false);
        chatScrollPane.setBorder(null);
    }

    private void startTypingAnimation() {
        timerDigitar = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                digitarIndex = (digitarIndex + 1) % digitar.length;
            }
        });
        timerDigitar.start();
    }

    public void addToParent(JPanel parent) {
        parent.add(chatField);
        parent.add(sendButton);
        parent.add(chatScrollPane);
        // Load initial messages
        carregarMensagens();
    }

    private void carregarMensagens() {
        String mensagens = CGS.obterUltimasMensagens();
        chatArea.setText(mensagens);
    }

    public void render(Graphics g, int xPersonagem, int yPersonagem) {
        if (CD.isChatAtivo()) {
            if (!chatVisivel) {
                chatVisivel = true;
                chatField.setVisible(true);
                sendButton.setVisible(true);
                chatScrollPane.setVisible(true);
                chatArea.setVisible(true);
            }
            BufferedImage img = digitar[digitarIndex];
            g.drawImage(img, xPersonagem, yPersonagem - 60, 30, 30, null);
        } else {
            if (chatVisivel) {
                chatField.setText("");
                chatField.setVisible(false);
                sendButton.setVisible(false);
                chatScrollPane.setVisible(false);
                chatArea.setVisible(false);
                chatVisivel = false;
            }
        }
    }
    
    public BufferedImage getTypingImage() {
        return digitar[digitarIndex];
    }
}
