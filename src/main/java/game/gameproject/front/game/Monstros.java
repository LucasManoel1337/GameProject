package game.gameproject.front.game;

import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Monstros {
    
    private Player player; // Atributo para a instância do Player

    // Vetor de tipos de inimigos
    private String[] tipos = {"Slime", "Goblin", "FadaMacabra"};

    // Variaveis globais
    // Atributos do inimigo
    private String tipo;
    private String boss;
    private int vida;
    private int bossVida;
    private int forca;
    private int bossForca;
    private int xp;
    private int bossExp;
    private int ouro;
    private int bossOuro;
    private BufferedImage imagemMonstro;

    // Construtor
    public Monstros() {
        Random random = new Random();

        // Escolhe um tipo de inimigo aleatório
        this.tipo = tipos[random.nextInt(tipos.length)];
        this.boss = "MoonDust";
        // Define atributos com base no tipo de monstro
        switch (this.tipo) {
            case "Slime":
                this.vida = random.nextInt(6) + 20; // Vida entre 20 e 26
                this.forca = random.nextInt(5) + 5; // Força entre 5 e 10
                this.xp = random.nextInt(10) + 15; // XP entre 15 e 25
                this.ouro = random.nextInt(10) + 5; // Ouro entre 5 e 15
                carregarImagem("Slime"); // Carrega a imagem correta do Slime
                break;
            case "Goblin":
                this.vida = random.nextInt(10) + 25; // Vida entre 25 e 35
                this.forca = random.nextInt(7) + 8; // Força entre 8 e 15
                this.xp = random.nextInt(10) + 25; // XP entre 25 e 35
                this.ouro = random.nextInt(10) + 15; // Ouro entre 15 e 25
                carregarImagem("Goblin"); // Carrega a imagem correta do Goblin
                break;
            case "FadaMacabra":
                this.vida = random.nextInt(15) + 35; // Vida entre 35 e 50
                this.forca = random.nextInt(10) + 10; // Força entre 10 e 20
                this.xp = random.nextInt(15) + 30; // XP entre 30 e 45
                this.ouro = random.nextInt(10) + 20; // Ouro entre 20 e 30
                carregarImagem("FadaMacabra"); // Carrega a imagem correta da FadaMacabra
                break;
        }
        if (boss.equals("MoonDust")) {
            this.bossVida = 100; // vida 100
            this.bossForca = random.nextInt(15) + 20; // Força entre 20 e 35
            this.bossExp = 100; // xp 100
            this.bossOuro = 999; //Ouro 999 (Inutil pq quando vc mata o boss vc tem que sair do jogo kkkk).
            carregarImagem("MoonDust");
        }
    }



    // Getters
    public int getOuro(){
        return this.ouro;
    }

    public int getBossOuro(){
        return this.bossOuro;
    }

    public String getTipo() {
        return tipo;
    }

    public String getBoss(){
        return boss;
    }

    public int getVida() {
        return vida;
    }

    public int getBossVida() {
        return bossVida;
    }

    public int getForca() {
        return forca;
    }

    public int getBossForca() {
        return bossForca;
    }

    public int getXp() {
        return xp;
    }

    public int getBossExp() {
        return bossExp;
    }

    public BufferedImage getImagemMonstro() {
        return imagemMonstro;
    }

    // Método para carregar a imagem do monstro
    private void carregarImagem(String nomeDoMonstro) {
        try { // Tenta carregar a imagem do monstro
            imagemMonstro = ImageIO.read(new File("imagens/monstros/" + nomeDoMonstro + ".png"));
        } catch (IOException e) { // Caso não consiga ele ira dar esse erro:
            System.err.println("Erro ao carregar imagem do monstro " + nomeDoMonstro + " na classe Monstros!" + e.getMessage());
        }
    }

    // Método para exibir informações do inimigo
    public void exibirInformacoes() {
        System.out.println("Tipo: " + tipo);
        System.out.println("Vida: " + vida);
        System.out.println("Força: " + forca);
        System.out.println("XP: " + xp);
    }

    public void perderVida(int forca) {
        vida -= forca;
    }
    public void perderBossVida(int forca) {bossVida -= forca;}
}
