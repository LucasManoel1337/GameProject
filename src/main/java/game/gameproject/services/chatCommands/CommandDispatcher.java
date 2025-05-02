package game.gameproject.services.chatCommands;

import game.gameproject.dto.infoPlayerDto;

public class CommandDispatcher {

    public void processarComando(String comando, infoPlayerDto playerInfo) {
        if (comando.startsWith("//atributo")) {
            new AtributoCommands(playerInfo).buscarComando(comando);
        } else {
            System.out.println("Comando desconhecido: " + comando);
        }
    }
}