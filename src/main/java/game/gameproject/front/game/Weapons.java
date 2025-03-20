package game.gameproject.front.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Weapons extends Items {
    int dano;
    BufferedImage imagemArma;
    public Weapons(String nome, String descricao, int dano) {
        super(nome, descricao);
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao=descricao;
    }

    @Override
    public BufferedImage imagemItem() {
        return null;
    }
    public BufferedImage getImagemArma() {
        return imagemArma;
    }

    public BufferedImage carregarImagemArma(String nomeDaEspada) {
        try {
            return imagemArma = ImageIO.read(new File("imagens/itens/" + nomeDaEspada + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
