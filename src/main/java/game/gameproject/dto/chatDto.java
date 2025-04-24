package game.gameproject.dto;

public class chatDto {

	private static boolean chatAtivo = false;

	public static void setChatAtivo(boolean chatAtivo) {
	    chatDto.chatAtivo = chatAtivo;
	}

	public boolean isChatAtivo() {
	    return chatAtivo;
	}
	
}
