package game.gameproject.front;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.ConfiguracoesDto;
import game.gameproject.dto.VersoesDto;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.MenuBarService;
import game.gameproject.support.SliderCustomUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class JpConfiguracoes extends JPanel {

    private GameFrame gameFrame;
    private infoPlayerDto playerInfo;
    
    ConfiguracoesDto Config = new ConfiguracoesDto();
    
    public JSlider volumeMaster;
    public JSlider volumeMusica;
    public JSlider volumeEfeito;
    
    public JLabel lVolumeMaster;
    public JLabel lVolumeMusica;
    public JLabel lVolumeEfeito;
    
    public JLabel lResolucao;
    public String[] resolucoes = {
    	    "1280x768",
    	};
    public JComboBox<String> resolucao;
    
    public JCheckBox visualizarFPS;
    public JCheckBox visualizarOutrosJogadores;
    public JCheckBox visualizarDadosHub;
    public JCheckBox visualizarEfeitosVisuais;
    public JCheckBox filtroDeLinguagemNoChat;
    
    public JCheckBox modoDesenvolvedor;
    
    public JpConfiguracoes(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;
        this.playerInfo = playerInfo;
        setLayout(null);
        setBackground(Color.WHITE);
        
        JLabel lTituloTela = new JLabel("Config");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(55, 90, 700, 30);
        lTituloTela.setVisible(true);
        add(lTituloTela);

        // Criar a imagem
        ImageIcon logoIcon = new ImageIcon("imagens/Menu/PlacaTelas.png");
        Image img = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 50, 200, 100);
        add(logoLabel);
        
        add(lVolumeMaster = criarLabel("Volume Master", 78, 200, 300, 50, "Serif", 14));
        add(volumeMaster = criarSliders(70, 220, 300, 50));
        add(lVolumeMusica = criarLabel("Volume Música", 78, 270, 300, 50, "Serif", 14));
        add(volumeMusica = criarSliders(70, 290, 300, 50));
        add(lVolumeEfeito = criarLabel("Volume Efeitos Sonoros", 78, 340, 300, 50, "Serif", 14));
        add(volumeEfeito = criarSliders(70, 360, 300, 50));
        
        add(lResolucao = criarLabel("Resolução", 78, 400, 300, 50, "Serif", 14));
        add(resolucao = criarComboBox(78, 440, 290, 30, resolucoes));
        
        configurarCheckBoxVisual();
        add(visualizarFPS =criarCheckBox(
        	    "Visualizar FPS",
        	    900, 200, 200, 30,
        	    Config.isVisualizarFps(),
        	    e -> Config.setVisualizarFps(!Config.isVisualizarFps())
        	));
        
        add(visualizarOutrosJogadores = criarCheckBox(
        	    "Visualizar outros jogadores",
        	    900, 235, 200, 30,
        	    Config.isVisualizarOutrosJogadores(),
        	    e -> Config.setVisualizarOutrosJogadores(!Config.isVisualizarOutrosJogadores())
        	));
        
        add(visualizarDadosHub = criarCheckBox(
        		"Visualizar dados HUB",
        		900, 270, 200, 30,
        	    Config.isVisualizarDadosHub(),
        	    e -> Config.setVisualizarDadosHub(!Config.isVisualizarDadosHub())
        	));
        
        add(visualizarEfeitosVisuais = criarCheckBox(
        		"Visualizar Efeitos Visuais",
        		900, 305, 200, 30,
        	    Config.isVisualizarEfeitos(),
        	    e -> Config.setVisualizarEfeitos(!Config.isVisualizarEfeitos())
        	));
        
        add(filtroDeLinguagemNoChat = criarCheckBox(
        		"Filtro de linguagem no chat",
        		900, 340, 200, 30,
        	    Config.isFiltroLinguagemChat(),
        	    e -> Config.setFiltroLinguagemChat(!Config.isFiltroLinguagemChat())
        	));
        
        add(modoDesenvolvedor = criarCheckBox(
        		"Modo desenvolvedor",
        		900, 375, 200, 30,
        	    Config.isModoDev(),
        	    e -> Config.setModoDev(!Config.isModoDev())
        	));
        
        ImageIcon logoIconS = new ImageIcon("imagens/Menu/status/status.png");
        Image imgS = logoIconS.getImage().getScaledInstance(275+150, 410+150, Image.SCALE_SMOOTH);
        logoIconS = new ImageIcon(imgS);

        JLabel logoLabelS = new JLabel(logoIconS);
        logoLabelS.setBounds(420, 70 + 50, 275+150, 410+150);
        add(logoLabelS);
        
        JLabel logoLabelE = new JLabel(logoIconS);
        logoLabelE.setBounds(420+410, 70 + 50, 275+150, 410+150);
        add(logoLabelE);
        
        JLabel logoLabelD = new JLabel(logoIconS);
        logoLabelD.setBounds(10, 70 + 50, 275+150, 410+150);
        add(logoLabelD);
        
        MenuBarService.addMenu(this, gameFrame, playerInfo);
        bindEscapeKey();
    }
    
    public JComboBox<String> criarComboBox(int x, int y, int largura, int altura, String[] opcoes) {
        JComboBox<String> comboBox = new JComboBox<>(opcoes);
        comboBox.setBounds(x, y, largura, altura);
        comboBox.setVisible(true);
        comboBox.setFocusable(false);
        return comboBox;
    }
    
    public JLabel criarLabel(String texto, int x, int y, int largura, int altura, String fonte, int tamanhoFonte) {
    	JLabel jLabel = new JLabel(texto);
    	jLabel.setFont(new Font(fonte, Font.BOLD, tamanhoFonte));
        jLabel.setForeground(Color.BLACK);
        jLabel.setBounds(x, y, largura, altura);
    	return jLabel;
    }
    
    public JSlider criarSliders(int x, int y, int largura, int altura) {
        JSlider jSlider = new JSlider();
        jSlider.setMajorTickSpacing(10);
        jSlider.setPaintTicks(false);
        jSlider.setPaintLabels(false);
        jSlider.setOpaque(false);
        jSlider.setBackground(new Color(0, 0, 0, 0));
        jSlider.setVisible(true);
        jSlider.setFocusable(false);
        jSlider.setBounds(x, y, largura, altura);

        jSlider.setUI(new SliderCustomUI(jSlider, Color.YELLOW));

        return jSlider;
    }
    
    public JCheckBox criarCheckBox(String texto, int x, int y, int largura, int altura, boolean selecionadoInicial, ActionListener acao) {
        JCheckBox checkBox = new JCheckBox(texto);
        checkBox.setBounds(x, y, largura, altura);
        checkBox.setSelected(selecionadoInicial);
        checkBox.setFont(new Font("Serif", Font.BOLD, 16));
        checkBox.setForeground(Color.BLACK);
        checkBox.setVisible(true);
        checkBox.setOpaque(false);
        checkBox.setFocusable(false);
        checkBox.addActionListener(acao);
        return checkBox;
    }
    
    public void atualizarConfig1() {
    	volumeMaster.setValue(Config.getVolumeMaster());
    	volumeMusica.setValue(Config.getVolumeMusica());
    	volumeEfeito.setValue(Config.getVolumeEfeitosSonoros());
    }
    
    public void configurarCheckBoxVisual() {
        UIManager.put("CheckBox.icon", UIManager.getIcon("CheckBox.icon"));
        UIManager.put("CheckBox.background", new Color(0, 0, 0, 0));
        UIManager.put("CheckBox.focus", new Color(0, 0, 0, 0));
        UIManager.put("CheckBox.select", new ColorUIResource(255, 215, 100));
        UIManager.put("CheckBox.border", BorderFactory.createLineBorder(new Color(255, 215, 100)));
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
}