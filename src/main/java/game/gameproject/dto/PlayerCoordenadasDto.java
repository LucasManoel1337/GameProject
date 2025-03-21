package game.gameproject.dto;

public class PlayerCoordenadasDto {
    private int idPlayer;
    private int x;
    private int y;
    private int sprite;

    // Construtor corrigido
    public PlayerCoordenadasDto(int idPlayer, int x, int y, int sprite) {
        this.idPlayer = idPlayer;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSprite() {
        return sprite;
    }
}
