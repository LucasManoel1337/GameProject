package game.gameproject.dto;

public class infoPlayerDto {

    public infoPlayerDto(String playerNickname) {
    }
    private static int idPlayer = 0;
    private static String NickPlayer = "";
    
    private static int nivel = -1;
    private static int pontos = -1;
    private static int vida = -1;
    private static int stamina = -1;
    private static int forca = -1;
    private static int dinheiro = -1;

    public int getIdPlayer() {
        return idPlayer;
    }

    public static void setIdPlayer(int idPlayer) {
        infoPlayerDto.idPlayer = idPlayer;
    }

    public String getNickPlayer() {
        return NickPlayer;
    }

    public static void setNickPlayer(String NickPlayer) {
        infoPlayerDto.NickPlayer = NickPlayer;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        infoPlayerDto.nivel = nivel;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        infoPlayerDto.pontos = pontos;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        infoPlayerDto.vida = vida;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        infoPlayerDto.stamina = stamina;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        infoPlayerDto.forca = forca;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(int dinheiro) {
        infoPlayerDto.dinheiro = dinheiro;
    }
}
