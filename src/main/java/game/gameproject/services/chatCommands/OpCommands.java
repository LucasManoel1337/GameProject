package game.gameproject.services.chatCommands;

import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.StatusService;

public class OpCommands {

	private infoPlayerDto playerInfo;
	 public StatusService playerService = new StatusService();

	 public OpCommands(infoPlayerDto playerInfo) {
		 this.playerInfo = playerInfo;
	 }
	
}
