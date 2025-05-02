package game.gameproject.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.StatusService;

public class CommandsChatSupport {

    private infoPlayerDto playerInfo;
    public StatusService playerService = new StatusService();

    public CommandsChatSupport(infoPlayerDto playerInfo) {
        this.playerInfo = playerInfo;
    }
    
    public enum Atributo {
        CON, DEX, STR, SPI, WIL
    }

    private void atualizarStatus() {
        playerService.atualizarStatusBanco(
            playerInfo.getIdPlayer(),
            playerInfo.getNivel(),
            playerInfo.getPontos(),
            playerInfo.getVidaMaxima(),
            playerInfo.getStaminaMaxima(),
            playerInfo.getForca(),
            playerInfo.getManaMaxima(),
            playerInfo.getForcaMana(),
            playerInfo.getDinheiro(),
            playerInfo.getClasse(),
            playerInfo.getXpAtual(),
            playerInfo.getXpMaxima()
        );
    }

    public void buscarComando(String comando, infoPlayerDto playerInfo) {
        if (playerInfo == null) {
            System.out.println("Erro: playerInfo não foi inicializado.");
            return;
        }

        Pattern pattern = Pattern.compile("//atributo (set|add|remove) (con|dex|str|spi|wil) (\\d+)");
        Matcher matcher = pattern.matcher(comando.toLowerCase());

        if (matcher.matches()) {
            String acao = matcher.group(1);
            String atributo = matcher.group(2);
            int valor = Integer.parseInt(matcher.group(3));

            switch (atributo) {
                case "con" -> atualizarVida(playerInfo, valor, acao);
                case "dex" -> atualizarStamina(playerInfo, valor, acao);
                case "str" -> atualizarForca(playerInfo, valor, acao);
                case "spi" -> atualizarMana(playerInfo, valor, acao);
                case "wil" -> atualizarForcaMana(playerInfo, valor, acao);
            }

            atualizarStatus();
        }
    }

    private void atualizarVida(infoPlayerDto player, int valor, String acao) {
        int novaVida = calcularNovoValor(player.getVidaMaxima(), valor, acao);
        player.setVidaMaxima(novaVida);
        player.setVidaAtual(novaVida);
    }

    private void atualizarStamina(infoPlayerDto player, int valor, String acao) {
        int novaStamina = calcularNovoValor(player.getStaminaMaxima(), valor, acao);
        player.setStaminaMaxima(novaStamina);
        player.setStaminaAtual(novaStamina);
    }

    private void atualizarForca(infoPlayerDto player, int valor, String acao) {
        player.setForca(calcularNovoValor(player.getForca(), valor, acao));
    }

    private void atualizarMana(infoPlayerDto player, int valor, String acao) {
        int novaMana = calcularNovoValor(player.getManaMaxima(), valor, acao);
        player.setManaMaxima(novaMana);
        player.setManaAtual(novaMana);
    }

    private void atualizarForcaMana(infoPlayerDto player, int valor, String acao) {
        player.setForcaMana(calcularNovoValor(player.getForcaMana(), valor, acao));
    }

    private int calcularNovoValor(int atual, int valor, String acao) {
        return switch (acao) {
            case "set" -> Math.max(1, valor);
            case "add" -> atual + valor;
            case "remove" -> Math.max(1, atual - valor);
            default -> atual;
        };
    }
}
