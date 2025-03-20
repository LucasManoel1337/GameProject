package game.gameproject.front.game;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {
    private Clip trilhaSonora; // Armazena o objeto Clip para reproduzir o som

    // metodo para carregar som
    public void carregarSom(String caminhoArquivoSom) {
        try {
            // Carregar o arquivo de som e convertor
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(caminhoArquivoSom));
            AudioFormat af = ais.getFormat();

            // Criar um Clip para reproduzir o som
            trilhaSonora = AudioSystem.getClip();
            trilhaSonora.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // metodo para tocar a musica
    public void tocarSom() {  
        if (trilhaSonora != null) { // Caso nao estiver vazio
            trilhaSonora.start(); // Começar a tocar a musica
        }
    }

    public void pararSom() {
        if (trilhaSonora != null) { // Caso nao estiver vazio
            trilhaSonora.stop();// Fechar o objeto Clip
        }
    }
    
    // Foi tentado colocar o metodo de controlar o volume porem não deu certo, mas esta aqui o 'teste'
    public void setVolume(float volume) {
        if (trilhaSonora != null) { // Caso nao estiver vazio
            FloatControl volumeControl = (FloatControl) trilhaSonora.getControl(FloatControl.Type.VOLUME);
            volumeControl.setValue(volume);
        }
    }
    public void tocarSomEmLoop(String caminhoArquivoSom) {
        trilhaSonora.loop(Clip.LOOP_CONTINUOUSLY); // Seta para a musica tocar em loop
        
        trilhaSonora.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                trilhaSonora.setMicrosecondPosition(0); // Reseta a posição da musica
            }
        });
        trilhaSonora.start(); // começa a tocar a musica
    }

}

