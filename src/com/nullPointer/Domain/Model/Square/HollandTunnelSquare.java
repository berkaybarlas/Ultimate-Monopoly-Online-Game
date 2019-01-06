package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.DomainBoard;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class HollandTunnelSquare extends Square {

	public HollandTunnelSquare(String n, String t) {
		super(n, t);

	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		int HT1 = 14;
		int HT2 = 114;
		PlayerController playerController = gameEngine.getPlayerController();
		Player currentPlayer =playerController.getCurrentPlayer();
		DomainBoard domainBoard = gameEngine.getDomainBoard();
		if (currentPlayer.getTargetPosition() == HT1)
		{
			playerController.movePlayer(HT2);
			gameEngine.publishEvent("teleport" + HT2);
		}
		else if (currentPlayer.getTargetPosition() == HT2)
		{
			playerController.movePlayer(HT1);
			gameEngine.publishEvent("teleport" + HT1);
		}
		else
		{
			throw new UnsupportedOperationException("Not in Holland Tunnel! " + currentPlayer.getPosition());
		}
		gameEngine.publishEvent("empty");
	}

}
