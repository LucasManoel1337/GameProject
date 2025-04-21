package game.gameproject.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class AtualizarGameService {

    // Retorna o diretório da aplicação (pasta onde o JAR está sendo executado)
    public static String getAppDirectory() {
        try {
            File jar = new File(AtualizarGameService.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI());
            return jar.getParentFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Não foi possível determinar o diretório da aplicação.", e);
        }
    }

    // Detecta o sistema operacional
    public static String getOS() {
        return System.getProperty("os.name").toLowerCase();
    }

    // Método que abre o terminal e executa 'git pull' na pasta do jogo
    public static void atualizarJogoViaGit() {
        String os = getOS();
        String diretorio = getAppDirectory();

        try {
            if (os.contains("win")) {
                // Windows: abre cmd e executa git pull
                String comando = "cmd /c start cmd.exe /K \"cd /d " + diretorio + " && git pull\"";
                Runtime.getRuntime().exec(comando);
            } else if (os.contains("mac")) {
                // macOS: abre Terminal.app com AppleScript
                String[] comando = {
                    "osascript", "-e",
                    "tell application \"Terminal\" to do script \"cd '" + diretorio + "' && git pull\""
                };
                Runtime.getRuntime().exec(comando);
            } else if (os.contains("nix") || os.contains("nux")) {
                // Linux: abre terminal padrão e executa git pull
                String[] comando = {
                    "/bin/bash", "-c",
                    "gnome-terminal -- bash -c 'cd \"" + diretorio + "\" && git pull; exec bash'"
                };
                Runtime.getRuntime().exec(comando);
            } else {
                System.err.println("Sistema operacional não suportado para atualização.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao tentar executar git pull.");
        }
    }
}
