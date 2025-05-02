package game.gameproject.dto;

public class ConfiguracoesDto {

	private static boolean visualizarOutrosJogadores = true;
	private static boolean modoDev = false;
	private static boolean visualizarDadosHub = true;
	
	public boolean isVisualizarOutrosJogadores() {
    	return visualizarOutrosJogadores;
    }
    public void setVisualizarOutrosJogadores(boolean visualizarOutrosJogadores) {
    	ConfiguracoesDto.visualizarOutrosJogadores = visualizarOutrosJogadores;
    }
	
	public boolean isVisualizarDadosHub() {
    	return visualizarDadosHub;
    }
    public void setVisualizarDadosHub(boolean visualizarDadosHub) {
    	ConfiguracoesDto.visualizarDadosHub = visualizarDadosHub;
    }
	
	public boolean isModoDev() {
    	return modoDev;
    }
    public void setModoDev(boolean modoDev) {
    	ConfiguracoesDto.modoDev = modoDev;
    }
}
