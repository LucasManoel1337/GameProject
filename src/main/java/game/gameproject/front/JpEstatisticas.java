package game.gameproject.front;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import game.gameproject.controller.GameFrame;
import game.gameproject.dto.EstatisticasDto;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.MenuBarService;

public class JpEstatisticas extends JPanel {
	
	// === Estatísticas de Combate ===
		private JLabel qntMortes;
		private JLabel danoRealizado;
		private JLabel danoSofrido;
		private JLabel criticosAcertados;
		private JLabel manaGasta;
		private JLabel magiasLançadas;
		private JLabel buffsRecebidos;
		private JLabel debuffSofridos;

		// === Estatísticas de Progressão ===
		private JLabel xpGanho;
		private JLabel missoesConcluidas;
		private JLabel porcentagemExploradaDoMap;

		// === Estatísticas de Interações ===
		private JLabel interacoesComNpcs;
		private JLabel vezesUsouPontoDeTransporte;
		private JLabel teleportesDescobertos;

		// === Estatísticas de Itens e Recursos ===
		private JLabel itensColetados;
		private JLabel itensVendidos;
		private JLabel comprasRealizadas;
		private JLabel dinheiroTotalColetado;
		private JLabel itensCurativosUsados;

    private GameFrame gameFrame;
    private infoPlayerDto playerInfo;
    
    public EstatisticasDto ED = new EstatisticasDto();

    public JpEstatisticas(GameFrame gameFrame, infoPlayerDto playerInfo) {
        this.gameFrame = gameFrame;
        this.playerInfo = playerInfo;
        setLayout(null);
        setBackground(Color.WHITE);
        
        JLabel lTituloTela = new JLabel("Estatisticas");
        lTituloTela.setFont(new Font("Arial", Font.BOLD, 30));
        lTituloTela.setForeground(Color.BLACK);
        lTituloTela.setBounds(16, 90, 700, 30);
        lTituloTela.setVisible(true);
        add(lTituloTela);

        ImageIcon logoIcon = new ImageIcon("imagens/Menu/PlacaTelas.png");
        Image img = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(img);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 50, 200, 100);
        add(logoLabel);
        
        qntMortes = criarLabel("Quantidade de mortes: " + EstatisticasDto.getQntMortes(), 78, 200, 300, 50, "Arial", 14);
        danoRealizado = criarLabel("Dano realizado: " + EstatisticasDto.getDanoRealizado(), 78, 215, 300, 50, "Arial", 14);
        danoSofrido = criarLabel("Danos sofridos: " + EstatisticasDto.getDanoSofrido(), 78, 230, 300, 50, "Arial", 14);
        criticosAcertados = criarLabel("Críticos acertados: " + EstatisticasDto.getCriticosAcertados(), 78, 245, 300, 50, "Arial", 14);
        manaGasta = criarLabel("Mana gasta: " + EstatisticasDto.getManaGasta(), 78, 260, 300, 50, "Arial", 14);
        magiasLançadas = criarLabel("Magias lançadas: " + EstatisticasDto.getMagiasLançadas(), 78, 275, 300, 50, "Arial", 14);
        buffsRecebidos = criarLabel("Buffs recebidos: " + EstatisticasDto.getBuffsRecebidos(), 78, 290, 300, 50, "Arial", 14);
        debuffSofridos = criarLabel("Debuffs sofridos: " + EstatisticasDto.getDebuffSofridos(), 78, 305, 300, 50, "Arial", 14);

        xpGanho = criarLabel("Total XP ganho: " + EstatisticasDto.getXpGanho(), 78, 335, 300, 50, "Arial", 14);
        missoesConcluidas = criarLabel("Missões concluídas: " + EstatisticasDto.getMissoesConcluidas(), 78, 350, 300, 50, "Arial", 14);
        porcentagemExploradaDoMap = criarLabel("Mapa explorado: " + EstatisticasDto.getPorcentagemExploradaDoMapa() + "%", 78, 365, 300, 50, "Arial", 14);

        interacoesComNpcs = criarLabel("Interações com NPC: " + EstatisticasDto.getInteracoesComNpcs(), 78, 395, 300, 50, "Arial", 14);
        vezesUsouPontoDeTransporte = criarLabel("Teleportes utilizados: " + EstatisticasDto.getVezesUsouPontoDeTransporte(), 78, 410, 300, 50, "Arial", 14);
        teleportesDescobertos = criarLabel("Teleportes descobertos: " + EstatisticasDto.getTeleportesDescobertos(), 78, 425, 300, 50, "Arial", 14);

        itensColetados = criarLabel("Itens coletados: " + EstatisticasDto.getItensColetados(), 78, 455, 300, 50, "Arial", 14);
        itensVendidos = criarLabel("Itens vendidos: " + EstatisticasDto.getItensVendidos(), 78, 470, 300, 50, "Arial", 14);
        comprasRealizadas = criarLabel("Compras realizadas: " + EstatisticasDto.getComprasRealizadas(), 78, 485, 300, 50, "Arial", 14);
        dinheiroTotalColetado = criarLabel("Dinheiro já coletado: " + EstatisticasDto.getDinheiroTotalColetado(), 78, 500, 300, 50, "Arial", 14);
        itensCurativosUsados = criarLabel("Itens curativos usados: " + EstatisticasDto.getItensCurativosUsados(), 78, 515, 300, 50, "Arial", 14);

        // Adiciona ao painel
        add(qntMortes);
        add(danoRealizado);
        add(danoSofrido);
        add(criticosAcertados);
        add(manaGasta);
        add(magiasLançadas);
        add(buffsRecebidos);
        add(debuffSofridos);

        add(xpGanho);
        add(missoesConcluidas);
        add(porcentagemExploradaDoMap);

        add(interacoesComNpcs);
        add(vezesUsouPontoDeTransporte);
        add(teleportesDescobertos);

        add(itensColetados);
        add(itensVendidos);
        add(comprasRealizadas);
        add(dinheiroTotalColetado);
        add(itensCurativosUsados);
        
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
    
    public void atualizarEstatisticas() {
        qntMortes.setText("Quantidade de mortes: " + EstatisticasDto.getQntMortes());
        danoRealizado.setText("Dano realizado: " + EstatisticasDto.getDanoRealizado());
        danoSofrido.setText("Danos sofridos: " + EstatisticasDto.getDanoSofrido());
        criticosAcertados.setText("Críticos acertados: " + EstatisticasDto.getCriticosAcertados());
        manaGasta.setText("Mana gasta: " + EstatisticasDto.getManaGasta());
        magiasLançadas.setText("Magias lançadas: " + EstatisticasDto.getMagiasLançadas());
        buffsRecebidos.setText("Buffs recebidos: " + EstatisticasDto.getBuffsRecebidos());
        debuffSofridos.setText("Debuffs sofridos: " + EstatisticasDto.getDebuffSofridos());

        xpGanho.setText("Total XP ganho: " + EstatisticasDto.getXpGanho());
        missoesConcluidas.setText("Missões concluídas: " + EstatisticasDto.getMissoesConcluidas());
        porcentagemExploradaDoMap.setText("Mapa explorado: " + EstatisticasDto.getPorcentagemExploradaDoMapa() + "%");

        interacoesComNpcs.setText("Interações com NPC: " + EstatisticasDto.getInteracoesComNpcs());
        vezesUsouPontoDeTransporte.setText("Teleportes utilizados: " + EstatisticasDto.getVezesUsouPontoDeTransporte());
        teleportesDescobertos.setText("Teleportes descobertos: " + EstatisticasDto.getTeleportesDescobertos());

        itensColetados.setText("Itens coletados: " + EstatisticasDto.getItensColetados());
        itensVendidos.setText("Itens vendidos: " + EstatisticasDto.getItensVendidos());
        comprasRealizadas.setText("Compras realizadas: " + EstatisticasDto.getComprasRealizadas());
        dinheiroTotalColetado.setText("Dinheiro já coletado: " + EstatisticasDto.getDinheiroTotalColetado());
        itensCurativosUsados.setText("Itens curativos usados: " + EstatisticasDto.getItensCurativosUsados());
    }
    
    public JLabel criarLabel(String texto, int x, int y, int largura, int altura, String fonte, int tamanhoFonte) {
    	JLabel jLabel = new JLabel(texto);
    	jLabel.setFont(new Font(fonte, Font.BOLD, tamanhoFonte));
        jLabel.setForeground(Color.BLACK);
        jLabel.setBounds(x, y, largura, altura);
    	return jLabel;
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