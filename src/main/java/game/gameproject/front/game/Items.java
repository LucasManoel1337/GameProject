package game.gameproject.front.game;

import java.awt.image.BufferedImage;

public abstract class Items{
    String nome; // variavel do nome
    String descricao; // variavel da  descrição

    public Items(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;

    }

    public abstract String getNome(); // get o nome

    public abstract void setNome(String nome); // set o nome

    public abstract String getDescricao(); // get descrição

    public abstract void setDescricao(String descricao); // set descrição

    public abstract BufferedImage imagemItem();
}
