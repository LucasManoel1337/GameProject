package game.gameproject.dto;

public class ConfiguracoesDto {

	private static boolean visualizarOutrosJogadores = true;
	private static boolean modoDev = false;
	private static boolean visualizarDadosHub = true;
	private static boolean visualizarFps = false;
	private static boolean visualizarEfeitos = true;
	private static int volumeMaster = 100;
	private static int volumeMusica = 100;
	private static int volumeEfeitosSonoros = 100;
	private static boolean filtroLinguagemChat = false;
	
	public boolean isVisualizarFps() {
    	return visualizarFps;
    }
    public void setVisualizarFps(boolean visualizarFps) {
    	ConfiguracoesDto.visualizarFps = visualizarFps;
    }
	
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
	public static int getVolumeMaster() {
		return volumeMaster;
	}
	public static int getVolumeMusica() {
		return volumeMusica;
	}
	public static int getVolumeEfeitosSonoros() {
		return volumeEfeitosSonoros;
	}
	public static void setVolumeMaster(int volumeMaster) {
		ConfiguracoesDto.volumeMaster = volumeMaster;
	}
	public static void setVolumeMusica(int volumeMusica) {
		ConfiguracoesDto.volumeMusica = volumeMusica;
	}
	public static void setVolumeEfeitosSonoros(int volumeEfeitosSonoros) {
		ConfiguracoesDto.volumeEfeitosSonoros = volumeEfeitosSonoros;
	}
	public static boolean isVisualizarEfeitos() {
		return visualizarEfeitos;
	}
	public static void setVisualizarEfeitos(boolean visualizarEfeitos) {
		ConfiguracoesDto.visualizarEfeitos = visualizarEfeitos;
	}
	public static boolean isFiltroLinguagemChat() {
		return filtroLinguagemChat;
	}
	public static void setFiltroLinguagemChat(boolean filtroLinguagemChat) {
		ConfiguracoesDto.filtroLinguagemChat = filtroLinguagemChat;
	}
}
