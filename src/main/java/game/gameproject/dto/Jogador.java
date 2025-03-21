package game.gameproject.dto;

import java.awt.Image;

public class Jogador {
    private int idPlayer;
    private String nomePlayer;
    private int xPlayer;
    private int yPlayer;
    private Image spritePlayer;

    public Jogador(int idPlayer, String nomePlayer, int xPlayer, int yPlayer, Image spritePlayer) {
        this.idPlayer = idPlayer;
        this.nomePlayer = nomePlayer;
        this.xPlayer = xPlayer;
        this.yPlayer = yPlayer;
        this.spritePlayer = spritePlayer;
    }

    // Getters e Setters
    public int getIdPlayer() {
        return idPlayer;
    }

    public String getNomePlayer() {
        return nomePlayer;
    }

    public int getxPlayer() {
        return xPlayer;
    }

    public int getyPlayer() {
        return yPlayer;
    }

    public Image getSpritePlayer() {
        return spritePlayer;
    }
}
