package game.gameproject.dto;

public class infoPlayerDto {

    public infoPlayerDto(String playerNickname) {
    }
    private static int idPlayer = 0;
    private static boolean isOp = false;
    private static String NickPlayer = "";
    private static String classe = "";
    private static int nivel = -1;
    private static int pontos = -1;
    
    private static int xpMaxima = -1;
    private static int xpAtual = -1;
    
    private static int vidaMaxima = -1;
    private static int vidaAtual = -1;
    
    private static int staminaMaxima = -1;
    private static int staminaAtual = -1;
    
    private static int forca = -1;
    
    private static int manaMaxima = -1;
    private static int manaAtual = -1;
    
    private static int forcaMana = -1;
    
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

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vida) {
        infoPlayerDto.vidaMaxima = vida;
    }

    public int getStaminaMaxima() {
        return staminaMaxima;
    }

    public void setStaminaMaxima(int stamina) {
        infoPlayerDto.staminaMaxima = stamina;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        infoPlayerDto.forca = forca;
    }
    
    public int getManaMaxima() {
        return manaMaxima;
    }

    public void setManaMaxima(int mana) {
        infoPlayerDto.manaMaxima = mana;
    }
    
    public int getForcaMana() {
        return forcaMana;
    }

    public void setForcaMana(int forcaMana) {
        infoPlayerDto.forcaMana = forcaMana;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(int dinheiro) {
        infoPlayerDto.dinheiro = dinheiro;
    }
    
    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        infoPlayerDto.classe = classe;
    }
    
    
    public int getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(int vidaAtual) {
        infoPlayerDto.vidaAtual = vidaAtual;
    }
    
    public int getManaAtual() {
        return manaAtual;
    }

    public void setManaAtual(int manaAtual) {
        infoPlayerDto.manaAtual = manaAtual;
    }
    
    public int getStaminaAtual() {
        return staminaAtual;
    }

    public void setStaminaAtual(int staminaAtual) {
        infoPlayerDto.staminaAtual = staminaAtual;
    }
    
    public int getXpAtual() {
        return xpAtual;
    }

    public void setXpAtual(int xpAtual) {
        infoPlayerDto.xpAtual = xpAtual;
    }
    
    public int getXpMaxima() {
        return xpMaxima;
    }

    public void setXpMaxima(int xpMaxima) {
        infoPlayerDto.xpMaxima = xpMaxima;
    }
    
    public boolean isOp() {
        return isOp;
    }

    public void setOp(boolean isOp) {
        this.isOp = isOp;
    }
}
