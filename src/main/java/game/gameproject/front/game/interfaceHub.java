package game.gameproject.front.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import game.gameproject.dto.ConfiguracoesDto;

public class interfaceHub {
	
	ConfiguracoesDto Config = new ConfiguracoesDto();
	
	private BufferedImage fundoHub;
	
	private BufferedImage[] moedas = new BufferedImage[6];
	private int moedaIndex = 0;
	private int xMoeda = 22;
    private int yMoeda = 120;
    
    private Color colorLabel = new Color(199, 179, 136);
    
    JProgressBar jBarXp = new JProgressBar();
    JProgressBar jBarVida = new JProgressBar();
    JProgressBar jBarMana = new JProgressBar();
    JProgressBar jBarStamina = new JProgressBar();
	
	public interfaceHub() {
    	carregarImagens();
    	
    	Timer timerMoeda = new Timer(100, new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                animarMoeda();
            }
        });
        timerMoeda.start();
    }

	public void desenharHubStats(Graphics g, String nick, int nivel, int dinheiro, JPanel painel) {
		
		g.drawImage(fundoHub, 5, 5, 300, 150, null);
		
		g.drawImage(moedas[moedaIndex], xMoeda, yMoeda, 26, 26, null);
		
		JLabel lNick = new JLabel(nick);
        lNick.setForeground(colorLabel);
		lNick.setBounds(20, 0, 150, 50);
		lNick.setVisible(true);
		painel.add(lNick);
		
		JLabel lNivel = new JLabel("Lv. "+nivel);
        lNivel.setForeground(colorLabel);
		lNivel.setBounds(20, 0, 150, 80);
		lNivel.setVisible(true);
		painel.add(lNivel);
		
		jBarXp.setForeground(new Color(199, 136, 35)); 
	    jBarXp.setMaximum(100);
	    jBarXp.setMinimum(0);
	    jBarXp.setValue(2);
	    jBarXp.setBorder(null);
	    jBarXp.setBorderPainted(false);
	    jBarXp.setBounds(15, 50, 280, 12);
	    if(Config.isVisualizarDadosHub()) {
	    	jBarXp.setStringPainted(true);
	    } else {
	    	jBarXp.setStringPainted(false);
	    }
	    jBarXp.setString("2 / 100");
	    jBarXp.setIndeterminate(false);
	    painel.add(jBarXp);
	    
	    jBarVida.setForeground(new Color(159, 55, 36)); 
        jBarVida.setMaximum(100);
        jBarVida.setMinimum(0);
        jBarVida.setValue(80);
        jBarVida.setBorder(null);
        jBarVida.setBorderPainted(false);
        jBarVida.setBounds(15, 68, 280, 12);
        if(Config.isVisualizarDadosHub()) {
        	jBarVida.setStringPainted(true);
        } else {
        	jBarVida.setStringPainted(false);
        }
        jBarVida.setString("80 / 100");
        jBarVida.setIndeterminate(false);
        painel.add(jBarVida);
        
        jBarMana.setForeground(new Color(40, 68, 91));
        jBarMana.setMaximum(100);
        jBarMana.setMinimum(0);
        jBarMana.setValue(60);
        jBarMana.setBorder(null);
        jBarMana.setBorderPainted(false);
        jBarMana.setBounds(15, 86, 280, 12);
        if(Config.isVisualizarDadosHub()) {
        	jBarMana.setStringPainted(true);
        } else {
        	jBarMana.setStringPainted(false);
        }
        jBarMana.setString("60 / 100");
        jBarMana.setIndeterminate(false);
        painel.add(jBarMana);
        
        jBarStamina.setForeground(new Color(64, 96, 35));
        jBarStamina.setMaximum(100);
        jBarStamina.setMinimum(0);
        jBarStamina.setValue(40);
        jBarStamina.setBorder(null);
        jBarStamina.setBorderPainted(false);
        jBarStamina.setBounds(15, 104, 280, 12);
        if(Config.isVisualizarDadosHub()) {
        	jBarStamina.setStringPainted(true);
        } else {
        	jBarStamina.setStringPainted(false);
        }
        jBarStamina.setString("40 / 100");
        jBarStamina.setIndeterminate(false);
        painel.add(jBarStamina);
        
        JLabel lDinheiro = new JLabel("$ "+dinheiro);
        lDinheiro.setForeground(colorLabel);
		lDinheiro.setBounds(49, 94, 150, 80);
		lDinheiro.setVisible(true);
		painel.add(lDinheiro);
    }
	
	private void carregarImagens() {
        try {
        	moedas[0] = ImageIO.read(new File("imagens/game/interface/statusHUB/animacaoMoeda/moeda1.png"));
            moedas[1] = ImageIO.read(new File("imagens/game/interface/statusHUB/animacaoMoeda/moeda2.png"));
            moedas[2] = ImageIO.read(new File("imagens/game/interface/statusHUB/animacaoMoeda/moeda3.png"));
            moedas[3] = ImageIO.read(new File("imagens/game/interface/statusHUB/animacaoMoeda/moeda4.png"));
            moedas[4] = ImageIO.read(new File("imagens/game/interface/statusHUB/animacaoMoeda/moeda5.png"));
            moedas[5] = ImageIO.read(new File("imagens/game/interface/statusHUB/animacaoMoeda/moeda6.png"));
            
            fundoHub = ImageIO.read(new File("imagens/game/interface/statusHUB/fundoHubStats.png"));
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagens na classe interfaceHub!" + e.getMessage());
            System.exit(1);
        }
	}
	
	private void animarMoeda() {
        moedaIndex = (moedaIndex + 1) % moedas.length;
    }
	
}
