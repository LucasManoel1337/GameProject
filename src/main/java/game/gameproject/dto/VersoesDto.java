package game.gameproject.dto;

public class VersoesDto {
    
    private String versaoLauncher = "v 1.0.0";
    private String versaoGame = "1.0.14";
    private static boolean modoDev = false;

    public String getVersaoLauncher() {
        return versaoLauncher;
    }

    public String getVersaoGame() {
        return versaoGame;
    }
    
    public boolean isModoDev() {
    	return modoDev;
    }
    
    public void setModoDev(boolean modoDev) {
    	VersoesDto.modoDev = modoDev;
    }
}
