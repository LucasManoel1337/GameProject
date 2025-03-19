package game.gameproject.dto;

public class infoPlayerDto {

    public infoPlayerDto(String playerNickname) {
    }
    private int idPlayer = 0;
    private static String NickPlayer = "a";

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getNickPlayer() {
        return NickPlayer;
    }

    public static void setNickPlayer(String NickPlayer) {
        infoPlayerDto.NickPlayer = NickPlayer;
    }
}
