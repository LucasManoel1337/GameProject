package game.gameproject.front.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PocaoHP extends Items {
    int quantidade;
    int valorHP;
    BufferedImage imagemPocao;
    public PocaoHP(String nome, String descricao, int valorHP, int quantidade) {
        super(nome, descricao);
        this.valorHP = valorHP;
        this.quantidade = quantidade;
        try {
            imagemPocao = ImageIO.read(new File("imagens/itens/PocaoHp.png"));
        }catch (Exception e){
            System.err.println("Erro ao carregar imagem da pocao" + e.getMessage());
        }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getValorPocao() {
        return valorHP;
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
        this.descricao = descricao;
    }

    @Override
    public BufferedImage imagemItem() {
        return imagemPocao;
    }

}
