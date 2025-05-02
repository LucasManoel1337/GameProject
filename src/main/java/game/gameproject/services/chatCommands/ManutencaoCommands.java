package game.gameproject.services.chatCommands;

import game.gameproject.dto.infoPlayerDto;
import game.gameproject.services.StatusService;

public class ManutencaoCommands {

	 private infoPlayerDto playerInfo;
	 public StatusService playerService = new StatusService();

	 public ManutencaoCommands(infoPlayerDto playerInfo) {
		 this.playerInfo = playerInfo;
	 }
	
}
