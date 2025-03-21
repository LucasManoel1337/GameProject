package game.gameproject.cache;

import game.gameproject.dto.PlayerCoordenadasDto;
import java.util.ArrayList;
import java.util.List;

public class JogadoresOnlineCache {
    private static List<PlayerCoordenadasDto> jogadoresOnline = new ArrayList<>();

    public static void atualizarLista(List<PlayerCoordenadasDto> novaLista) {
        jogadoresOnline = novaLista;
    }

    public static List<PlayerCoordenadasDto> getJogadoresOnline() {
        return jogadoresOnline;
    }
}
